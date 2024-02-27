/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.servicios.impl;


import GIDAC.modelo.ProyectoInvestigacion;
import GIDAC.modelo.GrupoInvestigacion;
import GIDAC.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import GIDAC.repositorios.ProyectoInvestigacionRepository;
import GIDAC.repositorios.GrupoInvestigacionRepository;
import GIDAC.servicios.GrupoInvestigacionService;

/**
 *
 * @author My Notebook
 */
@Service
public class GrupoInvestigacionServiceImpl implements GrupoInvestigacionService{
   @Autowired
   private GrupoInvestigacionRepository DAO;
   
   @Autowired
   private ProyectoInvestigacionRepository DAOInvestigadion;
   
   @Override
   @Transactional(readOnly=true)
   public List<GrupoInvestigacion> findAll(){
       return (List<GrupoInvestigacion>) DAO.findAll();
   }
    @Override
    public GrupoInvestigacion save(Object objeto) {
        GrupoInvestigacion oA=(GrupoInvestigacion) objeto;
        return DAO.save(oA);
    }
   
  @Override
    public List<GrupoInvestigacion> obtenerInvestigadoresEnProyectosInvestigacion(Integer id) {
        ProyectoInvestigacion oInves = new ProyectoInvestigacion();
        oInves.setIdProyecto(id);
        return DAO.findByVigenciaAndProyectoInvestigacionAndUsuarioRolNombreRol(true, oInves, "INVESTIGADOR");
    }
    
    @Override
    public List<GrupoInvestigacion> obtenerDirectorEnProyectosInvestigacion(Integer id) {
        ProyectoInvestigacion oInves = new ProyectoInvestigacion();
        oInves.setIdProyecto(id);
        return DAO.findByVigenciaAndProyectoInvestigacionAndUsuarioRolNombreRol(true, oInves, "DIRECTOR");
    }
   @Override
    public List<GrupoInvestigacion> obtenerInvestigacionesInvestigador(Integer idUsuario) {
        Usuario oUsuario = new Usuario();
        oUsuario.setIdUsuario(idUsuario);
        return DAO.findByVigenciaAndUsuarioIdUsuarioAndProyectoInvestigacionVigencia(true,idUsuario, true);
    }
    
    @Override
    public List<GrupoInvestigacion> obtenerInvestigacionesDeInvestigadorVigentes(Integer idUsuario) {
        Usuario oUsuario = new Usuario();
        oUsuario.setIdUsuario(idUsuario);
        return DAO.findByVigenciaAndUsuarioIdUsuarioAndProyectoInvestigacionVigencia(true,idUsuario, true);
    }
    
    
    

    @Override
    public boolean verificarVigenciaInvestigadorEnProyectoInvestigacion(Integer idUsuario, Integer idProyectoInvestigacion) {
        ProyectoInvestigacion oInves = new ProyectoInvestigacion();
        oInves.setIdProyecto(idProyectoInvestigacion);
        Usuario oUsuario = new Usuario();
        oUsuario.setIdUsuario(idUsuario);
        if(null==DAO.findByVigenciaAndUsuarioAndProyectoInvestigacion(true, oUsuario, oInves)){
            return false;
        }else{
            return true;
        }
    }
    
    @Override
    public Object obtenerUsuarioConProyectoInvestigacion(Integer idUsuario, Integer idProyectoInvestigacion) {
        ProyectoInvestigacion oInves = new ProyectoInvestigacion();
        oInves.setIdProyecto(idProyectoInvestigacion);
        Usuario oUsuario = new Usuario();
        oUsuario.setIdUsuario(idUsuario);
        return DAO.findByUsuarioAndProyectoInvestigacion(oUsuario, oInves);
    }
    
    /*@Override
    public List<Investigador> obtenerinvestigadorInvestigacion(Integer id) {
        Investigacion oInves = DAOInvestigadion.findById(id).orElseThrow(() -> new NoSuchElementException("Curso no encontrado"));
        System.out.println(oInves.getInvestigacionInvestigador().toString());
        List<InvestigacionInvestigador> inscripciones=new ArrayList<>();
        inscripciones.addAll(oInves.getInvestigacionInvestigador());
        System.out.println("........................................");
        System.out.println("llega ..................................");
        //List<UsuarioRol> inscripciones = (List<UsuarioRol>) curso.getUsuarioRoles();
        List<Investigador> oInvestigador = new ArrayList<>();
        
        for (InvestigacionInvestigador inscripcion : inscripciones) {
            oInvestigador.add(inscripcion.getInvestigador());
            
            
        }
        return oInvestigador;
    }*/

    @Override
    public List obtenerInvestigacionesVigentesDirector(Integer id) {
        Usuario oUsuario = new Usuario();
        oUsuario.setIdUsuario(id);
        return DAO.findByUsuarioAndProyectoInvestigacionVigenciaAndVigencia(oUsuario, true, true);
    }

    @Override
    public List obtenerInvestigacionesEliminadosDirector(Integer id) {
        Usuario oUsuario = new Usuario();
        oUsuario.setIdUsuario(id);
        return DAO.findByUsuarioAndProyectoInvestigacionVigenciaAndVigencia(oUsuario, false, true);
    }

    
    @Override
    public List<Object[]> obtenerProyectosDeDirector( Integer idUsuario){
        return DAO.obtenerProyectosDeDirector(idUsuario);
    }
    


}
