package GIDAC.controladores;


import GIDAC.modelo.CatalogoOrganizacion;
import GIDAC.modelo.Organizacion;
import GIDAC.modelo.Variable;
import GIDAC.servicios.CatalogoOrganizacionService;
import GIDAC.servicios.OrganizacionService;
import GIDAC.servicios.VariableService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organizacion")
@CrossOrigin("*")
public class OrganizacionController {

    
    @Autowired
    private OrganizacionService service;
    
    @Autowired
    private CatalogoOrganizacionService catalogoOrganizacionService;
    
    @Autowired
    private VariableService variableService;

    @PostMapping("/guardar")
    public Object guardar(@RequestBody Organizacion oC)
    {
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oV.fechaActual());
        return service.guardar(oC);    
    }
    
    @PutMapping("/actualizar")
    public Object editar(@RequestBody Organizacion oC)
    {
        Organizacion oD=(Organizacion) service.buscarPorId(oC.getIdOrganizacion());
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
    
    @GetMapping("/listar")
    public List<Organizacion> listar()
    {
        return service.buscarPorVigencia(true);
    }
    
    @GetMapping("/listar-eliminados")
    public List<Organizacion> listarEliminados()
    {
        return service.buscarPorVigencia(false);
    }
    
    
    @DeleteMapping("/reestablecer/{id}")
    public void reestablecer(@PathVariable Integer id)
    {
        Organizacion oC=(Organizacion) service.buscarPorId(id);
        oC.setVigencia(true);
        cValidaciones oV=new cValidaciones();
        oC.setFechaActualizacion(oV.fechaActual());
        service.guardar(oC);  
        
        List<CatalogoOrganizacion> oListaCatalogo= catalogoOrganizacionService.findByVigenciaAndOrganizacionIdOrganizacion(false, id);
        for (CatalogoOrganizacion cat : oListaCatalogo) {
            cat.setFechaActualizacion(oV.fechaActual());
            cat.setVigencia(true);
            catalogoOrganizacionService.guardar(cat); 
            if(cat.isVariableSistema()==true){
                if(variableService.findByVigenciaAndIdVariableAndCodigoVariable(false, cat.getVariable().getIdVariable(), cat.getVariable().getCodigoVariable())!=null){
                        Variable var=cat.getVariable();
                        var.setFechaActualizacion(oV.fechaActual());
                        var.setVigencia(true);
                        variableService.guardar(var); 
                }
            }
        }
        
    }
    
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        Organizacion oC=(Organizacion) service.buscarPorId(id);
        cValidaciones oV=new cValidaciones();
        oC.setFechaActualizacion(oV.fechaActual());
        oC.setVigencia(false);
        service.guardar(oC);    
        
        List<CatalogoOrganizacion> oListaCatalogo= catalogoOrganizacionService.findByVigenciaAndOrganizacionIdOrganizacion(true, id);
        for (CatalogoOrganizacion cat : oListaCatalogo) {
            cat.setFechaActualizacion(oV.fechaActual());
            cat.setVigencia(false);
            catalogoOrganizacionService.guardar(cat); 
            if(cat.isVariableSistema()==true){
                if(variableService.findByVigenciaAndIdVariableAndCodigoVariable(true, cat.getVariable().getIdVariable(), cat.getVariable().getCodigoVariable())!=null){
                        Variable var=cat.getVariable();
                        var.setFechaActualizacion(oV.fechaActual());
                        var.setVigencia(false);
                        variableService.guardar(var); 
                }
            }
        }
    }
    
    
}
