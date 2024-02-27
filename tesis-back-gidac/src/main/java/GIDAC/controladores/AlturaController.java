package GIDAC.controladores;


import GIDAC.modelo.Altura;
import GIDAC.servicios.AlturaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;

@RestController
@RequestMapping("/altura")
@CrossOrigin("*")
public class AlturaController {

    
    @Autowired
    private AlturaService service;

    @PostMapping("/guardar-altura")
    public Object guardar(@RequestBody Altura oC)
    {
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oV.fechaActual());
        return service.guardar(oC);    
    }
    
     @PutMapping("/actualizar")
    public Object actualizar(@RequestBody Altura oC)
    {
        Altura oD=(Altura) service.buscarPorId(oC.getIdAltura());
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oD.getFechaCreacion());
        oC.setFechaActualizacion(oV.fechaActual());
        return service.guardar(oC);    
    }
    
    @GetMapping("/actualizar-editable")
    public void actualizarEditable()
    {
        List<Altura> listaCompleta=service.buscarTodos();
        List<Object[]> datos=service.obtenerAlturasUsadas();
        for (Altura lista : listaCompleta) {
            boolean aux=true;
            for (Object[] dato : datos) {
                
                if(lista.getIdAltura()==(Integer)dato[0]){
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
    
    @GetMapping("/obtener-altura/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-altura")
    public List<Altura> listar()
    {
        List<Altura> oC= service.buscarPorVigencia(true);
        oC.sort(Comparator.comparing(Altura::getAltura));
        return oC;
    }
    
    @GetMapping("/listar-altura-eliminadas")
    public List<Altura> listarEliminados()
    {
        List<Altura> oC= service.buscarPorVigencia(false);
        oC.sort(Comparator.comparing(Altura::getAltura));
        return oC;
    }
    
    @DeleteMapping("/eliminar-altura/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        Altura oC= (Altura) service.buscarPorId(id);
        oC.setVigencia(false);
        cValidaciones oV=new cValidaciones();
        oC.setFechaActualizacion(oV.fechaActual());
        service.guardar(oC); 
    }
    
    @DeleteMapping("/restarurar-altura/{id}")
    public void restaurar(@PathVariable Integer id)
    {
        Altura oC= (Altura) service.buscarPorId(id);
        oC.setVigencia(true);
        cValidaciones oV=new cValidaciones();
        oC.setFechaActualizacion(oV.fechaActual());
        service.guardar(oC); 
    }
    
    
}
