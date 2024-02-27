/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.controladores;

import GIDAC.modelo.GrupoInvestigacion;
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
import GIDAC.servicios.GrupoInvestigacionService;
import GIDAC.servicios.UsuarioService;
/**
 *
 * @author My Notebook
 */
@RestController
@CrossOrigin(origins={"*"})
@RequestMapping("/api")
public class GrupoInvestigacionControlador {
    @Autowired
    private GrupoInvestigacionService crud;
    
    @Autowired
    private UsuarioService crudUsuario;
    
    //listar
    @GetMapping("/investigacion-investigador")
    public List<GrupoInvestigacion> listar()
    {
        return crud.findAll();
    }
    //guardar
    @PostMapping("/investigacion-investigador")
    public Object guardarInvestigadorEnProyectoInvestigacion(@RequestBody GrupoInvestigacion oC)
    {
        cValidaciones validaciones =new cValidaciones();
        if(null==crud.obtenerUsuarioConProyectoInvestigacion(oC.getUsuario().getIdUsuario(),oC.getProyectoInvestigacion().getIdProyecto())){
           
            oC.setIdProyecto(oC.getProyectoInvestigacion().getIdProyecto());
            oC.setIdUsuario(oC.getUsuario().getIdUsuario());
            oC.setFechaCreacion(validaciones.fechaActual());
            oC.setFechaActualizacion(validaciones.fechaActual());
            oC.setVigencia(true); 
            return crud.save(oC);
        }else{
            GrupoInvestigacion grupoInvestigacion=(GrupoInvestigacion) crud.obtenerUsuarioConProyectoInvestigacion(oC.getUsuario().getIdUsuario(),oC.getProyectoInvestigacion().getIdProyecto());
            grupoInvestigacion.setFechaActualizacion(validaciones.fechaActual());
            grupoInvestigacion.setVigencia(true); 
            return crud.save(grupoInvestigacion);
        }
    }
    
    //eliminar logiacmente
    @PostMapping("/eliminar-investigador-de-proyecto-investigacion")
    public Object eliminarInvestigadorDeproyectoInvestigacion(@RequestBody GrupoInvestigacion oC)
    {
        cValidaciones validaciones =new cValidaciones();
        GrupoInvestigacion grupoInvestigacion=(GrupoInvestigacion) crud.obtenerUsuarioConProyectoInvestigacion(oC.getUsuario().getIdUsuario(),oC.getProyectoInvestigacion().getIdProyecto());
        grupoInvestigacion.setFechaActualizacion(validaciones.fechaActual());
        grupoInvestigacion.setVigencia(false);
        return crud.save(grupoInvestigacion);
    }
    
    
    
    //get un administrador
    
    
    //modificar
    //    @PutMapping("/investigacion-investigador/{id}")
    //    public Object modificar(@RequestBody GrupoInvestigacion oC, @PathVariable Integer id){
    //        GrupoInvestigacion oC1=(GrupoInvestigacion) crud.findById(id);
    //        oC1.setIdGrupoInvestigacion(oC.getIdGrupoInvestigacion());
    //        return crud.save(oC1);
    //    }
    
    //eliminar 
    
    @GetMapping("/lista-investigadores-en-proyectors-investigacion/{id}")
    public List<GrupoInvestigacion>listarInvestigadoresEnProyectosInvestigacion(@PathVariable Integer id){
        return crud.obtenerInvestigadoresEnProyectosInvestigacion(id);
    }
    
    @GetMapping("/lista-investigacion-investigador/{id}")
    public List<GrupoInvestigacion>listarInvestigaciones(@PathVariable Integer id){
        return crud.obtenerInvestigacionesInvestigador(id);
    }
    
    @GetMapping("/investigacion-vigentes-director/{id}")
    public List<GrupoInvestigacion>listarInvestigacionVigentesDirector(@PathVariable Integer id){
        return crud.obtenerInvestigacionesVigentesDirector(id);
    }
    
    @GetMapping("/investigacion-eliminados-director/{id}")
    public List<GrupoInvestigacion>listarInvestigacionEliminadosDirector(@PathVariable Integer id){
        return crud.obtenerInvestigacionesEliminadosDirector(id);
    }
    
    @GetMapping("/listar-director-proyecto/{id}")
    public List<GrupoInvestigacion>listarDirectoresProyecto(@PathVariable Integer id){
        return crud.obtenerDirectorEnProyectosInvestigacion(id);
    }
    
    
    
}   

