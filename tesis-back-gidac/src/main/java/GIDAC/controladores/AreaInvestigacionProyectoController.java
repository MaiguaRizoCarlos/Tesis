package GIDAC.controladores;


import GIDAC.modelo.AreaInvestigacionProyecto;
import GIDAC.servicios.AreaInvestigacionProyectoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/area-investigacion-proyecto")
@CrossOrigin("*")
public class AreaInvestigacionProyectoController {

    
    @Autowired
    private AreaInvestigacionProyectoService service;

    @PostMapping("/guardar")
    public Object guardar(@RequestBody AreaInvestigacionProyecto oC)
    {
        return service.guardar(oC);    
    }
    
    @PostMapping("/actualizar")
    public Object actualizar(@RequestBody AreaInvestigacionProyecto oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/listar")
    public List<AreaInvestigacionProyecto> listar()
    {
        return service.buscarTodos();
    }
   
    @GetMapping("/listar-por-proyecto/{id}")
    public List<AreaInvestigacionProyecto> listarPorProyecto(@PathVariable Integer id)
    {
        return service.buscarPorProyecto(id);
    }
    
}
