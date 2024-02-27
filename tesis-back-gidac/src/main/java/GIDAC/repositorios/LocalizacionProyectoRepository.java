package GIDAC.repositorios;

import GIDAC.modelo.LocalizacionProyecto;
import GIDAC.modelo.LocalizacionProyectoId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocalizacionProyectoRepository extends JpaRepository<LocalizacionProyecto,LocalizacionProyectoId> {
    List<LocalizacionProyecto> findByVigenciaAndProyectoInvestigacionIdProyecto(Boolean estado,Integer idProyecto);
    LocalizacionProyecto findByVigenciaAndProyectoInvestigacionIdProyectoAndLocalizacionIdLocalizacion(Boolean estado,Integer idProyecto, Integer idLocalizacion);
    LocalizacionProyecto findByProyectoInvestigacionIdProyectoAndLocalizacionIdLocalizacion(Integer idProyecto, Integer idLocalizacion);
    
    
}
