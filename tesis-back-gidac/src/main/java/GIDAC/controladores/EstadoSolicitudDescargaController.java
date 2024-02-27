package GIDAC.controladores;

import GIDAC.modelo.EstadoSolicitudDescarga;
import GIDAC.servicios.EstadoSolicitudDescargaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estado-solicitud-descarga")
@CrossOrigin("*")
public class EstadoSolicitudDescargaController {

    
    @Autowired
    private EstadoSolicitudDescargaService service;

    @PostMapping("/guardar-estado-solicitud-descarga")
    public Object guardar(@RequestBody EstadoSolicitudDescarga oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener-estado-solicitud-descarga/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-estado-solicitud-descarga")
    public List<EstadoSolicitudDescarga> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar-estado-solicitud-descarga/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        service.eliminar(id);
    }
    
    
}
