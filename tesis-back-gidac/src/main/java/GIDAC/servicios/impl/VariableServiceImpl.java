
package GIDAC.servicios.impl;


import GIDAC.controladores.cValidaciones;
import GIDAC.modelo.CatalogoOrganizacion;
import GIDAC.modelo.Organizacion;
import GIDAC.modelo.Variable;
import GIDAC.repositorios.CatalogoOrganizacionRepository;
import GIDAC.repositorios.OrganizacionRepository;
import GIDAC.repositorios.VariableRepository;
import GIDAC.servicios.VariableService;
import java.io.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bouncycastle.dvcs.DVCSRequestInfo;

//--------------------------------------------------
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font.FontFamily;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
//--------------------------------------------------


@Service
public class VariableServiceImpl implements VariableService {

    @Autowired
    private VariableRepository repository;
    
    @Autowired
    private CatalogoOrganizacionRepository catalogoOrganizacionRepository;
    
    @Autowired
    private OrganizacionRepository organizacionRepository;

    @Override
    public Variable guardar(Object objeto) {
         Variable oA=(Variable) objeto;
        return repository.save(oA);
    }

    @Override
    public Variable buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List buscarTodos() {
        return repository.findAll();
    }

    @Override
    public void eliminar(Integer id) {
        List<CatalogoOrganizacion> oC=catalogoOrganizacionRepository.findByVigenciaAndVariableIdVariable(true, id);
        cValidaciones val=new cValidaciones();
        for(CatalogoOrganizacion dato:oC){
            dato.setFechaActualizacion(val.fechaActual());
            dato.setVigencia(false);
            catalogoOrganizacionRepository.save(dato);
        }
        Variable variable=repository.findById(id).orElse(null);
        variable.setVigencia(false);
        variable.setFechaActualizacion(val.fechaActual());
        repository.save(variable);
    }
    
    @Override
    public void activar(Integer id) {
        List<CatalogoOrganizacion> oC=catalogoOrganizacionRepository.findByVigenciaAndVariableIdVariable(false, id);
        cValidaciones val=new cValidaciones();
        for(CatalogoOrganizacion dato:oC){
            dato.setVigencia(true);
            dato.setFechaActualizacion(val.fechaActual());
            catalogoOrganizacionRepository.save(dato);
            Organizacion oranizacion=organizacionRepository.findByVigenciaAndIdOrganizacion(false,dato.getOrganizacion().getIdOrganizacion());
            if(oranizacion!=null){
                oranizacion.setFechaActualizacion(val.fechaActual());
                oranizacion.setVigencia(true);
                organizacionRepository.save(oranizacion);
            }
        }
        Variable variable=repository.findById(id).orElse(null);
        variable.setFechaActualizacion(val.fechaActual());
        variable.setVigencia(true);
        repository.save(variable);
        
        
    }

    @Override
    public List<Object[]>  litsarVairbalesCompletas() {
        return repository.obtenerVariablesCompletas();
    }

    
    @Override
    public List<Object[]>  litsarVairbalesCompletasInvestigador() {
        return repository.obtenerVariablesCompletasInvestigador();
    }

    
    @Override
    public List<Object[]>  litsarVairbalesIncompletas() {
        return repository.obtenerVariablesImcompletas();
    }

    @Override
    public List<Object[]>  litsarVairbalesConDatosSinFamilia() {
        return repository.obtenerVariablesConDatosSinFamilia();
    }

    @Override
    public List<Object[]>  litsarVairbalesConDatosConFiltroFamilia(Integer idFamilia) {
        return repository.obtenerVariablesConDatosFiltradoPorFamilia(idFamilia);
    }
    
    @Override
    public List<Object[]>  litsarVairbalesConDatosSinFamiliaOrganizacion(Integer idOrganizacion) {
        return repository.obtenerVariablesConDatosSinFamiliaOrganizacion(idOrganizacion);
    }

    @Override
    public List<Object[]>  litsarVairbalesConDatosConFiltroFamiliaOrganizacion(Integer idFamilia, Integer idOrganizacion) {
        return repository.obtenerVariablesConDatosFiltradoPorFamiliaOrganizacion(idFamilia, idOrganizacion);
    }

    @Override
    public List<Object[]>  litsarVairbalesConDatosSinFamiliaInvestigador(Integer idProyecto) {
        return repository.obtenerVariablesConDatosSinFamiliaInvestigador(idProyecto);
    }

    @Override
    public List<Object[]>  litsarVairbalesConDatosConFiltroFamiliaInvestigador(Integer idFamilia, Integer idProyecto) {
        return repository.obtenerVariablesConDatosFiltradoPorFamiliaInvestigador(idFamilia,idProyecto);
    }

    @Override
    public List<Object[]> listarCatalogoParaPerfilado() {
        return repository.obtenerVariablesParaCatalogo();
    }
    
