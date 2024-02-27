package GIDAC.controladores;


import GIDAC.modelo.SectorImpactoProyecto;
import GIDAC.servicios.SectorImpactoProyectoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sector-impacto-proyecto")
@CrossOrigin("*")
public class SectorImpactoProyectoController {

    
    @Autowired
    private SectorImpactoProyectoService service;

    @PostMapping("/guardar")
    public Object guardar(@RequestBody SectorImpactoProyecto oC)
    {
        return service.guardar(oC);    
    }
    
    @PostMapping("/actualizar")
    public Object actualizar(@RequestBody SectorImpactoProyecto oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/listar")
    public List<SectorImpactoProyecto> listar()
    {
        return service.buscarTodos();
    }
    
    @GetMapping("/listar-por-proyecto/{id}")
    public List<SectorImpactoProyecto> listarPorProyecto(@PathVariable Integer id)
    {
        return service.buscarPorProyecto(id);
    }
    
}
