package GIDAC.controladores;


import static GIDAC.controladores.DatoRecolectadoController.compararCadenasCaracteres;
import GIDAC.modelo.DatoRecolectado;
import GIDAC.modelo.Perfilado;
import GIDAC.modelo.ProyectoInvestigacion;
import GIDAC.modelo.ValorPermitido;
import GIDAC.modelo.VariablesEncontradas;
import GIDAC.modelo.outlier;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import GIDAC.servicios.UsuarioService;
import GIDAC.servicios.UnidadMedidaService;
import GIDAC.servicios.VariableService;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import org.apache.poi.ss.usermodel.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import org.apache.poi.xssf.usermodel.XSSFColor;


@RestController
@RequestMapping("/dato-recolectado-archivo")
@CrossOrigin("*")
public class DatoRecolectadoArchivoController {

    
    
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


  
    @PostMapping("/cambiar-color")
    public ResponseEntity<byte[]> cambiarColor(@RequestParam("proyectoInvestigacion") String datosJson, @RequestParam("variablesEncontradas") String datosJsonVariables, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            
            ObjectMapper objectMapper=new ObjectMapper();
            ProyectoInvestigacion oC1 = new ObjectMapper().readValue(datosJson, ProyectoInvestigacion.class);

            List<VariablesEncontradas> variablesEncontradas = objectMapper.readValue(datosJsonVariables, new TypeReference<List<VariablesEncontradas>>() {});

            List<Perfilado> listaPerfilado=new ArrayList();
            
            cValidaciones oF=new cValidaciones();
            // Cargar el archivo Excel
            InputStream inputStream = file.getInputStream();
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream); // Cambio aquí a HSSFWorkbook
            
            // Resto del código para cambiar el color de las celdas
            // ...
            
            Sheet sheet = workbook.getSheetAt(0);

            // Obtener la última fila con datos en la columna seleccionada
            int ultimaFilaConDatos = sheet.getLastRowNum();

            System.out.println("dato: " + ultimaFilaConDatos);

            
        

            // Obtener el valor de color como un short
            short colorIndex = workbook.getCustomPalette().findSimilarColor(20, 200, 20).getIndex();

