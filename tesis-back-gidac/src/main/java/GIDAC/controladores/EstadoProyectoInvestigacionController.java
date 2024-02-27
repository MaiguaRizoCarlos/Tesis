package GIDAC.controladores;

import GIDAC.modelo.EstadoProyectoInvestigacion;
import GIDAC.servicios.EstadoProyectoInvestigacionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estado-proyecto-investigacion")
@CrossOrigin("*")
public class EstadoProyectoInvestigacionController {

    
    @Autowired
    private EstadoProyectoInvestigacionService estadoProyectoInvestigacionService;

    
    @GetMapping("/")
    public List<EstadoProyectoInvestigacion> mostrarRoles()
    {
        return estadoProyectoInvestigacionService.findAll();
    }
    @GetMapping("/{idEstadoPoryectoInvestigacion}")
    public Object getRol(@PathVariable("idEstadoPoryectoInvestigacion") Integer idEstadoPoryectoInvestigacion)
    {
        return estadoProyectoInvestigacionService.findById(idEstadoPoryectoInvestigacion);
    }
    @PostMapping("/")
    public Object guardarRol(@RequestBody EstadoProyectoInvestigacion oC)
    {
        return estadoProyectoInvestigacionService.save(oC);    
    }
}
