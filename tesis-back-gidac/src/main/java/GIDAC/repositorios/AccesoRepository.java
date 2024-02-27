package GIDAC.repositorios;

import GIDAC.modelo.Acceso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccesoRepository extends JpaRepository<Acceso,Integer> {
    List<Acceso> findByUsuarioRolNombreRol(String rol);
}
