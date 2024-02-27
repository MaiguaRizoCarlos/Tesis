/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.controladores;

import GIDAC.modelo.AreaInvestigacion;
import GIDAC.modelo.AreaInvestigacionAux;
import GIDAC.modelo.AreaInvestigacionProyecto;
import GIDAC.modelo.EstadoProyectoInvestigacion;
import GIDAC.modelo.GrupoInvestigacion;
import GIDAC.modelo.LineaInvestigacion;
import GIDAC.modelo.LineaInvestigacionProyecto;
import GIDAC.modelo.Localizacion;
import GIDAC.modelo.LocalizacionProyecto;
import GIDAC.modelo.ProyectoInvestigacion;
import GIDAC.modelo.SectorImpacto;
import GIDAC.modelo.SectorImpactoProyecto;
import GIDAC.modelo.Usuario;
import GIDAC.servicios.AreaInvestigacionProyectoService;
import GIDAC.servicios.AreaInvestigacionService;
import GIDAC.servicios.GrupoInvestigacionService;
import GIDAC.servicios.LineaInvestigacionProyectoService;
import GIDAC.servicios.LineaInvestigacionService;
import GIDAC.servicios.LocalizacionProyectoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import GIDAC.servicios.ProyectoInvestigacionService;
import GIDAC.servicios.SectorImpactoProyectoService;
import GIDAC.servicios.SectorImpactoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestParam;
import GIDAC.servicios.LocalizacionService;
/**
 *
 * @author My Notebook
 */
@RestController
@CrossOrigin(origins={"*"})
@RequestMapping("/api")
public class ProyectoInvestigacionControlador {
    @Autowired
    private ProyectoInvestigacionService crud;
    
    @Autowired
    private AreaInvestigacionProyectoService areaInvestigacionProyectoService;
    
    @Autowired
    private LineaInvestigacionProyectoService lineaInvestigacionProyectoService;
    
    @Autowired
    private SectorImpactoProyectoService sectorImpactoProyectoService;
    
    @Autowired
    private LocalizacionProyectoService localizacionProyectoService;
    
    @Autowired
    private GrupoInvestigacionService grupoInvestigacionService;
    
    
    //listar
    @GetMapping("/investigacion")
    public List<ProyectoInvestigacion> listar()
    {
        return crud.findAll();
    }
    
    //listar investigaciones publicas
    @GetMapping("/investigacion-publicas")
    public List<ProyectoInvestigacion> listarInvestigacionesPublicas()
    {
        return crud.findInvestigacionesPublicas();
    }
    
    //listar investigaciones vigentes true
    @GetMapping("/investigacion-vigentes-true")
    public List<ProyectoInvestigacion> listarInvestigacionesVigentesTrue()
    {
        return crud.findAllVigentes(true);
    }
    
    //listar investigaciones vigentes false
    @GetMapping("/investigacion-vigentes-false")
    public List<ProyectoInvestigacion> listarInvestigacionesVigentesFalse()
    {
        return crud.findAllVigentes(false);
    }
    
