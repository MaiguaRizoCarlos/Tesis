package GIDAC.repositorios;


import GIDAC.modelo.SolicitudActualizarDato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudActualizarRepository extends JpaRepository<SolicitudActualizarDato,Integer> {

    List<SolicitudActualizarDato> findByEstadoSolicitudActualizar_NombreEstadoSolicitud(String estado);
    List<SolicitudActualizarDato> findByEstadoSolicitudActualizar_NombreEstadoSolicitudAndGrupoInvestigacionProyectoInvestigacionIdProyecto(String nombreEstado, Integer idProyecto);
    //List<SolicitudActualizarMuestra> findByEstado(String estado);
    
}
