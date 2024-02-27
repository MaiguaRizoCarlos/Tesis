/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.servicios;


import java.util.List;

/**
 *
 * @author My Notebook
 */
public interface UnidadMedidaVariableService<T>{
    public T guardar(T objeto);
    public T buscarPorId(Integer id);
    public List<T> buscarPorUnidadMedidaAndVariableAndVigencia(Integer idUnidadMedida, Integer idVariable, Boolean vigencia);
    public List<T> buscarPorUnidadMedidaAndVariable(Integer idUnidadMedida, Integer idVariable);
    public List<T> buscarTodos(Boolean vigencia);
    public List<T> buscarVigenciaVariableVigencia(Boolean vigencia, Boolean vigenciaVariable);
    public List<T> findByVigenciaAndVariableIdVariableAndVariableVigenciaAndUnidadMedidaVigencia(Boolean vigencia, Integer idVariable, Boolean vigVariable, Boolean vigUnidadMedida);
    public List<T> findByVariableIdVariable(Integer idVariable);
    public void eliminar(Integer id);
}

