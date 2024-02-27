package GIDAC.servicios.impl;



import GIDAC.modelo.TipoVariable;
import GIDAC.repositorios.TipoVariableRepository;
import GIDAC.servicios.TipoVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TipoVariableServiceImpl implements TipoVariableService {

    @Autowired
    private TipoVariableRepository repository;

    @Override
    public TipoVariable guardar(Object objeto) {
        TipoVariable oA=(TipoVariable) objeto;
        return repository.save(oA);
    }

    @Override
    public TipoVariable buscarPorId(Integer id) {
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

}
