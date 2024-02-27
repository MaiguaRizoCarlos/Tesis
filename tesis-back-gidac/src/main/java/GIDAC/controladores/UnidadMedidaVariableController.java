package GIDAC.controladores;


import GIDAC.modelo.VariableUnidadMedida;
import GIDAC.servicios.UnidadMedidaVariableService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unidad-medida-variable")
@CrossOrigin("*")
public class UnidadMedidaVariableController {

    
    @Autowired
    private UnidadMedidaVariableService service;
    

    @PostMapping("/guardar-equivalencia-variable")
    public Object guardar(@RequestBody VariableUnidadMedida oC) throws JsonProcessingException{
        return service.guardar(oC);    
    }
    
    @PutMapping("/actualizar")
    public Object actualizar(@RequestBody VariableUnidadMedida oC) throws JsonProcessingException{
        cValidaciones oVx=new cValidaciones();
        if(service.buscarPorUnidadMedidaAndVariable(oC.getUnidadMedida().getIdUnidadMedida(), oC.getVariable().getIdVariable())==null){
            oC.setFechaCreacion(oVx.fechaActual());
        }else{
            List<VariableUnidadMedida> oCAux=service.buscarPorUnidadMedidaAndVariable(oC.getUnidadMedida().getIdUnidadMedida(), oC.getVariable().getIdVariable());
            oC=oCAux.get(0);
            oC.setFechaActualizacion(oVx.fechaActual());
        }
        oC.setVigencia(true);
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener/{id}")
    public Object obtenerVigente(@PathVariable Integer id)
    {    
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar")
    public List<VariableUnidadMedida> listarVigentes1()
    {    
        return service.buscarTodos(true);
    }
    
    @GetMapping("/listar-por-variable/{id}")
    public List<VariableUnidadMedida> findByVariableIdVariable(@PathVariable Integer id)
    {    
        return service.findByVariableIdVariable( id);
    }
    
    @GetMapping("/listar-por-variable-vigente/{id}")
    public List<VariableUnidadMedida> findByVigenciaAndVariableIdVariableAndVariableVigenciaAndUnidadMedidaVigencia(@PathVariable Integer id)
    {    
        return service.findByVigenciaAndVariableIdVariableAndVariableVigenciaAndUnidadMedidaVigencia(true, id, true, true);
    }
    
    @GetMapping("/listar-por-variable-no-vigente/{id}")
    public List<VariableUnidadMedida> findByVigenciaAndVariableIdVariableAndVariableVigenciaAndUnidadMedidaVigenciaEliminada(@PathVariable Integer id)
    {    
        return service.findByVigenciaAndVariableIdVariableAndVariableVigenciaAndUnidadMedidaVigencia(false, id, true, true);
    }
    
    @GetMapping("/listar-equivalencia-variable-vigentes")
    public List<VariableUnidadMedida> listarVigentes()
    {    
        return service.buscarTodos(true);
    }
    
    @GetMapping("/listar-equivalencia-variable-eliminados")
    public List<VariableUnidadMedida> listarEliminados()
    {    
        return service.buscarTodos(false);
    }
    
    @GetMapping("/listar-equivalencia-variable-vigente-variable-vigente")
    public List<VariableUnidadMedida> listarVigenteVariableVigente()
    {    
        List<VariableUnidadMedida> lista=service.buscarVigenciaVariableVigencia(true, true);
        List<VariableUnidadMedida> listaOrdenada = lista.stream()
                .sorted(Comparator.comparing(vum -> vum.getVariable().getNombreVariable()))
                .collect(Collectors.toList());

        return listaOrdenada;
    }
    
    @GetMapping("/listar-equivalencia-variable-no-vigente-variable-no-vigente")
    public List<VariableUnidadMedida> listarNoVigenteVariableNoVigente()
    {    
        
        return service.buscarVigenciaVariableVigencia(false, false);
    }
    
    
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id)
    {   
        cValidaciones oVx=new cValidaciones();
        VariableUnidadMedida oC=(VariableUnidadMedida) service.buscarPorId(id);
        oC.setVigencia(false);
        oC.setFechaActualizacion(oVx.fechaActual());
        service.guardar(oC);
    }
    
    @DeleteMapping("/restaurar/{id}")
    public void restaurar(@PathVariable Integer id)
    {   
        cValidaciones oVx=new cValidaciones();
        VariableUnidadMedida oC=(VariableUnidadMedida) service.buscarPorId(id);
        oC.setVigencia(true);
        oC.setFechaActualizacion(oVx.fechaActual());
        service.guardar(oC);
    }
    
    
    
    
    
}
