package GIDAC.repositorios;

import GIDAC.modelo.Organizacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizacionRepository extends JpaRepository<Organizacion,Integer> {
    List<Organizacion> findByVigencia(Boolean vigencia);
    Organizacion findByVigenciaAndIdOrganizacion(Boolean vigencia, Integer idOrganizacion);
}
