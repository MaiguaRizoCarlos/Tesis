/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GIDAC.servicios;


import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author My Notebook
 */
public interface EmailEnvioService <T> {
    public T save(T objeto);
    public T update(T objeto);
    public T findById(Integer id);
    public void delete(Integer id);
    public void restore(Integer id);
    public void enviarEmailResetearContrasenia(T object)throws Exception ;
    public void enviarEmailRegistroUsuario(T object)throws Exception ;
    public void enviarEmailActualizacionUsuario(T object)throws Exception ;
    public void enviarEmailActualizacionUsuarioEmailDiferente(T object, String email)throws Exception ;
    public void enviarEmailActualizacionPerfilUsuario(T object)throws Exception ;
    public void enviarEmailActualizacionPerfilUsuarioEmailDiferente(T object, String email)throws Exception ;
    public void enviarEmailAprobarSolicitudDescarga(T object, MultipartFile file)throws Exception ;
    public void enviarEmailRechazarSolicitudDescarga(T object)throws Exception ;
    public List<T> findAll();
    public List<T>  findByVigencia(Boolean vigencia);
}