    @Override
    public List<Object[]> listarCatalogoParaPerfiladoPorProyecto(Integer id) {
        return repository.obtenerVariablesParaCatalogoProProyeyecto(id);
    }
    
    @Override
    public List<Object[]> listarCatalogoParaPerfiladoPorProyectoOganizacion(Integer id, Integer idOrganizacion) {
        return repository.obtenerVariablesParaCatalogoProProyeyectoOrganizacion(id,idOrganizacion);
    }

    @Override
    public List buscarPorVigencia(Boolean vigencia) {
        return repository.findByVigencia(vigencia);
    }

    @Override
    public List buscarPorVigenciaAndCodigoVariable(Boolean vigencia, String codigoVariable) {
        return repository.findByVigenciaAndCodigoVariable(vigencia, codigoVariable);
    }

    @Override
    public List listarCatalogoParaPerfiladoPorProyectoCodigoDataset(Integer id, Integer codigoDataset) {
        return repository.obtenerVariablesParaCatalogoProProyeyectoCodigoDataset(id, codigoDataset);
    }

    @Override
    public List listarCatalogoParaPerfiladoPorProyectoOganizacionCodigoDataset(Integer id, Integer idOrganizacion, Integer codigoDataset) {
        return repository.obtenerVariablesParaCatalogoProProyeyectoOrganizacionCodigoDataset(id,idOrganizacion, codigoDataset);
    }

    @Override
    public Object findByVigenciaAndIdVariableAndCodigoVariable(Boolean vigencia, Integer idVariable, String codigoVariable) {
        return repository.findByVigenciaAndIdVariableAndCodigoVariable(vigencia,idVariable, codigoVariable);
    }
    
    //Crear PDF
    @Override
     public byte[] dowloadPdf() {
        List<Object[]> datos = catalogoOrganizacionRepository.obtenerDatosDescargar();
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Document document = new Document(PageSize.A4.rotate()); 
            PdfWriter.getInstance(document, outputStream);
            document.open();
            Font font = new Font(FontFamily.HELVETICA, 13, Font.BOLD, BaseColor.BLACK);
            
            // 3 columnas para Nombre, Apellido y Cédula
            PdfPTable table = new PdfPTable(8); 
            
            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);
            Font fontSubtitle = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.NORMAL, BaseColor.DARK_GRAY);

            // Añade título al documento
            Paragraph title = new Paragraph("EcoAndes", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);

            // Añade subtítulo al documento
            Paragraph subtitle = new Paragraph("Catálogo de datos", fontSubtitle);
            subtitle.setAlignment(Element.ALIGN_CENTER);

            document.add(title);
            document.add(subtitle);
            
            Chunk chunk = new Chunk("\n");
            document.add(chunk);
            
            // Añade encabezados de columna
            PdfPCell cell = new PdfPCell(new Phrase("Código variable", font));
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Nombre variable", font));
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Tipo variable", font));
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Código variable organización", font));
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Nombre variable organización", font));
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Organización", font));
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Unidad de medida abreviatura", font));
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Unidad medida", font));
            table.addCell(cell);

            for (Object[] dato : datos) {
                table.addCell((String) dato[0]);
                table.addCell((String) dato[1]);
                table.addCell((String) dato[2]);
                table.addCell((String) dato[3]);
                table.addCell((String) dato[4]);
                table.addCell((String) dato[5]);
                table.addCell((String) dato[6]);
                table.addCell((String) dato[7]);
            }
            document.add(table);
            document.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //Crear Excel
    @Override
    public byte[] dowloadExcel() {
        List<Object[]> datos = catalogoOrganizacionRepository.obtenerDatosDescargar();
        try{
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Sheet sheet = workbook.createSheet("catalogo");

            // Crear encabezados
            Row headerRow = sheet.createRow(0);
            
            String[] columnas = {"Código variable", "Nombre variable", "Tipo variable", "Código variable organización", "Nombre variable organizacion", "Organización", "Unidad de medida abreviatura", "Unidad_medida"};
            for (int i = 0; i < columnas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnas[i]);
            }

            // Agregar datos 
            
            for (int i = 0; i < datos.size(); i++) {
                Row row = sheet.createRow(i + 1);
                Object[] dato = datos.get(i);
                row.createCell(0).setCellValue((String) dato[0]);
                row.createCell(1).setCellValue((String) dato[1]);
                row.createCell(2).setCellValue((String) dato[2]);
                row.createCell(3).setCellValue((String) dato[3]);
                row.createCell(4).setCellValue((String) dato[4]);
                row.createCell(5).setCellValue((String) dato[5]);
                row.createCell(6).setCellValue((String) dato[6]);
                row.createCell(7).setCellValue((String) dato[7]);
            }

            for (int i = 0; i < columnas.length; i++) {
                sheet.autoSizeColumn(i);
            }
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }
    
}
