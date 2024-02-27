package GIDAC.modelo;


public class VariableDifusion {
    private Integer idVariable;
    private String codigoVariable;
    private String nombreVariable;
    private String nombreSIglas;
    private String nombreOrganizacion;

    public String getNombreVariable() {
        return nombreVariable;
    }

    public String getNombreSIglas() {
        return nombreSIglas;
    }

    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public void setNombreVariable(String nombreVariable) {
        this.nombreVariable = nombreVariable;
    }

    public void setNombreSIglas(String nombreSIglas) {
        this.nombreSIglas = nombreSIglas;
    }

    public void setNombreOrganizacion(String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }

    public Integer getIdVariable() {
        return idVariable;
    }

    public String getCodigoVariable() {
        return codigoVariable;
    }

    public void setIdVariable(Integer idVariable) {
        this.idVariable = idVariable;
    }

    public void setCodigoVariable(String codigoVariable) {
        this.codigoVariable = codigoVariable;
    }
    
    
}
