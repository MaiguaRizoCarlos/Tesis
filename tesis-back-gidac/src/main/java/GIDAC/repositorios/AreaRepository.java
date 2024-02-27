package GIDAC.repositorios;


import GIDAC.modelo.Area;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AreaRepository extends JpaRepository<Area,Integer> {
     Area findByAreaAndUnidadMedida_Abreviatura(float area, String abreviatura);
     
     @Query(value=" SELECT distinct a.id_area" +
                    " FROM area a JOIN parcela p ON p.id_area = a.id_area",
            nativeQuery=true)
    List<Object[]> obtenerAreasUsadas();
    
    List<Area> findByVigencia(Boolean vigencia);
}
