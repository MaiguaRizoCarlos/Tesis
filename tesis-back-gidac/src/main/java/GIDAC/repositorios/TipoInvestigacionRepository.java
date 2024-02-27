package GIDAC.repositorios;


import GIDAC.modelo.SectorImpacto;
import GIDAC.modelo.TipoInvestigacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoInvestigacionRepository extends JpaRepository<TipoInvestigacion,Integer> {
    public List<TipoInvestigacion> findByVigencia(Boolean vigencia);
}
