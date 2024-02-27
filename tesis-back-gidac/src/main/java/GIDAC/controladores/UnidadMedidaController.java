package GIDAC.controladores;

import GIDAC.modelo.UnidadMedida;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import GIDAC.servicios.UnidadMedidaService;
import java.util.Comparator;

@RestController
@RequestMapping("/medida")
@CrossOrigin("*")
public class UnidadMedidaController {

    
    @Autowired
    private UnidadMedidaService service;

    @PostMapping("/guardar-medida")
    public Object guardar(@RequestBody UnidadMedida oC)
    {
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oV.fechaActual());
        System.out.println("....................................."+oC.getIdUnidadMedida());
        return service.guardar(oC);    
    }
    
    @PutMapping("/actualizar-medida")
    public Object actualizar(@RequestBody UnidadMedida oC)
    {
        UnidadMedida oD=(UnidadMedida) service.buscarPorId(oC.getIdUnidadMedida());
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oD.getFechaCreacion());
        oC.setFechaActualizacion(oV.fechaActual());
        return service.guardar(oC);    
    }
    
    @GetMapping("/actualizar-editable")
    public void actualizarEditable()
    {
        List<UnidadMedida> listaCompleta=service.buscarTodos();
        List<Object[]> datos=service.obtenerUnidadesMedidaUsadas();
        for (UnidadMedida lista : listaCompleta) {
            boolean aux=true;
            for (Object[] dato : datos) {
                
                if(lista.getIdUnidadMedida()==(Integer)dato[0]){
                    lista.setEditable(false);
                    service.guardar(lista);    
                    
                    aux=false;  
                    break;
                }
            }
            if(aux==true){
                if(lista.isEditable()==false){
                    lista.setEditable(true);
                    service.guardar(lista);   
                }
            }
        }
    }
    
    @GetMapping("/obtener-medida/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-medida")
    public List<UnidadMedida> listar()
    {
        List<UnidadMedida> oC= service.buscarPorVigencia(true);
        oC.sort(Comparator.comparing(UnidadMedida::getUnidadMedida));
        return oC;
    }
    
    @GetMapping("/listar-medida-eliminado")
    public List<UnidadMedida> listarEliminado()
    {
        List<UnidadMedida> oC= service.buscarPorVigencia(false);
        oC.sort(Comparator.comparing(UnidadMedida::getUnidadMedida));
        return oC;
    }
    
    @DeleteMapping("/eliminar-medida/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        UnidadMedida oC= (UnidadMedida) service.buscarPorId(id);
        oC.setVigencia(false);
        cValidaciones oV=new cValidaciones();
        oC.setFechaActualizacion(oV.fechaActual());
        service.guardar(oC);  
    }
    
    @DeleteMapping("/restaurar-medida/{id}")
    public void restairar(@PathVariable Integer id)
    {
        UnidadMedida oC= (UnidadMedida) service.buscarPorId(id);
        oC.setVigencia(true);
        cValidaciones oV=new cValidaciones();
        oC.setFechaActualizacion(oV.fechaActual());
        service.guardar(oC);  
    }
    
    
}
