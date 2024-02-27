package GIDAC.repositorios;

import GIDAC.modelo.Altura;
import GIDAC.modelo.Conglomerado;
import GIDAC.modelo.ProyectoInvestigacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConglomeradoRepository extends JpaRepository<Conglomerado,Integer> {
    List<Conglomerado> findByVigenciaAndProyectoInvestigacion(Boolean vigencia,ProyectoInvestigacion proyectoInvestigacion);
    Conglomerado findByCodigoConglomeradoAndProyectoInvestigacionAndAltura(String codigoConglomerado,ProyectoInvestigacion proyectoInvestigacion, Altura altura);
    Conglomerado findByCodigoConglomeradoAndProyectoInvestigacion(String codigoConglomerado,ProyectoInvestigacion proyectoInvestigacion);
    
     @Query(value="  SELECT distinct c.id_conglomerado" +
                "   FROM parcela p JOIN conglomerado c ON c.id_conglomerado = p.id_conglomerado" +
                "   WHERE c.id_proyecto=:idProyecto AND p.vigencia=true",
            nativeQuery=true)
    List<Object[]> obtenerConglomeradosUsados(@Param("idProyecto") Integer idProyecto);
    
    
}