    //listarParaFiltrar
    @GetMapping("/investigacion-mapa")
    public List<ProyectoInvestigacion> listarMapa()
    {
        ProyectoInvestigacion oI=new ProyectoInvestigacion();
        oI.setIdProyecto(0);
        oI.setNombreProyecto("Todos");
        List<ProyectoInvestigacion> oITT=new ArrayList();
        oITT.add(oI);
        List<ProyectoInvestigacion> oIT=crud.findAll();
        for(ProyectoInvestigacion oInv:oIT) {
            oITT.add(oInv);
        }
        return oITT;
    }
    //guardar
    @PostMapping("/investigacion")
    public Object guardar(@RequestParam("proyectoInvestigacion") String proyInvestigacion, 
                          @RequestParam("usuarioDirector") String datosJsonUsuario, 
                          @RequestParam("listaAreaInvestigacion") String datosJsonAreas, 
                          @RequestParam("listaSectorImpacto") String datosJsonSector, 
                          @RequestParam("listaLineaInvestigacion") String datosJsonLinea, 
                          @RequestParam("listaLocalizacion") String datosJsonLocalizacion) throws JsonProcessingException{
        
        ObjectMapper objectMapper=new ObjectMapper();
        ProyectoInvestigacion oC = new ObjectMapper().readValue(proyInvestigacion, ProyectoInvestigacion.class);
        Usuario usuarioDirector = new ObjectMapper().readValue(datosJsonUsuario, Usuario.class);
        List<SectorImpacto> listaSectorImpacto = objectMapper.readValue(datosJsonSector, new TypeReference<List<SectorImpacto>>() {});
        List<LineaInvestigacion> listaLineaInvestigacion = objectMapper.readValue(datosJsonLinea, new TypeReference<List<LineaInvestigacion>>() {});
        List<Localizacion> listaLocalizacion = objectMapper.readValue(datosJsonLocalizacion, new TypeReference<List<Localizacion>>() {});
        
        List<AreaInvestigacion> listaAreaInvestigacion = objectMapper.readValue(datosJsonAreas, new TypeReference<List<AreaInvestigacion>>() {});
        
        cValidaciones validaciones=new cValidaciones();
        oC.setFechaCreacion(validaciones.fechaActual());
        oC.setVigencia(true);
        oC =(ProyectoInvestigacion) crud.save(oC);
        
        
        GrupoInvestigacion oGrupo=new GrupoInvestigacion();
        oGrupo.setIdUsuario(usuarioDirector.getIdUsuario());
        oGrupo.setIdProyecto(oC.getIdProyecto());
        oGrupo.setProyectoInvestigacion(oC);
        oGrupo.setUsuario(usuarioDirector);
        oGrupo.setFechaCreacion(validaciones.fechaActual());
        oGrupo.setVigencia(true);
        grupoInvestigacionService.save(oGrupo);
        
        for(SectorImpacto oDato:listaSectorImpacto) {
            SectorImpactoProyecto dato=new SectorImpactoProyecto();
            dato.setProyectoInvestigacion(oC);
            dato.setIdProyecto(oC.getIdProyecto());
            dato.setIdSectorImpacto(oDato.getIdSectorImpacto());
            dato.setSectorImpacto(oDato);
            dato.setFechaCreacion(validaciones.fechaActual());
            dato.setVigencia(true);
            sectorImpactoProyectoService.guardar(dato);
        }
        
        for(AreaInvestigacion oDato:listaAreaInvestigacion) {
            AreaInvestigacionProyecto dato=new AreaInvestigacionProyecto();
            
            //eliminar cuado se arregle el area{
            AreaInvestigacion datoArea=new AreaInvestigacion();
            datoArea.setIdAreaInvestigacion(oDato.getIdAreaInvestigacion());
            //}
            
            dato.setProyectoInvestigacion(oC);
            dato.setIdProyecto(oC.getIdProyecto());
            dato.setIdAreaInvestigacion(oDato.getIdAreaInvestigacion());
            dato.setAreaInvestigacion(datoArea);
            dato.setFechaCreacion(validaciones.fechaActual());
            dato.setVigencia(true);
            areaInvestigacionProyectoService.guardar(dato);
        }
        
        
        
        for(LineaInvestigacion oDato:listaLineaInvestigacion) {
            LineaInvestigacionProyecto dato=new LineaInvestigacionProyecto();
            dato.setProyectoInvestigacion(oC);
            dato.setIdProyecto(oC.getIdProyecto());
            dato.setIdLineaInvestigacion(oDato.getIdLineaInvestigacion());
            dato.setLineaInvestigacion(oDato);
            dato.setFechaCreacion(validaciones.fechaActual());
            dato.setVigencia(true);
            lineaInvestigacionProyectoService.guardar(dato);
        }
        
        for(Localizacion oDato:listaLocalizacion) {
            LocalizacionProyecto dato=new LocalizacionProyecto();
            dato.setProyectoInvestigacion(oC);
            dato.setIdProyecto(oC.getIdProyecto());
            dato.setIdLocalizacion(oDato.getIdLocalizacion());
            dato.setLocalizacion(oDato);
            dato.setFechaCreacion(validaciones.fechaActual());
            dato.setVigencia(true);
            localizacionProyectoService.guardar(dato);
        }
        
        return oC;
    }
    //get un administrador
    @GetMapping("/obtener-proyecto-investigacion/{id}")
    public Object getUno(@PathVariable Integer id)
    {
        return crud.findById(id);
    }
    
    //modificar
    @PutMapping("/actualizar")
    public Object modificar(@RequestBody ProyectoInvestigacion oC){
        ProyectoInvestigacion oD= (ProyectoInvestigacion) crud.findById(oC.getIdProyecto());
        cValidaciones validaciones=new cValidaciones();
        oC.setFechaCreacion(oD.getFechaCreacion());
        oC.setFechaActualizacion(validaciones.fechaActual());
        return crud.save(oC);
    }
    
