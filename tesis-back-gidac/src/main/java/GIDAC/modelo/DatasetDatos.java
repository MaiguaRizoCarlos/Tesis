/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class DatasetDatos{
   
    private Integer codigoDataset;
    private Date fechaInicioDataset;
    private Date fechaFinDataset;
    
    public DatasetDatos() {
        // Constructor sin argumentos
    }

    public DatasetDatos(Integer codigoDataset, Date fechaInicioDataset, Date fechaFinDataset) {
        this.codigoDataset = codigoDataset;
        this.fechaInicioDataset = fechaInicioDataset;
        this.fechaFinDataset = fechaFinDataset;
    }
    
    

    public Date getFechaInicioDataset() {
        return fechaInicioDataset;
    }

    public void setFechaInicioDataset(Date fechaInicioDataset) {
        this.fechaInicioDataset = fechaInicioDataset;
    }

    public Date getFechaFinDataset() {
        return fechaFinDataset;
    }

    public void setFechaFinDataset(Date fechaFinDataset) {
        this.fechaFinDataset = fechaFinDataset;
    }

    

    public Integer getCodigoDataset() {
        return codigoDataset;
    }

    public void setCodigoDataset(Integer codigoDataset) {
        this.codigoDataset = codigoDataset;
    }

   
    
}
