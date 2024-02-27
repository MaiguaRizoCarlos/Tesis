/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.repositorios;



import GIDAC.modelo.ProyectoInvestigacion;
import GIDAC.modelo.GrupoInvestigacion;
import GIDAC.modelo.GrupoInvestigacionId;
import GIDAC.modelo.GrupoInvestigacionPk;
import GIDAC.modelo.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author My Notebook
 */
public interface GrupoInvestigacionRepository extends CrudRepository<GrupoInvestigacion,GrupoInvestigacionId>{
    List<GrupoInvestigacion> findByProyectoInvestigacion(ProyectoInvestigacion investigacion);
    List<GrupoInvestigacion> findByVigenciaAndProyectoInvestigacionAndUsuarioRolNombreRol(Boolean estado,ProyectoInvestigacion investigacion, String nombreRol);
    List<GrupoInvestigacion> findByVigenciaAndUsuarioAndProyectoInvestigacion(Boolean estado,Usuario usuario,ProyectoInvestigacion investigacion);
    
    GrupoInvestigacion findByUsuarioAndProyectoInvestigacion(Usuario usuario,ProyectoInvestigacion investigacion);
    
    List<GrupoInvestigacion> findByVigenciaAndUsuarioIdUsuarioAndProyectoInvestigacionVigencia(Boolean vigencia, Integer idUsuario, Boolean vigenciaProy);
    List<GrupoInvestigacion> findByUsuarioAndProyectoInvestigacionVigenciaAndVigencia(Usuario usuario, Boolean vigenciaProy, Boolean vigencia);
    //List<GrupoInvestigacion> findByIdProyectoInvestigacion(Integer idProyectoInvestigacion);
    
    @Query(value="select u.id_usuario, pi.id_proyecto" +
                " from grupo_investigacion gi join usuario u on u.id_usuario = gi.id_usuario" +
                "	join proyecto_investigacion pi ON pi.id_proyecto = gi.id_proyecto" +
                " where u.id_usuario=:idUsuario and gi.vigencia=true and pi.vigencia=true",
            nativeQuery=true)
    List<Object[]> obtenerProyectosDeDirector(@Param("idUsuario") Integer idUsuario);
}
