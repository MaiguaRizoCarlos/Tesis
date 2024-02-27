package GIDAC.repositorios;

import GIDAC.modelo.VariableUnidadMedida;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadMedidaVariableRepository extends JpaRepository<VariableUnidadMedida, Integer> {
    //List<EquivalenciaVariable> findByCatalogoEspochProyectoInvestigacionIdProyecto( Integer idProyecto);
    List<VariableUnidadMedida> findByVigencia(Boolean vigencia);
    List<VariableUnidadMedida> findByVariableIdVariable(Integer idVariable);
    List<VariableUnidadMedida> findByVigenciaAndVariableIdVariableAndVariableVigenciaAndUnidadMedidaVigencia(Boolean vigencia, Integer idVariable, Boolean vigVariable, Boolean vigUnidadMedida);
    List<VariableUnidadMedida> findByVigenciaAndVariableVigencia(Boolean vigencia, Boolean vigenciaVariable);
    List<VariableUnidadMedida> findByIdVariableUnidadMedidaAndVigencia(Integer id, Boolean vigencia);
    List<VariableUnidadMedida> findByUnidadMedidaIdUnidadMedidaAndVariableIdVariableAndVigencia(Integer idUnidadMedida, Integer idVariable, Boolean vigencia);
    List<VariableUnidadMedida> findByUnidadMedidaIdUnidadMedidaAndVariableIdVariable(Integer idUnidadMedida, Integer idVariable);
}
