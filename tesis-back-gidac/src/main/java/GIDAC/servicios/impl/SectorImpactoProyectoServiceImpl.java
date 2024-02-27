package GIDAC.servicios.impl;



import GIDAC.modelo.AreaInvestigacion;
import GIDAC.modelo.SectorImpactoProyecto;
import GIDAC.repositorios.AreaInvestigacionRepository;
import GIDAC.repositorios.SectorImpactoProyectoRepository;
import GIDAC.servicios.AreaInvestigacionProyectoService;
import GIDAC.servicios.AreaInvestigacionService;
import GIDAC.servicios.SectorImpactoProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SectorImpactoProyectoServiceImpl implements SectorImpactoProyectoService {

     @Autowired
    private SectorImpactoProyectoRepository repository;

    @Override
    public SectorImpactoProyecto guardar(Object objeto) {
        SectorImpactoProyecto oA=(SectorImpactoProyecto) objeto;
        return repository.save(oA);
    }


    @Override
    public List buscarTodos() {
        return repository.findAll();
    }

    @Override
    public List buscarPorProyecto(Integer id) {
        return repository.findByVigenciaAndProyectoInvestigacionIdProyecto(Boolean.TRUE, id);
    }

    @Override
    public Object buscarPorVigenciaProyectoSectorImpacto(Integer idProyecto, Integer idSector) {
        return repository.findByVigenciaAndProyectoInvestigacionIdProyectoAndSectorImpactoIdSectorImpacto(Boolean.TRUE, idProyecto, idSector);
    }

    @Override
    public Object buscarPorProyectoSectorImpacto(Integer idProyecto, Integer idSector) {
        return repository.findByProyectoInvestigacionIdProyectoAndSectorImpactoIdSectorImpacto(idProyecto, idSector);
    }
    
}
