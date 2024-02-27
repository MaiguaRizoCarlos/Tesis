package GIDAC.controladores;


import GIDAC.modelo.Area;
import GIDAC.servicios.AreaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;

@RestController
@RequestMapping("/area")
@CrossOrigin("*")
public class AreaController {

    
    @Autowired
    private AreaService service;

    @PostMapping("/guardar")
    public Object guardar(@RequestBody Area oC)
    {
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oV.fechaActual());
        return service.guardar(oC);    
    }
    
    @PutMapping("/actualizar")
    public Object actualizar(@RequestBody Area oC)
    {
        Area oD=(Area) service.buscarPorId(oC.getIdArea());
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oD.getFechaCreacion());
        oC.setFechaActualizacion(oV.fechaActual());
        return service.guardar(oC);    
    }
    
    @GetMapping("/actualizar-editable")
    public void actualizarEditable()
    {
        List<Area> listaCompleta=service.buscarTodos();
        List<Object[]> datos=service.obtenerAreasUsadas();
        for (Area lista : listaCompleta) {
            boolean aux=true;
            for (Object[] dato : datos) {
                
                if(lista.getIdArea()==(Integer)dato[0]){
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
    
    @GetMapping("/obtener/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar")
    public List<Area> listar()
    {
        List<Area> oC= service.buscarPorVigencia(true);
        oC.sort(Comparator.comparing(Area::getArea));
        return oC;
    }
    
    @GetMapping("/listar-eliminada")
    public List<Area> listarEliminada()
    {
        List<Area> oC= service.buscarPorVigencia(false);
        oC.sort(Comparator.comparing(Area::getArea));
        return oC;
    }
    
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        Area oC= (Area) service.buscarPorId(id);
        oC.setVigencia(false);
        cValidaciones oV=new cValidaciones();
        oC.setFechaActualizacion(oV.fechaActual());
        service.guardar(oC); 
    }
    
    @DeleteMapping("/restaurar/{id}")
    public void restaurar(@PathVariable Integer id)
    {
        Area oC= (Area) service.buscarPorId(id);
        oC.setVigencia(true);
        cValidaciones oV=new cValidaciones();
        oC.setFechaActualizacion(oV.fechaActual());
        service.guardar(oC); 
    }
}
