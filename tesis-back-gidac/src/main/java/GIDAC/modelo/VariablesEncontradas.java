/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.modelo;

public class VariablesEncontradas{
    
    private Integer idVariableUnidadMedida;
    private Integer idVariable;
    private String codigoVariable;
    private Integer numeroColumna;
    private Integer cantidadDatos;
    private String nombreVariable;
    private String nombreOrganizacion;
    private String nombreVariableOrganizacion;
    private String nombreTipoVariable;
    private String unidadMedida;

    public String getCodigoVariable() {
        return codigoVariable;
    }

    public void setCodigoVariable(String codigoVariable) {
        this.codigoVariable = codigoVariable;
    }

    
    
    
    public void setIdVariable(Integer idVariable) {
        this.idVariable = idVariable;
    }

    public void setNumeroColumna(Integer numeroColumna) {
        this.numeroColumna = numeroColumna;
    }

    public Integer getIdVariable() {
        return idVariable;
    }

    public Integer getNumeroColumna() {
        return numeroColumna;
    }

    public void setNombreVariable(String nombreVariable) {
        this.nombreVariable = nombreVariable;
    }

    public void setNombreTipoVariable(String nombreTipoVariable) {
        this.nombreTipoVariable = nombreTipoVariable;
    }

    public String getNombreVariable() {
        return nombreVariable;
    }

    public String getNombreTipoVariable() {
        return nombreTipoVariable;
    }

    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public void setNombreOrganizacion(String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }

    public void setCantidadDatos(Integer cantidadDatos) {
        this.cantidadDatos = cantidadDatos;
    }

    public Integer getCantidadDatos() {
        return cantidadDatos;
    }

    public Integer getIdVariableUnidadMedida() {
        return idVariableUnidadMedida;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setIdVariableUnidadMedida(Integer idVariableUnidadMedida) {
        this.idVariableUnidadMedida = idVariableUnidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getNombreVariableOrganizacion() {
        return nombreVariableOrganizacion;
    }

    public void setNombreVariableOrganizacion(String nombreVariableOrganizacion) {
        this.nombreVariableOrganizacion = nombreVariableOrganizacion;
    }

    
    
    
    
}