package GIDAC.repositorios;

import GIDAC.modelo.TiempoEdicionDato;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TiempoEdicionDatoRepository extends JpaRepository<TiempoEdicionDato,Integer> {
    
    List<TiempoEdicionDato> findByVigencia(Boolean vigencia);
}
