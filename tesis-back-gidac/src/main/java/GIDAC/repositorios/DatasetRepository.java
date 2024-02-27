package GIDAC.repositorios;

import GIDAC.modelo.Dataset;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;

public interface DatasetRepository extends JpaRepository<Dataset,Integer> {
    List<Dataset> findByVigenciaAndProfundidadParcela_idParcela(Boolean vigencia, Integer idParcela);
    List<Dataset> findByFechaSalidaCampo(Date fecha);
    //codigoDataset
    
    List<Dataset> findByProyectoInvestigacionIdProyecto(Integer idProyecto);
    List<Dataset> findByCodigoDatasetAndProyectoInvestigacionIdProyecto(Integer codigoDataset,Integer idProyecto);
    List<Dataset> findByProfundidadParcelaParcelaIdParcelaAndProfundidadParcelaProfundidadIdProfundidad(Integer idParcela, Integer idProfundidad);
    List<Dataset> findByCodigoDatasetAndProfundidadParcelaParcelaIdParcelaAndProfundidadParcelaProfundidadIdProfundidad(Integer codigoDataset, Integer idParcela, Integer idProfundidad);
    
    @Query(value="  SELECT distinct ds.id_dataset" +
                "   FROM dato_recolectado dr JOIN dataset ds ON ds.id_dataset = dr.id_dataset" +
                "   WHERE ds.id_parcela=:idDataset AND dr.vigencia",
            nativeQuery=true)
    List<Object[]> obtenerDataSetUsados(@Param("idDataset") Integer idDataset);
    
    @Query(value="  SELECT distinct ds.codigo_dataset, ds.fecha_inicio_dataset, ds.fecha_fin_dataset" +
                " FROM dataset ds" +
                " WHERE ds.id_proyecto=:idProyecto" +
                " ORDER BY 1 desc",
            nativeQuery=true)
    List<Object[]> obtenerDatasets(@Param("idProyecto") Integer idProyecto);
    
    @Query(value="  SELECT distinct ds.codigo_dataset, ds.fecha_inicio_dataset, ds.fecha_fin_dataset" +
                " FROM dataset ds" +
                " WHERE ds.id_proyecto=:idProyecto" +
                " ORDER BY 1 asc",
            nativeQuery=true)
    List<Object[]> obtenerDatasetsAsc(@Param("idProyecto") Integer idProyecto);
    
    
}
