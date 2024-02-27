package GIDAC.controladores;


import GIDAC.modelo.Altura;
import GIDAC.modelo.Area;
import GIDAC.modelo.Conglomerado;
import GIDAC.modelo.DashDatos;
import GIDAC.modelo.Dataset;
import GIDAC.modelo.DatasetDatos;
import GIDAC.modelo.Dato;
import GIDAC.modelo.DatoRecolectado;
import GIDAC.modelo.MapaCoordenadas;
import GIDAC.modelo.MapaProfundidadValor;
import GIDAC.modelo.MapaVariable;
import GIDAC.modelo.UnidadMedida;
import GIDAC.modelo.ModeloDatosAgrupados;
import java.util.Collections;
import GIDAC.modelo.Parcela;
import GIDAC.modelo.Perfilado;
import java.io.File;
import java.nio.file.Files;
import GIDAC.modelo.Profundidad;
import GIDAC.modelo.ProfundidadParcela;
import GIDAC.modelo.ProyectoInvestigacion;
import GIDAC.modelo.TiempoEdicionDato;
import GIDAC.modelo.ValorPermitido;
import GIDAC.modelo.Variable;
import GIDAC.modelo.VariableUnidadMedida;
import GIDAC.modelo.VariablesEncontradas;
import GIDAC.modelo.modeloDescarga;
import GIDAC.modelo.outlier;
import GIDAC.modelo.valoresDescarga;
import GIDAC.servicios.AlturaService;
import GIDAC.servicios.AreaService;
import GIDAC.servicios.ConglomeradoService;
import GIDAC.servicios.DataSetService;
import GIDAC.servicios.DatoRecolectadoService;
import GIDAC.servicios.ParcelaService;
import GIDAC.servicios.ProfundidadParcelaService;
import GIDAC.servicios.ProfundidadService;
import GIDAC.servicios.TiempoEdicionDatoService;
import GIDAC.servicios.ValorPermitidoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static org.hibernate.criterion.Projections.id;
import org.springframework.http.ResponseEntity;
import GIDAC.servicios.UsuarioService;
import GIDAC.servicios.UnidadMedidaService;
import GIDAC.servicios.VariableService;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.format.Colour;
import jxl.write.WritableCell;
import jxl.write.WriteException;

import jxl.Workbook;
import jxl.write.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import jxl.Cell;
import jxl.CellType;
import jxl.read.biff.BiffException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



        

@RestController
@RequestMapping("/dato-recolectado")
@CrossOrigin("*")
public class DatoRecolectadoController {

    
    
    @Autowired
    private DatoRecolectadoService service;
    
    
    @Autowired
    private ValorPermitidoService valorPermitidoService;
    
    @Autowired
    private AlturaService alturaService;
    
    @Autowired
    private ConglomeradoService conglomeradoService;
    
    @Autowired
    private ParcelaService parcelaService;
    
    @Autowired
    private ProfundidadParcelaService profundidadParcelaService ;
    
    @Autowired
    private ProfundidadService profundidadService ;
    
    @Autowired
    private DataSetService datasetService;
    
    @Autowired
    private AreaService areaService;
    
    @Autowired
    private UnidadMedidaService medidaService;
    
    @Autowired
    private VariableService variableService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private TiempoEdicionDatoService tiempoEdicionDatoService;


    @PostMapping("/guardar-dato-recolectado")
    public Object guardar(@RequestBody DatoRecolectado oC)
    {
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oV.fechaActual());
        
        Dataset DataSetRes= oC.getDataset();
        
        List<Dataset> datasetAux1= (List<Dataset>) datasetService.findByCodigoDatasetAndProyectoInvestigacionIdProyecto(oC.getDataset().getCodigoDataset(),oC.getDataset().getProyectoInvestigacion().getIdProyecto());
        Dataset datasetAux=datasetAux1.get(0);
        DataSetRes.setCodigoDataset(datasetAux.getCodigoDataset());
        DataSetRes.setFechaInicioDataset(datasetAux.getFechaInicioDataset());
        DataSetRes.setFechaFinDataset(datasetAux.getFechaFinDataset());
        DataSetRes.setFechaCreacion(oV.fechaActual());
        
        ProfundidadParcela pp=oC.getDataset().getProfundidadParcela();
        pp.setIdParcela(oC.getDataset().getProfundidadParcela().getParcela().getIdParcela());
        pp.setIdProfundidad(oC.getDataset().getProfundidadParcela().getProfundidad().getIdProfundidad());
        DataSetRes.setProfundidadParcela(pp);
        
        DataSetRes.setProyectoInvestigacion(oC.getDataset().getProyectoInvestigacion());
        DataSetRes.setVigencia(true);
        
        Dataset respuesta=(Dataset) datasetService.guardar(DataSetRes);
        
        oC.setDataset(respuesta);
        
        List<TiempoEdicionDato> oLista=tiempoEdicionDatoService.findAll();
        int dias=1;
        if(!oLista.isEmpty()){
            TiempoEdicionDato oTiempoEdicionDato=oLista.get(0);    
            double tiempoDouble = oTiempoEdicionDato.getTiempo();
            dias = (int) Math.round(tiempoDouble);
        }
        
