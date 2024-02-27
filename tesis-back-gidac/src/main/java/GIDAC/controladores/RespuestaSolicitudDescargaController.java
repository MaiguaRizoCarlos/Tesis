package GIDAC.controladores;

import GIDAC.modelo.GrupoInvestigacion;
import GIDAC.modelo.RespuestaSolicitudDescarga;
import GIDAC.servicios.GrupoInvestigacionService;
import GIDAC.servicios.RespuestaSolicitudDescargaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respuesta-solicitud-descarga")
@CrossOrigin("*")
public class RespuestaSolicitudDescargaController {

    
    @Autowired
    private RespuestaSolicitudDescargaService service;
    
    @Autowired
    private GrupoInvestigacionService grupoInvestigacionService;
    

    @PostMapping("/guardar-respuesta-solicitud-descarga")
    public Object guardar(@RequestBody RespuestaSolicitudDescarga oC)
    {
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener-respuesta-solicitud-descarga/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-respuesta-solicitud-descarga")
    public List<RespuestaSolicitudDescarga> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar-respuesta-solicitud-descarga/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        service.eliminar(id);
    }
    
    @GetMapping("/aprobado/{id}")
    public List<RespuestaSolicitudDescarga> listarAprobado(@PathVariable Integer id){
        List<GrupoInvestigacion> listaInvestigaciones = grupoInvestigacionService.obtenerInvestigacionesDeInvestigadorVigentes(id);
        List<RespuestaSolicitudDescarga> listaSolicitudes=new ArrayList();
        for (GrupoInvestigacion dato : listaInvestigaciones) {
            List<RespuestaSolicitudDescarga> lista=service.listarPorEstadoDescarga("Aprobado", dato.getProyectoInvestigacion().getIdProyecto());
            for (RespuestaSolicitudDescarga datoSolicitud : lista) {
                listaSolicitudes.add(datoSolicitud);
            }
        }
        return listaSolicitudes;
        //return service.listarPorEstadoDescarga("Aprobado", id);
    }
    
    @GetMapping("/rechazado/{id}")
    public List<RespuestaSolicitudDescarga> listarRechazado(@PathVariable Integer id){
        List<GrupoInvestigacion> listaInvestigaciones = grupoInvestigacionService.obtenerInvestigacionesDeInvestigadorVigentes(id);
        List<RespuestaSolicitudDescarga> listaSolicitudes=new ArrayList();
        for (GrupoInvestigacion dato : listaInvestigaciones) {
            List<RespuestaSolicitudDescarga> lista=service.listarPorEstadoDescarga("Rechazado", dato.getProyectoInvestigacion().getIdProyecto());
            for (RespuestaSolicitudDescarga datoSolicitud : lista) {
                listaSolicitudes.add(datoSolicitud);
            }
        }
        return listaSolicitudes;
        //return service.listarPorEstadoDescarga("Rechazado", id);
    }
    
    
}