    @PutMapping("/actializar-adicional")
    public void actualizarInformacionAdicional(@RequestParam("proyectoInvestigacion") String proyInvestigacion, 
                          @RequestParam("listaAreaInvestigacion") String datosJsonAreas, 
                          @RequestParam("listaSectorImpacto") String datosJsonSector, 
                          @RequestParam("listaLineaInvestigacion") String datosJsonLinea) throws JsonProcessingException{
        
        ObjectMapper objectMapper=new ObjectMapper();
        ProyectoInvestigacion oC = new ObjectMapper().readValue(proyInvestigacion, ProyectoInvestigacion.class);
        List<SectorImpacto> listaSectorImpacto = objectMapper.readValue(datosJsonSector, new TypeReference<List<SectorImpacto>>() {});
        List<LineaInvestigacion> listaLineaInvestigacion = objectMapper.readValue(datosJsonLinea, new TypeReference<List<LineaInvestigacion>>() {});
        
        List<AreaInvestigacion> listaAreaInvestigacion = objectMapper.readValue(datosJsonAreas, new TypeReference<List<AreaInvestigacion>>() {});
       
        cValidaciones validaciones=new cValidaciones();
        
        
        //-----------------------------------------------------------
        //actualizar sector de impact no ocupadas
        List<AreaInvestigacionProyecto> areaActuales=areaInvestigacionProyectoService.buscarPorProyecto(oC.getIdProyecto());
        for(AreaInvestigacionProyecto oDato:areaActuales) {
            boolean ban=false;
            for(AreaInvestigacion oDatoAcualizado:listaAreaInvestigacion) {
               if(oDatoAcualizado.getIdAreaInvestigacion()==oDato.getAreaInvestigacion().getIdAreaInvestigacion()){
                   ban=true;
               }
            }
            if(ban==false){
                oDato.setVigencia(false);
                oDato.setFechaActualizacion(validaciones.fechaActual());
                areaInvestigacionProyectoService.guardar(oDato);
            }
        }
        
        //guardar sectores de impacto actuales
        for(AreaInvestigacion oDato:listaAreaInvestigacion) {
            AreaInvestigacionProyecto dato=new AreaInvestigacionProyecto();
            if(areaInvestigacionProyectoService.buscarPorProyectoAreaInvestigacion(oC.getIdProyecto(), oDato.getIdAreaInvestigacion())==null){
                dato.setFechaCreacion(validaciones.fechaActual());
            }else{
                dato=(AreaInvestigacionProyecto) areaInvestigacionProyectoService.buscarPorProyectoAreaInvestigacion(oC.getIdProyecto(), oDato.getIdAreaInvestigacion());
                dato.setFechaActualizacion(validaciones.fechaActual());
            }
            
            dato.setProyectoInvestigacion(oC);
            dato.setIdProyecto(oC.getIdProyecto());
            
            dato.setIdAreaInvestigacion(oDato.getIdAreaInvestigacion());
            dato.setAreaInvestigacion(oDato);
            
            dato.setVigencia(true);
            areaInvestigacionProyectoService.guardar(dato);
        }
        //-----------------------------------------------------------
        
        
        //-----------------------------------------------------------
        //actualizar sector de impact no ocupadas
        List<SectorImpactoProyecto> sectorActuales=sectorImpactoProyectoService.buscarPorProyecto(oC.getIdProyecto());
        for(SectorImpactoProyecto oDato:sectorActuales) {
            boolean ban=false;
            for(SectorImpacto oDatoAcualizado:listaSectorImpacto) {
               if(oDatoAcualizado.getIdSectorImpacto()==oDato.getSectorImpacto().getIdSectorImpacto()){
                   ban=true;
               }
            }
            if(ban==false){
                oDato.setVigencia(false);
                oDato.setFechaActualizacion(validaciones.fechaActual());
                sectorImpactoProyectoService.guardar(oDato);
            }
        }
        
        //guardar sectores de impacto actuales
        for(SectorImpacto oDato:listaSectorImpacto) {
            SectorImpactoProyecto dato=new SectorImpactoProyecto();
            if(sectorImpactoProyectoService.buscarPorProyectoSectorImpacto(oC.getIdProyecto(), oDato.getIdSectorImpacto())==null){
                dato.setFechaCreacion(validaciones.fechaActual());
            }else{
                dato=(SectorImpactoProyecto) sectorImpactoProyectoService.buscarPorProyectoSectorImpacto(oC.getIdProyecto(), oDato.getIdSectorImpacto());
                dato.setFechaActualizacion(validaciones.fechaActual());
            }
            
            dato.setProyectoInvestigacion(oC);
            dato.setIdProyecto(oC.getIdProyecto());
            
            dato.setIdSectorImpacto(oDato.getIdSectorImpacto());
            dato.setSectorImpacto(oDato);
            
            dato.setVigencia(true);
            sectorImpactoProyectoService.guardar(dato);
        }
        //-----------------------------------------------------------
        
        //-----------------------------------------------------------
        //actualizar lineas de investigacion no ocupadas
        List<LineaInvestigacionProyecto> lineasActuales=lineaInvestigacionProyectoService.buscarPorProyecto(oC.getIdProyecto());
        for(LineaInvestigacionProyecto oDato:lineasActuales) {
            boolean ban=false;
            for(LineaInvestigacion oDatoAcualizado:listaLineaInvestigacion) {
               if(oDatoAcualizado.getIdLineaInvestigacion()==oDato.getLineaInvestigacion().getIdLineaInvestigacion()){
                   ban=true;
               }
            }
            if(ban==false){
                oDato.setVigencia(false);
                oDato.setFechaActualizacion(validaciones.fechaActual());
                lineaInvestigacionProyectoService.guardar(oDato);
            }
        }
        
        //guardar lineas de investigacion
        for(LineaInvestigacion oDato:listaLineaInvestigacion) {
            LineaInvestigacionProyecto dato=new LineaInvestigacionProyecto();
            if(lineaInvestigacionProyectoService.buscarPorProyectoLineaInvestigacion(oC.getIdProyecto(), oDato.getIdLineaInvestigacion())==null){
                dato.setFechaCreacion(validaciones.fechaActual());
            }else{
                dato=(LineaInvestigacionProyecto) lineaInvestigacionProyectoService.buscarPorProyectoLineaInvestigacion(oC.getIdProyecto(), oDato.getIdLineaInvestigacion());
                dato.setFechaActualizacion(validaciones.fechaActual());
            }
            
            dato.setProyectoInvestigacion(oC);
            dato.setIdProyecto(oC.getIdProyecto());
            
            dato.setIdLineaInvestigacion(oDato.getIdLineaInvestigacion());
            dato.setLineaInvestigacion(oDato);
            
            dato.setVigencia(true);
            lineaInvestigacionProyectoService.guardar(dato);
        }
        //-----------------------------------------------------------
        
    }
    
    
    //eliminar 
    @DeleteMapping("/eliminar-proyecto-investigacion/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        cValidaciones validaciones=new cValidaciones();
        
        ProyectoInvestigacion proyectoInvestigacion=(ProyectoInvestigacion) crud.findById(id);
        proyectoInvestigacion.setFechaActualizacion(validaciones.fechaActual());
        proyectoInvestigacion.setVigencia(false);
        //Aux para cambiar publico
//        EstadoProyectoInvestigacion estadoProyectoInvestigacion=new EstadoProyectoInvestigacion();
//        estadoProyectoInvestigacion.setIdEstadoProyecto(1);
//        proyectoInvestigacion.setEstadoProyectoInvestigacion(estadoProyectoInvestigacion);
        crud.save(proyectoInvestigacion);
    }
    
