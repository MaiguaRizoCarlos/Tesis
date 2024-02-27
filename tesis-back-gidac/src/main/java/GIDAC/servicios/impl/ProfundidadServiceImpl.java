package GIDAC.servicios.impl;


import GIDAC.modelo.UnidadMedida;
import GIDAC.modelo.Profundidad;
import GIDAC.repositorios.ProfundidadRepository;
import GIDAC.servicios.ProfundidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProfundidadServiceImpl implements ProfundidadService {

    @Autowired
    private ProfundidadRepository repository;

    @Override
    public Profundidad guardar(Object objeto) {
        Profundidad oA=(Profundidad) objeto;
        return repository.save(oA);
    }

    @Override
    public Profundidad buscarPorId(Integer id) {
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
    public List buscarPorMedida(Integer id) {
        UnidadMedida medida=new UnidadMedida();
        medida.setIdUnidadMedida(id);
        return repository.findByUnidadMedida(medida);
    }

    @Override
    public Object buscarPorProfundidadMinimaProfundidadMaximaAbreviatura(Double profundidadMinima, Double profundidadMaxima, String abreviatura) {
        return repository.findByProfundidadMinimaAndProfundidadMaximaAndUnidadMedida_Abreviatura(profundidadMinima, profundidadMaxima, abreviatura);
    }


    @Override
    public List<Object[]>  obtenerProfundidadesUsadas() {
        return repository.obtenerProfundidadesUsadas();
    }
    
    @Override
    public List buscarPorVigencia(Boolean vigencia) {
        return repository.findByVigencia(vigencia);
    }
}
