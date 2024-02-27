package GIDAC.servicios.impl;


import GIDAC.modelo.RespuestaSolicitudActualizar;
import GIDAC.repositorios.RespuestaSolicitudActualizarRepository;
import GIDAC.servicios.RespuestaSolicitudActualizarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RespuestaSolicitudActualizarServiceImpl implements RespuestaSolicitudActualizarService {

    @Autowired
    private RespuestaSolicitudActualizarRepository repository;

    @Override
    public RespuestaSolicitudActualizar guardar(Object objeto) {
        RespuestaSolicitudActualizar oA=(RespuestaSolicitudActualizar) objeto;
        return repository.save(oA);
    }

    @Override
    public RespuestaSolicitudActualizar buscarPorId(Integer id) {
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
    public List<RespuestaSolicitudActualizar> listarPorEstadoActualizar(String estado, Integer idProyecto) {
        return repository.findBySolicitudActualizarDatoEstadoSolicitudActualizarNombreEstadoSolicitudAndSolicitudActualizarDatoGrupoInvestigacionProyectoInvestigacionIdProyecto(estado, idProyecto);
    }
}
