package GIDAC.servicios.impl;


import GIDAC.modelo.Familia;
import GIDAC.repositorios.FamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import GIDAC.servicios.FamiliaService;

@Service
public class FamiliaServiceImpl implements FamiliaService {

    @Autowired
    private FamiliaRepository repository;

    @Override
    public Familia guardar(Object objeto) {
        Familia oA=(Familia) objeto;
        return repository.save(oA);
    }

    @Override
    public Familia buscarPorId(Integer id) {
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
    public List buscarPorVigencia(Boolean vigencia) {
        return repository.findByVigencia(vigencia);
    }
    
    @Override
    public List<Familia> findAllFinalChildren() {
        return repository.findAllFinalChildren();
    }

    @Override
    public List<Object[]> buscarPadres() {
        return repository.obtenerPadres();
    }

    @Override
    public List<Object[]> buscarHijos(Integer idPadre) {
        return repository.obtenerHijosRecursivos(idPadre);
    }

    @Override
    public List buscarPadresUsuarioComun() {
        return repository.obtenerPadresUsuarioComun();
    }

    @Override
    public List buscarHijosUsuarioComun(Integer idPadre) {
        return repository.obtenerHijosRecursivosUsuarioComun(idPadre);
    }

    @Override
    public List<Object[]>  obtenerPorIdAux(Integer id) {
        return repository.obtenerPorId(id);
    }

    @Override
    public List<Familia> findByIdFamiliaAndVigencia(Integer idFamilia, Boolean vigencia) {
        return repository.findByIdFamiliaAndVigencia(idFamilia, vigencia);
    }

    @Override
    public List<Familia> buscarFamiliaPorPadre(Integer idPadre) {
        return repository.findByFamilia_IdFamiliaAndVigencia(idPadre, true);
    }


}
