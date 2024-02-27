package GIDAC.repositorios;

import GIDAC.modelo.SectorImpacto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorImpactoRepository extends JpaRepository<SectorImpacto,Integer> {
    public List<SectorImpacto> findByVigencia(Boolean vigencia);
}
