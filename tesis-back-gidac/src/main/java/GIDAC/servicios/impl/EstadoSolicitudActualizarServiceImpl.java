package GIDAC.servicios.impl;


import GIDAC.modelo.EstadoSolicitudActualizar;
import GIDAC.repositorios.EstadoSolicitudActualizarRepository;
import GIDAC.servicios.EstadoSolicitudActualizarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EstadoSolicitudActualizarServiceImpl implements EstadoSolicitudActualizarService {

    @Autowired
    private EstadoSolicitudActualizarRepository repository;

    @Override
    public EstadoSolicitudActualizar guardar(Object objeto) {
        EstadoSolicitudActualizar oA=(EstadoSolicitudActualizar) objeto;
        return repository.save(oA);
    }

    @Override
    public EstadoSolicitudActualizar buscarPorId(Integer id) {
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
