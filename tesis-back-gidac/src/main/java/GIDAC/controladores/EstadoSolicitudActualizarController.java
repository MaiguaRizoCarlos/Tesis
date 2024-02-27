package GIDAC.controladores;


import GIDAC.modelo.EstadoSolicitudActualizar;
import GIDAC.servicios.EstadoSolicitudActualizarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estado-solicitud-actualizar")
@CrossOrigin("*")
public class EstadoSolicitudActualizarController {

    
    @Autowired
    private EstadoSolicitudActualizarService service;

    @PostMapping("/guardar-estado-solicitud-actualizar")
    public Object guardar(@RequestBody EstadoSolicitudActualizar oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener-estado-solicitud-actualizar/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-estado-solicitud-actualizar")
    public List<EstadoSolicitudActualizar> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar-estado-solicitud-actualizar/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        service.eliminar(id);
    }
    
    
}
