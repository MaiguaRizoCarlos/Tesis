/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.servicios;


import GIDAC.modelo.Parcela;
import java.util.List;

/**
 *
 * @author My Notebook
 */
public interface ParcelaService<T>{
    public T guardar(T objeto);
    public T buscarPorId(Integer id);
    public T buscarPorCodigoParcelaConglomerado(String codigoParcela, Integer idConglomerado);
    public List<T> buscarTodos();
    public void eliminar(Integer id);
    public List<T> buscarPorConglomerado(Integer id);
    List<Object[]> obtenerParcelasUsadas(Integer id);
}
