package GIDAC.repositorios;

import GIDAC.modelo.Familia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FamiliaRepository extends JpaRepository<Familia,Integer> {
    List<Familia> findByIdFamiliaAndVigencia(Integer idFamilia, Boolean vigenica);
    List<Familia> findByFamilia_IdFamiliaAndVigencia(Integer idPadre, Boolean vigencia);
    
    List<Familia> findByVigencia(Boolean vigenica);
    //@Query(value = "SELECT f FROM Familia f WHERE f.idFamilia NOT IN (SELECT DISTINCT f2.familia.idFamilia FROM Familia f2 WHERE f2.familia.idFamilia IS NOT NULL)")
    @Query("SELECT f FROM Familia f WHERE f.vigencia = true AND NOT EXISTS (SELECT 1 FROM Familia f2 WHERE f2.familia = f AND f2.vigencia = true)")
    List<Familia> findAllFinalChildren();
    
    
    @Query(value = "SELECT f.id_familia, f.codigo, f.descripcion, f.id_padre " +
                   " FROM familia f" +
                   " WHERE f.id_familia = :idPadre AND f.vigencia=true",
                    nativeQuery=true)
    List<Object[]> obtenerPorId(@Param("idPadre") Integer idPadre);
    
    @Query(value = "SELECT f.id_familia, f.codigo, f.descripcion, f.id_padre " +
                   " FROM familia f" +
                   " WHERE f.id_padre IS null AND f.vigencia=true",
                    nativeQuery=true)
    List<Object[]> obtenerPadres();
    
     @Query(value = "SELECT f.id_familia, f.codigo, f.descripcion, f.id_padre " +
                   " FROM familia f" +
                   " WHERE f.id_padre = :idPadre AND f.vigencia=true",
                    nativeQuery=true)
    List<Object[]> obtenerHijosRecursivos(@Param("idPadre") Integer idPadre);
    
    @Query(value = "SELECT f.* " +
                   " FROM familia f" +
                   " WHERE f.id_padre IS null AND f.vigencia=true",
                    nativeQuery=true)
    List<Familia> obtenerPadresUsuarioComun();
    
     @Query(value = "SELECT f.* " +
                   " FROM familia f" +
                   " WHERE f.id_padre = :idPadre AND f.vigencia=true",
                    nativeQuery=true)
    List<Familia> obtenerHijosRecursivosUsuarioComun(@Param("idPadre") Integer idPadre);
    
    
    
    
}
