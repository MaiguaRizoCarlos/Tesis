package GIDAC.servicios.impl;



import GIDAC.modelo.TipoInvestigacion;
import GIDAC.repositorios.TipoInvestigacionRepository;
import GIDAC.servicios.TipoInvestigacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TipoInvestigacionServiceImpl implements TipoInvestigacionService {

    @Autowired
    private TipoInvestigacionRepository repository;

    @Override
    public TipoInvestigacion guardar(Object objeto) {
        TipoInvestigacion oA=(TipoInvestigacion) objeto;
        return repository.save(oA);
    }

    @Override
    public TipoInvestigacion buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List buscarTodos(Boolean vigencia) {
        return repository.findByVigencia(vigencia);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }


}
