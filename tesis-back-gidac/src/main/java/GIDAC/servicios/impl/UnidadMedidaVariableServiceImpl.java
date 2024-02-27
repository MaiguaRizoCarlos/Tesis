package GIDAC.servicios.impl;


import GIDAC.modelo.VariableUnidadMedida;
import GIDAC.repositorios.UnidadMedidaVariableRepository;
import GIDAC.servicios.UnidadMedidaVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UnidadMedidaVariableServiceImpl implements UnidadMedidaVariableService {

    @Autowired
    private UnidadMedidaVariableRepository repository;

    @Override
    public VariableUnidadMedida guardar(Object objeto) {
        VariableUnidadMedida oA=(VariableUnidadMedida) objeto;
        return repository.save(oA);
    }

//    @Override
//    public List buscarPorProyecto(Integer idProyecto) {
//        return repository.findByCatalogoEspochProyectoInvestigacionIdProyecto(idProyecto);
//    }

    @Override
    public Object buscarPorId(Integer id) {
        return repository.findByIdVariableUnidadMedidaAndVigencia(id, true);
    }

    @Override
    public List buscarTodos(Boolean vigencia) {
        return repository.findByVigencia(vigencia);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List buscarPorUnidadMedidaAndVariableAndVigencia(Integer idUnidadMedida, Integer idVariable, Boolean vigencia) {
        return repository.findByUnidadMedidaIdUnidadMedidaAndVariableIdVariableAndVigencia(idUnidadMedida, idVariable, vigencia);
    }
    
    @Override
    public List buscarPorUnidadMedidaAndVariable(Integer idUnidadMedida, Integer idVariable) {
        return repository.findByUnidadMedidaIdUnidadMedidaAndVariableIdVariable(idUnidadMedida, idVariable);
    }

    @Override
    public List buscarVigenciaVariableVigencia(Boolean vigencia, Boolean vigenciaVariable) {
        return repository.findByVigenciaAndVariableVigencia(vigencia, vigenciaVariable);
    }

    @Override
    public List findByVigenciaAndVariableIdVariableAndVariableVigenciaAndUnidadMedidaVigencia(Boolean vigencia, Integer idVariable, Boolean vigVariable, Boolean vigUnidadMedida) {
        return repository.findByVigenciaAndVariableIdVariableAndVariableVigenciaAndUnidadMedidaVigencia(vigencia, idVariable, vigVariable, vigUnidadMedida);
    }

    @Override
    public List findByVariableIdVariable(Integer idVariable) {
        return repository.findByVariableIdVariable(idVariable);
    }


}
