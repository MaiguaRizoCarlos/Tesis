package GIDAC.controladores;

import GIDAC.modelo.DatoRecolectado;
import GIDAC.modelo.EstadoSolicitudActualizar;
import GIDAC.modelo.EstadoSolicitudDescarga;
import GIDAC.modelo.GrupoInvestigacion;
import GIDAC.modelo.ProyectoInvestigacion;
import GIDAC.modelo.RespuestaSolicitudActualizar;
import GIDAC.modelo.RespuestaSolicitudDescarga;
import GIDAC.modelo.SolicitudActualizarDato;
import GIDAC.modelo.SolicitudDescarga;
import GIDAC.modelo.Usuario;
import GIDAC.modelo.contadorSolicitudes;
import GIDAC.servicios.DatoRecolectadoService;
import GIDAC.servicios.EmailEnvioService;
import GIDAC.servicios.EstadoSolicitudActualizarService;
import GIDAC.servicios.EstadoSolicitudDescargaService;
import GIDAC.servicios.GrupoInvestigacionService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import GIDAC.servicios.TiempoEdicionDatoService;
import GIDAC.modelo.TiempoEdicionDato;
import java.util.List;
import GIDAC.servicios.SolicitudDescargaService;
import GIDAC.servicios.RespuestaSolicitudActualizarService;
import GIDAC.servicios.RespuestaSolicitudDescargaService;
import GIDAC.servicios.SolicitudActualizarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.Date;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/solicitud")
@CrossOrigin("*")
public class SolicitudesController {

    @Autowired
    private SolicitudDescargaService solicitudDescargaService;
    
    @Autowired
    private SolicitudActualizarService solicitudActualizarService;
    
    @Autowired
    private EstadoSolicitudActualizarService estadoSolicitudActualizarService;
    
    @Autowired
    private RespuestaSolicitudActualizarService respuestaSolicitudActualizarService;
    
    @Autowired
    private EstadoSolicitudDescargaService estadoSolicitudDescargaService;
    
    @Autowired
    private RespuestaSolicitudDescargaService respuestaSolicitudDescargaService;
    
    @Autowired
    private DatoRecolectadoService datoRecolectadoService;
    
    @Autowired
    private GrupoInvestigacionService grupoInvestigacionService;
    
    @Autowired
    private TiempoEdicionDatoService tiempoEdicionDatoService;
    
    @Autowired
    private EmailEnvioService emailEnvioService;
    
    @GetMapping("/contador-solicitudes/{id}")
    public contadorSolicitudes contadorSolicitudes(@PathVariable Integer id){
//        contadorSolicitudes oC=new contadorSolicitudes();
//        
//        List<Object[]> listaInvestigaciones = grupoInvestigacionService.obtenerProyectosDeDirector(id);
//        
//        int contadorEliminar=0;
//        int contadorDescarga=0;
//        
//        for (Object[] dato : listaInvestigaciones) {
//            contadorDescarga=contadorDescarga+solicitudDescargaService.listarPorEstadoAndIdProyecto("Solicitado", (Integer) dato[1]).size();
//            contadorEliminar=contadorEliminar +solicitudActualizarService.listarPorEstadoAndIdProyecto("Solicitado",(Integer) dato[1]).size();
//        }
//        oC.setContSolEliminar(contadorEliminar);
//        oC.setContSolAcceso(contadorDescarga);

        contadorSolicitudes oC=new contadorSolicitudes();
        
        List<GrupoInvestigacion> listaInvestigaciones = grupoInvestigacionService.obtenerInvestigacionesDeInvestigadorVigentes(id);
        
        int contadorEliminar=0;
        int contadorDescarga=0;
        
        for (GrupoInvestigacion dato : listaInvestigaciones) {
            contadorDescarga=contadorDescarga+solicitudDescargaService.listarPorEstadoAndIdProyecto("Solicitado", dato.getProyectoInvestigacion().getIdProyecto()).size();
            contadorEliminar=contadorEliminar +solicitudActualizarService.listarPorEstadoAndIdProyecto("Solicitado",dato.getProyectoInvestigacion().getIdProyecto()).size();
        }
        oC.setContSolEliminar(contadorEliminar);
        oC.setContSolAcceso(contadorDescarga);
        return oC;
    } 
    
