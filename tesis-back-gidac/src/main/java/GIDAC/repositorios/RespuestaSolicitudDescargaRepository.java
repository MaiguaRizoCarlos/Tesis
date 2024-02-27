package GIDAC.repositorios;


import GIDAC.modelo.RespuestaSolicitudDescarga;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespuestaSolicitudDescargaRepository extends JpaRepository<RespuestaSolicitudDescarga,Integer> {
    List<RespuestaSolicitudDescarga> findBySolicitudDescargaEstadoSolicitudDescargaNombreEstadoDescargaAndSolicitudDescargaProyectoInvestigacionIdProyecto(String nombreEstado, Integer idProyecto);
}
