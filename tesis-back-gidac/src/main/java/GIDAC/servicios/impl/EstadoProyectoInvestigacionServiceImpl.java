package GIDAC.servicios.impl;


import GIDAC.modelo.EstadoProyectoInvestigacion;
import GIDAC.modelo.Rol;
import GIDAC.repositorios.EstadoProyectoInvestigacionRepository;
import GIDAC.servicios.EstadoProyectoInvestigacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EstadoProyectoInvestigacionServiceImpl implements EstadoProyectoInvestigacionService {

    @Autowired
    private EstadoProyectoInvestigacionRepository estadoProyectoInvestigacionRepository;

    
    @Override
    public EstadoProyectoInvestigacion save(Object objeto) {
        EstadoProyectoInvestigacion oA=(EstadoProyectoInvestigacion) objeto;
        return estadoProyectoInvestigacionRepository.save(oA);
    }
    
    @Override
    public EstadoProyectoInvestigacion findById(Integer id) {
        return estadoProyectoInvestigacionRepository.findById(id).orElse(null);
    }

    @Override
    public List findAll() {
        return estadoProyectoInvestigacionRepository.findAll();
    }
    
    
}
