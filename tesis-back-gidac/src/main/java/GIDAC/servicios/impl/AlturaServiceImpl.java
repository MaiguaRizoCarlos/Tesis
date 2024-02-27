package GIDAC.servicios.impl;



import GIDAC.modelo.Altura;
import GIDAC.repositorios.AlturaRepository;
import GIDAC.servicios.AlturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AlturaServiceImpl implements AlturaService {

    @Autowired
    private AlturaRepository repository;

    @Override
    public Altura guardar(Object objeto) {
        Altura oA=(Altura) objeto;
        return repository.save(oA);
    }

    @Override
    public Altura buscarPorId(Integer id) {
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

//    @Override
//    public Altura buscarPorAlturaMinimaAlturaMaximaAbreviatura(float alturaMinima, float alturaMaxima, String abreviatura) {
//        return repository.findByAlturaMinimaAndAlturaMaximaAndUnidadMedida_Abreviatura(alturaMinima,alturaMaxima, abreviatura);
//    }

    @Override
    public List<Altura> buscarPorVigenciaAlturaAbreviatura(Boolean vigencia, Double altura, String abreviatura) {
        return repository.findByVigenciaAndAlturaAndUnidadMedida_Abreviatura(vigencia, altura, abreviatura);
    }
    
    @Override
    public List<Object[]>  obtenerAlturasUsadas() {
        return repository.obtenerAlturasUsadas();
    }

    @Override
    public List buscarPorVigencia(Boolean vigencia) {
        return repository.findByVigencia(vigencia);
    }

}
