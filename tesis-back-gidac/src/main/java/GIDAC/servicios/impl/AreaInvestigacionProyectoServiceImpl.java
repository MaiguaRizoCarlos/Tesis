package GIDAC.servicios.impl;



import GIDAC.modelo.AreaInvestigacion;
import GIDAC.modelo.AreaInvestigacionProyecto;
import GIDAC.repositorios.AreaInvestigacionProyectoRepository;
import GIDAC.repositorios.AreaInvestigacionRepository;
import GIDAC.servicios.AreaInvestigacionProyectoService;
import GIDAC.servicios.AreaInvestigacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AreaInvestigacionProyectoServiceImpl implements AreaInvestigacionProyectoService {

     @Autowired
    private AreaInvestigacionProyectoRepository repository;

    @Override
    public AreaInvestigacionProyecto guardar(Object objeto) {
        AreaInvestigacionProyecto oA=(AreaInvestigacionProyecto) objeto;
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
    public Object buscarPorVigenciaProyectoAreaInvestigacion(Integer idProyecto, Integer idArea) {
        return repository.findByVigenciaAndProyectoInvestigacionIdProyectoAndAreaInvestigacionIdAreaInvestigacion(Boolean.TRUE, idProyecto, idArea);
    }

    @Override
    public Object buscarPorProyectoAreaInvestigacion(Integer idProyecto, Integer idarea) {
        return repository.findByProyectoInvestigacionIdProyectoAndAreaInvestigacionIdAreaInvestigacion(idProyecto, idarea);
    }
    
}
