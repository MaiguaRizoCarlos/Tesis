/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.modelo;

import java.util.Date;
import java.util.List;

public class modeloDescarga{
    
    
    private Double altura;
    private String unidadMedidaAltura;
    private Integer idConglomerado;
    private String codigoConglomerado;
    private String nombreConglomerado;
    private String sector;
    private Integer idParcela;
    private String codigoParcel;
    private String nombreParcel;
    private String coordenadaX;
    private String coordenadaY;
    private float areaParcela;
    private Double profundidadMinima;
    private Double profundidadMaxima;
    private String unidadMedidaArea;
    private String unidadMedidaProfundidad;
    private Integer idProfundidad;
    private Integer idDataset;
    private String fechaSalida;

    private List<valoresDescarga> valorDescarga;

    public Double getAltura() {
        return altura;
    }
    
    public void setAltura(Double altura) {
        this.altura = altura;
    }


    public Integer getIdConglomerado() {
        return idConglomerado;
    }

    public String getNombreConglomerado() {
        return nombreConglomerado;
    }

    public Integer getIdParcela() {
        return idParcela;
    }

    public String getNombreParcel() {
        return nombreParcel;
    }

    public Integer getIdProfundidad() {
        return idProfundidad;
    }

  

    public Integer getIdDataset() {
        return idDataset;
    }

    public List<valoresDescarga> getValorDescarga() {
        return valorDescarga;
    }

    

    public void setIdConglomerado(Integer idConglomerado) {
        this.idConglomerado = idConglomerado;
    }

    public void setNombreConglomerado(String nombreConglomerado) {
        this.nombreConglomerado = nombreConglomerado;
    }

    public void setIdParcela(Integer idParcela) {
        this.idParcela = idParcela;
    }

    public void setNombreParcel(String nombreParcel) {
        this.nombreParcel = nombreParcel;
    }

    public void setIdProfundidad(Integer idProfundidad) {
        this.idProfundidad = idProfundidad;
    }


    public void setIdDataset(Integer idDataset) {
        this.idDataset = idDataset;
    }

    public void setValorDescarga(List<valoresDescarga> valorDescarga) {
        this.valorDescarga = valorDescarga;
    }

 
    public String getCodigoConglomerado() {
        return codigoConglomerado;
    }

    public String getCodigoParcel() {
        return codigoParcel;
    }

    public String getCoordenadaX() {
        return coordenadaX;
    }

    public String getCoordenadaY() {
        return coordenadaY;
    }

    public float getAreaParcela() {
        return areaParcela;
    }

   

    public String getUnidadMedidaProfundidad() {
        return unidadMedidaProfundidad;
    }

  

    public void setCodigoConglomerado(String codigoConglomerado) {
        this.codigoConglomerado = codigoConglomerado;
    }

    public void setCodigoParcel(String codigoParcel) {
        this.codigoParcel = codigoParcel;
    }

    public void setCoordenadaX(String coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public void setCoordenadaY(String coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public void setAreaParcela(float areaParcela) {
        this.areaParcela = areaParcela;
    }

    public void setUnidadMedidaProfundidad(String unidadMedidaProfundidad) {
        this.unidadMedidaProfundidad = unidadMedidaProfundidad;
    }

    public String getUnidadMedidaArea() {
        return unidadMedidaArea;
    }

    public void setUnidadMedidaArea(String unidadMedidaArea) {
        this.unidadMedidaArea = unidadMedidaArea;
    }

    public String getUnidadMedidaAltura() {
        return unidadMedidaAltura;
    }

    public void setUnidadMedidaAltura(String unidadMedidaAltura) {
        this.unidadMedidaAltura = unidadMedidaAltura;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Double getProfundidadMinima() {
        return profundidadMinima;
    }

    public Double getProfundidadMaxima() {
        return profundidadMaxima;
    }

    public void setProfundidadMinima(Double profundidadMinima) {
        this.profundidadMinima = profundidadMinima;
    }

    public void setProfundidadMaxima(Double profundidadMaxima) {
        this.profundidadMaxima = profundidadMaxima;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    
    
    
}