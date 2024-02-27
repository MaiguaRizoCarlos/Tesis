package GIDAC.modelo;

public class JwtRequest {

    private String email;
    private String contrasenia;

    public JwtRequest(){

    }

    public JwtRequest(String email, String contrasenia) {
        this.email = email;
        this.contrasenia = contrasenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    
}
