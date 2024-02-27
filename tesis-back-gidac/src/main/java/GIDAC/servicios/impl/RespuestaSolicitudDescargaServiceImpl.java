package GIDAC.servicios.impl;


import GIDAC.modelo.RespuestaSolicitudDescarga;
import GIDAC.repositorios.RespuestaSolicitudDescargaRepository;
import GIDAC.servicios.RespuestaSolicitudDescargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RespuestaSolicitudDescargaServiceImpl implements RespuestaSolicitudDescargaService {

    @Autowired
    private RespuestaSolicitudDescargaRepository repository;

    @Override
    public RespuestaSolicitudDescarga guardar(Object objeto) {
        RespuestaSolicitudDescarga oA=(RespuestaSolicitudDescarga) objeto;
        return repository.save(oA);
    }

    @Override
    public RespuestaSolicitudDescarga buscarPorId(Integer id) {
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
    public List<RespuestaSolicitudDescarga> listarPorEstadoDescarga(String estado, Integer idProyecto) {
        return repository.findBySolicitudDescargaEstadoSolicitudDescargaNombreEstadoDescargaAndSolicitudDescargaProyectoInvestigacionIdProyecto(estado, idProyecto);
    }

}
