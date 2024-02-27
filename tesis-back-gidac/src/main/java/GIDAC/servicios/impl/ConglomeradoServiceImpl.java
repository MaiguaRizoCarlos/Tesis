package GIDAC.servicios.impl;


import GIDAC.modelo.Altura;
import GIDAC.modelo.Conglomerado;
import GIDAC.modelo.ProyectoInvestigacion;
import GIDAC.repositorios.ConglomeradoRepository;
import GIDAC.repositorios.ProyectoInvestigacionRepository;
import GIDAC.servicios.ConglomeradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ConglomeradoServiceImpl implements ConglomeradoService {

    @Autowired
    private ConglomeradoRepository repository;
    

    @Override
    public Conglomerado guardar(Object objeto) {
        Conglomerado oA=(Conglomerado) objeto;
        return repository.save(oA);
    }

    @Override
    public Conglomerado buscarPorId(Integer id) {
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
    public List buscarPorProyectoInvestigacion(Integer id) {
        ProyectoInvestigacion proyectoInvestigacion=new ProyectoInvestigacion();
        proyectoInvestigacion.setIdProyecto(id);
        return repository.findByVigenciaAndProyectoInvestigacion(true, proyectoInvestigacion);
    }

    @Override
    public Object buscarPorCodigoConglomeradoProyectoInvestigacionAltura(String codigoConglomerado, Integer idProyecto, Integer idAltura) {
        
        ProyectoInvestigacion proyecto=new ProyectoInvestigacion();
        proyecto.setIdProyecto(idProyecto);
        Altura altura=new Altura();
        altura.setIdAltura(idAltura);
        return repository.findByCodigoConglomeradoAndProyectoInvestigacionAndAltura(codigoConglomerado, proyecto, altura);
    }

    @Override
    public Object buscarPorCodigoConglomeradoProyectoInvestigacion(String codigoConglomerado, Integer idProyecto) {
       ProyectoInvestigacion proyecto=new ProyectoInvestigacion();
        proyecto.setIdProyecto(idProyecto);
        return repository.findByCodigoConglomeradoAndProyectoInvestigacion(codigoConglomerado, proyecto);
    }

    @Override
    public List<Object[]> obtenerConglomeradosUsados(Integer id) {
        return repository.obtenerConglomeradosUsados(id);
    }
}
