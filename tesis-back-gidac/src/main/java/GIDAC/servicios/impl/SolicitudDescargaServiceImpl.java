package GIDAC.servicios.impl;


import GIDAC.modelo.EstadoSolicitudDescarga;
import GIDAC.modelo.SolicitudDescarga;
import GIDAC.repositorios.EstadoSolicitudDescargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import GIDAC.repositorios.SolicitudDescargaRepository;
import GIDAC.servicios.SolicitudDescargaService;

@Service
public class SolicitudDescargaServiceImpl implements SolicitudDescargaService {

    @Autowired
    private SolicitudDescargaRepository solicitudAccesoRepository;
    
    @Autowired
    private EstadoSolicitudDescargaRepository estadoSolicitudDescargaRepository;

    
    @Override
    public SolicitudDescarga save(Object objeto) {
        SolicitudDescarga oA=(SolicitudDescarga) objeto;
        return solicitudAccesoRepository.save(oA);
    }
    
    @Override
    public SolicitudDescarga findById(Integer id) {
        return solicitudAccesoRepository.findById(id).orElse(null);
    }
    
    @Override
    public List<SolicitudDescarga> listarPorEstado(String estado) {
        return solicitudAccesoRepository.findByEstadoSolicitudDescarga_NombreEstadoDescarga(estado);
    }
    
    @Override
    public List<SolicitudDescarga> listarPorEstadoAndIdProyecto(String estado, Integer idProyecto) {
        return solicitudAccesoRepository.findByEstadoSolicitudDescarga_NombreEstadoDescargaAndProyectoInvestigacionIdProyecto(estado,idProyecto);
    }
}
