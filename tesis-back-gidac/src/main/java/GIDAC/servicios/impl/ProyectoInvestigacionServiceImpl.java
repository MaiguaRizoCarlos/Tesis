/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.servicios.impl;


import GIDAC.modelo.AreaInvestigacion;
import GIDAC.modelo.ProyectoInvestigacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import GIDAC.repositorios.ProyectoInvestigacionRepository;
import GIDAC.servicios.ProyectoInvestigacionService;

/**
 *
 * @author My Notebook
 */
@Service
public class ProyectoInvestigacionServiceImpl implements ProyectoInvestigacionService{
   @Autowired
   private ProyectoInvestigacionRepository DAO;
   
   
   @Override
   @Transactional(readOnly=true)
   public List<ProyectoInvestigacion> findAll(){
       return (List<ProyectoInvestigacion>) DAO.findAll();
   }
   
   @Override
   @Transactional(readOnly=true)
   public List<ProyectoInvestigacion> findInvestigacionesPublicas(){
       return (List<ProyectoInvestigacion>) DAO.findByVigenciaAndEstadoProyectoInvestigacion_nombreEstadoProyecto(true, "Público");
   }
   
    @Override
    public Object save(Object objeto) {
        ProyectoInvestigacion oA=(ProyectoInvestigacion) objeto;
        return DAO.save(oA);
    }
   @Override
   @Transactional(readOnly=true)
    public Object findById(Integer id) {
        return DAO.findById(id).orElse(null);
    }
   @Override
   @Transactional(readOnly=false)
   public void delete(Integer id){
       DAO.deleteById(id);
   }

    @Override
    public List findAllVigentes(Boolean vigencia) {
        return DAO.findByVigencia(vigencia);
    }

}
