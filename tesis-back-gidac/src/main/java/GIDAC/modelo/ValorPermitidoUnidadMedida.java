package GIDAC.modelo;

public class ValorPermitidoUnidadMedida {

    private Integer idUnidadMedida;
    private String unidadMedida;
    private String abreviatura;
    private float valorMaximo;
    private float valorMinimo;
    private String ValorPermitido;

    public Integer getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public float getValorMaximo() {
        return valorMaximo;
    }

    public float getValorMinimo() {
        return valorMinimo;
    }

    public String getValorPermitido() {
        return ValorPermitido;
    }

    public void setIdUnidadMedida(Integer idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public void setValorMaximo(float valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public void setValorMinimo(float valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public void setValorPermitido(String ValorPermitido) {
        this.ValorPermitido = ValorPermitido;
    }
    
}
