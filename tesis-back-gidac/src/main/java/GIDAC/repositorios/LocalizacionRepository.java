package GIDAC.repositorios;

import GIDAC.modelo.Localizacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocalizacionRepository extends JpaRepository<Localizacion,Integer> {
    List<Localizacion> findByCodigoPais(String codigoPais);
    List<Localizacion> findByCodigoProvincia(String codigoProvincia);
    List<Localizacion> findByCodigoCanton(String codigoCanton);
    List<Localizacion> findByCodigoParroquia(String codigoParroquia);
    
    List<Localizacion> findByCodigoPaisAndVigencia(String codigoPais, Boolean vigencia);
    List<Localizacion> findByCodigoPaisAndCodigoProvinciaAndVigencia(String codigoPais, String codigoProvincia, Boolean vigencia);
    List<Localizacion> findByCodigoPaisAndCodigoProvinciaAndCodigoCantonAndVigencia(String codigoPais, String codigoProvincia, String codigoCanton, Boolean vigencia);
    List<Localizacion> findByCodigoPaisAndCodigoProvinciaAndCodigoCantonAndCodigoParroquiaAndVigencia(String codigoPais, String codigoProvincia, String codigoCanton, String codigoParroquia, Boolean vigencia);
    
    
    List<Localizacion> findByVigencia(Boolean vigencia);
    
    @Query(value="SELECT DISTINCT (l.codigo_pais), l.nombre_pais " +
                " FROM localizacion l"+
                " WHERE l.vigencia = true",
            nativeQuery=true)
    List<Object[]> obtenerPaises();
    
    @Query(value="SELECT DISTINCT (l.codigo_provincia), l.nombre_provincia " +
                " FROM localizacion l"+
                " WHERE l.codigo_pais = :idPais AND l.vigencia = true",
            nativeQuery=true)
    List<Object[]> obtenerProvincias(@Param("idPais") String idP);
    
     @Query(value="SELECT DISTINCT (l.codigo_canton), l.nombre_canton " +
                " FROM localizacion l"+
                " WHERE l.codigo_pais= :idPais AND l.codigo_provincia= :idProv AND l.vigencia = true",
            nativeQuery=true)
    List<Object[]> obtenerCantones(@Param("idPais") String idPais, @Param("idProv") String idProv);
    
    
    
}