            CellStyle styleCorrecto = workbook.createCellStyle();
            styleCorrecto.setFillForegroundColor(colorIndex);
            styleCorrecto.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            colorIndex = workbook.getCustomPalette().findSimilarColor(74, 70, 70).getIndex();
            CellStyle styleNulo = workbook.createCellStyle();
            styleNulo.setFillForegroundColor(colorIndex);
            styleNulo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            
            colorIndex = workbook.getCustomPalette().findSimilarColor(174, 7, 70).getIndex();
            CellStyle styleFueraRango = workbook.createCellStyle();
            styleFueraRango.setFillForegroundColor(colorIndex);
            styleFueraRango.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            
            colorIndex = workbook.getCustomPalette().findSimilarColor(255, 179, 26).getIndex();
            CellStyle styleRepetido = workbook.createCellStyle();
            styleRepetido.setFillForegroundColor(colorIndex);
            styleRepetido.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            
            colorIndex = workbook.getCustomPalette().findSimilarColor(0, 133, 163).getIndex();
            CellStyle styleOutlaier = workbook.createCellStyle();
            styleOutlaier.setFillForegroundColor(colorIndex);
            styleOutlaier.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            
            colorIndex = workbook.getCustomPalette().findSimilarColor(0, 0, 0).getIndex();
            CellStyle styleDatoPrincipal = workbook.createCellStyle();
            styleDatoPrincipal.setFillForegroundColor(colorIndex);
            styleDatoPrincipal.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            
            for (int j = 0; j <= 14; j++) {
                for (int i = 2; i <= ultimaFilaConDatos; i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        Cell cell = row.getCell(j);
                        if (cell != null) {
                            cell.setCellStyle(styleCorrecto);
                        }
                    }
                }
            }
            
            for (int j = 0; j <= 14; j++) {
                for (int i = 2; i <= ultimaFilaConDatos; i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        Cell cell = row.getCell(j);
                        String datoPrincipal=cell.getStringCellValue();
                        if(!datoPrincipal.isEmpty()){
                            if(j==0){
                                try{
                                    Date fechaSalida=oF.Fecha(datoPrincipal);
                                }catch(Exception e){
                                    cell.setCellStyle(styleDatoPrincipal);
                                    System.out.println("Error: "+e);
                                }    
                            }else if(j==4 || j==11 || j==14){
                                try{
                                    if(medidaService.buscarPorAbreviatura(datoPrincipal)==null){
                                        cell.setCellStyle(styleDatoPrincipal);
                                    }
                                }catch(Exception e){
                                    cell.setCellStyle(styleDatoPrincipal);
                                    System.out.println("Error: "+e);
                                }  
                            }
                        }else{
                            cell.setCellStyle(styleDatoPrincipal);
                        }   
                    }
                }
            }
            
            
            
            for(VariablesEncontradas variableEncontrada:variablesEncontradas) {
                int columnaAColor = variableEncontrada.getNumeroColumna()-1;
                ArrayList<outlier> datosParaOutlier = new ArrayList<>();
                List<ValorPermitido> valoresPermitidos=valorPermitidoService.obtenerPorVariableUnidadMedida(variableEncontrada.getIdVariableUnidadMedida());
                
                //Marcar correctas
                for (int i = 2; i <= ultimaFilaConDatos; i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        Cell cell = row.getCell(columnaAColor);
                        if (cell != null) {
                            cell.setCellStyle(styleCorrecto);
                        }
                    }
                }
                
                //Buscar repetidos
                for (int i = 2; i <= ultimaFilaConDatos; i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        
                        Cell cell = row.getCell(0);
                        String datoRes1=cell.getStringCellValue();
                        
                        cell = row.getCell(3);
                        String datoRes2=cell.getStringCellValue();
                        
                        cell = row.getCell(4);
                        String datoRes3=cell.getStringCellValue();
                        
                        cell = row.getCell(5);
                        String datoRes4=cell.getStringCellValue();
                        
                        cell = row.getCell(8);
                        String datoRes5=cell.getStringCellValue();
                        
                        cell = row.getCell(12);
                        String datoRes6=cell.getStringCellValue();
                        
                        cell = row.getCell(13);
                        String datoRes7=cell.getStringCellValue();
                        
                        cell = row.getCell(14);
                        String datoRes8=cell.getStringCellValue();
                        
                        cell = row.getCell(columnaAColor);
                        String datoRes9=cell.getStringCellValue();
                        
                        if(!datoRes1.isEmpty() && !datoRes2.isEmpty() && !datoRes3.isEmpty() && !datoRes4.isEmpty() && !datoRes5.isEmpty() && !datoRes6.isEmpty() && !datoRes7.isEmpty() && !datoRes8.isEmpty() && !datoRes9.isEmpty()){
                            try{
                                Date fechaSalidaCampoPerf=oF.Fecha(datoRes1);
                                Double alturaPef=Double.parseDouble(datoRes2);
                                String unidadMedidaAlturaPef=datoRes3;
                                String codigoConglomeradoPef=datoRes4;
                                String codigoParcelaPef=datoRes5;
                                Double profundidadMinimaPef=Double.parseDouble(datoRes6);
                                Double profundidadMaximaPef=Double.parseDouble(datoRes7);
                                String unidadMedidaProfundidadPef=datoRes8;
                                String valorPef=datoRes9;
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
                                    cell.setCellStyle(styleRepetido); 
                                }
                            }catch(Exception e){
                                System.out.println("Error: "+e);
                            }
                        }
                        
                        
                    }
                } 
                
                
                //Buscar nulos
                for (int i = 2; i <= ultimaFilaConDatos; i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        Cell cell = row.getCell(columnaAColor);
                        if (cell != null) {
                            String dato = cell.getStringCellValue();
                            if(dato.isEmpty()){
                                cell.setCellStyle(styleNulo);    
                            }else{
                                //buscar fuera de rango
                                boolean aux=false;
                                if(compararCadenasCaracteres(variableEncontrada.getNombreTipoVariable(),"Categórico")){
                                    for(ValorPermitido valor:valoresPermitidos){
                                        if(compararCadenasCaracteres(valor.getValorPermitido(),dato)){
                                            aux=true;
                                            break;
                                        }
                                    }
                                    if(aux==false){
                                        cell.setCellStyle(styleFueraRango); 
                                    }
                                }else{
                                    dato=dato.replaceAll("\\,",".");
                                    float numeroFloat = Float.parseFloat(dato);
                                    Double numeroDouble = Double.parseDouble(dato);
                                    outlier outlier=new outlier();
                                    outlier.setDato(numeroDouble);
                                    outlier.setFila(i);
                                    datosParaOutlier.add(outlier);
                                    for(ValorPermitido valor:valoresPermitidos){
                                        if(numeroFloat>=valor.getValorMinimo() && numeroFloat<=valor.getValorMaximo()){
                                            aux=true;
                                            break;
                                        }
                                    }
                                    if(aux==false){
                                        cell.setCellStyle(styleFueraRango); 
                                    }
                                }

                            }
                        }
                    }
                }
                
                if(!datosParaOutlier.isEmpty()){
                    double[] iqrLimits = calcularIQR(datosParaOutlier);
                    ArrayList<outlier> dataOut = encontrarOutliers(datosParaOutlier, iqrLimits);
                    
                    for(outlier valor:dataOut){
                        Row row = sheet.getRow(valor.getFila());
                        if (row != null) {
                            Cell cell = row.getCell(columnaAColor);
                            if (cell != null) {
                                cell.setCellStyle(styleOutlaier);
                            }
                        }
                    }
                }
                
                
            }
            
            
            
            

            // Crear un ByteArrayOutputStream para guardar el archivo en memoria
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();

            // Convertir el ByteArrayOutputStream a un array de bytes
            byte[] fileContent = outputStream.toByteArray();

            // Definir los encabezados para la respuesta HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
            headers.setContentDispositionFormData("attachment", "archivo_editado.xls");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    
   
    
    public double[] calcularIQR(ArrayList<outlier> dataOut) {
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
    public ArrayList<outlier> encontrarOutliers(ArrayList<outlier> data, double[] limits) {
        ArrayList<outlier> dataOut = new ArrayList<>();
        for (outlier value : data) {
            if (value.getDato() < limits[0] || value.getDato() > limits[1]) {
                dataOut.add(value);
            }
        }
        return dataOut;
    }
    
}
