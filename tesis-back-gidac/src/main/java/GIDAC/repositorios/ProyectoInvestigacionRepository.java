/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.repositorios;


import GIDAC.modelo.AreaInvestigacion;
import GIDAC.modelo.ProyectoInvestigacion;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author My Notebook
 */
public interface ProyectoInvestigacionRepository extends CrudRepository<ProyectoInvestigacion, Integer>{
    List<ProyectoInvestigacion> findByVigenciaAndEstadoProyectoInvestigacion_nombreEstadoProyecto(Boolean vigencia, String estado);
    List<ProyectoInvestigacion> findByVigencia(Boolean vigencia);
    
}
