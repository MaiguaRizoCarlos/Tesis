package GIDAC.repositorios;

import GIDAC.modelo.Acceso;
import GIDAC.modelo.EmailEnvio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailEnvioRepository extends JpaRepository<EmailEnvio,Integer> {
    
    List<EmailEnvio> findByVigencia(Boolean vigencia);
}
