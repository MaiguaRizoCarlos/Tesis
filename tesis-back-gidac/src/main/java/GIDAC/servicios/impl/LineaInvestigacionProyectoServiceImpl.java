package GIDAC.servicios.impl;



import GIDAC.modelo.AreaInvestigacion;
import GIDAC.modelo.LineaInvestigacionProyecto;
import GIDAC.repositorios.AreaInvestigacionRepository;
import GIDAC.repositorios.LineaInvestigacionProyectoRepository;
import GIDAC.servicios.AreaInvestigacionProyectoService;
import GIDAC.servicios.AreaInvestigacionService;
import GIDAC.servicios.LineaInvestigacionProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class LineaInvestigacionProyectoServiceImpl implements LineaInvestigacionProyectoService {

     @Autowired
    private LineaInvestigacionProyectoRepository repository;

    @Override
    public LineaInvestigacionProyecto guardar(Object objeto) {
        LineaInvestigacionProyecto oA=(LineaInvestigacionProyecto) objeto;
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
    public LineaInvestigacionProyecto buscarPorVigenciaProyectoLineaInvestigacion(Integer idProyecto, Integer idLinea) {
        return repository.findByVigenciaAndProyectoInvestigacionIdProyectoAndLineaInvestigacionIdLineaInvestigacion(Boolean.TRUE, idProyecto, idLinea);
    }

    @Override
    public LineaInvestigacionProyecto buscarPorProyectoLineaInvestigacion(Integer idProyecto, Integer idLinea) {
        return repository.findByProyectoInvestigacionIdProyectoAndLineaInvestigacionIdLineaInvestigacion( idProyecto, idLinea);
    }
    
}