    //Acceso a datos
    @PostMapping("/guardar-solicitud-descarga")
    public Object guardarSolicitud(@RequestBody SolicitudDescarga oC)
    {
        cValidaciones validaciones =new cValidaciones();
        EstadoSolicitudDescarga estadoSolicitudDescarga=new EstadoSolicitudDescarga();
        estadoSolicitudDescarga.setIdEstadoDescarga(1);
        if(null==estadoSolicitudDescargaService.buscarPorId(1)){
            estadoSolicitudDescarga.setNombreEstadoDescarga("Solicitado");
            estadoSolicitudDescargaService.guardar(estadoSolicitudDescarga);
        }
        oC.setEstadoSolicitudDescarga(estadoSolicitudDescarga);
        oC.setFechaEnvioSolicitud(validaciones.fechaActual());
        return solicitudDescargaService.save(oC);    
    }
    
    @PostMapping("/solicitud-aprobada-envio-mensaje")
    public Object solicitudAprobadaEnvioMensaje(@RequestParam("solicitudDescarga") String datosJson, @RequestParam("file") MultipartFile file) throws JsonProcessingException, Exception{
        
        SolicitudDescarga oC1 = new ObjectMapper().readValue(datosJson, SolicitudDescarga.class);
        
        cValidaciones validaciones = new cValidaciones();
        RespuestaSolicitudDescarga respuestaSolicitudDescarga=new RespuestaSolicitudDescarga();
        respuestaSolicitudDescarga.setRespuesta("La Escuela Superior Politecnica de chimborazo (ESPOCH), da acceso a los datos:");
        respuestaSolicitudDescarga.setFechaRespuesta(validaciones.fechaActual());
        
        SolicitudDescarga oC=(SolicitudDescarga) solicitudDescargaService.findById(oC1.getIdSolicitudDescarga());
        respuestaSolicitudDescarga.setSolicitudDescarga(oC);
        respuestaSolicitudDescargaService.guardar(respuestaSolicitudDescarga);
        
        EstadoSolicitudDescarga estadoSolicitudDescarga=new EstadoSolicitudDescarga();
        estadoSolicitudDescarga.setIdEstadoDescarga(2);
        if(null==estadoSolicitudDescargaService.buscarPorId(2)){
            estadoSolicitudDescarga.setNombreEstadoDescarga("Aprobado");
            estadoSolicitudDescargaService.guardar(estadoSolicitudDescarga);
        }
        oC.setEstadoSolicitudDescarga(estadoSolicitudDescarga);
        emailEnvioService.enviarEmailAprobarSolicitudDescarga(respuestaSolicitudDescarga, file);
        return solicitudDescargaService.save(oC);    
    }
    
    @GetMapping("/solicitud-aprobada/{id}")
    public Object aprobarSolicitud(@PathVariable("id") Integer id) throws Exception
    {
        cValidaciones validaciones = new cValidaciones();
        RespuestaSolicitudDescarga respuestaSolicitudDescarga=new RespuestaSolicitudDescarga();
        respuestaSolicitudDescarga.setRespuesta("La Escuela Superior Politecnica de chimborazo (ESPOCH), da acceso a los datos:");
        respuestaSolicitudDescarga.setFechaRespuesta(validaciones.fechaActual());
        
        SolicitudDescarga oC=(SolicitudDescarga) solicitudDescargaService.findById(id);
        respuestaSolicitudDescarga.setSolicitudDescarga(oC);
        respuestaSolicitudDescargaService.guardar(respuestaSolicitudDescarga);
        
        EstadoSolicitudDescarga estadoSolicitudDescarga=new EstadoSolicitudDescarga();
        estadoSolicitudDescarga.setIdEstadoDescarga(2);
        if(null==estadoSolicitudDescargaService.buscarPorId(2)){
            estadoSolicitudDescarga.setNombreEstadoDescarga("Aprobado");
            estadoSolicitudDescargaService.guardar(estadoSolicitudDescarga);
        }
        oC.setEstadoSolicitudDescarga(estadoSolicitudDescarga);
        return solicitudDescargaService.save(oC);    
    }
    
    @GetMapping("/solicitud-rechazada/{id}/{respuesta}")
    public Object rechazarSolicitud(@PathVariable("id") Integer id,@PathVariable("respuesta") String respuesta) throws Exception
    {
        cValidaciones validaciones = new cValidaciones();
        RespuestaSolicitudDescarga respuestaSolicitudDescarga=new RespuestaSolicitudDescarga();
        respuestaSolicitudDescarga.setRespuesta(respuesta);
        respuestaSolicitudDescarga.setFechaRespuesta(validaciones.fechaActual());
        
        SolicitudDescarga oC=(SolicitudDescarga) solicitudDescargaService.findById(id);
        respuestaSolicitudDescarga.setSolicitudDescarga(oC);
        respuestaSolicitudDescargaService.guardar(respuestaSolicitudDescarga);
        
        EstadoSolicitudDescarga estadoSolicitudDescarga=new EstadoSolicitudDescarga();
        estadoSolicitudDescarga.setIdEstadoDescarga(3);
        if(null==estadoSolicitudDescargaService.buscarPorId(3)){
            estadoSolicitudDescarga.setNombreEstadoDescarga("Rechazado");
            estadoSolicitudDescargaService.guardar(estadoSolicitudDescarga);
        }
        oC.setEstadoSolicitudDescarga(estadoSolicitudDescarga);
        emailEnvioService.enviarEmailRechazarSolicitudDescarga(respuestaSolicitudDescarga);
        return solicitudDescargaService.save(oC); 
        
    }
    
