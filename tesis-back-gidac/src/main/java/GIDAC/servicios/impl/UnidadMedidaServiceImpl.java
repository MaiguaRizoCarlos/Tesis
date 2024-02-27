package GIDAC.servicios.impl;


import GIDAC.modelo.UnidadMedida;
import GIDAC.modelo.Rol;
import GIDAC.repositorios.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import GIDAC.repositorios.UnidadMedidaRepository;
import GIDAC.servicios.UnidadMedidaService;

@Service
public class UnidadMedidaServiceImpl implements UnidadMedidaService {

    @Autowired
    private UnidadMedidaRepository repository;

    @Override
    public UnidadMedida guardar(Object objeto) {
        UnidadMedida oA=(UnidadMedida) objeto;
        return repository.save(oA);
    }

    @Override
    public UnidadMedida buscarPorId(Integer id) {
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
    public UnidadMedida buscarPorAbreviatura(String abreviatura) {
        return repository.findByAbreviatura(abreviatura);
    }

    @Override
    public List<Object[]> obtenerUnidadesMedidaUsadas() {
        return repository.obtenerUnidadesMedidaUsadas();
    }
    
    @Override
    public List buscarPorVigencia(Boolean vigencia) {
        return repository.findByVigencia(vigencia);
    }

}
