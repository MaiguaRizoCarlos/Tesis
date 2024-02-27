package GIDAC.servicios.impl;



import GIDAC.modelo.AreaInvestigacion;
import GIDAC.modelo.Usuario;
import GIDAC.repositorios.AreaInvestigacionRepository;
import GIDAC.servicios.AreaInvestigacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AreaIvestigacionServiceImpl implements AreaInvestigacionService {

    @Autowired
    private AreaInvestigacionRepository areaInvestigacionRepository;

    
    @Override
    public AreaInvestigacion save(Object objeto) {
        AreaInvestigacion oA=(AreaInvestigacion) objeto;
        return areaInvestigacionRepository.save(oA);
    }
    
    @Override
    public AreaInvestigacion findById(Integer id) {
        return areaInvestigacionRepository.findById(id).orElse(null);
    }

    @Override
    public List<AreaInvestigacion> findAll() {
        return areaInvestigacionRepository.findAll();
    }

    @Override
    public List obtenerAreasInvestigacionVigentes() {
        return areaInvestigacionRepository.findByVigencia(true);
    }
    
    @Override
    public List obtenerAreasInvestigacionNoVigentes() {
        return areaInvestigacionRepository.findByVigencia(false);
    }
    

    
}