        oC.setFechaMaximaEdicion(oV.agregarFechaMaximaEdicion(oC.getFechaCreacion(), dias));
        return service.guardar(oC);    
    }
    
    @PutMapping("/actualizar-dato-recolectado")
    public Object actualizar(@RequestBody DatoRecolectado oC)
    {
        DatoRecolectado oD=(DatoRecolectado) service.buscarPorId(oC.getIdDatoRecolectado());    
        cValidaciones oV=new cValidaciones();
        oC.setFechaCreacion(oD.getFechaCreacion());
        oC.setFechaActualizacion(oV.fechaActual());
        
        Dataset DataSetRes= oD.getDataset();
        
        List<Dataset> datasetAux1= (List<Dataset>) datasetService.findByCodigoDatasetAndProyectoInvestigacionIdProyecto(oC.getDataset().getCodigoDataset(),oC.getDataset().getProyectoInvestigacion().getIdProyecto());
        Dataset datasetAux=datasetAux1.get(0);
        DataSetRes.setCodigoDataset(datasetAux.getCodigoDataset());
        DataSetRes.setFechaInicioDataset(datasetAux.getFechaInicioDataset());
        DataSetRes.setFechaFinDataset(datasetAux.getFechaFinDataset());
        DataSetRes.setFechaCreacion(oV.fechaActual());
        
        ProfundidadParcela pp=oC.getDataset().getProfundidadParcela();
        pp.setIdParcela(oC.getDataset().getProfundidadParcela().getParcela().getIdParcela());
        pp.setIdProfundidad(oC.getDataset().getProfundidadParcela().getProfundidad().getIdProfundidad());
        DataSetRes.setProfundidadParcela(pp);
        
        DataSetRes.setProyectoInvestigacion(oC.getDataset().getProyectoInvestigacion());
        DataSetRes.setVigencia(true);
        
        Dataset respuesta=(Dataset) datasetService.guardar(DataSetRes);
        
        oC.setDataset(respuesta);
        return service.guardar(oC);
        
    }
   
    @GetMapping("/listar-todos-dato")
    public  List<Parcela>  listarTodosLosDatosN(){
        return parcelaService.buscarTodos();
    }
    
    @GetMapping("/listar-todos-datos-catalogo/{id}")
    public  Object  listarTodosLosDatoCatalogo(@PathVariable Integer id)
    {
        List<Object[]> datos; 
        if(id.equals("0")){
            datos = service.listarTodosLosDatosNumerico();   
        }else{
            datos = service.listarTodosLosDatosVariableNumerico(id);
        }
        Map<String, Map<String, List<Dato>>> datosAgrupados = new HashMap<>();
        for (Object[] dato : datos) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedidaVariable = (String) dato[4];
            String unidadMedida = (String) dato[5];
            String tipoVariable = (String) dato[6];
            Double valorAux = (Double) dato[7];
            if(unidadMedidaVariable.equals("NA")){
                unidadMedidaVariable="";
            }else{
                unidadMedidaVariable=" ("+unidadMedidaVariable+")";
            }
            tipoVariable=tipoVariable+unidadMedidaVariable;
            BigDecimal bd = new BigDecimal(valorAux);
            bd = bd.setScale(3, RoundingMode.HALF_UP);
            valorAux = bd.doubleValue();
            String valor=valorAux.toString();
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
        }
        return ResponseEntity.ok(datosAgrupados);  
    }
    
    @GetMapping("/listar-todos-datos-catalogo-nominal/{id}")
    public  Object  listarTodosLosDatoCatalogoNominal(@PathVariable Integer id)
    {
        List<Object[]> datos; 
        if(id.equals("0")){
            datos = service.listarTodosLosDatosNominal();   
        }else{
            datos = service.listarTodosLosDatosVariableNominal(id);
        }
        // Mapa para almacenar los datos agrupados
        Map<String, Map<String, List<Dato>>> datosAgrupados = new HashMap<>();
        // Agrupar los datos por coordenadas, tipos de variables y profundidades
        for (Object[] dato : datos) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedida = (String) dato[4];
            String tipoVariable = (String) dato[5];
            String valor = (String) dato[6];
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
        }
        return ResponseEntity.ok(datosAgrupados);  
    }
    
    @GetMapping("/listar-todos-datos/{id}")
    public  Object  listarTodosLosDato(@PathVariable Integer id)
    {
        List<Object[]> datos;
        if(id==0){
            datos = service.listarTodosLosDatosNumerico();   
        }else{
            datos = service.listarTodosLosDatosProyectoNumerico(id);   
        }
        // Mapa para almacenar los datos agrupados
        Map<String, Map<String, List<Dato>>> datosAgrupados = new HashMap<>();
        // Agrupar los datos por coordenadas, tipos de variables y profundidades
        for (Object[] dato : datos) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedidaVariable = (String) dato[4];
            String unidadMedida = (String) dato[5];
            String tipoVariable = (String) dato[6];
            Double valorAux = (Double) dato[7];
            if(unidadMedidaVariable.equals("NA")){
                unidadMedidaVariable="";
            }else{
                unidadMedidaVariable=" ("+unidadMedidaVariable+")";
            }
            tipoVariable=tipoVariable+unidadMedidaVariable;
            BigDecimal bd = new BigDecimal(valorAux);
            bd = bd.setScale(3, RoundingMode.HALF_UP);
            valorAux = bd.doubleValue();
            String valor=valorAux.toString();
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
        }
        return ResponseEntity.ok(datosAgrupados);  
    }
    
    @GetMapping("/listar-todos-datos-nominal/{id}")
    public  Object  listarTodosLosDatoNominal(@PathVariable Integer id)
    {
        List<Object[]> datos;
        if(id==0){
            datos = service.listarTodosLosDatosNominal();   
        }else{
            datos = service.listarTodosLosDatosProyectoNominal(id);   
        }
        // Mapa para almacenar los datos agrupados
        Map<String, Map<String, List<Dato>>> datosAgrupados = new HashMap<>();
        // Agrupar los datos por coordenadas, tipos de variables y profundidades
        for (Object[] dato : datos) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedida = (String) dato[4];
            String tipoVariable = (String) dato[5];
            String valor = (String) dato[6];
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
        }
        return ResponseEntity.ok(datosAgrupados);  
    }
    
    @GetMapping("/listar-todos-datos-unidos/{id}/{idVariable}/{codigoDataset}")
    public  Object  listarTodosLosDatoUnidos(@PathVariable Integer id, @PathVariable Integer idVariable,@PathVariable Integer codigoDataset )
    {
        List<Object[]> datos;
        List<Object[]> datosNominales;
        
        if(id==0){
            if(idVariable==0){
                datos = service.listarTodosLosDatosNumerico();
                datosNominales = service.listarTodosLosDatosNominal();     
            }else{
                datos = service.listarTodosLosDatosNumericoVariable(idVariable);
                datosNominales = service.listarTodosLosDatosNominalVariable(idVariable); 
            }
        }else{
            if(idVariable==0){
                if(codigoDataset==0){
                    datos = service.listarTodosLosDatosProyectoNumerico(id);
                    datosNominales = service.listarTodosLosDatosProyectoNominal(id);
                }else{
                    datos = service.listarTodosLosDatosProyectoNumericoDataset(id, codigoDataset);
                    datosNominales = service.listarTodosLosDatosProyectoNominalDataset(id, codigoDataset);
                }
            }else{
                if(codigoDataset==0){
                    datos = service.listarTodosLosDatosProyectoNumericoVariable(id,idVariable);
                    datosNominales = service.listarTodosLosDatosProyectoNominalVariable(id,idVariable);
                }else{
                    datos = service.listarTodosLosDatosProyectoNumericoVariableDataset(id,idVariable, codigoDataset);
                    datosNominales = service.listarTodosLosDatosProyectoNominalVariableDataset(id,idVariable, codigoDataset);
                }
            }
            
        }
        // Mapa para almacenar los datos agrupados
        Map<String, Map<String, List<Dato>>> datosAgrupados = new HashMap<>();
        // Agrupar los datos por coordenadas, tipos de variables y profundidades
        for (Object[] dato : datos) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedidaVariable = (String) dato[4];
            String unidadMedida = (String) dato[5];
            String tipoVariable = (String) dato[6];
            Double valorAux = (Double) dato[7];
            if(unidadMedidaVariable.equals("NA")){
                unidadMedidaVariable="";
            }else{
                unidadMedidaVariable=" ("+unidadMedidaVariable+")";
            }
            tipoVariable=tipoVariable+unidadMedidaVariable;
            BigDecimal bd = new BigDecimal(valorAux);
            bd = bd.setScale(3, RoundingMode.HALF_UP);
            valorAux = bd.doubleValue();
            String valor=valorAux.toString();
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
        }
        
        for (Object[] dato : datosNominales) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedida = (String) dato[4];
            String tipoVariable = (String) dato[5];
            String valor = (String) dato[6];
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
        }
        return ResponseEntity.ok(datosAgrupados); 
    }
    
    
    
    @GetMapping("/listar-todos-datos-catalogo-unidos/{id}")
    public  Object  listarTodosLosDatoCatalogoUnido(@PathVariable Integer id)
    {
        
        List<Object[]> datos;
        List<Object[]> datosNominales;
        if(id.equals("0")){
            datos = service.listarTodosLosDatosNumerico();
            datosNominales = service.listarTodosLosDatosNominal();   
        }else{
            datos = service.listarTodosLosDatosVariableNumerico(id);
            datosNominales = service.listarTodosLosDatosVariableNominal(id);
        }
        // Mapa para almacenar los datos agrupados
        Map<String, Map<String, List<Dato>>> datosAgrupados = new HashMap<>();
        // Agrupar los datos por coordenadas, tipos de variables y profundidades
        for (Object[] dato : datos) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedidaVariable = (String) dato[4];
            String unidadMedida = (String) dato[5];
            String tipoVariable = (String) dato[6];
            Double valorAux = (Double) dato[7];
            if(unidadMedidaVariable.equals("NA")){
                unidadMedidaVariable="";
            }else{
                unidadMedidaVariable=" ("+unidadMedidaVariable+")";
            }
            tipoVariable=tipoVariable+unidadMedidaVariable;
            BigDecimal bd = new BigDecimal(valorAux);
            bd = bd.setScale(3, RoundingMode.HALF_UP);
            valorAux = bd.doubleValue();
            String valor=valorAux.toString();
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
        }
        
        for (Object[] dato : datosNominales) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedida = (String) dato[4];
            String tipoVariable = (String) dato[5];
            String valor = (String) dato[6];
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
        }
        return ResponseEntity.ok(datosAgrupados);  
    }
    
    
    
    //-----------------------------------------------------------------------------------------------------
    
    @GetMapping("/listar-todos-datos-proyecto-variable/{idProyecto}/{idVariable}")
    public  Object  listarTodosLosDatoProyectoVariable(@PathVariable Integer idProyecto, @PathVariable Integer idVariable)
    {
        List<Object[]> datos;
        if(idVariable==0){
            datos = service.listarTodosLosDatosProyectoNumerico(idProyecto);   
        }else{
            datos = service.listarTodosLosDatosProyectoVariableNumerico(idProyecto, idVariable);   
        }
        // Mapa para almacenar los datos agrupados
        Map<String, Map<String, List<Dato>>> datosAgrupados = new HashMap<>();
        // Agrupar los datos por coordenadas, tipos de variables y profundidades
        for (Object[] dato : datos) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedidaVariable = (String) dato[4];
            String unidadMedida = (String) dato[5];
            String tipoVariable = (String) dato[6];
            Double valorAux = (Double) dato[7];
            if(unidadMedidaVariable.equals("NA")){
                unidadMedidaVariable="";
            }else{
                unidadMedidaVariable=" ("+unidadMedidaVariable+")";
            }
            tipoVariable=tipoVariable+unidadMedidaVariable;
            BigDecimal bd = new BigDecimal(valorAux);
            bd = bd.setScale(3, RoundingMode.HALF_UP);
            valorAux = bd.doubleValue();
            String valor=valorAux.toString();
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
        }
        return ResponseEntity.ok(datosAgrupados);  
    }
    
    @GetMapping("/listar-todos-datos-proyecto-variable-nominal/{idProyecto}/{idVariable}")
    public  Object  listarTodosLosDatoProyectoVariableNominal(@PathVariable Integer idProyecto, @PathVariable Integer idVariable)
    {
        List<Object[]> datos;
        if(idVariable==0){
            datos = service.listarTodosLosDatosProyectoNominal(idProyecto);   
        }else{
            datos = service.listarTodosLosDatosProyectoVariableNominal(idProyecto, idVariable);   
        }
        // Mapa para almacenar los datos agrupados
        Map<String, Map<String, List<Dato>>> datosAgrupados = new HashMap<>();
        // Agrupar los datos por coordenadas, tipos de variables y profundidades
        for (Object[] dato : datos) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedida = (String) dato[4];
            String tipoVariable = (String) dato[5];
            String valor = (String) dato[6];
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
        }
        return ResponseEntity.ok(datosAgrupados);  
    }
    
    @GetMapping("/listar-todos-datos-proyecto-variable-unidos/{idProyecto}/{idVariable}/{codigoDataset}")
    public  Object  listarTodosLosDatoProyectoVariableUnido(@PathVariable Integer idProyecto, @PathVariable Integer idVariable, @PathVariable Integer codigoDataset)
    {
        List<Object[]> datos;
        List<Object[]> datosNominales;
        
        if(idVariable==0){
            if(codigoDataset==0){
                datos = service.listarTodosLosDatosProyectoNumerico(idProyecto);
                datosNominales = service.listarTodosLosDatosProyectoNominal(idProyecto);    
            }else{
                datos = service.listarTodosLosDatosProyectoNumericoDataset(idProyecto, codigoDataset);
                datosNominales = service.listarTodosLosDatosProyectoNominalDataset(idProyecto, codigoDataset);   
            }
            
        }else{
            if(codigoDataset==0){
                datos = service.listarTodosLosDatosProyectoVariableNumerico(idProyecto, idVariable);   
                datosNominales = service.listarTodosLosDatosProyectoVariableNominal(idProyecto, idVariable);       
            }else{
                datos = service.listarTodosLosDatosProyectoNumericoVariableDataset(idProyecto,idVariable, codigoDataset);
                datosNominales = service.listarTodosLosDatosProyectoNominalVariableDataset(idProyecto,idVariable, codigoDataset);
            }
            
        }
        
        // Mapa para almacenar los datos agrupados
        Map<String, Map<String, List<Dato>>> datosAgrupados = new HashMap<>();
        // Agrupar los datos por coordenadas, tipos de variables y profundidades
        for (Object[] dato : datos) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedidaVariable = (String) dato[4];
            String unidadMedida = (String) dato[5];
            String tipoVariable = (String) dato[6];
            Double valorAux = (Double) dato[7];
            if(unidadMedidaVariable.equals("NA")){
                unidadMedidaVariable="";
            }else{
                unidadMedidaVariable=" ("+unidadMedidaVariable+")";
            }
            tipoVariable=tipoVariable+unidadMedidaVariable;
            BigDecimal bd = new BigDecimal(valorAux);
            bd = bd.setScale(3, RoundingMode.HALF_UP);
            valorAux = bd.doubleValue();
            String valor=valorAux.toString();
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
        }
        
        for (Object[] dato : datosNominales) {
            String coordenadaX = (String) dato[0];
            String coordenadaY = (String) dato[1];
            Double profundidadMaxima = (Double) dato[2];
            Double profundidadMinima = (Double) dato[3];
            String unidadMedida = (String) dato[4];
            String tipoVariable = (String) dato[5];
            String valor = (String) dato[6];
            String coordenadas = coordenadaX + "," + coordenadaY;
            String profundidades = profundidadMinima+" - "+profundidadMaxima+" "+unidadMedida;
            //double profundidades = (Double.parseDouble(profundidadMinima)+ Double.parseDouble(profundidadMaxima))/2;
            // Verificar si las coordenadas existen en el mapa
            if (!datosAgrupados.containsKey(coordenadas)) {
                datosAgrupados.put(coordenadas, new HashMap<>());
            }
            // Verificar si el tipo de variable existe en el mapa de coordenadas
            Map<String, List<Dato>> variablesMapa = datosAgrupados.get(coordenadas);
            if (!variablesMapa.containsKey(tipoVariable)) {
                variablesMapa.put(tipoVariable, new ArrayList<>());
            }
            // Agregar el dato a la lista correspondiente en el mapa
            List<Dato> listaDatos = variablesMapa.get(tipoVariable);
            listaDatos.add(new Dato(profundidades, valor));
        }
        return ResponseEntity.ok(datosAgrupados);  
    }
    
    //-----------------------------------------------------------------------------------------------------
    
    @GetMapping("/obtener-dato-recolectado/{id}")
    public Object obtener(@PathVariable Integer id)
    {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/listar-dato-recolectado/por-proyecto/{id}")
    public List<DatoRecolectado> obtenerPorProyecto(@PathVariable Integer id)
    {
        return service.buscarPorVigenciaProyecto(true, id);
    }
    
    @GetMapping("/listar-dato-recolectado")
    public List<DatoRecolectado> listar()
    {
        return service.buscarTodos();
    }
    
    @DeleteMapping("/eliminar-dato-recolectado/{id}")
    public void eliminar(@PathVariable Integer id)
    {
        DatoRecolectado oC=(DatoRecolectado) service.buscarPorId(id);
        cValidaciones oV=new cValidaciones();
        oC.setFechaActualizacion(oV.fechaActual());
        oC.setVigencia(false);
        service.guardar(oC);    
    }
    
    @GetMapping("/obtener-por-dataset/{id}")
    public List obtenerPorDataset(@PathVariable Integer id )
    {
        return service.buscarPorVigenciaDataset(true, id);
    }
    
    @GetMapping("/obtener-por-profundidad-parcela/{idProfundidad}/{idParcela}")
    public List obtenerPorDataset(@PathVariable Integer idProfundidad,@PathVariable Integer idParcela)
    {
        return service.buscarPorVigenciaProfundidadParcela(true, idProfundidad, idParcela);
    }
    
    //tiempo edicion
    @GetMapping("/actualizar-editables")
    public void actualizarEditables()
    {
        cValidaciones oV=new cValidaciones();
        Date fechaActual=oV.fechaActual();
        List<DatoRecolectado> datoRecolectado=service.buscarPorEditable();
        List<TiempoEdicionDato> oLista=tiempoEdicionDatoService.findAll();
        int dias=1;
        if(!oLista.isEmpty()){
            TiempoEdicionDato oTiempoEdicionDato=oLista.get(0);    
            double tiempoDouble = oTiempoEdicionDato.getTiempo();
            dias = (int) Math.round(tiempoDouble);
        }
        
        for(DatoRecolectado dato:datoRecolectado) {
            if(oV.compararFechaMaxima(dato.getFechaMaximaEdicion(), fechaActual)){
                dato.setEditable(false);
                dato.setFechaActualizacion(oV.fechaActual());
                service.guardar(dato);
            }else{
                dato.setEditable(true);
                dato.setFechaActualizacion(oV.fechaActual());
                service.guardar(dato);
            }
        }
    }
    
    //comporbar archivo
    @PostMapping("/comprobar-estado-archivo")
    public boolean comprobarXLS(@RequestParam("proyectoInvestigacion") String datosJson, @RequestParam("file") MultipartFile file) throws JsonProcessingException{
        
        ProyectoInvestigacion oC1 = new ObjectMapper().readValue(datosJson, ProyectoInvestigacion.class);
        
        int fila=1;
        int cont=1;
        boolean estado=true;
       
        try{
            InputStream inputStream = file.getInputStream();
            Workbook archivoExcel= Workbook.getWorkbook(inputStream);
            Sheet hoja=archivoExcel.getSheet(0);
            String dato;
            for(int colm=0;colm<15;colm++){
                dato=hoja.getCell(colm,fila).getContents();
                System.out.println(dato);
                switch(cont){
                    case 1:
                        if(compararCadenasCaracteres(dato,"Fecha salida campo")){estado=true;}
                        else{ estado=false; break;}
                        cont=2;
                        break;
                    case 2:
                        if(compararCadenasCaracteres(dato,"Coordenada x")){estado=true;}
                        else{ estado=false; break;}
                        cont=3;
                        break;
                    case 3:
                        if(compararCadenasCaracteres(dato,"Coordenada y")){estado=true;}
                        else{ estado=false; break;}
                        cont=4;
                        break;
                    case 4:
                        if(compararCadenasCaracteres(dato,"Altura")){estado=true;}
                        else{ estado=false; break;}
                        cont=5;
                        break;
                    case 5:
                        if(compararCadenasCaracteres(dato,"Unidad de medidad altura")){estado=true;}
                        else{ estado=false; break;}
                        cont=6;
                        break;
                    case 6:
                        if(compararCadenasCaracteres(dato,"Código conglomerado")){estado=true;}
                        else{ estado=false; break;}
                        cont=7;
                        break;
                    case 7:
                        if(compararCadenasCaracteres(dato,"Nombre conglomerado")){estado=true;}
                        else{ estado=false; break;}
                        cont=8;
                        break;
                    case 8:
                        if(compararCadenasCaracteres(dato,"Sector")){estado=true;}
                        else{ estado=false; break;}
                        cont=9;
                        break;
                    case 9:
                        if(compararCadenasCaracteres(dato,"Código parcela")){estado=true;}
                        else{ estado=false; break;}
                        cont=10;
                        break;
                    case 10:
                        if(compararCadenasCaracteres(dato,"Nombre parcela")){estado=true;}
                        else{ estado=false; break;}
                        cont=11;
                        break;
                    case 11:
                        if(compararCadenasCaracteres(dato,"Area parcela")){estado=true;}
                        else{ estado=false; break;}
                        cont=12;
                        break;
                    case 12:
                        if(compararCadenasCaracteres(dato,"Unidad de medida parcela")){estado=true;}
                        else{ estado=false; break;}
                        cont=13;
                        break;
                    case 13:
                        if(compararCadenasCaracteres(dato,"Profundidad minima")){estado=true;}
                        else{ estado=false; break;}
                        cont=14;
                        break;
                    case 14:
                        if(compararCadenasCaracteres(dato,"Profundidad maxima")){estado=true;}
                        else{ estado=false; break;}
                        cont=15;
                        break;
                    case 15:
                        if(compararCadenasCaracteres(dato,"Unidad de medidad profundidad")){estado=true;}
                        else{ estado=false; break;}
                        cont=1;
                        break;
                    
                }
                if(estado==false) {
                    break;
                }
            }
            archivoExcel.close();
            inputStream.close();
            return estado;
        }catch(Exception e){
            System.out.println("Error "+e);
            return false;
        }
    }
    
    //buscar datos para perfilar
     @PostMapping("/buscar-datos-para-perfilado")
    public List buscarDatosParaPerfilarXLS(@RequestParam("proyectoInvestigacion") String datosJson, @RequestParam("file") MultipartFile file) throws JsonProcessingException{
        
        ProyectoInvestigacion oC1 = new ObjectMapper().readValue(datosJson, ProyectoInvestigacion.class);
        
        List<VariablesEncontradas> variablesEncontradas=new ArrayList();
        
        List<Object[]> listaObject= variableService.listarCatalogoParaPerfilado();
        List<VariablesEncontradas> listaEquivalencia = new ArrayList<>();
        for (Object[] objeto : listaObject) {
            VariablesEncontradas variable = new VariablesEncontradas();
            variable.setIdVariableUnidadMedida((Integer) objeto[0]);
            variable.setIdVariable((Integer) objeto[1]);
            variable.setCodigoVariable((String) objeto[2]);
            variable.setNombreVariable((String) objeto[3]);
            variable.setNombreOrganizacion((String) objeto[4]);
            variable.setNombreVariableOrganizacion((String) objeto[5]);
            variable.setNombreTipoVariable((String) objeto[6]);
            variable.setUnidadMedida((String) objeto[7]);
            System.out.println("-------------------------------");
            System.out.println("-------------------------------"+variable.getNombreVariableOrganizacion());
            System.out.println("-------------------------------"+variable.getUnidadMedida());
            System.out.println("-------------------------------");
            listaEquivalencia.add(variable);
        }
        
        int fila=1;
        int numColumnas=0;
        int numFilas=0;
       
        try{
            InputStream inputStream = file.getInputStream();
            Workbook archivoExcel= Workbook.getWorkbook(inputStream);
            Sheet hoja=archivoExcel.getSheet(0);
            numColumnas=hoja.getColumns();
            numFilas=hoja.getRows();
            if(numColumnas<=14 || numFilas<=2){
                return null;
                
            }else{
                String dato;
                for(int colm=15;colm<numColumnas;colm++){
                    dato=hoja.getCell(colm,fila).getContents();
                    System.out.println("Carga "+dato);
                    for(VariablesEncontradas equivalencia:listaEquivalencia) {
                        if(equivalencia.getNombreTipoVariable().equals("Numérico")){
                            String datoAux="";
                            
                            boolean confirmarUnidadMedida=true;
                            try{
                                datoAux=hoja.getCell(colm+1,fila).getContents();
                                if(medidaService.buscarPorAbreviatura(datoAux)==null){
                                    confirmarUnidadMedida=false;
                                }else{
                                    confirmarUnidadMedida=true;
                                }
                            }catch(Exception E){
                                System.out.println("Carga incorrecta "+datoAux);
                            }
                            
                            if(confirmarUnidadMedida==false){
                                if(compararCadenasCaracteres(dato,equivalencia.getNombreVariable()) && compararCadenasCaracteres("NA",equivalencia.getUnidadMedida())){
                                    VariablesEncontradas variableEncontrada=new VariablesEncontradas();
                                    variableEncontrada.setIdVariableUnidadMedida(equivalencia.getIdVariableUnidadMedida());
                                    variableEncontrada.setIdVariable(equivalencia.getIdVariable());
                                    variableEncontrada.setNombreVariable(equivalencia.getNombreVariable());
                                    variableEncontrada.setNombreVariableOrganizacion(equivalencia.getNombreVariableOrganizacion());
                                    variableEncontrada.setNombreTipoVariable(equivalencia.getNombreTipoVariable());
                                    variableEncontrada.setNombreOrganizacion(equivalencia.getNombreOrganizacion());
                                    variableEncontrada.setUnidadMedida(equivalencia.getUnidadMedida());
                                    variableEncontrada.setNumeroColumna(colm+1);
                                    variableEncontrada.setCantidadDatos(numFilas-2);
                                    variablesEncontradas.add(variableEncontrada);
                                    System.out.println("------------------------llega a ser iguales "+dato);
                                    break;
                                } else{
                                    if(compararCadenasCaracteres(dato,equivalencia.getNombreVariableOrganizacion()) && compararCadenasCaracteres("NA",equivalencia.getUnidadMedida())){
                                        VariablesEncontradas variableEncontrada=new VariablesEncontradas();
                                        variableEncontrada.setIdVariableUnidadMedida(equivalencia.getIdVariableUnidadMedida());
                                        variableEncontrada.setIdVariable(equivalencia.getIdVariable());
                                        variableEncontrada.setNombreVariable(equivalencia.getNombreVariable());
                                        variableEncontrada.setNombreVariableOrganizacion(equivalencia.getNombreVariableOrganizacion());
                                        variableEncontrada.setNombreTipoVariable(equivalencia.getNombreTipoVariable());
                                        variableEncontrada.setNombreOrganizacion(equivalencia.getNombreOrganizacion());
                                        variableEncontrada.setUnidadMedida(equivalencia.getUnidadMedida());
                                        variableEncontrada.setNumeroColumna(colm+1);
                                        variableEncontrada.setCantidadDatos(numFilas-2);
                                        variablesEncontradas.add(variableEncontrada);
                                        System.out.println("------------------------llega a ser iguales "+dato);
                                        break;
                                    } 
                                }
                                
                            }else{
                                if(compararCadenasCaracteres(dato,equivalencia.getNombreVariable()) && compararCadenasCaracteres(datoAux,equivalencia.getUnidadMedida())){
                                    VariablesEncontradas variableEncontrada=new VariablesEncontradas();
                                    variableEncontrada.setIdVariableUnidadMedida(equivalencia.getIdVariableUnidadMedida());
                                    variableEncontrada.setIdVariable(equivalencia.getIdVariable());
                                    variableEncontrada.setNombreVariable(equivalencia.getNombreVariable());
                                    variableEncontrada.setNombreVariableOrganizacion(equivalencia.getNombreVariableOrganizacion());
                                    variableEncontrada.setNombreTipoVariable(equivalencia.getNombreTipoVariable());
                                    variableEncontrada.setNombreOrganizacion(equivalencia.getNombreOrganizacion());
                                    variableEncontrada.setUnidadMedida(equivalencia.getUnidadMedida());
                                    variableEncontrada.setNumeroColumna(colm+1);
                                    variableEncontrada.setCantidadDatos(numFilas-2);
                                    variablesEncontradas.add(variableEncontrada);
                                    System.out.println("------------------------llega a ser iguales "+dato);
                                    colm=colm+1;
                                    break;
                                } else{
                                    if(compararCadenasCaracteres(dato,equivalencia.getNombreVariableOrganizacion()) && compararCadenasCaracteres(datoAux,equivalencia.getUnidadMedida())){
                                        VariablesEncontradas variableEncontrada=new VariablesEncontradas();
                                        variableEncontrada.setIdVariableUnidadMedida(equivalencia.getIdVariableUnidadMedida());
                                        variableEncontrada.setIdVariable(equivalencia.getIdVariable());
                                        variableEncontrada.setNombreVariable(equivalencia.getNombreVariable());
                                        variableEncontrada.setNombreVariableOrganizacion(equivalencia.getNombreVariableOrganizacion());
                                        variableEncontrada.setNombreTipoVariable(equivalencia.getNombreTipoVariable());
                                        variableEncontrada.setNombreOrganizacion(equivalencia.getNombreOrganizacion());
                                        variableEncontrada.setUnidadMedida(equivalencia.getUnidadMedida());
                                        variableEncontrada.setNumeroColumna(colm+1);
                                        variableEncontrada.setCantidadDatos(numFilas-2);
                                        variablesEncontradas.add(variableEncontrada);
                                        System.out.println("------------------------llega a ser iguales "+dato);
                                        colm=colm+1;
                                        break;
                                    } 
                                }
                            }
                            
                            
                        }else{
                            if(compararCadenasCaracteres(dato,equivalencia.getNombreVariable())){
                                VariablesEncontradas variableEncontrada=new VariablesEncontradas();
                                variableEncontrada.setIdVariableUnidadMedida(equivalencia.getIdVariableUnidadMedida());
                                variableEncontrada.setIdVariable(equivalencia.getIdVariable());
                                variableEncontrada.setNombreVariable(equivalencia.getNombreVariable());
                                variableEncontrada.setNombreVariableOrganizacion(equivalencia.getNombreVariableOrganizacion());
                                variableEncontrada.setNombreTipoVariable(equivalencia.getNombreTipoVariable());
                                variableEncontrada.setNombreOrganizacion(equivalencia.getNombreOrganizacion());
                                variableEncontrada.setUnidadMedida(equivalencia.getUnidadMedida());
                                variableEncontrada.setNumeroColumna(colm+1);
                                variableEncontrada.setCantidadDatos(numFilas-2);
                                variablesEncontradas.add(variableEncontrada);
                                break;
                            } else{
                                if(compararCadenasCaracteres(dato,equivalencia.getNombreVariableOrganizacion())){
                                    VariablesEncontradas variableEncontrada=new VariablesEncontradas();
                                    variableEncontrada.setIdVariableUnidadMedida(equivalencia.getIdVariableUnidadMedida());
                                    variableEncontrada.setIdVariable(equivalencia.getIdVariable());
                                    variableEncontrada.setNombreVariable(equivalencia.getNombreVariable());
                                    variableEncontrada.setNombreVariableOrganizacion(equivalencia.getNombreVariableOrganizacion());
                                    variableEncontrada.setNombreTipoVariable(equivalencia.getNombreTipoVariable());
                                    variableEncontrada.setNombreOrganizacion(equivalencia.getNombreOrganizacion());
                                    variableEncontrada.setUnidadMedida(equivalencia.getUnidadMedida());
                                    variableEncontrada.setNumeroColumna(colm+1);
                                    variableEncontrada.setCantidadDatos(numFilas-2);
                                    variablesEncontradas.add(variableEncontrada);
                                    break;
                                } 
                            }
                            
                        }
                    }
                } 
                return variablesEncontradas;
            }
        }catch(Exception e){
            System.out.println("Error "+e);
            return null;
        }
    }
    
    
     public static boolean compararCadenasCaracteres(String cadena1, String cadena2) {
        String formattedCadena1 = cadena1.replaceAll("\\s", "").toLowerCase();
        String formattedCadena2 = cadena2.replaceAll("\\s", "").toLowerCase();
        return formattedCadena1.equals(formattedCadena2);
    }


     //perfilar datos
    @PostMapping("/perfilar-datos-comprobar")
    public boolean perfilarComporbarEstructura(@RequestParam("proyectoInvestigacion") String datosJson, @RequestParam("variablesEncontradas") String datosJsonVariables, @RequestParam("file") MultipartFile file) throws JsonProcessingException{
        
        ObjectMapper objectMapper=new ObjectMapper();
        ProyectoInvestigacion oC1 = new ObjectMapper().readValue(datosJson, ProyectoInvestigacion.class);
        
        List<VariablesEncontradas> variablesEncontradas = objectMapper.readValue(datosJsonVariables, new TypeReference<List<VariablesEncontradas>>() {});
        
        List<Perfilado> listaPerfilado=new ArrayList();
        cValidaciones oF=new cValidaciones();
        int numColumnas=0;
        boolean retornoRespuesta=true;
        int numFilas=0;
        System.out.println("llega al perfilado");
        try{
            InputStream inputStream = file.getInputStream();
            Workbook archivoExcel= Workbook.getWorkbook(inputStream);
            Sheet hoja=archivoExcel.getSheet(0);
            numColumnas=hoja.getColumns();
            numFilas=hoja.getRows();
            
                for(int fila=2;fila<numFilas;fila++){
                    
                    String datoRes0=hoja.getCell(0,fila).getContents();
                    String datoRes1=hoja.getCell(1,fila).getContents();
                    String datoRes2=hoja.getCell(2,fila).getContents();
                    String datoRes3=hoja.getCell(3,fila).getContents();
                    String datoRes4=hoja.getCell(4,fila).getContents();
                    String datoRes5=hoja.getCell(5,fila).getContents();
                    String datoRes6=hoja.getCell(6,fila).getContents();
                    String datoRes7=hoja.getCell(7,fila).getContents();
                    String datoRes8=hoja.getCell(8,fila).getContents();
                    String datoRes9=hoja.getCell(9,fila).getContents();
                    String datoRes10=hoja.getCell(10,fila).getContents();
                    String datoRes11=hoja.getCell(11,fila).getContents();
                    String datoRes12=hoja.getCell(12,fila).getContents();
                    String datoRes13=hoja.getCell(13,fila).getContents();
                    String datoRes14=hoja.getCell(14,fila).getContents();
                    if(datoRes0.isEmpty() || datoRes1.isEmpty() || datoRes2.isEmpty() || datoRes3.isEmpty() || datoRes4.isEmpty() || datoRes5.isEmpty() || datoRes6.isEmpty() 
                       || datoRes7.isEmpty() || datoRes8.isEmpty() || datoRes9.isEmpty() || datoRes10.isEmpty() || datoRes11.isEmpty() || datoRes12.isEmpty() || datoRes13.isEmpty() || datoRes14.isEmpty()){
                       return false; 
                    } else{
                        try{
                            Date fechaSalida=oF.Fecha(datoRes0);
                        }catch(Exception e){
                            System.out.println("Error: "+e);
                            return false;
                        }    
                        try{
                            if(medidaService.buscarPorAbreviatura(datoRes4)==null){
                                return false;
                            }
                            if(medidaService.buscarPorAbreviatura(datoRes11)==null){
                                return false;
                            }
                            if(medidaService.buscarPorAbreviatura(datoRes14)==null){
                                return false;
                            }
                        }catch(Exception e){
                            System.out.println("Error: "+e);
                            return false;
                        }  
                    }
                }
        }catch(Exception e){
            System.out.println("Error "+e);
            return false;
        }
        return retornoRespuesta;
    }
     
//perfilar datos
    @PostMapping("/perfilar-datos")
    public List Perfilar(@RequestParam("proyectoInvestigacion") String datosJson, @RequestParam("variablesEncontradas") String datosJsonVariables, @RequestParam("file") MultipartFile file) throws JsonProcessingException{
        
        ObjectMapper objectMapper=new ObjectMapper();
        ProyectoInvestigacion oC1 = new ObjectMapper().readValue(datosJson, ProyectoInvestigacion.class);
        
        List<VariablesEncontradas> variablesEncontradas = objectMapper.readValue(datosJsonVariables, new TypeReference<List<VariablesEncontradas>>() {});
        
        List<Perfilado> listaPerfilado=new ArrayList();
        cValidaciones oF=new cValidaciones();
        int numColumnas=0;
        int numFilas=0;
        Date fechaDataset=null;
        System.out.println("llega al perfilado");
        try{
            InputStream inputStream = file.getInputStream();
            Workbook archivoExcel= Workbook.getWorkbook(inputStream);
            Sheet hoja=archivoExcel.getSheet(0);
            numColumnas=hoja.getColumns();
            numFilas=hoja.getRows();
            
            
            String dato;
            for(VariablesEncontradas variableEncontrada:variablesEncontradas) {
                
                ArrayList<outlier> datosParaOutlier1 = new ArrayList<>();
                Perfilado perfilado=new Perfilado();
                
                perfilado.setIdVariable(variableEncontrada.getIdVariable());
                perfilado.setNombreOrganizacion(variableEncontrada.getNombreOrganizacion());
                perfilado.setUnidadMedida(variableEncontrada.getUnidadMedida());
                perfilado.setNombreVariable(variableEncontrada.getNombreVariable());
                perfilado.setNombreVariableOrganizacion(variableEncontrada.getNombreVariableOrganizacion());
                perfilado.setNombreTipoVariable(variableEncontrada.getNombreTipoVariable());
                
                perfilado.setNumeroColumna(variableEncontrada.getNumeroColumna());
                perfilado.setCantidadDato(numFilas-2);
                //aqui guardar datos
                
                int contadorRestantes=0;
                List<ValorPermitido> valoresPermitidos=valorPermitidoService.obtenerPorVariableUnidadMedida(variableEncontrada.getIdVariableUnidadMedida());
                
                int contadorFaltantes=0;
                int contadorFueraRango=0;
                int contadorRepetidos=0;
                
                ArrayList<Double> datosParaOutlier = new ArrayList<>();
                
                Set<Integer> valoresUnicos = new HashSet<>();
                
                for(int fila=2;fila<numFilas;fila++){
                    
                    String datoRes1=hoja.getCell(0,fila).getContents();
                    String datoRes2=hoja.getCell(3,fila).getContents();
                    String datoRes3=hoja.getCell(4,fila).getContents();
                    String datoRes4=hoja.getCell(5,fila).getContents();
                    String datoRes5=hoja.getCell(8,fila).getContents();
                    String datoRes6=hoja.getCell(12,fila).getContents();
                    String datoRes7=hoja.getCell(13,fila).getContents();
                    String datoRes8=hoja.getCell(14,fila).getContents();
                    String datoRes9=hoja.getCell(variableEncontrada.getNumeroColumna()-1,fila).getContents();
                    if(!datoRes1.isEmpty() && !datoRes2.isEmpty() && !datoRes3.isEmpty() && !datoRes4.isEmpty() && !datoRes5.isEmpty() && !datoRes6.isEmpty() && !datoRes7.isEmpty() && !datoRes8.isEmpty() && !datoRes9.isEmpty()){
                    
                        try{
                        
                            Date fechaSalidaCampoPerf=oF.Fecha(hoja.getCell(0,fila).getContents());

                            //altura
                            Double alturaPef=Double.parseDouble(hoja.getCell(3,fila).getContents());
                            String unidadMedidaAlturaPef=hoja.getCell(4,fila).getContents();

                            //conglomerado
                            String codigoConglomeradoPef=hoja.getCell(5,fila).getContents();

                            //parcela
                            String codigoParcelaPef=hoja.getCell(8,fila).getContents();

                            //purfundidad
                            Double profundidadMinimaPef=Double.parseDouble(hoja.getCell(12,fila).getContents());
                            Double profundidadMaximaPef=Double.parseDouble(hoja.getCell(13,fila).getContents());
                            String unidadMedidaProfundidadPef=hoja.getCell(14,fila).getContents();

                            dato=hoja.getCell(variableEncontrada.getNumeroColumna()-1,fila).getContents();

                            String valorPef=hoja.getCell(variableEncontrada.getNumeroColumna()-1,fila).getContents();

                            List<DatoRecolectado> listaDatoRecolectadoRepetidos=service.findByDatasetProfundidadParcelaParcelaConglomeradoAlturaAlturaAndDatasetProfundidadParcelaParcelaConglomeradoAlturaVigenciaAndDatasetProfundidadParcelaParcelaConglomeradoAlturaUnidadMedidaAbreviaturaAndDatasetProfundidadParcelaParcelaConglomeradoCodigoConglomeradoAndDatasetProfundidadParcelaParcelaConglomeradoVigenciaAndDatasetProfundidadParcelaParcelaConglomeradoProyectoInvestigacionIdProyectoAndDatasetProfundidadParcelaParcelaCodigoParcelaAndDatasetProfundidadParcelaParcelaVigenciaAndDatasetProfundidadParcelaProfundidadProfundidadMinimaAndDatasetProfundidadParcelaProfundidadProfundidadMaximaAndDatasetProfundidadParcelaProfundidadVigenciaAndDatasetProfundidadParcelaProfundidadUnidadMedidaAbreviaturaAndDatasetFechaSalidaCampoAndVariableUnidadMedidaIdVariableUnidadMedidaAndValorAndVigencia(
                                alturaPef,
                                true,
                                unidadMedidaAlturaPef,
                                codigoConglomeradoPef,
                                true,
                                oC1.getIdProyecto(),
                                codigoParcelaPef,
                                true,
                                profundidadMinimaPef, 
                                profundidadMaximaPef,
                                true,
                                unidadMedidaProfundidadPef,
                                fechaSalidaCampoPerf,
                                variableEncontrada.getIdVariableUnidadMedida(),
                                valorPef,
                                true);

                            if(!listaDatoRecolectadoRepetidos.isEmpty()){
                                contadorRepetidos++;
                                valoresUnicos.add(fila);
                            }
                        }catch(Exception e){
                            System.out.println("Error: "+e);
                        }
                        
                    }
                    
                    
                    dato=hoja.getCell(variableEncontrada.getNumeroColumna()-1,fila).getContents();
                
                    if(dato.equals("")){
                        contadorFaltantes++;
                        valoresUnicos.add(fila);
                    }else{
                        boolean aux=false;
                        if(compararCadenasCaracteres(variableEncontrada.getNombreTipoVariable(),"Categórico")){
                            for(ValorPermitido valor:valoresPermitidos){
                                if(compararCadenasCaracteres(valor.getValorPermitido(),dato)){
                                    aux=true;
                                    contadorRestantes++;
                                    break;
                                }
                            }
                            if(aux==false){
                                valoresUnicos.add(fila);
                                contadorFueraRango++;
                                //aqui guardar la columna con problema
                            }
                        }else{
                            dato=dato.replaceAll("\\,",".");
                            float numeroFloat = Float.parseFloat(dato);
                            Double numeroDouble = Double.parseDouble(dato);
                            datosParaOutlier.add(numeroDouble);
                            outlier outlier=new outlier();
                            outlier.setDato(numeroDouble);
                            outlier.setFila(fila);
                            datosParaOutlier1.add(outlier);
                            for(ValorPermitido valor:valoresPermitidos){
                                if(numeroFloat>=valor.getValorMinimo() && numeroFloat<=valor.getValorMaximo()){
                                    aux=true;
                                    contadorRestantes++;
                                    break;
                                }
                            }
                            if(aux==false){
                                valoresUnicos.add(fila);
                                contadorFueraRango++;
                            }
                        }
                    }   
                }
                
                
                perfilado.setCantidadFueraRanngo(contadorFueraRango);
                perfilado.setCantidadNulos(contadorFaltantes); 
                perfilado.setCantidadRepetidos(contadorRepetidos); 
                if(datosParaOutlier.isEmpty()){
                    perfilado.setCantidadOutlier(0); 
                }else{
                    double[] iqrLimits = calcularIQR(datosParaOutlier);
                    
                    ArrayList<outlier> dataOut = encontrarOutliers1(datosParaOutlier1, iqrLimits);
                    perfilado.setCantidadOutlier(dataOut.size()); 
                    for(outlier valor:dataOut){
                        valoresUnicos.add(valor.getFila());
                    }
                }
                
                perfilado.setCantidadDatosCorrectos(numFilas-valoresUnicos.size());
                
                listaPerfilado.add(perfilado);
                
            }
        }catch(Exception e){
            System.out.println("Error "+e);
            return null;
        }
        return listaPerfilado;
    }
    
    public double[] calcularIQR(ArrayList<Double> data) {
        Collections.sort(data);
        int size = data.size();
        int cuartil1 = size / 4;
        int cuartil3 = cuartil1 * 3;
        double q1 = cuartil1 % 2 == 0 ? (data.get(cuartil1 - 1) + data.get(cuartil1)) / 2 : data.get(cuartil1);
        double q3 = cuartil3 % 2 == 0 ? (data.get(cuartil3 - 1) + data.get(cuartil3)) / 2 : data.get(cuartil3);
        double iqr = q3 - q1;

        double lowerLimit = q1 - 1.5 * iqr;
        double upperLimit = q3 + 1.5 * iqr;

        return new double[]{lowerLimit, upperLimit};
    }

    // Función para encontrar valores atípicos
    public int encontrarOutliers(ArrayList<Double> data, double[] limits) {
        int count = 0;
        for (Double value : data) {
            if (value < limits[0] || value > limits[1]) {
                count++;
            }
        }
        return count;
    }
    
    
    public double[] calcularIQR1(ArrayList<outlier> dataOut) {
        ArrayList<Double> data = new ArrayList<>();
        for (outlier datou : dataOut) {
            data.add(datou.getDato());
        }
        
        Collections.sort(data);
        int size = data.size();
        int cuartil1 = size / 4;
        int cuartil3 = cuartil1 * 3;
        double q1 = cuartil1 % 2 == 0 ? (data.get(cuartil1 - 1) + data.get(cuartil1)) / 2 : data.get(cuartil1);
        double q3 = cuartil3 % 2 == 0 ? (data.get(cuartil3 - 1) + data.get(cuartil3)) / 2 : data.get(cuartil3);
        double iqr = q3 - q1;

        double lowerLimit = q1 - 1.5 * iqr;
        double upperLimit = q3 + 1.5 * iqr;

        return new double[]{lowerLimit, upperLimit};
    }

    // Función para encontrar valores atípicos
    public ArrayList<outlier> encontrarOutliers1(ArrayList<outlier> data, double[] limits) {
        ArrayList<outlier> dataOut = new ArrayList<>();
        for (outlier value : data) {
            if (value.getDato() < limits[0] || value.getDato() > limits[1]) {
                dataOut.add(value);
            }
        }
        return dataOut;
    }
    
    
    
     //perfilar datos
    @PostMapping("/perfilar-archivo")
    public List PerfilarArchivo(@RequestParam("proyectoInvestigacion") String datosJson, @RequestParam("variablesEncontradas") String datosJsonVariables, @RequestParam("file") MultipartFile file) throws JsonProcessingException{
        
        ObjectMapper objectMapper=new ObjectMapper();
        ProyectoInvestigacion oC1 = new ObjectMapper().readValue(datosJson, ProyectoInvestigacion.class);
        
        List<VariablesEncontradas> variablesEncontradas = objectMapper.readValue(datosJsonVariables, new TypeReference<List<VariablesEncontradas>>() {});
        
        List<Perfilado> listaPerfilado=new ArrayList();
        cValidaciones oF=new cValidaciones();
        int numColumnas=0;
        int numFilas=0;
        Date fechaDataset=null;
        
        
        try{
            InputStream inputStream = file.getInputStream();
            Workbook archivoExcel= Workbook.getWorkbook(inputStream);
            Sheet hoja=archivoExcel.getSheet(0);
            numColumnas=hoja.getColumns();
            numFilas=hoja.getRows();
            
            String datoFecha=hoja.getCell(0,numFilas-1).getContents();
            fechaDataset=oF.Fecha(datoFecha);
            String dato;
            for(VariablesEncontradas variableEncontrada:variablesEncontradas) {
                Perfilado perfilado=new Perfilado();
                
                perfilado.setIdVariable(variableEncontrada.getIdVariable());
                perfilado.setNombreOrganizacion(variableEncontrada.getNombreOrganizacion());
                perfilado.setUnidadMedida(variableEncontrada.getUnidadMedida());
                perfilado.setNombreVariable(variableEncontrada.getNombreVariable());
                perfilado.setNombreVariableOrganizacion(variableEncontrada.getNombreVariableOrganizacion());
                perfilado.setNombreTipoVariable(variableEncontrada.getNombreTipoVariable());
                
                perfilado.setNumeroColumna(variableEncontrada.getNumeroColumna());
                perfilado.setCantidadDato(numFilas-2);
                //aqui guardar datos
                
                int contadorRestantes=0;
                List<ValorPermitido> valoresPermitidos=valorPermitidoService.obtenerPorVariableUnidadMedida(variableEncontrada.getIdVariableUnidadMedida());
                
                int contadorFaltantes=0;
                int contadorFueraRango=0;
                int contadorRepetidos=0;
                for(int fila=2;fila<numFilas;fila++){
                    
                    Date fechaSalidaCampoPerf=oF.Fecha(hoja.getCell(0,fila).getContents());
                    
                    //altura
                    Double alturaPef=Double.parseDouble(hoja.getCell(3,fila).getContents());
                    String unidadMedidaAlturaPef=hoja.getCell(4,fila).getContents();

                    //conglomerado
                    String codigoConglomeradoPef=hoja.getCell(5,fila).getContents();

                    //parcela
                    String codigoParcelaPef=hoja.getCell(8,fila).getContents();

                    //purfundidad
                    Double profundidadMinimaPef=Double.parseDouble(hoja.getCell(12,fila).getContents());
                    Double profundidadMaximaPef=Double.parseDouble(hoja.getCell(13,fila).getContents());
                    String unidadMedidaProfundidadPef=hoja.getCell(14,fila).getContents();
                    
                    
                    
                    List<Dataset> listaDatoDatasetPrueba=datasetService.findByFechaSalidaCampo(fechaSalidaCampoPerf);
                    
                    //String fechaSalidaCampoPerf=hoja.getCell(15,fila).getContents();
                    dato=hoja.getCell(variableEncontrada.getNumeroColumna()-1,fila).getContents();
                    //valor
                    String valorPef=hoja.getCell(variableEncontrada.getNumeroColumna()-1,fila).getContents();
                    
                    List<DatoRecolectado> listaDatoRecolectadoRepetidos=service.findByDatasetProfundidadParcelaParcelaConglomeradoAlturaAlturaAndDatasetProfundidadParcelaParcelaConglomeradoAlturaVigenciaAndDatasetProfundidadParcelaParcelaConglomeradoAlturaUnidadMedidaAbreviaturaAndDatasetProfundidadParcelaParcelaConglomeradoCodigoConglomeradoAndDatasetProfundidadParcelaParcelaConglomeradoVigenciaAndDatasetProfundidadParcelaParcelaConglomeradoProyectoInvestigacionIdProyectoAndDatasetProfundidadParcelaParcelaCodigoParcelaAndDatasetProfundidadParcelaParcelaVigenciaAndDatasetProfundidadParcelaProfundidadProfundidadMinimaAndDatasetProfundidadParcelaProfundidadProfundidadMaximaAndDatasetProfundidadParcelaProfundidadVigenciaAndDatasetProfundidadParcelaProfundidadUnidadMedidaAbreviaturaAndDatasetFechaSalidaCampoAndVariableUnidadMedidaIdVariableUnidadMedidaAndValorAndVigencia(
                        alturaPef,
                        true,
                        unidadMedidaAlturaPef,
                        codigoConglomeradoPef,
                        true,
                        oC1.getIdProyecto(),
                        codigoParcelaPef,
                        true,
                        profundidadMinimaPef, 
                        profundidadMaximaPef,
                        true,
                        unidadMedidaProfundidadPef,
                        fechaSalidaCampoPerf,
                        variableEncontrada.getIdVariableUnidadMedida(),
                        valorPef,
                        true);
                    
                    if(!listaDatoRecolectadoRepetidos.isEmpty()){
                        contadorRepetidos++;
                    }

                
                    if(dato.equals("")){
                        contadorFaltantes++;
                    }else{
                        boolean aux=false;
                        if(compararCadenasCaracteres(variableEncontrada.getNombreTipoVariable(),"Categórico")){
                            for(ValorPermitido valor:valoresPermitidos){
                                if(compararCadenasCaracteres(valor.getValorPermitido(),dato)){
                                    aux=true;
                                    contadorRestantes++;
                                    break;
                                }
                            }
                            if(aux==false){
                                contadorFueraRango++;
                                //aqui guardar la columna con problema
                            }
                        }else{
                            dato=dato.replaceAll("\\,",".");
                            float numeroFloat = Float.parseFloat(dato);
                            for(ValorPermitido valor:valoresPermitidos){
                                if(numeroFloat>=valor.getValorMinimo() && numeroFloat<=valor.getValorMaximo()){
                                    aux=true;
                                    contadorRestantes++;
                                    break;
                                }
                            }
                            if(aux==false){
                                contadorFueraRango++;
                            }
                        }
                    }
                    
                }
                perfilado.setCantidadDatosCorrectos(contadorRestantes);
                perfilado.setCantidadFueraRanngo(contadorFueraRango);
                perfilado.setCantidadNulos(contadorFaltantes); 
                perfilado.setCantidadRepetidos(contadorRepetidos); 
                listaPerfilado.add(perfilado);
            }
        }catch(Exception e){
            System.out.println("Error "+e);
            return null;
        }
        return listaPerfilado;
    }
    
    public boolean compararTextos(String texto1, String texto2) {
        return texto1.equalsIgnoreCase(texto2);
    }
    
    //altura
    private Double alturaDato;
    //private float alturaMinima=0;
    private String unidadMedidaAltura="";
    
    //conglomerado
    private String codigoConglomerado="";
    private String nombreConglomerado="";
    private String sector="";
    
    //parcela
    private String codigoParcela;
    private String nombreParcela;
    private float areaParcela;
    private String coordenadaX;
    private String coordenadaY;
    private String sistemaCoordenada;
    private String unidadMedidaArea;
    
    //purfundidad
    private Double profundidadMinima;
    private Double profundidadMaxima;
    private String unidadMedidaProfundidad;
    
    //dataset
    private Date fechaSalida;
    private Date fechaDataset;
    
    private String valor;
    private boolean vigencia=true;
    
     
    //registrar datos
    @PostMapping("/registrar-datos-xls")
    public void registrarXLS(@RequestParam("proyectoInvestigacion") String datosJson,@RequestParam("datasetSeleccionado") String datosDatasetJson, @RequestParam("variablesEncontradas") String datosJsonVariables, @RequestParam("file") MultipartFile file) throws JsonProcessingException{
        
        ObjectMapper objectMapper=new ObjectMapper();
        ProyectoInvestigacion oC1Aux = new ObjectMapper().readValue(datosJson, ProyectoInvestigacion.class);
        DatasetDatos oDatasetDatos1= new ObjectMapper().readValue(datosDatasetJson, DatasetDatos.class);
        List<VariablesEncontradas> variablesEncontradas = objectMapper.readValue(datosJsonVariables, new TypeReference<List<VariablesEncontradas>>() {});
        ProyectoInvestigacion oC1=new ProyectoInvestigacion();
        oC1.setIdProyecto(oC1Aux.getIdProyecto());
        List<Perfilado> perfilado=new ArrayList();
        cValidaciones cVal=new cValidaciones();
        int numColumnas=0;
        int numFilas=0;
        int l=1;
        cValidaciones oF=new cValidaciones();
        int cont=1;
        
        int controlModulos=1;
        
        Dataset dataSetRes= new Dataset();
        
        List<Dataset> datasetAux1= (List<Dataset>) datasetService.findByCodigoDatasetAndProyectoInvestigacionIdProyecto(oDatasetDatos1.getCodigoDataset(),oC1Aux.getIdProyecto());
        Dataset datasetAux=datasetAux1.get(0);
        dataSetRes.setCodigoDataset(datasetAux.getCodigoDataset());
        dataSetRes.setFechaInicioDataset(datasetAux.getFechaInicioDataset());
        dataSetRes.setFechaFinDataset(datasetAux.getFechaFinDataset());
      
        boolean boolDataset=true;
        try{
            InputStream inputStream = file.getInputStream();
            Workbook archivoExcel= Workbook.getWorkbook(inputStream);
            
            for(int hojas=0;hojas<archivoExcel.getNumberOfSheets();hojas++){
                Sheet hoja=archivoExcel.getSheet(hojas);
                
                numColumnas=hoja.getColumns();
                numFilas=hoja.getRows();
                
                String dato;
                
                String datoFecha=hoja.getCell(0,numFilas-1).getContents();
                fechaDataset=oF.Fecha(datoFecha);
                                    
                for(int fila=2;fila<numFilas;fila++){
                    
                    int controlColumnas=1;
                    Altura altura=new Altura();
                    Conglomerado conglomerado=new Conglomerado(); 
                    Area area=new Area();
                    Parcela parcela=new Parcela();
                    Profundidad profundidad=new Profundidad();
                    ProfundidadParcela profundidadParcela=new ProfundidadParcela();
                    
                    Variable variable=new Variable();
                    
                    
                
                    for(int colm=0;colm<numColumnas;colm++){
                        dato=hoja.getCell(colm,fila).getContents();
                        System.out.println("......................"+controlColumnas);
                        switch(controlColumnas){
                            
                            case 1:
                                if(dato.equals("")){fechaSalida=oF.fechaDumi();}
                                else{ fechaSalida=oF.Fecha(dato);}
                                controlColumnas=2;
                                break;
                            
                            case 2:
                                if(dato.equals("")){coordenadaX="NA";}
                                else{coordenadaX=dato.replaceAll("\\,",".");}
                                controlColumnas=3;
                                break;
                                
                            case 3:
                                if(dato.equals("")){coordenadaY="NA";}
                                else{coordenadaY=dato.replaceAll("\\,",".");}
                                controlColumnas=4;
                                break;
                                
                            case 4:
                                if(dato.equals("")){alturaDato=0.0;}
                                else{dato=dato.replaceAll("\\,","."); alturaDato=Double.parseDouble(dato);}
                                controlColumnas=5;
                                break;
                                
                            case 5:
                                if(dato.equals("")){unidadMedidaAltura="NA";}
                                else{ unidadMedidaAltura=dato.replaceAll("\\,",".");}
                                
                                List<Altura> areaLista=alturaService.buscarPorVigenciaAlturaAbreviatura(true, alturaDato, unidadMedidaAltura);
                                
                                if(areaLista.isEmpty()){
                                    System.out.println("llega al dato que si");
                                    UnidadMedida medida=(UnidadMedida) medidaService.buscarPorAbreviatura(dato);
                                    
                                    altura.setAltura(alturaDato);
                                    altura.setUnidadMedida(medida);
                                    altura.setEditable(false);
                                    altura.setFechaCreacion(cVal.fechaActual());
                                    System.out.println("llega al dato que si consulta");
                                    altura=(Altura) alturaService.guardar(altura);
                                    System.out.println("llega al dato que si consulta parte 2");
                                    
                                }else{
                                    System.out.println("llega al dato que no");
                                    altura=areaLista.get(0);
                                }
                                controlColumnas=6;
                                break;
                                
                            case 6:
                                if(dato.equals("")){codigoConglomerado="NA";}
                                else{ codigoConglomerado=dato;}
                                controlColumnas=7;
                                break;
                                
                            case 7:
                                if(dato.equals("")){nombreConglomerado="NA";}
                                else{ nombreConglomerado=dato;}
                                controlColumnas=8;
                                break;
                                
                            case 8:
                                if(dato.equals("")){sector="NA";}
                                else{ sector=dato;}
                                //if(conglomeradoService.buscarPorCodigoConglomeradoProyectoInvestigacionAlturaMaximaAlturaMinima(codigoConglomerado, oC1.getIdProyecto(), alturaMinima, alturaMaxima)!=null){
                                if(conglomeradoService.buscarPorCodigoConglomeradoProyectoInvestigacionAltura(codigoConglomerado, oC1.getIdProyecto(), altura.getIdAltura())!=null){
                                    conglomerado=(Conglomerado) conglomeradoService.buscarPorCodigoConglomeradoProyectoInvestigacionAltura(codigoConglomerado, oC1.getIdProyecto(),altura.getIdAltura());
                                }else{
                                    
                                    conglomerado.setCodigoConglomerado(codigoConglomerado);
                                    conglomerado.setNombreConglomerado(nombreConglomerado);
                                    conglomerado.setProyectoInvestigacion(oC1);
                                    conglomerado.setSector(sector);
                                    conglomerado.setAltura(altura);
                                    conglomerado.setEditable(false);
                                    conglomerado.setFechaCreacion(cVal.fechaActual());
                                    conglomerado=(Conglomerado) conglomeradoService.guardar(conglomerado);
                                }
                                controlColumnas=9;
                                break;
                                
                            case 9:
                                if(dato.equals("")){codigoParcela="NA";}
                                else{ codigoParcela=dato;}
                                controlColumnas=10;
                                break;
                                
                            case 10:
                                if(dato.equals("")){nombreParcela="NA";}
                                else{ nombreParcela=dato;}
                                controlColumnas=11;
                                break;
                                
                            case 11:
                                if(dato.equals("")){areaParcela=0;}
                                else{dato=dato.replaceAll("\\,","."); areaParcela=Float.parseFloat(dato);}
                                controlColumnas=12;
                                break;
                            case 12:
                                if(dato.equals("")){unidadMedidaArea="NA";}
                                else{ unidadMedidaArea=dato.replaceAll("\\,",".");}
                                
                                
                                if(areaService.buscarPorAreaAbreviatura(areaParcela, unidadMedidaArea)!=null){
                                    area=(Area) areaService.buscarPorAreaAbreviatura(areaParcela, unidadMedidaArea);
                                }else{
                                    UnidadMedida medida=(UnidadMedida) medidaService.buscarPorAbreviatura(unidadMedidaArea);
                                    area.setArea(areaParcela);
                                    area.setUnidadMedida(medida);
                                    area.setEditable(false);
                                    area.setFechaCreacion(cVal.fechaActual());
                                    areaService.guardar(area);
                                }
                                
                                if(parcelaService.buscarPorCodigoParcelaConglomerado(codigoParcela, conglomerado.getIdConglomerado())!=null){
                                    parcela=(Parcela) parcelaService.buscarPorCodigoParcelaConglomerado(codigoParcela, conglomerado.getIdConglomerado());
                                }else{
                                    parcela.setCodigoParcela(codigoParcela);
                                    parcela.setNombreParcela(nombreParcela);
                                    parcela.setConglomerado(conglomerado);
                                    parcela.setCoordenadaX(coordenadaX);
                                    parcela.setCoordenadaY(coordenadaY);
                                    parcela.setArea(area);
                                    parcela.setEditable(false);
                                    parcela.setFechaCreacion(cVal.fechaActual());
                                    parcela=(Parcela) parcelaService.guardar(parcela);
                                }
                                controlColumnas=13;
                                break;
                            case 13:
                                if(dato.equals("")){profundidadMinima=0.0;}
                                else{dato=dato.replaceAll("\\,",".");profundidadMinima=Double.parseDouble(dato);}
                                controlColumnas=14;
                                break;
                            case 14:
                                if(dato.equals("")){profundidadMaxima=0.0;}
                                else{dato=dato.replaceAll("\\,",".");profundidadMaxima=Double.parseDouble(dato); }
                                controlColumnas=15;
                                break;
                            case 15:
                                if(dato.equals("")){unidadMedidaProfundidad="NA";}
                                else{ unidadMedidaProfundidad=dato.replaceAll("\\,",".");}
                                
                                if(profundidadService.buscarPorProfundidadMinimaProfundidadMaximaAbreviatura(profundidadMinima, profundidadMaxima, unidadMedidaProfundidad)!=null){
                                    profundidad=(Profundidad) profundidadService.buscarPorProfundidadMinimaProfundidadMaximaAbreviatura(profundidadMinima, profundidadMaxima, unidadMedidaProfundidad);
                                }else{
                                   UnidadMedida medida=(UnidadMedida) medidaService.buscarPorAbreviatura(unidadMedidaProfundidad);
                                   profundidad.setUnidadMedida(medida);
                                   profundidad.setProfundidadMaxima(profundidadMaxima);
                                   profundidad.setProfundidadMinima(profundidadMinima);
                                   profundidad.setFechaCreacion(cVal.fechaActual());
                                   profundidad.setEditable(false);
                                   profundidad=(Profundidad) profundidadService.guardar(profundidad);
                                }
                                
                                if(profundidadParcelaService.buscarPorParcelaProfundidad(parcela.getIdParcela(), profundidad.getIdProfundidad())!=null){
                                    profundidadParcela=(ProfundidadParcela) profundidadParcelaService.buscarPorParcelaProfundidad(parcela.getIdParcela(), profundidad.getIdProfundidad());
                                }else{
                                    profundidadParcela.setIdParcela(parcela.getIdParcela());
                                    profundidadParcela.setIdProfundidad(profundidad.getIdProfundidad());
                                    profundidadParcela.setParcela(parcela);
                                    profundidadParcela.setProfundidad(profundidad);
                                    //profundidadParcela.setFechaSalidaCampo(fechaSalida);
                                    profundidadParcela=(ProfundidadParcela) profundidadParcelaService.guardar(profundidadParcela);
                                }
                                controlColumnas=16;
                                break;
                                
                            case 16:
                                for(VariablesEncontradas variableEncontrada:variablesEncontradas) {
                                    
                                    int auxCol=colm+1;
                                    if((auxCol)==variableEncontrada.getNumeroColumna()){
                                        
                                        
                                        DatoRecolectado datoRecolectado=new DatoRecolectado(); 
                                        if(variableEncontrada.getNombreTipoVariable().equals("Numérico")){
                                            if(dato.equals("")){valor="0";}
                                            else{dato=dato.replaceAll("\\,","."); valor=dato;}
                                        }else{
                                            if(dato.equals("")){valor="NA";}
                                            else{dato=dato.replaceAll("\\,","."); valor=dato;}
                                        }
                                        
                                        
                                        Dataset dataset=new Dataset();
                                        dataset.setProfundidadParcela(profundidadParcela);
                                        dataset.setFechaCreacion(cVal.fechaActual());
                                        dataset.setProyectoInvestigacion(oC1);
                                        dataset.setFechaInicioDataset(dataSetRes.getFechaInicioDataset());
                                        dataset.setFechaFinDataset(dataSetRes.getFechaFinDataset());
                                        dataset.setFechaSalidaCampo(fechaSalida);
                                        dataset.setCodigoDataset(dataSetRes.getCodigoDataset());
                                        dataset=(Dataset) datasetService.guardar(dataset);
                                        
                                        VariableUnidadMedida variableUnidadMedida=new VariableUnidadMedida();
                                        variableUnidadMedida.setIdVariableUnidadMedida(variableEncontrada.getIdVariableUnidadMedida());
                                        //variable.setIdVariable(variableEncontrada.getIdVariable());
                                        datoRecolectado.setDataset(dataset);
                                        datoRecolectado.setValor(valor);
                                        datoRecolectado.setVariableUnidadMedida(variableUnidadMedida);
                                        //datoRecolectado.setVariable(variable);
                                        datoRecolectado.setFechaCreacion(oF.fechaActual());
                                        
                                        List<TiempoEdicionDato> oLista=tiempoEdicionDatoService.findAll();
                                        int dias=1;
                                        if(!oLista.isEmpty()){
                                            TiempoEdicionDato oTiempoEdicionDato=oLista.get(0);    
                                            double tiempoDouble = oTiempoEdicionDato.getTiempo();
                                            dias = (int) Math.round(tiempoDouble);
                                        }

                                        datoRecolectado.setFechaMaximaEdicion(cVal.agregarFechaMaximaEdicion(datoRecolectado.getFechaCreacion(), dias));
                                        
                                        datoRecolectado=(DatoRecolectado) service.guardar(datoRecolectado);
                                        System.out.println("Dato registrado: "+valor);
                                    }
                                }
                                break;
                        }
                    }   
                }
             }
            archivoExcel.close();
            inputStream.close();
        }catch(Exception e){
            System.out.println("Error "+e);
        }
        
    }
    
   
   
    //Unir datos para descarga
    @PostMapping("/unir-datos")
    public List unirDatos(@RequestParam("equivalenciasVariables") String datosJsonVariables,
            @RequestParam("proyectoDatos") String datosJsonProyecto,
            @RequestParam("datasetDatos") String datosJsonDataset) throws JsonProcessingException{
        
        cValidaciones oV=new cValidaciones();
        ObjectMapper objectMapper=new ObjectMapper();
        List<VariablesEncontradas> equivalenciasVariables = objectMapper.readValue(datosJsonVariables, new TypeReference<List<VariablesEncontradas>>() {});
        ProyectoInvestigacion oC1 = new ObjectMapper().readValue(datosJsonProyecto, ProyectoInvestigacion.class);
        DatasetDatos oC2 = new ObjectMapper().readValue(datosJsonDataset, DatasetDatos.class);
        List<DatoRecolectado> listaCompleta = new ArrayList();
        List<modeloDescarga> modelosDescarga = new ArrayList();
        int aux=0;
        if(oC2.getCodigoDataset()==0){
            for(VariablesEncontradas variable:equivalenciasVariables){
                List<DatoRecolectado> listaDatoRecolectadoVariable=service.buscarPorVigenciaVariableUnidadMedidaAndProyecto(true, variable.getIdVariableUnidadMedida(), oC1.getIdProyecto());            
                listaCompleta.addAll(listaDatoRecolectadoVariable);
            }
        }else{
            for(VariablesEncontradas variable:equivalenciasVariables){
                List<DatoRecolectado> listaDatoRecolectadoVariable=service.buscarPorVigenciaVariableUnidadMedidaAndCodigoDatasetAndProyecto(true, variable.getIdVariableUnidadMedida(),oC2.getCodigoDataset(), oC1.getIdProyecto());            
                listaCompleta.addAll(listaDatoRecolectadoVariable);
            }
        }
        List<DatoRecolectado> listaCompletaAux=listaCompleta;
        
        
        for(int i=0;i<listaCompleta.size();i++){
            
            DatoRecolectado dato=listaCompletaAux.get(i);
            if(dato.getIdDatoRecolectado()!=-1){
            modeloDescarga modeloDescargaDato=new modeloDescarga();
            List<valoresDescarga> valoresDescarga = new ArrayList();
            
            //aqui se cargan todo los datos de lal datset, parcela, conglomerado, altura
            
            modeloDescargaDato.setAltura(dato.getDataset().getProfundidadParcela().getParcela().getConglomerado().getAltura().getAltura());
            modeloDescargaDato.setUnidadMedidaAltura(dato.getDataset().getProfundidadParcela().getParcela().getConglomerado().getAltura().getUnidadMedida().getAbreviatura());
            
            modeloDescargaDato.setCodigoConglomerado(dato.getDataset().getProfundidadParcela().getParcela().getConglomerado().getCodigoConglomerado());
            modeloDescargaDato.setNombreConglomerado(dato.getDataset().getProfundidadParcela().getParcela().getConglomerado().getNombreConglomerado());
            modeloDescargaDato.setSector(dato.getDataset().getProfundidadParcela().getParcela().getConglomerado().getSector());
            
            modeloDescargaDato.setCodigoParcel(dato.getDataset().getProfundidadParcela().getParcela().getCodigoParcela());
            modeloDescargaDato.setNombreParcel(dato.getDataset().getProfundidadParcela().getParcela().getNombreParcela());
            modeloDescargaDato.setCoordenadaX(dato.getDataset().getProfundidadParcela().getParcela().getCoordenadaX());
            modeloDescargaDato.setCoordenadaY(dato.getDataset().getProfundidadParcela().getParcela().getCoordenadaY());
            modeloDescargaDato.setAreaParcela(dato.getDataset().getProfundidadParcela().getParcela().getArea().getArea());
            modeloDescargaDato.setUnidadMedidaArea(dato.getDataset().getProfundidadParcela().getParcela().getArea().getUnidadMedida().getAbreviatura());
            
            
            modeloDescargaDato.setProfundidadMinima(dato.getDataset().getProfundidadParcela().getProfundidad().getProfundidadMinima());
            modeloDescargaDato.setProfundidadMaxima(dato.getDataset().getProfundidadParcela().getProfundidad().getProfundidadMaxima());
            modeloDescargaDato.setUnidadMedidaProfundidad(dato.getDataset().getProfundidadParcela().getProfundidad().getUnidadMedida().getAbreviatura());
            modeloDescargaDato.setFechaSalida(oV.formatearFechaComoString( dato.getDataset().getFechaSalidaCampo()));
            
            for(int j=0;j<listaCompletaAux.size();j++){
                
                DatoRecolectado datoAux=listaCompletaAux.get(j);
                if(datoAux.getIdDatoRecolectado()!=-1){
                valoresDescarga valorDescarga=new valoresDescarga();
                if(dato.getDataset().getProfundidadParcela().getIdProfundidad()==datoAux.getDataset().getProfundidadParcela().getIdProfundidad() && dato.getDataset().getProfundidadParcela().getIdParcela()==datoAux.getDataset().getProfundidadParcela().getIdParcela()){
                    for(VariablesEncontradas variable:equivalenciasVariables){
                        if(variable.getIdVariable()==datoAux.getVariableUnidadMedida().getVariable().getIdVariable()){
                            valorDescarga.setNombreVariable(variable.getNombreVariable());
                            break;
                        }
                    }
                    valorDescarga.setIdVariable(datoAux.getVariableUnidadMedida().getVariable().getIdVariable());
                    valorDescarga.setValor(datoAux.getValor());
                    valorDescarga.setVariableUnidadMedida(datoAux.getVariableUnidadMedida().getUnidadMedida().getAbreviatura());
                    valoresDescarga.add(valorDescarga);
                    datoAux.setIdDatoRecolectado(-1);
                    listaCompletaAux.set(j,datoAux);
                    listaCompleta.set(j,datoAux);
                    
                    }
                }
            }
            
            modeloDescargaDato.setValorDescarga(valoresDescarga);
            modelosDescarga.add(modeloDescargaDato);
            }
        }
        
        List<List<Object>> listaDeListas = new ArrayList<>();
        
        for(modeloDescarga mod:modelosDescarga){
            List<Object> lista = new ArrayList<>();
            
            lista.add(mod.getFechaSalida());
            
            lista.add(mod.getCoordenadaX());
            lista.add(mod.getCoordenadaY());
            
            lista.add(mod.getAltura());
            lista.add(mod.getUnidadMedidaAltura());
            
            lista.add(mod.getCodigoConglomerado());
            lista.add(mod.getNombreConglomerado());
            lista.add(mod.getSector());
            
            lista.add(mod.getCodigoParcel());
            lista.add(mod.getNombreParcel());
            
            lista.add(mod.getAreaParcela());
            lista.add(mod.getUnidadMedidaArea());
            
            lista.add(mod.getProfundidadMinima());
            lista.add(mod.getProfundidadMaxima());
            lista.add(mod.getUnidadMedidaProfundidad());
            
            
            
            for(valoresDescarga eq:mod.getValorDescarga()){
                lista.add(eq.getNombreVariable());
                lista.add(eq.getVariableUnidadMedida());
                lista.add(eq.getValor());
            }
            listaDeListas.add(lista);
        }
        return listaDeListas;
    }
    
    
    
    public boolean buscarElementoString(List<List<Object>> listaDeListas, String elementoBuscado) {
        for (List<Object> lista : listaDeListas) {
            for (Object obj : lista) {
                if (obj instanceof String) {
                    String elemento = (String) obj;
                    if (elemento.equals(elementoBuscado)) {
                        return true;
                    }
                }
            }
        }
        return false;
    } 
    
}
