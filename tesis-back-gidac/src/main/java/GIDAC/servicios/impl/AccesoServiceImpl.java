package GIDAC.servicios.impl;


import GIDAC.modelo.Acceso;
import GIDAC.repositorios.AccesoRepository;
import GIDAC.servicios.AccesoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AccesoServiceImpl implements AccesoService {

    @Autowired
    private AccesoRepository repository;

    
    @Override
    public Acceso save(Object objeto) {
        Acceso oA=(Acceso) objeto;
        return repository.save(oA);
    }
    
    @Override
    public Acceso findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List findAll() {
        return repository.findAll();
    }

    @Override
    public List findAllByRol(String rol) {
        return repository.findByUsuarioRolNombreRol(rol);
    }
}