    @GetMapping("/get-solicitud")
    public Object getSolicitud(@PathVariable("id") Integer id)
    {
        return solicitudDescargaService.findById(id);
    }
   
    @GetMapping("/solicitado/{id}")
    public List<SolicitudDescarga> listarSolicitados(@PathVariable Integer id){
        List<GrupoInvestigacion> listaInvestigaciones = grupoInvestigacionService.obtenerInvestigacionesDeInvestigadorVigentes(id);
        List<SolicitudDescarga> listaSolicitudes=new ArrayList();
        for (GrupoInvestigacion dato : listaInvestigaciones) {
            List<SolicitudDescarga> lista=solicitudDescargaService.listarPorEstadoAndIdProyecto("Solicitado", dato.getProyectoInvestigacion().getIdProyecto());
            for (SolicitudDescarga datoSolicitud : lista) {
                listaSolicitudes.add(datoSolicitud);
            }
        }
        return listaSolicitudes;
    } 
    
    
    //Eliminar datos
    
    @GetMapping("/guardar-solicitud-eliminar/{id}/{idProyInv}/{idUsuario}/{motivo}")
    public Object guardarSolicitudEliminar(@PathVariable("id") Integer idDatoRecolectado,@PathVariable("idProyInv") Integer idProyecto, @PathVariable("motivo") String motivoEliminar, @PathVariable("idUsuario") Integer idUsuario)
    {
        cValidaciones validaciones = new cValidaciones();
        EstadoSolicitudActualizar estadoSolicitudActualizar=new EstadoSolicitudActualizar();
        estadoSolicitudActualizar.setIdEstadoSolicitud(1);
        if(null==estadoSolicitudActualizarService.buscarPorId(1)){
            estadoSolicitudActualizar.setNombreEstadoSolicitud("Solicitado");
            estadoSolicitudActualizarService.guardar(estadoSolicitudActualizar);
        }
        ProyectoInvestigacion proyectoInvestigacion=new ProyectoInvestigacion();
        proyectoInvestigacion.setIdProyecto(idProyecto);
        Usuario usuario=new Usuario();
        usuario.setIdUsuario(idUsuario);
        GrupoInvestigacion grupoInvestigacion=new GrupoInvestigacion();
        grupoInvestigacion.setIdProyecto(idProyecto);
        grupoInvestigacion.setProyectoInvestigacion(proyectoInvestigacion);
        grupoInvestigacion.setIdUsuario(idUsuario);
        grupoInvestigacion.setUsuario(usuario);
        DatoRecolectado oCar=(DatoRecolectado) datoRecolectadoService.buscarPorId(idDatoRecolectado);
        oCar.setVigencia(false);
        datoRecolectadoService.guardar(oCar);
        SolicitudActualizarDato oC=new SolicitudActualizarDato();
        oC.setMotivo(motivoEliminar);
        oC.setFechaEnvioSolicitud(validaciones.fechaActual());
        oC.setGrupoInvestigacion(grupoInvestigacion);
        oC.setDatoRecolectado(oCar);
        oC.setEstadoSolicitudActualizar(estadoSolicitudActualizar);
        return solicitudActualizarService.save(oC);   
    }
   
    
    @GetMapping("/aprobar-solicitud-eliminar/{id}")
    public Object aprobarSolicitudEliminar(@PathVariable("id") Integer id)
    {
        String respuesta="El dato del proyecto de investigacion ha sido aproabada para su actualizacion";
        cValidaciones validaciones = new cValidaciones();
        EstadoSolicitudActualizar estadoSolicitudActualizar=new EstadoSolicitudActualizar();
        estadoSolicitudActualizar.setIdEstadoSolicitud(2);
        if(null==estadoSolicitudActualizarService.buscarPorId(2)){
            estadoSolicitudActualizar.setNombreEstadoSolicitud("Aprobado");
            estadoSolicitudActualizarService.guardar(estadoSolicitudActualizar);
        }
        SolicitudActualizarDato solicitudActualizar=(SolicitudActualizarDato) solicitudActualizarService.findById(id);
        DatoRecolectado oCar=(DatoRecolectado) datoRecolectadoService.buscarPorId(solicitudActualizar.getDatoRecolectado().getIdDatoRecolectado());
        oCar.setVigencia(true);
        oCar.setEditable(true);
        
        
        List<TiempoEdicionDato> oLista=tiempoEdicionDatoService.findAll();
        Date fechaActual=validaciones.fechaActual();
        int dias=1;
        if(!oLista.isEmpty()){
            TiempoEdicionDato oTiempoEdicionDato=oLista.get(0);    
            double tiempoDouble = oTiempoEdicionDato.getTiempo();
            dias = (int) Math.round(tiempoDouble);
        }
        
        oCar.setFechaMaximaEdicion(validaciones.agregarFechaMaximaEdicion(fechaActual, dias));
        
        
        oCar.setFechaActualizacion(validaciones.fechaActual());
        
        datoRecolectadoService.guardar(oCar);
        solicitudActualizar.setEstadoSolicitudActualizar(estadoSolicitudActualizar);
        solicitudActualizarService.save(solicitudActualizar);    
        RespuestaSolicitudActualizar respuestaSolicitudActualizar=new RespuestaSolicitudActualizar();
        respuestaSolicitudActualizar.setSolicitudActualizarDato(solicitudActualizar);
        respuestaSolicitudActualizar.setRespuesta(respuesta);
        respuestaSolicitudActualizar.setFechaRespuesta(validaciones.fechaActual());
        return respuestaSolicitudActualizarService.guardar(respuestaSolicitudActualizar); 
    }
    
