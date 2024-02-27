package GIDAC.servicios.impl;


import GIDAC.modelo.Dataset;
import GIDAC.repositorios.DatasetRepository;
import GIDAC.servicios.DataSetService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DataSetServiceImpl implements DataSetService {

    @Autowired
    private DatasetRepository repository;

    @Override
    public Dataset guardar(Object objeto) {
        Dataset oA=(Dataset) objeto;
        return repository.save(oA);
    }

    @Override
    public Dataset buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List buscarTodos() {
        return repository.findAll();
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
    
    @Override
    public List buscarPorParcela(Integer id) {
        return repository.findByVigenciaAndProfundidadParcela_idParcela(true, id);
    }
  

    @Override
    public List<Dataset> buscarPorParcelaProfundidad(Integer idParcela, Integer idProfundidad) {
        return repository.findByProfundidadParcelaParcelaIdParcelaAndProfundidadParcelaProfundidadIdProfundidad(idParcela, idProfundidad);
    }

    @Override
    public List<Object[]> obtenerDataSetUsados(Integer id) {
        return repository.obtenerDataSetUsados(id);
    }

    @Override
    public List<Dataset> buscarPorCodigoDatasetParcelaProfundidad(Integer codigoDataset, Integer idParcela, Integer idProfundidad) {
        return repository.findByCodigoDatasetAndProfundidadParcelaParcelaIdParcelaAndProfundidadParcelaProfundidadIdProfundidad(codigoDataset, idParcela, idProfundidad);
    }

    @Override
    public List buscarPorProyecto(Integer id) {
        return repository.findByProyectoInvestigacionIdProyecto(id);
    }

    @Override
    public List<Object[]>  obtenerDatasets(Integer id) {
        return repository.obtenerDatasets(id);
    }
    
    @Override
    public List<Object[]>  obtenerDatasetsAsc(Integer id) {
        return repository.obtenerDatasetsAsc(id);
    }
    
    @Override
    public List<Dataset> findByCodigoDatasetAndProyectoInvestigacionIdProyecto(Integer codigoDataset,Integer idProyecto){
       return repository.findByCodigoDatasetAndProyectoInvestigacionIdProyecto(codigoDataset, idProyecto);
    }

    @Override
    public List findByFechaSalidaCampo(Date fecha) {
        return repository.findByFechaSalidaCampo(fecha);
    }

}
