package GIDAC.controladores;


import GIDAC.modelo.Dataset;
import GIDAC.modelo.DatasetDatos;
import GIDAC.modelo.DatoRecolectado;
import GIDAC.modelo.ProfundidadParcela;
import GIDAC.servicios.DataSetService;
import GIDAC.servicios.DatoRecolectadoService;
import GIDAC.servicios.ProfundidadParcelaService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dataset")
@CrossOrigin("*")
public class DatasetController {

    
    @Autowired
    private DataSetService service;
     
    @Autowired
    private ProfundidadParcelaService profundidadParcelaService;
    
    @Autowired
    private DatoRecolectadoService datoRecolectadoService;
     

    @PostMapping("/guardar-dataset")
    public Object guardar(@RequestBody Dataset oC)
    {
        
        List<Dataset> datasetCodigo=service.buscarPorProyecto(oC.getProyectoInvestigacion().getIdProyecto());
        int codigoDataset=1;
        if (!datasetCodigo.isEmpty()) {
            Dataset dsAux=datasetCodigo.get(datasetCodigo.size()-1);
            codigoDataset=dsAux.getCodigoDataset()+1;
        }
//        
//        ProfundidadParcela profundidadParcela=new ProfundidadParcela();
//        profundidadParcela.setIdParcela(oC.getProfundidadParcela().getIdParcela());
//        profundidadParcela.setIdProfundidad(oC.getProfundidadParcela().getIdProfundidad());
//        profundidadParcela.setProfundidad(oC.getProfundidadParcela().getProfundidad());
//        profundidadParcela.setParcela(oC.getProfundidadParcela().getParcela());
//        profundidadParcela = (ProfundidadParcela) profundidadParcelaService.guardar(profundidadParcela);
//        oC.setProfundidadParcela(profundidadParcela);
        oC.setCodigoDataset(codigoDataset);
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oV.fechaActual());
        return service.guardar(oC);    
    }
    
    @PutMapping("/actualizar-dataset")
    public Object actualizar(@RequestBody Dataset oC)
    {
        ProfundidadParcela profundidadParcela=new ProfundidadParcela();
        profundidadParcela.setIdParcela(oC.getProfundidadParcela().getIdParcela());
        profundidadParcela.setIdProfundidad(oC.getProfundidadParcela().getIdProfundidad());
        profundidadParcela.setProfundidad(oC.getProfundidadParcela().getProfundidad());
        profundidadParcela.setParcela(oC.getProfundidadParcela().getParcela());
        profundidadParcelaService.guardar(profundidadParcela);
        
        Dataset oD=(Dataset) service.buscarPorId(oC.getIdDataset());
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oD.getFechaCreacion());
        oC.setFechaActualizacion(oV.fechaActual());
        return service.guardar(oC);    
    }
    
    @GetMapping("/obtener-dataset/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-dataset")
    public List<Dataset> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar-dataset/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        Dataset oC=(Dataset) service.buscarPorId(id);
        oC.setVigencia(false);
        cValidaciones oV=new cValidaciones();
        oC.setFechaActualizacion(oV.fechaActual());
        service.guardar(oC);    
    }
    
    //investigador
    @GetMapping("/obtener-dataset-por-proyecto/{id}")
    public List<DatasetDatos> obtenerDatasetProyecto(@PathVariable Integer id){
        List<Object[]> listaObject= service.obtenerDatasets(id);
        List<DatasetDatos> listaDataset = new ArrayList<>();
        
        double dato4=0;
        for (Object[] objeto : listaObject) {
            DatasetDatos dato = new DatasetDatos();
            Integer dato1=(Integer) objeto[0];
            Date dato2=(Date) objeto[1];
            Date dato3=(Date) objeto[2];
            dato.setCodigoDataset(dato1);
            dato.setFechaInicioDataset(dato2);
            dato.setFechaFinDataset(dato3);
            listaDataset.add(dato);
        }
        return listaDataset;   
    }
    
    @GetMapping("/obtener-dataset-por-proyecto-asc/{id}")
    public List<DatasetDatos> obtenerDatasetProyectoDesc(@PathVariable Integer id){
        List<Object[]> listaObject= service.obtenerDatasetsAsc(id);
        List<DatasetDatos> listaDataset = new ArrayList<>();
        
        double dato4=0;
        for (Object[] objeto : listaObject) {
            DatasetDatos dato = new DatasetDatos();
            Integer dato1=(Integer) objeto[0];
            Date dato2=(Date) objeto[1];
            Date dato3=(Date) objeto[2];
            dato.setCodigoDataset(dato1);
            dato.setFechaInicioDataset(dato2);
            dato.setFechaFinDataset(dato3);
            listaDataset.add(dato);
        }
        return listaDataset;   
    }
    
    @GetMapping("/obtener-dataset/por-parcela/{id}")
    public List obtenerPorParcela(@PathVariable Integer id)
    {
        
        List<Dataset> listaCompleta=service.buscarPorParcela(id);
        List<Object[]> datos=service.obtenerDataSetUsados(id);
        for (Dataset lista : listaCompleta) {
            boolean aux=true;
            for (Object[] dato : datos) {
                int num=(Integer)dato[0];
                if(lista.getIdDataset()==num){
                    service.guardar(lista);    
                    aux=false;  
                    break;
                }
            }
        }
        return service.buscarPorParcela(id);
    }
    
}
