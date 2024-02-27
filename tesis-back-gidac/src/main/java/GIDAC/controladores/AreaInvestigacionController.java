package GIDAC.controladores;

import GIDAC.modelo.AreaInvestigacion;
import GIDAC.servicios.AreaInvestigacionService;
import GIDAC.servicios.ProyectoInvestigacionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/area-investigacion")
@CrossOrigin("*")
public class AreaInvestigacionController {

    
    @Autowired
    private AreaInvestigacionService areaInvestigacionService;
    
    
    @Autowired
    private ProyectoInvestigacionService proyectoInvestigacionService;

    
    @GetMapping("/mostrar-areas-investigacion")
    public List<AreaInvestigacion> mostrarAreaInvestigacion()
    {
        return areaInvestigacionService.findAll();
    }
    @GetMapping("/obtener-area-investigacion/{idAreaInvestigacion}")
    public Object obtenerAreaInvestigacion(@PathVariable("idAreaInvestigacion") Integer idAreaInvestigacion)
    {
        return areaInvestigacionService.findById(idAreaInvestigacion);
    }
    @PostMapping("/guardar-area-investigacion")
    public Object guardarAreaInvestigacion(@RequestBody AreaInvestigacion oC)
    {
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oV.fechaActual());
        oC.setVigencia(true);
        return areaInvestigacionService.save(oC);    
    }
    
    @PutMapping("/actualizar-area-investigacion")
    public Object actualizarAreaInvestigacion(@RequestBody AreaInvestigacion oC)
    {
        AreaInvestigacion oD=(AreaInvestigacion) areaInvestigacionService.findById(oC.getIdAreaInvestigacion());
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oD.getFechaCreacion());
        oC.setFechaActualizacion(oV.fechaActual());
        oC.setVigencia(true);
        return areaInvestigacionService.save(oC);    
    }
    
    @DeleteMapping("/eliminar-area-investigacion/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        AreaInvestigacion areaInvestigacion=(AreaInvestigacion) areaInvestigacionService.findById(id);
        areaInvestigacion.setVigencia(false);
        cValidaciones oV=new cValidaciones();
        areaInvestigacion.setFechaActualizacion(oV.fechaActual());
        areaInvestigacionService.save(areaInvestigacion);
    }
    
    @GetMapping("/reestablecer-vigencia/{idAreaInvestigacion}")
    public void reestablecerVigencia(@PathVariable Integer idAreaInvestigacion)
    {
        AreaInvestigacion areaInvestigacion=(AreaInvestigacion) areaInvestigacionService.findById(idAreaInvestigacion);
        areaInvestigacion.setVigencia(true);
        cValidaciones oV=new cValidaciones();
        areaInvestigacion.setFechaActualizacion(oV.fechaActual());
        areaInvestigacionService.save(areaInvestigacion);
    }
    
    @GetMapping("/listar-area-investigacion-vigentes")
    public List<AreaInvestigacion> listarAreaInvestigacionConDirectoresProyectosInvestigacion()
    {
        return areaInvestigacionService.obtenerAreasInvestigacionVigentes();
//        List<AreaInvestigacion> areaInvestigaciones=areaInvestigacionService.obtenerAreasInvestigacionVigentes();
//        List<AreaInvestigacionVista> areaInvestigacionesVista=new ArrayList<>();
//        for (AreaInvestigacion areaInvestigacion : areaInvestigaciones) {
//            System.out.println("---------------------------------------------------llega");
//            AreaInvestigacionVista areaInvestigacionVista=new AreaInvestigacionVista();
//            DirectorAreaInvestigacion directorAreaInvestigacion=new DirectorAreaInvestigacion();
//            areaInvestigacionVista.setIdAreaInvestigacion(areaInvestigacion.getIdAreaInvestigacion());
//            areaInvestigacionVista.setNombreAreaInvestigacion(areaInvestigacion.getNombreAreaInvestigacion());
//            
//            System.out.println(""+areaInvestigacionVista.getIdAreaInvestigacion());
//            try{
//                directorAreaInvestigacion=(DirectorAreaInvestigacion) directorAreaInvestigacionService.obtenerDirectorDeAreaInvestigacion(areaInvestigacion.getIdAreaInvestigacion());
//                areaInvestigacionVista.setIdUsuario(directorAreaInvestigacion.getUsuario().getIdUsuario());
//                areaInvestigacionVista.setNombreUsuario(directorAreaInvestigacion.getUsuario().getNombreUsuario());
//                areaInvestigacionVista.setApellidoUsuario(directorAreaInvestigacion.getUsuario().getApellidoUsuario());
//                
//            }catch(Exception e){
//                areaInvestigacionVista.setIdUsuario(0);
//                areaInvestigacionVista.setNombreUsuario("Sin director");
//                areaInvestigacionVista.setApellidoUsuario("");
//            }
//            try{
//                areaInvestigacionVista.setCantidadInvestigaciones(proyectoInvestigacionService.obtnerInvestigacionesPorAreaInvestigacion(areaInvestigacion.getIdAreaInvestigacion()).size());
//            }catch(Exception e){
//                areaInvestigacionVista.setCantidadInvestigaciones(0);
//            }
//            areaInvestigacionesVista.add(areaInvestigacionVista);
//        }
//        return areaInvestigacionesVista;
    }
    
    @GetMapping("/listar-area-investigacion-eliminadas")
    public List<AreaInvestigacion> listarAreaInvestigacionConDirectoresProyectosInvestigacionEliminadas()
    {
        return areaInvestigacionService.obtenerAreasInvestigacionNoVigentes();
//        List<AreaInvestigacion> areaInvestigaciones=areaInvestigacionService.obtenerAreasInvestigacionNoVigentes();
//        List<AreaInvestigacionVista> areaInvestigacionesVista=new ArrayList<>();
//        for (AreaInvestigacion areaInvestigacion : areaInvestigaciones) {
//            System.out.println("---------------------------------------------------llega");
//            AreaInvestigacionVista areaInvestigacionVista=new AreaInvestigacionVista();
//            DirectorAreaInvestigacion directorAreaInvestigacion=new DirectorAreaInvestigacion();
//            areaInvestigacionVista.setIdAreaInvestigacion(areaInvestigacion.getIdAreaInvestigacion());
//            areaInvestigacionVista.setNombreAreaInvestigacion(areaInvestigacion.getNombreAreaInvestigacion());
//            
//            System.out.println(""+areaInvestigacionVista.getIdAreaInvestigacion());
//            try{
//                directorAreaInvestigacion=(DirectorAreaInvestigacion) directorAreaInvestigacionService.obtenerDirectorDeAreaInvestigacion(areaInvestigacion.getIdAreaInvestigacion());
//                areaInvestigacionVista.setIdUsuario(directorAreaInvestigacion.getUsuario().getIdUsuario());
//                areaInvestigacionVista.setNombreUsuario(directorAreaInvestigacion.getUsuario().getNombreUsuario());
//                areaInvestigacionVista.setApellidoUsuario(directorAreaInvestigacion.getUsuario().getApellidoUsuario());
//                
//            }catch(Exception e){
//                areaInvestigacionVista.setIdUsuario(0);
//                areaInvestigacionVista.setNombreUsuario("Sin director");
//                areaInvestigacionVista.setApellidoUsuario("");
//            }
//            try{
//                areaInvestigacionVista.setCantidadInvestigaciones(proyectoInvestigacionService.obtnerInvestigacionesPorAreaInvestigacion(areaInvestigacion.getIdAreaInvestigacion()).size());
//            }catch(Exception e){
//                areaInvestigacionVista.setCantidadInvestigaciones(0);
//            }
//            areaInvestigacionesVista.add(areaInvestigacionVista);
//        }
//        return areaInvestigacionesVista;
    }
    
}
