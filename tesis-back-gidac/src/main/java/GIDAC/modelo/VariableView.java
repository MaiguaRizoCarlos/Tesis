package GIDAC.modelo;


public class VariableView {

    private String idVariable;
    private String nombreVariable;
    private String siglas;
    

    public String getIdVariable() {
        return idVariable;
    }

    public void setIdVariable(String idVariable) {
        this.idVariable = idVariable;
    }

    public String getNombreVariable() {
        return nombreVariable;
    }

    public void setNombreVariable(String nombreVariable) {
        this.nombreVariable = nombreVariable;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getSiglas() {
        return siglas;
    } 
}
