package GIDAC.servicios.impl;


import GIDAC.controladores.cValidaciones;
import GIDAC.modelo.ProfundidadParcela;
import GIDAC.repositorios.ProfundidadParcelaRepository;
import GIDAC.servicios.ProfundidadParcelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProfundidadParcelaServiceImpl implements ProfundidadParcelaService {

    @Autowired
    private ProfundidadParcelaRepository repository;

    @Override
    public ProfundidadParcela guardar(Object objeto) {
        ProfundidadParcela oA=(ProfundidadParcela) objeto;
        cValidaciones ov=new cValidaciones();
        if((repository.findByIdProfundidadAndIdParcela(oA.getProfundidad().getIdProfundidad(), oA.getParcela().getIdParcela())==null)){
            oA.setIdParcela(oA.getParcela().getIdParcela());
            oA.setIdProfundidad(oA.getProfundidad().getIdProfundidad());
            oA.setVigencia(true);
            oA.setFechaCreacion(ov.fechaActual());
        }else{
            oA=repository.findByIdProfundidadAndIdParcela(oA.getProfundidad().getIdProfundidad(), oA.getParcela().getIdParcela());
            oA.setVigencia(true);
            oA.setFechaActualizacion(ov.fechaActual());
        }
        return repository.save(oA);
    }
    
    @Override
    public ProfundidadParcela actualizar(Object objeto) {
        ProfundidadParcela oA=(ProfundidadParcela) objeto;
        oA.setIdParcela(oA.getParcela().getIdParcela());
        oA.setIdProfundidad(oA.getProfundidad().getIdProfundidad());
        ProfundidadParcela oC=repository.findByIdProfundidadAndIdParcela(oA.getProfundidad().getIdProfundidad(), oA.getParcela().getIdParcela());
        oA.setFechaCreacion(oC.getFechaCreacion());
        oA.setVigencia(true);
        cValidaciones ov=new cValidaciones();
        oA.setFechaActualizacion(ov.fechaActual());
        repository.save(oA);
        return repository.save(oA);
    }

    @Override
    public List<ProfundidadParcela> buscarTodos() {
        return repository.findAll();
    }
    
    @Override
    public ProfundidadParcela buscarPorParcelaProfundidad(Integer idParcela, Integer idProfundidad) {
        return repository.findByIdProfundidadAndIdParcela(idProfundidad, idParcela);
    }

    @Override
    public void eliminar(Integer  idProfundidad, Integer idParcela) {
        ProfundidadParcela oA=repository.findByIdProfundidadAndIdParcela(idProfundidad, idParcela);
        oA.setVigencia(false);
        cValidaciones ov=new cValidaciones();
        oA.setFechaActualizacion(ov.fechaActual());
        repository.save(oA);
    }

    @Override
    public List<Object[]> obtenerProfundiadParcelaUsados(Integer id) {
        return repository.obtenerProfundiadParcelaUsados(id);
    }

    @Override
    public List<ProfundidadParcela> buscarPorVigenciaParcela(Boolean vigencia, Integer idParcela) {
        return repository.findByVigenciaAndParcelaIdParcela(vigencia, idParcela);
    }
    
    @Override
    public List<ProfundidadParcela> buscarPorParcela( Integer idParcela) {
        return repository.findByParcelaIdParcela( idParcela);
    }


}
