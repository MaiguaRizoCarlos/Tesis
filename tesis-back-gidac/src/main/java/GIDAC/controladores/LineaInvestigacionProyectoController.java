package GIDAC.controladores;


import GIDAC.modelo.LineaInvestigacionProyecto;
import GIDAC.servicios.LineaInvestigacionProyectoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/linea-investigacion-proyecto")
@CrossOrigin("*")
public class LineaInvestigacionProyectoController {

    
    @Autowired
    private LineaInvestigacionProyectoService service;

    @PostMapping("/guardar")
    public Object guardar(@RequestBody LineaInvestigacionProyecto oC)
    {
        return service.guardar(oC);    
    }
    
    @PostMapping("/actualizar")
    public Object actualizar(@RequestBody LineaInvestigacionProyecto oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/listar")
    public List<LineaInvestigacionProyecto> listar()
    {
        return service.buscarTodos();
    }
    
     @GetMapping("/listar-por-proyecto/{id}")
    public List<LineaInvestigacionProyecto> listarPorProyecto(@PathVariable Integer id)
    {
        return service.buscarPorProyecto(id);
    }
    
}
