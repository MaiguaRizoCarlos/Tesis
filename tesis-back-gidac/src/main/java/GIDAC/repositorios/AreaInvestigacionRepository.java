package GIDAC.repositorios;

import GIDAC.modelo.AreaInvestigacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaInvestigacionRepository extends JpaRepository<AreaInvestigacion,Integer> {
    List<AreaInvestigacion> findByVigencia(Boolean vigencia);
}
