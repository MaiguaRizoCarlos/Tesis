package GIDAC.controladores;


import GIDAC.modelo.TipoVariable;
import GIDAC.servicios.TipoVariableService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipo-variable")
@CrossOrigin("*")
public class TipoVariableController {

    
    @Autowired
    private TipoVariableService service;

    @PostMapping("/guardar-tipo-variable")
    public Object guardar(@RequestBody TipoVariable oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener-tipo-variable/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-tipo-variable")
    public List<TipoVariable> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar-tipo-variable/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        
        service.eliminar(id);
    }
    
    
}
