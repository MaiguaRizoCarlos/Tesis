package GIDAC.repositorios;

import GIDAC.modelo.UnidadMedida;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida,Integer> {
    UnidadMedida findByAbreviatura(String abreviatura);
    
      @Query(value="SELECT distinct um.id_unidad_medida" +
                    "   FROM unidad_medida um LEFT JOIN altura a ON (um.id_unidad_medida=a.id_unidad_medida)" +
                    "	LEFT JOIN profundidad p ON (p.id_unidad_medida = um.id_unidad_medida)" +
                    "	LEFT JOIN variable_unidad_medida umv on (umv.id_unidad_medida = um.id_unidad_medida)" +
                    "   LEFT join area ar on ar.id_unidad_medida = um.id_unidad_medida"+
                    "   WHERE a.id_altura is not null or p.id_profundidad is not null or umv.id_unidad_medida is not null or ar.id_area is not null",
            nativeQuery=true)
    List<Object[]> obtenerUnidadesMedidaUsadas();
    
    List<UnidadMedida> findByVigencia(Boolean vigencia);
}