    @GetMapping("/restaurar-proyecto-investigacion/{id}")
    public void restaurar(@PathVariable Integer id)
    {
        cValidaciones validaciones=new cValidaciones();
        ProyectoInvestigacion proyectoInvestigacion=(ProyectoInvestigacion) crud.findById(id);
        proyectoInvestigacion.setFechaActualizacion(validaciones.fechaActual());
        proyectoInvestigacion.setVigencia(true);
        crud.save(proyectoInvestigacion);
    }
    
    @GetMapping("/investigacion/cambio-estado-proyecto-investigacion/{id}")
    public Object cambioEstadoProyectoInvestigacion(@PathVariable Integer id)
    {
        ProyectoInvestigacion oC=(ProyectoInvestigacion) crud.findById(id);
        EstadoProyectoInvestigacion estadoProyectoInvestigacion=new EstadoProyectoInvestigacion();
        if(oC.getEstadoProyectoInvestigacion().getIdEstadoProyecto()==1){
            estadoProyectoInvestigacion.setIdEstadoProyecto(2);
            oC.setEstadoProyectoInvestigacion(estadoProyectoInvestigacion);
        }else{
            estadoProyectoInvestigacion.setIdEstadoProyecto(1);
            oC.setEstadoProyectoInvestigacion(estadoProyectoInvestigacion);
        }
        return crud.save(oC);
    }
}   
