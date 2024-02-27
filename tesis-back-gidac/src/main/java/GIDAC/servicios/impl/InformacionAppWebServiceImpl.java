/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.servicios.impl;


import GIDAC.modelo.InformacionEcoAndes;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import GIDAC.repositorios.InformacionAppWebRepository;
import GIDAC.servicios.InformacionAppWebService;

/**
 *
 * @author My Notebook
 */
@Service
public class InformacionAppWebServiceImpl implements InformacionAppWebService{
   @Autowired
   private InformacionAppWebRepository DAO;
   
   
   @Override
   @Transactional(readOnly=true)
   public List<InformacionEcoAndes> findAll(){
       //return (List<InformacionEcoAndes>) DAO.findByVigencia(false);
       return (List<InformacionEcoAndes>) DAO.findAll();
   }
    @Override
    public InformacionEcoAndes save(Object objeto) {
        InformacionEcoAndes oA=(InformacionEcoAndes) objeto;
        return DAO.save(oA);
    }
    
   @Override
    @Transactional(readOnly=true)
    public InformacionEcoAndes findById(Integer id) {
        return DAO.findById(id).orElse(null);
    }

    @Override
    public InformacionEcoAndes findByVigencia() {
        System.out.println("---------------------------------------------------");
        System.out.println("llega");
        System.out.println("....................................................");
        List<InformacionEcoAndes> inf=DAO.findByVigencia(true);
        return inf.get(0);
    }
   
}
