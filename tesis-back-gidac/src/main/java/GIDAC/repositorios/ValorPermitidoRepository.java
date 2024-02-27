package GIDAC.repositorios;

import GIDAC.modelo.ValorPermitido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ValorPermitidoRepository extends JpaRepository<ValorPermitido,Integer> {
    
    List<ValorPermitido> findByVigenciaAndVariableUnidadMedidaIdVariableUnidadMedidaAndVariableUnidadMedidaVigencia(Boolean vigencia1, Integer id, Boolean vigencia2);
    
    List<ValorPermitido> findByVigenciaAndVariableUnidadMedidaVariableIdVariableAndVariableUnidadMedidaUnidadMedidaIdUnidadMedida(Boolean vigencia1, Integer idVariable, Integer idUnidadMedida);
    
    ValorPermitido findByVariableUnidadMedidaVariableIdVariableAndVariableUnidadMedidaUnidadMedidaIdUnidadMedida(Integer idVariable, Integer idMedida);
    
    ValorPermitido findByValorMaximoAndValorMinimoAndValorPermitidoAndVariableUnidadMedidaVariableIdVariableAndVariableUnidadMedidaUnidadMedidaIdUnidadMedida(Float max, Float min, String per, Integer idVariable, Integer idMedida);
    
    ValorPermitido findByValorPermitidoAndVariableUnidadMedidaVariableIdVariableAndVariableUnidadMedidaUnidadMedidaIdUnidadMedida(String per, Integer idVariable, Integer idMedida);
    
    List<ValorPermitido> findByVigenciaAndVariableUnidadMedidaVariableIdVariable(Boolean vigencia1, Integer id);
    
    
}
