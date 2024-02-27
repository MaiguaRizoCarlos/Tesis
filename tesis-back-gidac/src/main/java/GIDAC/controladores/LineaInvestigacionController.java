package GIDAC.controladores;


import GIDAC.modelo.LineaInvestigacion;
import GIDAC.servicios.LineaInvestigacionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/linea-investigacion")
@CrossOrigin("*")
public class LineaInvestigacionController {

    
    @Autowired
    private LineaInvestigacionService service;

    @PostMapping("/guardar")
    public Object guardar(@RequestBody LineaInvestigacion oC)
    {
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oV.fechaActual());
        return service.guardar(oC);    
    }
    
    @PutMapping("/actualizar")
    public Object actualizar(@RequestBody LineaInvestigacion oC)
    {
        LineaInvestigacion oD=(LineaInvestigacion) service.buscarPorId(oC.getIdLineaInvestigacion());
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oD.getFechaCreacion());
        oC.setFechaActualizacion(oV.fechaActual());
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-vigentes")
    public List<LineaInvestigacion> listarVigentes()
    {
        return service.buscarTodos(true);
    }
    
    @GetMapping("/listar-eliminados")
    public List<LineaInvestigacion> listarEliminados()
    {
        return service.buscarTodos(false);
    }
    
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        LineaInvestigacion oC=(LineaInvestigacion) service.buscarPorId(id);
        oC.setVigencia(false);
        cValidaciones oV=new cValidaciones();
        oC.setFechaActualizacion(oV.fechaActual());
        service.guardar(oC);  
    }
    
    @GetMapping("/restablecer/{id}")
    public void restablecer(@PathVariable Integer id)
    {
        LineaInvestigacion oC=(LineaInvestigacion) service.buscarPorId(id);
        oC.setVigencia(true);
        cValidaciones oV=new cValidaciones();
        oC.setFechaActualizacion(oV.fechaActual());
        service.guardar(oC);  
    }
    
}
