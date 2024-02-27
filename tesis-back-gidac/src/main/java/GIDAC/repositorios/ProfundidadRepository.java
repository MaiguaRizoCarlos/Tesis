package GIDAC.repositorios;

import GIDAC.modelo.UnidadMedida;
import GIDAC.modelo.Profundidad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfundidadRepository extends JpaRepository<Profundidad,Integer> {
    List<Profundidad> findByUnidadMedida(UnidadMedida medida);
    Profundidad findByProfundidadMinimaAndProfundidadMaximaAndUnidadMedida_Abreviatura(Double profundidadMinima, Double profundidadMaxima, String abreviatura);
    
    @Query(value=" SELECT distinct p.id_profundidad" +
                 " FROM profundidad p JOIN profundidad_parcela pp ON pp.id_profundidad = p.id_profundidad",
            nativeQuery=true)
    List<Object[]> obtenerProfundidadesUsadas();
    
    List<Profundidad> findByVigencia(Boolean vigencia);
}
