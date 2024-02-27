package GIDAC.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    
    private String nombreUsuario;
    private String apellidoUsuario;
    private String email;
    private String contrasenia;
    private String telefono;
    private String cedula;
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private boolean vigencia=true;
    @Lob
    private byte[] imagenPerfil;
    
    

    @ManyToOne
    @JoinColumn(name = "id_rol")
    @JsonBackReference
    private Rol rol;
    
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "usuario")
    @JsonIgnore
    private Set<GrupoInvestigacion> grupoInvestigacion = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "usuario")
    @JsonIgnoreProperties("usuario")
    private Set<Acceso> acceso = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "usuario")
    @JsonIgnoreProperties("usuario")
    private Set<EmailEnvio> emailEnvio = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "usuario")
    @JsonIgnoreProperties("usuario")
    private Set<TiempoEdicionDato> tiempoEdicionDato = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "usuario")
    @JsonIgnoreProperties("usuario")
    private Set<InformacionEcoAndes> informacionEcoAndes = new HashSet<>();
    
   
//    @OneToMany(mappedBy = "usuario")
//    @JsonManagedReference 
//    private Set<GrupoInvestigacion> grupoInvestigacion;

    public Usuario(){

    }

    public Usuario( Integer idUsuario, String email, String contrasenia, String nombreUsuario, String apellidoUsuario, boolean estado) {
        this.idUsuario=idUsuario;
        this.email = email;
        this.contrasenia = contrasenia;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.vigencia = estado;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        Set<Authority> autoridades = new HashSet<>();
        if (this.rol != null) {
            autoridades.add(new Authority(this.rol.getNombreRol()));
        }
            
        return autoridades;
     
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getContrasenia() {
        return this.contrasenia;
    }
     
    public Set<Acceso> getAcceso() {
        return acceso;
    }

    public void setAcceso(Set<Acceso> acceso) {
        this.acceso = acceso;
    }
    
    public byte[] getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(byte[] imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Set<GrupoInvestigacion> getGrupoInvestigacion() {
        return grupoInvestigacion;
    }

    public void setGrupoInvestigacion(Set<GrupoInvestigacion> grupoInvestigacion) {
        this.grupoInvestigacion = grupoInvestigacion;
    }


    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.contrasenia;
    }
    
    public boolean isVigencia() {
        return vigencia;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    @Override
    public boolean isEnabled() {
        return vigencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCedula() {
        return cedula;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Set<InformacionEcoAndes> getInformacionEcoAndes() {
        return informacionEcoAndes;
    }

    public void setInformacionEcoAndes(Set<InformacionEcoAndes> informacionEcoAndes) {
        this.informacionEcoAndes = informacionEcoAndes;
    }

    public Set<EmailEnvio> getEmailEnvio() {
        return emailEnvio;
    }

    public void setEmailEnvio(Set<EmailEnvio> emailEnvio) {
        this.emailEnvio = emailEnvio;
    }

    public Set<TiempoEdicionDato> getTiempoEdicionDato() {
        return tiempoEdicionDato;
    }

    public void setTiempoEdicionDato(Set<TiempoEdicionDato> tiempoEdicionDato) {
        this.tiempoEdicionDato = tiempoEdicionDato;
    }
    
    
    
}