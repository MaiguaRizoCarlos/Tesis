package GIDAC.servicios.impl;



import GIDAC.modelo.TipoProyecto;
import GIDAC.repositorios.TipoProyectoRepository;
import GIDAC.servicios.TipoProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TipoProyectoServiceImpl implements TipoProyectoService {

    @Autowired
    private TipoProyectoRepository repository;

    @Override
    public TipoProyecto guardar(Object objeto) {
        TipoProyecto oA=(TipoProyecto) objeto;
        return repository.save(oA);
    }

    @Override
    public TipoProyecto buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List buscarTodos(Boolean vigencia) {
        return repository.findByVigencia( vigencia);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }


}
