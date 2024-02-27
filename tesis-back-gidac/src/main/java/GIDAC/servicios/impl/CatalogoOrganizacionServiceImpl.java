package GIDAC.servicios.impl;


import GIDAC.modelo.CatalogoOrganizacion;
import GIDAC.repositorios.CatalogoOrganizacionRepository;
import GIDAC.servicios.CatalogoOrganizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CatalogoOrganizacionServiceImpl implements CatalogoOrganizacionService {

    @Autowired
    private CatalogoOrganizacionRepository repository;

    @Override
    public CatalogoOrganizacion guardar(Object objeto) {
        CatalogoOrganizacion oA=(CatalogoOrganizacion) objeto;
        return repository.save(oA);
    }

    @Override
    public CatalogoOrganizacion buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }
    
     @Override
    public CatalogoOrganizacion buscarPorCodigoOrganizacion(String codigo) {
        return repository.findByCodigoVariableOrganizacion(codigo);
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
    public List buscarPorVigenciaAndOrganizacion(Boolean vigencia, Integer idOrganizacion, Boolean vigenciaOrganizacion) {
        return repository.findByVigenciaAndOrganizacionIdOrganizacionAndOrganizacionVigencia(vigencia, idOrganizacion, vigenciaOrganizacion);
    }
    
    @Override
    public List findByVigenciaAndVariableIdVariableOrganizacionVigencia(Boolean vigencia, Integer idVariable, Boolean vigenciaOrganizacion) {
        return repository.findByVigenciaAndVariableIdVariableAndOrganizacionVigencia(vigencia, idVariable, vigenciaOrganizacion);
    }

    @Override
    public List findByVigenciaAndOrganizacionIdOrganizacion(Boolean vigencia, Integer id) {
        return repository.findByVigenciaAndOrganizacionIdOrganizacion(vigencia, id);
    }
    
    
}
