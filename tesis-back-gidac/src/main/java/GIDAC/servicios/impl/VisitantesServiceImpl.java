/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.servicios.impl;


import GIDAC.modelo.Visitantes;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import GIDAC.repositorios.VisitantesRepository;
import GIDAC.servicios.VisitantesService;

/**
 *
 * @author My Notebook
 */
@Service
public class VisitantesServiceImpl implements VisitantesService{
   @Autowired
   private VisitantesRepository DAO;
   
   
   @Override
   @Transactional(readOnly=true)
   public List<Visitantes> findAll(){
       return (List<Visitantes>) DAO.findAll();
   }
    @Override
    public Visitantes save(Object objeto) {
        Visitantes oA=(Visitantes) objeto;
        return DAO.save(oA);
    }
   
}
