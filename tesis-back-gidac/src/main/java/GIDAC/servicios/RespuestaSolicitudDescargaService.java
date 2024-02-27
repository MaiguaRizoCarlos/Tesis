/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.servicios;


import GIDAC.modelo.RespuestaSolicitudDescarga;
import java.util.List;

/**
 *
 * @author My Notebook
 */
public interface RespuestaSolicitudDescargaService<T>{
    public T guardar(T objeto);
    public T buscarPorId(Integer id);
    public List<T> buscarTodos();
    public void eliminar(Integer id);
    public List<T> listarPorEstadoDescarga(String estado, Integer idAreaInvestigacion);
}
