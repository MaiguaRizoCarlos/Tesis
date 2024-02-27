package GIDAC.controladores;


import GIDAC.modelo.GrupoInvestigacion;
import GIDAC.modelo.RespuestaSolicitudActualizar;
import GIDAC.modelo.SolicitudActualizarDato;
import GIDAC.servicios.GrupoInvestigacionService;
import GIDAC.servicios.RespuestaSolicitudActualizarService;
import GIDAC.servicios.SolicitudActualizarService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respuesta-solicitud-actualizar")
@CrossOrigin("*")
public class RespuestaSolicitudActualizarController {

    
    @Autowired
    private RespuestaSolicitudActualizarService service;
    
    @Autowired
    private GrupoInvestigacionService grupoInvestigacionService;
    
    @Autowired
    private SolicitudActualizarService solicitudActualizarService;
    
    @PostMapping("/guardar-respuesta-solicitud-actualizar")
    public Object guardar(@RequestBody RespuestaSolicitudActualizar oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener-respuesta-solicitud-actualizar/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-respuesta-solicitud-actualizar")
    public List<RespuestaSolicitudActualizar> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar-respuesta-solicitud-actualizar/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        service.eliminar(id);
    }
    
    @GetMapping("/aprobado/{id}")
    public List<RespuestaSolicitudActualizar> listarAprobado(@PathVariable Integer id){
        List<GrupoInvestigacion> listaInvestigaciones = grupoInvestigacionService.obtenerInvestigacionesDeInvestigadorVigentes(id);
        List<RespuestaSolicitudActualizar> listaSolicitudes=new ArrayList();
        for (GrupoInvestigacion dato : listaInvestigaciones) {
            List<RespuestaSolicitudActualizar> lista=service.listarPorEstadoActualizar("Aprobado", dato.getProyectoInvestigacion().getIdProyecto());
            for (RespuestaSolicitudActualizar datoSolicitud : lista) {
                listaSolicitudes.add(datoSolicitud);
            }
        }
        return listaSolicitudes;
        //return service.listarPorEstadoActualizar("Aprobado", id);
    }
    
    @GetMapping("/rechazado/{id}")
    public List<RespuestaSolicitudActualizar> listarRechazado(@PathVariable Integer id){
        List<GrupoInvestigacion> listaInvestigaciones = grupoInvestigacionService.obtenerInvestigacionesDeInvestigadorVigentes(id);
        List<RespuestaSolicitudActualizar> listaSolicitudes=new ArrayList();
        for (GrupoInvestigacion dato : listaInvestigaciones) {
            List<RespuestaSolicitudActualizar> lista=service.listarPorEstadoActualizar("Rechazado", dato.getProyectoInvestigacion().getIdProyecto());
            for (RespuestaSolicitudActualizar datoSolicitud : lista) {
                listaSolicitudes.add(datoSolicitud);
            }
        }
        return listaSolicitudes;
        //return service.listarPorEstadoActualizar("Rechazado", id);
    }
    
    
}
