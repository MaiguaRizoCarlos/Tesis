package GIDAC.repositorios;

import GIDAC.modelo.SolicitudDescarga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudDescargaRepository extends JpaRepository<SolicitudDescarga,Integer> {

    List<SolicitudDescarga> findByEstadoSolicitudDescarga_NombreEstadoDescarga(String estado);
    List<SolicitudDescarga> findByEstadoSolicitudDescarga_NombreEstadoDescargaAndProyectoInvestigacionIdProyecto(String nombreEstado, Integer idProyecto);
    //List<SolicitudDescarga> findByEstado(String estado);
    
}
