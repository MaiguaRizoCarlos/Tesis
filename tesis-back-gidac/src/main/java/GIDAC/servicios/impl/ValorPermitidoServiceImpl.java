
package GIDAC.servicios.impl;



import GIDAC.modelo.ValorPermitido;
import GIDAC.repositorios.ValorPermitidoRepository;
import GIDAC.servicios.ValorPermitidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ValorPermitidoServiceImpl implements ValorPermitidoService {

    @Autowired
    private ValorPermitidoRepository repository;

    @Override
    public ValorPermitido guardar(Object objeto) {
        ValorPermitido oA=(ValorPermitido) objeto;
        return repository.save(oA);
    }

    @Override
    public ValorPermitido buscarPorId(Integer id) {
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
    public List obtenerPorVariableUnidadMedida(Integer id) {
        return repository.findByVigenciaAndVariableUnidadMedidaIdVariableUnidadMedidaAndVariableUnidadMedidaVigencia(true,id,true);
    }
    
    @Override
    public List listarPorVariable(Integer id) {
        return repository.findByVigenciaAndVariableUnidadMedidaVariableIdVariable(true,id);
    }

    @Override
    public Object obtenerPorVariableAndUnidadMedida(Integer idVariable, Integer idMedida) {
        return repository.findByVariableUnidadMedidaVariableIdVariableAndVariableUnidadMedidaUnidadMedidaIdUnidadMedida(idVariable, idMedida);
    }

    @Override
    public Object obtenerPorMaxMinPerVariableUnidadMedida(Float max, Float min, String per, Integer idVariable, Integer idMedida) {
        return repository.findByValorMaximoAndValorMinimoAndValorPermitidoAndVariableUnidadMedidaVariableIdVariableAndVariableUnidadMedidaUnidadMedidaIdUnidadMedida(max, min, per, idVariable, idMedida);
    }
    
    @Override
    public Object obtenerPorPerVariableUnidadMedida(String per, Integer idVariable, Integer idMedida) {
        return repository.findByValorPermitidoAndVariableUnidadMedidaVariableIdVariableAndVariableUnidadMedidaUnidadMedidaIdUnidadMedida( per, idVariable, idMedida);
    }

    @Override
    public List listarPorVigenciaVariableUnidadMedida(Boolean vigencia, Integer idVariable, Integer idUnidadMedida) {
        return repository.findByVigenciaAndVariableUnidadMedidaVariableIdVariableAndVariableUnidadMedidaUnidadMedidaIdUnidadMedida(vigencia, idVariable, idUnidadMedida);
    }

}
