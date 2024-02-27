package GIDAC.servicios.impl;




import GIDAC.modelo.Area;
import GIDAC.repositorios.AreaRepository;
import GIDAC.servicios.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaRepository repository;

    @Override
    public Area guardar(Object objeto) {
        Area oA=(Area) objeto;
        return repository.save(oA);
    }

    @Override
    public Area buscarPorId(Integer id) {
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
    public Area buscarPorAreaAbreviatura(float area, String abreviatura) {
        return repository.findByAreaAndUnidadMedida_Abreviatura(area, abreviatura);
    }

    @Override
    public List<Object[]>  obtenerAreasUsadas() {
        return repository.obtenerAreasUsadas();
    }

    @Override
    public List buscarPorVigencia(Boolean vigencia) {
        return repository.findByVigencia(vigencia);
    }
    
}
