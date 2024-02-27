package GIDAC.servicios.impl;


import GIDAC.modelo.Conglomerado;
import GIDAC.modelo.Parcela;
import GIDAC.repositorios.ParcelaRepository;
import GIDAC.servicios.ParcelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ParcelaServiceImpl implements ParcelaService {

    @Autowired
    private ParcelaRepository repository;

    @Override
    public Parcela guardar(Object objeto) {
        Parcela oA=(Parcela) objeto;
        return repository.save(oA);
    }

    @Override
    public Parcela buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List buscarTodos() {
        return repository.findAll();
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List buscarPorConglomerado(Integer id) {
        Conglomerado conglomerado=new Conglomerado();
        conglomerado.setIdConglomerado(id);
        return repository.findByVigenciaAndConglomerado(true, conglomerado);
    }

    @Override
    public Parcela buscarPorCodigoParcelaConglomerado(String codigoParcela, Integer idConglomerado) {
        Conglomerado conglomerado=new Conglomerado();
        conglomerado.setIdConglomerado(idConglomerado);
        return repository.findByCodigoParcelaAndConglomerado(codigoParcela, conglomerado);
    }

    @Override
    public List<Object[]> obtenerParcelasUsadas(Integer id) {
        return repository.obtenerParcelasUsadas(id);
    }
}
