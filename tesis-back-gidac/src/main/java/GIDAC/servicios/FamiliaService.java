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
public interface FamiliaService<T>{
    public T guardar(T objeto);
    public T buscarPorId(Integer id);
    public List<T>  buscarPorVigencia(Boolean vigencia);
    public List<T>  buscarFamiliaPorPadre(Integer idPadre);
    public List<T> findByIdFamiliaAndVigencia(Integer idFamilia, Boolean vigencia);
    public List<T>  findAllFinalChildren();
    public List<T> buscarTodos();
    public List<T> buscarPadres();
    public List<Object[]> buscarHijos(Integer idPadre);
    public List<Object[]> buscarPadresUsuarioComun();
    public List<T> buscarHijosUsuarioComun(Integer idPadre);
    public List<Object[]> obtenerPorIdAux(Integer id);
    public void eliminar(Integer id);
}
