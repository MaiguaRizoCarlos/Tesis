/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.repositorios;




import GIDAC.modelo.InformacionEcoAndes;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author My Notebook
 */
public interface InformacionAppWebRepository extends CrudRepository<InformacionEcoAndes, Integer>{
    List<InformacionEcoAndes> findByVigencia(Boolean vigenica);
}
