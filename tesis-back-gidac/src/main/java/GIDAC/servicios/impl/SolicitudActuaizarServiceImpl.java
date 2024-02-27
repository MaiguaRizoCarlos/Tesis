package GIDAC.servicios.impl;

import GIDAC.modelo.SolicitudActualizarDato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import GIDAC.repositorios.SolicitudActualizarRepository;
import GIDAC.servicios.SolicitudActualizarService;

@Service
public class SolicitudActuaizarServiceImpl implements SolicitudActualizarService {

    @Autowired
    private SolicitudActualizarRepository repository;

    
    @Override
    public SolicitudActualizarDato save(Object objeto) {
        SolicitudActualizarDato oA=(SolicitudActualizarDato) objeto;
        return repository.save(oA);
    }
    
    @Override
    public SolicitudActualizarDato findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
    
    @Override
    public List<SolicitudActualizarDato> listarPorEstadoAndIdProyecto(String estado, Integer idProyecto) {
        return repository.findByEstadoSolicitudActualizar_NombreEstadoSolicitudAndGrupoInvestigacionProyectoInvestigacionIdProyecto(estado, idProyecto);
    }
}
