/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.servicios;


import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author My Notebook
 */
public interface TiempoEdicionDatoService <T> {
    public T save(T objeto);
    public T update(T objeto);
    public T findById(Integer id);
    public void delete(Integer id);
    public void restore(Integer id);
    public List<T> findAll();
    public List<T>  findByVigencia(Boolean vigencia);
}
