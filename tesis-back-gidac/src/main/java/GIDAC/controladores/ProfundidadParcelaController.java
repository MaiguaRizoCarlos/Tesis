package GIDAC.controladores;


import GIDAC.modelo.CatalogoOrganizacion;
import GIDAC.modelo.ProfundidadParcela;
import GIDAC.servicios.ProfundidadParcelaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;

@RestController
@RequestMapping("/profundidad-parcela")
@CrossOrigin("*")
public class ProfundidadParcelaController {

    
    @Autowired
    private ProfundidadParcelaService service;

    @PostMapping("/guardar-profundidad-parcela")
    public Object guardar(@RequestBody ProfundidadParcela oC)
    {
        return service.guardar(oC);    
    }
    
    @PutMapping("/actualizar-profundidad-parcela")
    public Object actualizar(@RequestBody ProfundidadParcela oC)
    {
        return service.actualizar(oC);    
    }
    
    @GetMapping("/listar-profundidad-parcela")
    public List<ProfundidadParcela> listar()
    {
        return service.buscarTodos();
    }
    
    @GetMapping("/obtener-profundidad-parcela/por-profunidad-parcela/{idProfundidad}/{idParcela}")
    public ProfundidadParcela listarVariablesDescarga(@PathVariable Integer idProfundidad, @PathVariable Integer idParcela)
    {
        return (ProfundidadParcela) service.buscarPorParcelaProfundidad(idParcela, idProfundidad);
    }
    
    @DeleteMapping("/eliminar/{idProfundidad}/{idParcela}")
    public void eliminar(@PathVariable Integer idProfundidad, @PathVariable Integer idParcela)
    {
        service.eliminar(idProfundidad, idParcela );
    }
    
    @GetMapping("/listar-profundidad-parcela/por-parcela/{id}")
    public List obtenerPorParcela(@PathVariable Integer id)
    {
        
        List<ProfundidadParcela> listaCompleta=service.buscarPorParcela(id);
        List<Object[]> datos=service.obtenerProfundiadParcelaUsados(id);
        for (ProfundidadParcela lista : listaCompleta) {
            boolean aux=true;
            for (Object[] dato : datos) {
                int num=(Integer)dato[0];
                int num2=(Integer)dato[1];
                if(lista.getParcela().getIdParcela()==num && lista.getProfundidad().getIdProfundidad()==num2){
                    lista.setEditable(false);
                    service.guardar(lista);    
                    aux=false;  
                    break;
                }
            }
            if(aux==true){
                if(lista.isEditable()==false){
                    lista.setEditable(true);
                    service.guardar(lista);   
                }
            }
        }
        
        List<ProfundidadParcela> listaProfPar = service.buscarPorVigenciaParcela(true, id);
        listaProfPar.sort(Comparator.comparing(p -> p.getProfundidad().getProfundidadMinima()));

        return listaProfPar;
    }
    
}
