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
public interface AreaInvestigacionService <T> {
    public T save(T objeto);
    public T findById(Integer id);
    public List<T> findAll();
    public List<T> obtenerAreasInvestigacionVigentes();
    public List<T> obtenerAreasInvestigacionNoVigentes();
    
}
