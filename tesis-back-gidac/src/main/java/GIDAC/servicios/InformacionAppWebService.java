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
public interface InformacionAppWebService<T> {
    public List<T> findAll();
    public T save(T objeto);
    
    public T findById(Integer id);
    public T findByVigencia();
}
