package GIDAC.repositorios;

import GIDAC.modelo.AreaInvestigacionProyecto;
import GIDAC.modelo.AreaInvestigacionProyectoId;
import GIDAC.modelo.ProyectoInvestigacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaInvestigacionProyectoRepository extends JpaRepository<AreaInvestigacionProyecto,AreaInvestigacionProyectoId> {
    List<AreaInvestigacionProyecto> findByVigencia(Boolean vigencia);
    List<AreaInvestigacionProyecto> findByVigenciaAndProyectoInvestigacionIdProyecto(Boolean estado,Integer idProyecto);
    AreaInvestigacionProyecto findByVigenciaAndProyectoInvestigacionIdProyectoAndAreaInvestigacionIdAreaInvestigacion(Boolean estado,Integer idProyecto, Integer idArea);
    AreaInvestigacionProyecto findByProyectoInvestigacionIdProyectoAndAreaInvestigacionIdAreaInvestigacion(Integer idProyecto, Integer idArea);
    
    
}