    @GetMapping("/rechazadar-solicitud-eliminadar/{id}/{motivo}")
    public Object rechazarSolicitudEliminar(@PathVariable("id") Integer id,@PathVariable("motivo") String motivoRechazo)
    {
        cValidaciones validaciones = new cValidaciones();
        EstadoSolicitudActualizar estadoSolicitudActualizar=new EstadoSolicitudActualizar();
        estadoSolicitudActualizar.setIdEstadoSolicitud(3);
        if(null==estadoSolicitudActualizarService.buscarPorId(3)){
            estadoSolicitudActualizar.setNombreEstadoSolicitud("Rechazado");
            estadoSolicitudActualizarService.guardar(estadoSolicitudActualizar);
        }
        SolicitudActualizarDato solicitudActualizar=(SolicitudActualizarDato) solicitudActualizarService.findById(id);
        DatoRecolectado datoRecolectado=(DatoRecolectado) datoRecolectadoService.buscarPorId(solicitudActualizar.getDatoRecolectado().getIdDatoRecolectado());
        datoRecolectado.setVigencia(true);
        datoRecolectadoService.guardar(datoRecolectado);
        solicitudActualizar.setEstadoSolicitudActualizar(estadoSolicitudActualizar);
        solicitudActualizarService.save(solicitudActualizar);
        RespuestaSolicitudActualizar respuestaSolicitudActualizar=new RespuestaSolicitudActualizar();
        respuestaSolicitudActualizar.setSolicitudActualizarDato(solicitudActualizar);
        respuestaSolicitudActualizar.setRespuesta(motivoRechazo);
        respuestaSolicitudActualizar.setFechaRespuesta(validaciones.fechaActual());
        return respuestaSolicitudActualizarService.guardar(respuestaSolicitudActualizar);    
    }
    
    @GetMapping("/get-solicitud-eliminar")
    public Object getSolicitudEliminar(@PathVariable("id") Integer id)
    {
        return solicitudActualizarService.findById(id);
    }
    
    
    @GetMapping("/solicitado-eliminar/{id}")
    public List<SolicitudActualizarDato> listarSolicitadosEliminar(@PathVariable Integer id){
        
        List<GrupoInvestigacion> listaInvestigaciones = grupoInvestigacionService.obtenerInvestigacionesDeInvestigadorVigentes(id);
        List<SolicitudActualizarDato> listaSolicitudes=new ArrayList();
        for (GrupoInvestigacion dato : listaInvestigaciones) {
            List<SolicitudActualizarDato> lista=solicitudActualizarService.listarPorEstadoAndIdProyecto("Solicitado", dato.getProyectoInvestigacion().getIdProyecto());
            for (SolicitudActualizarDato datoSolicitud : lista) {
                listaSolicitudes.add(datoSolicitud);
            }
        }
        return listaSolicitudes;
    } 
    
}
