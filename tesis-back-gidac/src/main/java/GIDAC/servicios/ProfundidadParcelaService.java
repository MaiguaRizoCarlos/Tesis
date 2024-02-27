/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.servicios;


import GIDAC.modelo.ProfundidadParcela;
import java.util.List;

/**
 *
 * @author My Notebook
 */
public interface ProfundidadParcelaService<T>{
    public T guardar(T objeto);
    public T actualizar(T objeto);
    public List<T> buscarTodos();
    public T buscarPorParcelaProfundidad(Integer idParcela, Integer idProfundidad);
    public List<T> buscarPorVigenciaParcela(Boolean vigencia, Integer idParcela);
    public List<T> buscarPorParcela(Integer idParcela);
    public void eliminar(Integer idProfundidad, Integer idParcela);
    List<Object[]> obtenerProfundiadParcelaUsados(Integer id);
}
