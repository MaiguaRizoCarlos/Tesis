package GIDAC.servicios.impl;


import GIDAC.modelo.EstadoSolicitudDescarga;
import GIDAC.repositorios.EstadoSolicitudDescargaRepository;
import GIDAC.servicios.EstadoSolicitudDescargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EstadoSolicitudDescargaServiceImpl implements EstadoSolicitudDescargaService {
    @Autowired
    private EstadoSolicitudDescargaRepository repository;

    @Override
    public EstadoSolicitudDescarga guardar(Object objeto) {
        EstadoSolicitudDescarga oA=(EstadoSolicitudDescarga) objeto;
        return repository.save(oA);
    }

    @Override
    public EstadoSolicitudDescarga buscarPorId(Integer id) {
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

}
