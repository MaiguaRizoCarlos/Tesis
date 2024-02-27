package GIDAC.servicios.impl;



import GIDAC.modelo.SectorImpacto;
import GIDAC.repositorios.SectorImpactoRepository;
import GIDAC.servicios.SectorImpactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SectorImpactoServiceImpl implements SectorImpactoService {

    @Autowired
    private SectorImpactoRepository repository;

    @Override
    public SectorImpacto guardar(Object objeto) {
        SectorImpacto oA=(SectorImpacto) objeto;
        return repository.save(oA);
    }

    @Override
    public SectorImpacto buscarPorId(Integer id) {
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
