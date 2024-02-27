package GIDAC.repositorios;

import GIDAC.modelo.EstadoProyectoInvestigacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoProyectoInvestigacionRepository extends JpaRepository<EstadoProyectoInvestigacion,Integer> {
    
}
