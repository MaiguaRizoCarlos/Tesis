package GIDAC.repositorios;

import GIDAC.modelo.TipoProyecto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoProyectoRepository extends JpaRepository<TipoProyecto,Integer> {
    public List<TipoProyecto> findByVigencia(Boolean vigencia);
}
