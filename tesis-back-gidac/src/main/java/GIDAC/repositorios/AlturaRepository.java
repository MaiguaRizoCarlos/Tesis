package GIDAC.repositorios;

import GIDAC.modelo.Altura;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlturaRepository extends JpaRepository<Altura,Integer> {
    //Altura findByAlturaMinimaAndAlturaMaximaAndUnidadMedida_Abreviatura(float alturaMinima, float alturaMaxima, String abreviatura);
    List<Altura> findByVigenciaAndAlturaAndUnidadMedida_Abreviatura(Boolean vigencia, Double altura, String abreviatura);
    
    @Query(value=" SELECT distinct a.id_altura" +
                  " FROM altura a JOIN conglomerado c ON c.id_altura = a.id_altura",
            nativeQuery=true)
    List<Object[]> obtenerAlturasUsadas();
    
    List<Altura> findByVigencia(Boolean vigencia);
}
