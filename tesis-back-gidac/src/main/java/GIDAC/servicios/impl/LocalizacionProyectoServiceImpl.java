package GIDAC.servicios.impl;



import GIDAC.controladores.cValidaciones;
import GIDAC.modelo.AreaInvestigacion;
import GIDAC.modelo.Localizacion;
import GIDAC.modelo.LocalizacionProyecto;
import GIDAC.modelo.ProyectoInvestigacion;
import GIDAC.repositorios.AreaInvestigacionRepository;
import GIDAC.repositorios.LocalizacionProyectoRepository;
import GIDAC.servicios.AreaInvestigacionProyectoService;
import GIDAC.servicios.AreaInvestigacionService;
import GIDAC.servicios.LocalizacionProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class LocalizacionProyectoServiceImpl implements LocalizacionProyectoService {

     @Autowired
    private LocalizacionProyectoRepository repository;

    @Override
    public LocalizacionProyecto guardar(Object objeto) {
        LocalizacionProyecto oA=(LocalizacionProyecto) objeto;
        return repository.save(oA);
    }


    @Override
    public List buscarTodos() {
        return repository.findAll();
    }

    @Override
    public List buscarPorProyecto(Integer id) {
        return repository.findByVigenciaAndProyectoInvestigacionIdProyecto(true, id);
    }

    @Override
    public LocalizacionProyecto buscarPorProyectoAndLocalizacion(Integer idProyecto, Integer idLocalizacion) {
        return repository.findByVigenciaAndProyectoInvestigacionIdProyectoAndLocalizacionIdLocalizacion(true, idProyecto, idLocalizacion);
    }

    @Override
    public void eliminar(Integer idProyecto, Integer idLocalizacion) {
        LocalizacionProyecto oA= repository.findByVigenciaAndProyectoInvestigacionIdProyectoAndLocalizacionIdLocalizacion(true, idProyecto, idLocalizacion);
        cValidaciones v=new cValidaciones();
        oA.setFechaActualizacion(v.fechaActual());
        Localizacion l=new Localizacion();
        ProyectoInvestigacion p=new ProyectoInvestigacion();
        l.setIdLocalizacion(idLocalizacion);
        p.setIdProyecto(idProyecto);
        oA.setIdProyecto(idProyecto);
        oA.setIdLocalizacion(idLocalizacion);
        oA.setProyectoInvestigacion(p);
        oA.setLocalizacion(l);
        oA.setVigencia(false);
        repository.save(oA);
    }

    @Override
    public Object buscarPorEliminadoProyectoAndLocalizacion(Integer idProyecto, Integer idLocalizacion) {
        return repository.findByProyectoInvestigacionIdProyectoAndLocalizacionIdLocalizacion( idProyecto, idLocalizacion);
    }

    
    
}
