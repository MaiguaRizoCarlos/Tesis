package GIDAC.repositorios;

import GIDAC.modelo.ProfundidadParcela;
import GIDAC.modelo.ProfundidadParcelaId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfundidadParcelaRepository extends JpaRepository<ProfundidadParcela,ProfundidadParcelaId> {
    
    ProfundidadParcela findByIdProfundidadAndIdParcela(Integer idProfundidad, Integer idParcela);
    
    List<ProfundidadParcela> findByVigenciaAndParcelaIdParcela(Boolean vigencia, Integer idParcela);
    
    List<ProfundidadParcela> findByParcelaIdParcela(Integer idParcela);
    
    @Query(value=" SELECT distinct ds.id_parcela, ds.id_profundidad" +
                " FROM dato_recolectado dr JOIN dataset ds ON ds.id_dataset = dr.id_dataset" +
                " JOIN profundidad_parcela pp ON pp.id_parcela = ds.id_parcela" +
                " WHERE ds.id_parcela=:idParcela AND dr.vigencia",
            nativeQuery=true)
    List<Object[]> obtenerProfundiadParcelaUsados(@Param("idParcela") Integer idParcela);
}
