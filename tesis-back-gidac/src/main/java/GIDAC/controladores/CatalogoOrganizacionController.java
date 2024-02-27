package GIDAC.controladores;


import GIDAC.modelo.CatalogoOrganizacion;
import GIDAC.modelo.Organizacion;
import GIDAC.modelo.Variable;
import GIDAC.servicios.CatalogoOrganizacionService;
import GIDAC.servicios.OrganizacionService;
import GIDAC.servicios.TipoVariableService;
import GIDAC.servicios.UnidadMedidaService;
import GIDAC.servicios.UnidadMedidaVariableService;
import GIDAC.servicios.ValorPermitidoService;
import GIDAC.servicios.VariableFamiliaService;
import GIDAC.servicios.VariableService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.InputStream;
import java.util.List;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Comparator;

@RestController
@RequestMapping("/catalogo-organizacion")
@CrossOrigin("*")
public class CatalogoOrganizacionController {

    
    @Autowired
    private CatalogoOrganizacionService service;
    
    @Autowired
    private VariableService variableService;
    
    @Autowired
    private OrganizacionService organizacionService;
    

    @PostMapping("/guardar-catalogo-organizacion")
    public Object guardar(@RequestBody CatalogoOrganizacion oC)
    {
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oV.fechaActual());
        return service.guardar(oC);    
    }
    
    @PutMapping("/actualizar-catalogo-organizacion")
    public Object actualizar(@RequestBody CatalogoOrganizacion oC)
    {
        cValidaciones oVal=new cValidaciones();
        CatalogoOrganizacion oD=(CatalogoOrganizacion) service.buscarPorId(oC.getIdVariableOrganizacion());
        
        if(oD.isVariableSistema()==true){
            Variable oVar=oD.getVariable();
            if(!oD.getCodigoVariableOrganizacion().equals(oC.getCodigoVariableOrganizacion()) || !oD.getNombreVariableOrganizacion().equals(oC.getNombreVariableOrganizacion())){
               oVar.setCodigoVariable(oC.getCodigoVariableOrganizacion());
               oVar.setNombreVariable(oC.getNombreVariableOrganizacion());
               oVar.setFechaActualizacion(oVal.fechaActual());
               variableService.guardar(oVar);
            }
        }
        oC.setVariableSistema(oD.isVariableSistema());
        oC.setVigencia(true);
        
        oC.setFechaCreacion(oD.getFechaCreacion());
        oC.setFechaActualizacion(oVal.fechaActual());
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener-catalogo-organizacion/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        
        return service.buscarPorId(id);
    }
    
    @GetMapping("/obtener-catalogo-organizacion-codigo/{id}")
    public Object buscarPorCodigoOrganizacion(@PathVariable String id)
    {
        return service.buscarPorCodigoOrganizacion(id);
    }
    
    @GetMapping("/listar-catalogo-organizacion")
    public List<CatalogoOrganizacion> listar()
    {
        List<CatalogoOrganizacion> oC= service.buscarPorVigencia(true);
        oC.sort(Comparator.comparing(CatalogoOrganizacion::getNombreVariableOrganizacion));
        return oC;
    }
    
    @GetMapping("/listar-por-organizacion/{id}")
    public List<CatalogoOrganizacion> listarCatalogoPorOrganizacion(@PathVariable Integer id)
    {
        List<CatalogoOrganizacion> oC= service.buscarPorVigenciaAndOrganizacion(true, id, true);
        oC.sort(Comparator.comparing(CatalogoOrganizacion::getNombreVariableOrganizacion));
        return oC;
    }
    
    @GetMapping("/listar-por-organizacion-eliminado/{id}")
    public List<CatalogoOrganizacion> listarCatalogoPorOrganizacionEliminado(@PathVariable Integer id)
    {
        List<CatalogoOrganizacion> oC= service.buscarPorVigenciaAndOrganizacion(false, id, true);
        oC.sort(Comparator.comparing(CatalogoOrganizacion::getNombreVariableOrganizacion));
        return oC;
    }
    
    @GetMapping("/listar-por-variable-sistema-vigente/{id}")
    public List<CatalogoOrganizacion> listarPorVariableSistemaVigente(@PathVariable Integer id)
    {
        List<CatalogoOrganizacion> oC= service.findByVigenciaAndVariableIdVariableOrganizacionVigencia(true, id, true);
        oC.sort(Comparator.comparing(CatalogoOrganizacion::getNombreVariableOrganizacion));
        return oC;
    }
    
    @GetMapping("/listar-por-variable-sistema-eliminado/{id}")
    public List<CatalogoOrganizacion> listarPorVariableSistemaEliminado(@PathVariable Integer id)
    {
        List<CatalogoOrganizacion> oC= service.findByVigenciaAndVariableIdVariableOrganizacionVigencia(false, id, false);
        oC.sort(Comparator.comparing(CatalogoOrganizacion::getNombreVariableOrganizacion));
        return oC;
    }
    
    @DeleteMapping("/eliminar-catalogo-organizacion/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        CatalogoOrganizacion oC=(CatalogoOrganizacion) service.buscarPorId(id);
        cValidaciones oV=new cValidaciones();
        if(oC.isVariableSistema()==true){
            Variable oVar=oC.getVariable();
            variableService.eliminar(oC.getVariable().getIdVariable());
        }else{
            oC.setFechaActualizacion(oV.fechaActual());
            oC.setVigencia(false);
            service.guardar(oC); 
        }
    }
    
    @DeleteMapping("/reestablecer/{id}")
    public void reestablecer(@PathVariable Integer id)
    {
        CatalogoOrganizacion oC=(CatalogoOrganizacion) service.buscarPorId(id);
        cValidaciones oV=new cValidaciones();
        if(oC.isVariableSistema()==true){
            Variable oVar=oC.getVariable();
            variableService.activar(oC.getVariable().getIdVariable());
        }else{
            oC.setFechaActualizacion(oV.fechaActual());
            oC.setVigencia(true);
            service.guardar(oC); 
        }
    }
    
    @PostMapping("/comprobar-archivo")
    public boolean comprobarDataset(@RequestParam("file") MultipartFile file) throws JsonProcessingException{
        
        int fila=0;
        int cont=1;
        boolean estado=true;
       
        try{
            InputStream inputStream = file.getInputStream();
            Workbook archivoExcel= Workbook.getWorkbook(inputStream);
            Sheet hoja=archivoExcel.getSheet(0);
            String dato;
            for(int colm=0;colm<4;colm++){
                dato=hoja.getCell(colm,fila).getContents();
                System.out.println(dato);
                switch(cont){
                    case 1:
                        if(dato.equals("Codigo")){estado=true;}
                        else{ estado=false; break;}
                        cont=2;
                        break;
                    case 2:
                        if(dato.equals("Nombre")){estado=true;}
                        else{ estado=false; break;}
                        cont=3;
                        break;
                    case 3:
                        if(dato.equals("Descripcion")){estado=true;}
                        else{ estado=false; break;}
                        cont=4;
                        break;
                    case 4:
                        if(dato.equals("Estandar")){estado=true;}
                        else{ estado=false; break;}
                        cont=1;
                        break;
                }
                if(estado==false) {
                    System.out.println("llega al false-----------------------------------------------");
                    break;
                }
            }
           archivoExcel.close();
            inputStream.close();
                        
            return estado;
        }catch(Exception e){
            System.out.println("Error "+e);
            return false;
        }
    }
    
    @PostMapping("/importar-archivo")
    public void importarDataset(@RequestParam("file") MultipartFile file) throws JsonProcessingException{
        
        int cont=1;
        boolean estado=true;
        Integer id=0;
        String codigo="";
        String nombre="";
        String descripcion="";
        String siglas="";
        
        List<Organizacion> organizaciones=organizacionService.buscarPorVigencia(true);
       
        try{
            InputStream inputStream = file.getInputStream();
            Workbook archivoExcel= Workbook.getWorkbook(inputStream);
            Sheet hoja=archivoExcel.getSheet(0);
            int numFilas=hoja.getRows();
                
            String dato;
            for(int fila=1;fila<numFilas;fila++){
                CatalogoOrganizacion oC=new CatalogoOrganizacion();
            for(int colm=0;colm<4;colm++){
                dato=hoja.getCell(colm,fila).getContents();
                System.out.println(dato);
                switch(cont){
                    case 1:
                        if(dato.equals("")){codigo="NA";}
                        else{ codigo=dato;}
                        cont=2;
                        break;
                    case 2:
                        if(dato.equals("")){nombre="NA";}
                        else{ nombre=dato;}
                        cont=3;
                        break;
                    case 3:
                        if(dato.equals("")){descripcion="NA";}
                        else{ descripcion=dato;}
                        cont=4;
                        break;
                    case 4:
                        if(dato.equals("")){siglas="NA";}
                        else{ siglas=dato;}
                        String datoMayuscula=siglas.toLowerCase();
                        if(codigo.equals("NA") || nombre.equals("NA") || siglas.equals("NA")){
                            System.out.println("Dato sin estructura");
                        }else{
                        for (Organizacion organizacion : organizaciones) {
                            String siglasElemento = organizacion.getSiglas().toLowerCase();
                            if (siglasElemento.equals(datoMayuscula)) {
                                Organizacion oOrganizacion=new Organizacion();
                                oOrganizacion.setIdOrganizacion(organizacion.getIdOrganizacion());
                                oC.setOrganizacion(oOrganizacion);
                                System.out.println("Organizacion encontrada");
                                break;
                            }
                        }
                        
                        if(service.buscarPorCodigoOrganizacion(codigo)==null){
                            System.out.println("Variable guardada");
                            oC.setCodigoVariableOrganizacion(codigo);
                            oC.setDescripcion(descripcion);
                            oC.setNombreVariableOrganizacion(nombre);
                            service.guardar(oC);
                        }
                        }
                        
                        cont=1;
                        break;
                }
                }
            }
           archivoExcel.close();
            inputStream.close();
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    }
    
}
