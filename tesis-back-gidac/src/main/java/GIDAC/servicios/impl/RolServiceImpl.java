package GIDAC.servicios.impl;


import GIDAC.modelo.Rol;
import GIDAC.repositorios.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import GIDAC.servicios.RolService;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    
    @Override
    public Rol save(Object objeto) {
        Rol oA=(Rol) objeto;
        return rolRepository.save(oA);
    }
    
    @Override
    public Rol findById(Integer id) {
        return rolRepository.findById(id).orElse(null);
    }

    @Override
    public List findAll() {
        return rolRepository.findAll();
    }
    
    
}
