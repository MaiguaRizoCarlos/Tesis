package GIDAC.modelo;

import javax.persistence.*;
public class AreaInvestigacionAux {

    private Integer idAreaInvestigacion;

    private String nombreAreaInvestigacion;
    private boolean vigencia=true;

    public Integer getIdAreaInvestigacion() {
        return idAreaInvestigacion;
    }

    public String getNombreAreaInvestigacion() {
        return nombreAreaInvestigacion;
    }

    public void setIdAreaInvestigacion(Integer idAreaInvestigacion) {
        this.idAreaInvestigacion = idAreaInvestigacion;
    }

    public void setNombreAreaInvestigacion(String nombreAreaInvestigacion) {
        this.nombreAreaInvestigacion = nombreAreaInvestigacion;
    }

    

    public boolean isVigencia() {
        return vigencia;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

}
