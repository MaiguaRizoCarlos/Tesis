package GIDAC.repositorios;

import GIDAC.modelo.Conglomerado;
import GIDAC.modelo.Parcela;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParcelaRepository extends JpaRepository<Parcela ,Integer> {
    List<Parcela> findByVigenciaAndConglomerado(Boolean vigencia, Conglomerado conglomerado);
    Parcela findByCodigoParcelaAndConglomerado(String codigoConglomerado,Conglomerado conglomerado);
    
    @Query(value="  SELECT distinct p.id_parcela" +
"                   FROM parcela p JOIN profundidad_parcela pp ON pp.id_parcela = p.id_parcela" +
"                   WHERE p.id_conglomerado=:idConglomerado AND pp.vigencia=true",
            nativeQuery=true)
    List<Object[]> obtenerParcelasUsadas(@Param("idConglomerado") Integer idConglomerado);
}
