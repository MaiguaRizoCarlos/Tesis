package GIDAC.servicios.impl;



import GIDAC.modelo.LineaInvestigacion;
import GIDAC.repositorios.LineaInvestigacionRepository;
import GIDAC.servicios.LineaInvestigacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class LineaInvestigacionServiceImpl implements LineaInvestigacionService {

    @Autowired
    private LineaInvestigacionRepository repository;

    @Override
    public LineaInvestigacion guardar(Object objeto) {
        LineaInvestigacion oA=(LineaInvestigacion) objeto;
        return repository.save(oA);
    }

    @Override
    public LineaInvestigacion buscarPorId(Integer id) {
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
