/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.modelo;


public class DashDatos{
    private String indicador;
    private Double valor;
    private String unidadMedida;
    
    private boolean editable=true;
    private boolean vigencia=true;

    public boolean isEditable() {
        return editable;
    }
    
     public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isVigencia() {
        return vigencia;
    }
    
    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }
    
    public String getIndicador() {
        return indicador;
    }

    public Double getValor() {
        return valor;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

   
    
    
}