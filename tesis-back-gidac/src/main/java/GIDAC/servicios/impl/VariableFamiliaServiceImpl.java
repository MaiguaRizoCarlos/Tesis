package GIDAC.servicios.impl;


import GIDAC.modelo.VariableFamilia;
import GIDAC.modelo.VariableFamiliaId;
import GIDAC.modelo.VariableUnidadMedida;
import GIDAC.repositorios.UnidadMedidaVariableRepository;
import GIDAC.repositorios.VariableFamiliaRepository;
import GIDAC.servicios.VariableFamiliaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class VariableFamiliaServiceImpl implements VariableFamiliaService {

    @Autowired
    private VariableFamiliaRepository repository;

    @Override
    public VariableFamilia guardar(Object objeto) {
        VariableFamilia oA=(VariableFamilia) objeto;
        return repository.save(oA);
    }

//    @Override
//    public List buscarPorProyecto(Integer idProyecto) {
//        return repository.findByCatalogoEspochProyectoInvestigacionIdProyecto(idProyecto);
//    }

    @Override
    public Object buscarPorId(String idVariable, Integer idFamilia, Boolean vigencia) {
        return repository.findByIdVariableAndIdFamiliaAndVigencia(idVariable, idFamilia, vigencia);
    }

    @Override
    public List buscarTodos(Boolean vigencia) {
        return repository.findByVigencia(vigencia);
    }

    @Override
    public void eliminar(Integer idVariable, Integer idFamilia) {
        VariableFamiliaId variableFamiliaId =new VariableFamiliaId();
        variableFamiliaId.setIdFamilia(idFamilia);
        variableFamiliaId.setIdVariable(idVariable);
        repository.deleteById(variableFamiliaId);
    }

    @Override
    public List<Object[]>  listarFamiliasDifusion() {
        return repository.obtenerFamiliasDisponibles();
    }

    @Override
    public List<VariableFamilia>  buscarPorVariable(Integer idVariable, Boolean vigencia) {
        return repository.findByVariableIdVariableAndVigencia(idVariable, vigencia);
    }
    
    @Override
    public VariableFamilia buscarPorVariableFamilia(Integer idVariable, Integer idFamilia, Boolean vigencia) {
        return repository.findByVariableIdVariableAndFamiliaIdFamiliaAndVigencia(idVariable, idFamilia, vigencia);
    }
    
    @Override
    public VariableFamilia buscarPorVariableFamilia(Integer idVariable, Integer idFamilia) {
        return repository.findByVariableIdVariableAndFamiliaIdFamilia(idVariable, idFamilia);
    }

}
