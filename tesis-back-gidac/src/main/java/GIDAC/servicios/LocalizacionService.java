/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.servicios;


import GIDAC.modelo.Localizacion;
import java.util.List;

/**
 *
 * @author My Notebook
 */
public interface LocalizacionService<T>{
    public T guardar(T objeto);
    public T buscarPorId(Integer id);
    public List<T> buscarTodos();
    public void eliminar(Integer id);
    public T buscarPorPais(String id);
    public T buscarPorProvincia(String id);
    public T buscarPorCanton(String id);
    public T buscarPorParroquia(String id);
    
    public List<Object[]> buscarPorPaisAdmin();
    public List<Object[]> buscarPorProvinciaAdmin(String id);
    public List<Object[]> buscarPorCantonAdmin(String idPais, String idProvincia);
    public List<T> buscarPorParroquiaAdmin(String idPais, String idProvincia, String idCanton);
    
    public List<T> buscarVigencia(Boolean vigencia);
    public List<T> buscarPaises();
    public List<T> buscarPrvincias(String codPais);
    public List<T> buscarCantones(String codPais, String codProvincia);
    public List<T> buscarParroquias(String codPais, String codProvincia, String codCanton);
    
    public List<T> buscarPaisesAdmin(String codPais, Boolean vigencia);
    public List<T> buscarProvinciasAdmin(String codPais, String codProvincia, Boolean vigencia);
    public List<T> buscarCantonesAdmin(String codPais, String codProvincia, String codCanton, Boolean vigencia);
    public List<T> buscarParroquiasAdmin(String codPais, String codProvincia, String codCanton, String codParroquia, Boolean vigencia);
    
}