package GIDAC.repositorios;

import GIDAC.modelo.LineaInvestigacion;
import GIDAC.modelo.LineaInvestigacionProyecto;
import GIDAC.modelo.LineaInvestigacionProyectoId;
import GIDAC.modelo.ProyectoInvestigacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineaInvestigacionProyectoRepository extends JpaRepository<LineaInvestigacionProyecto,LineaInvestigacionProyectoId> {
    List<LineaInvestigacionProyecto> findByVigenciaAndProyectoInvestigacionIdProyecto(Boolean estado,Integer idProyecto);
    LineaInvestigacionProyecto findByVigenciaAndProyectoInvestigacionIdProyectoAndLineaInvestigacionIdLineaInvestigacion(Boolean estado,Integer idProyecto, Integer idLineaInvestigacion);
    LineaInvestigacionProyecto findByProyectoInvestigacionIdProyectoAndLineaInvestigacionIdLineaInvestigacion(Integer idProyecto, Integer idLineaInvestigacion);
    
    
}
