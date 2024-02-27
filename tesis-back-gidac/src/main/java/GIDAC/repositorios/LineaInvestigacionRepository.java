package GIDAC.repositorios;

import GIDAC.modelo.LineaInvestigacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineaInvestigacionRepository extends JpaRepository<LineaInvestigacion,Integer> {
    public List<LineaInvestigacion> findByVigencia(Boolean vigencia);
}
