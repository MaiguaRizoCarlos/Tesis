package GIDAC.servicios.impl;


import GIDAC.modelo.Organizacion;
import GIDAC.repositorios.OrganizacionRepository;
import GIDAC.servicios.OrganizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OrganizacionServiceImpl implements OrganizacionService {

    @Autowired
    private OrganizacionRepository repository;

    @Override
    public Organizacion guardar(Object objeto) {
        Organizacion oA=(Organizacion) objeto;
        return repository.save(oA);
    }

    @Override
    public Organizacion buscarPorId(Integer id) {
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

    @Override
    public List buscarPorVigencia(Boolean vigencia) {
        return repository.findByVigencia(vigencia);
    }

    @Override
    public Object findByVigenciaAndIdOrganizacion(Boolean vigencia, Integer idOrganizacion) {
        return repository.findByVigenciaAndIdOrganizacion(vigencia, idOrganizacion);
    }

}
