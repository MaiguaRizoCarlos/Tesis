package GIDAC.servicios.impl;


import GIDAC.controladores.cValidaciones;
import GIDAC.modelo.CorreoElectronico;
import GIDAC.modelo.EmailEnvio;
import GIDAC.modelo.RespuestaSolicitudDescarga;
import GIDAC.modelo.Usuario;
import GIDAC.repositorios.EmailEnvioRepository;
import GIDAC.servicios.EmailEnvioService;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

@Service
public class EmailEnvioServiceImpl implements EmailEnvioService {

    @Autowired
    private EmailEnvioRepository repository;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JavaMailSender javaMailSender;

    public EmailEnvioServiceImpl() {
    }

    
    @Override
    public EmailEnvio save(Object objeto) {
        EmailEnvio oA=(EmailEnvio) objeto;
        cValidaciones ov = new cValidaciones();
        oA.setFechaCreacion(ov.fechaActual());
        updateJavaMailSenderConfiguration(oA);
        return repository.save(oA);
    }
    
    private void updateJavaMailSenderConfiguration(EmailEnvio emailEnvio) {
        if (javaMailSender instanceof JavaMailSenderImpl) {
            JavaMailSenderImpl mailSender = (JavaMailSenderImpl) javaMailSender;
            mailSender.setHost(emailEnvio.getHost());
            mailSender.setPort(Integer.parseInt(emailEnvio.getPort()));
            mailSender.setUsername(emailEnvio.getEmail());
            mailSender.setPassword(emailEnvio.getContrasenia());
        } else {
            throw new IllegalArgumentException("javaMailSender no es una instancia de JavaMailSenderImpl");
        }
    }
    
    @Override
    public EmailEnvio findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List findAll() {
        return repository.findAll();
    }

    @Override
    public EmailEnvio update(Object objeto) {
        EmailEnvio oA=(EmailEnvio) objeto;
        EmailEnvio oC=repository.findById(oA.getIdEmailEnvio()).orElse(null);
        oA.setFechaCreacion(oC.getFechaCreacion());
        cValidaciones ov = new cValidaciones();
        oA.setFechaActualizacion(ov.fechaActual());
        oA.setVigencia(oC.isVigencia());
        updateJavaMailSenderConfiguration(oA);
        return repository.save(oA);
    }

    @Override
    public void delete(Integer id) {
        EmailEnvio oA=repository.findById(id).orElse(null);
        cValidaciones ov = new cValidaciones();
        oA.setFechaActualizacion(ov.fechaActual());
        oA.setVigencia(false);
        repository.save(oA);
    }

    @Override
    public void restore(Integer id) {
        EmailEnvio oA=repository.findById(id).orElse(null);
        cValidaciones ov = new cValidaciones();
        oA.setFechaActualizacion(ov.fechaActual());
        oA.setVigencia(true);
        updateJavaMailSenderConfiguration(oA);
        repository.save(oA);
    }

    @Override
    public List<EmailEnvio> findByVigencia(Boolean vigencia) {
        return repository.findByVigencia(vigencia);
    }

    @Override
    public void enviarEmailResetearContrasenia(Object object)throws Exception  {
        Usuario usuario=(Usuario) object;
        List<EmailEnvio> oLista=repository.findByVigencia(true);
        if(!oLista.isEmpty()){
            EmailEnvio emailEnvio= oLista.get(0);    
            final String username = emailEnvio.getEmail(); 
            final String password = emailEnvio.getContrasenia(); 

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", emailEnvio.getHost());
            props.put("mail.smtp.port", emailEnvio.getPort());

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usuario.getEmail())); 
                message.setSubject("Reseteo de contraseña");
                
                CorreoElectronico oCor=new CorreoElectronico();
                String correoHTML = oCor.reseteoContraseniaUsuario(usuario);
    
                message.setContent(correoHTML, "text/html; charset=utf-8");
                
                Transport.send(message);
                System.out.println("El correo se ha enviado correctamente.");
            } catch (MessagingException e) {
                System.err.println("No se pudo enviar el correo 1: " + e.getMessage());
            } catch (Exception ex) {
                System.err.println("Ocurrió un error inesperado 1: " + ex.getMessage());
            }       
        }else{
            throw new Exception("No existe email para enviar");
        }
        
    }

    @Override
    public void enviarEmailRegistroUsuario(Object object) throws Exception {
        Usuario usuario=(Usuario) object;
        List<EmailEnvio> oLista=repository.findByVigencia(true);
        if(!oLista.isEmpty()){
            EmailEnvio emailEnvio= oLista.get(0);    
            final String username = emailEnvio.getEmail(); 
            final String password = emailEnvio.getContrasenia(); 

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", emailEnvio.getHost());
            props.put("mail.smtp.port", emailEnvio.getPort());

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usuario.getEmail())); 
                message.setSubject("Usuario Registrado");
                
                CorreoElectronico oCor=new CorreoElectronico();
                String correoHTML = oCor.registrarUsuario(usuario);
    
                message.setContent(correoHTML, "text/html; charset=utf-8");
                
                Transport.send(message);
                System.out.println("El correo se ha enviado correctamente.");
            } catch (MessagingException e) {
                System.err.println("No se pudo enviar el correo" + e.getMessage());
            } catch (Exception ex) {
                System.err.println("Ocurrió un error inesperado" + ex.getMessage());
            }       
        }else{
            throw new Exception("No existe email para enviar");
        }
    }

        @Override
    public void enviarEmailActualizacionPerfilUsuario(Object object) throws Exception {
        Usuario usuario=(Usuario) object;
        List<EmailEnvio> oLista=repository.findByVigencia(true);
        if(!oLista.isEmpty()){
            EmailEnvio emailEnvio= oLista.get(0);    
            final String username = emailEnvio.getEmail(); 
            final String password = emailEnvio.getContrasenia(); 

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", emailEnvio.getHost());
            props.put("mail.smtp.port", emailEnvio.getPort());

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usuario.getEmail())); 
                message.setSubject("Perfil Actualizado");
                
                CorreoElectronico oCor=new CorreoElectronico();
                String correoHTML = oCor.actualizacionPerfilUsuario(usuario);
    
                message.setContent(correoHTML, "text/html; charset=utf-8");
                
                Transport.send(message);
                System.out.println("El correo se ha enviado correctamente.");
            } catch (MessagingException e) {
                System.err.println("No se pudo enviar el correo" + e.getMessage());
            } catch (Exception ex) {
                System.err.println("Ocurrió un error inesperado" + ex.getMessage());
            }       
        }else{
            throw new Exception("No existe email para enviar");
        }
    }
    
    @Override
    public void enviarEmailActualizacionPerfilUsuarioEmailDiferente(Object object, String email) throws Exception {
        Usuario usuario=(Usuario) object;
        List<EmailEnvio> oLista=repository.findByVigencia(true);
        if(!oLista.isEmpty()){
            EmailEnvio emailEnvio= oLista.get(0);    
            final String username = emailEnvio.getEmail(); 
            final String password = emailEnvio.getContrasenia(); 

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", emailEnvio.getHost());
            props.put("mail.smtp.port", emailEnvio.getPort());

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email)); 
                message.setSubject("Perfil Actualizado");
                
                CorreoElectronico oCor=new CorreoElectronico();
                String correoHTML = oCor.actualizacionPerfilUsuario(usuario);
    
                message.setContent(correoHTML, "text/html; charset=utf-8");
                
                Transport.send(message);
                System.out.println("El correo se ha enviado correctamente.");
            } catch (MessagingException e) {
                System.err.println("No se pudo enviar el correo" + e.getMessage());
            } catch (Exception ex) {
                System.err.println("Ocurrió un error inesperado" + ex.getMessage());
            }       
        }else{
            throw new Exception("No existe email para enviar");
        }
    }


    @Override
    public void enviarEmailActualizacionUsuario(Object object) throws Exception {
        Usuario usuario=(Usuario) object;
        List<EmailEnvio> oLista=repository.findByVigencia(true);
        if(!oLista.isEmpty()){
            EmailEnvio emailEnvio= oLista.get(0);    
            final String username = emailEnvio.getEmail(); 
            final String password = emailEnvio.getContrasenia(); 

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", emailEnvio.getHost());
            props.put("mail.smtp.port", emailEnvio.getPort());

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usuario.getEmail())); 
                message.setSubject("Usuario Actualizado");
                
                CorreoElectronico oCor=new CorreoElectronico();
                String correoHTML = oCor.actualizarUsuario(usuario);
    
                message.setContent(correoHTML, "text/html; charset=utf-8");
                
                Transport.send(message);
                System.out.println("El correo se ha enviado correctamente.");
            } catch (MessagingException e) {
                System.err.println("No se pudo enviar el correo" + e.getMessage());
            } catch (Exception ex) {
                System.err.println("Ocurrió un error inesperado" + ex.getMessage());
            }       
        }else{
            throw new Exception("No existe email para enviar");
        }
    }
    
    @Override
    public void enviarEmailActualizacionUsuarioEmailDiferente(Object object, String email) throws Exception {
        Usuario usuario=(Usuario) object;
        List<EmailEnvio> oLista=repository.findByVigencia(true);
        if(!oLista.isEmpty()){
            EmailEnvio emailEnvio= oLista.get(0);    
            final String username = emailEnvio.getEmail(); 
            final String password = emailEnvio.getContrasenia(); 

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", emailEnvio.getHost());
            props.put("mail.smtp.port", emailEnvio.getPort());

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email)); 
                message.setSubject("Usuario Actualizado");
                
                CorreoElectronico oCor=new CorreoElectronico();
                String correoHTML = oCor.actualizarUsuario(usuario);
    
                message.setContent(correoHTML, "text/html; charset=utf-8");
                
                Transport.send(message);
                System.out.println("El correo se ha enviado correctamente.");
            } catch (MessagingException e) {
                System.err.println("No se pudo enviar el correo" + e.getMessage());
            } catch (Exception ex) {
                System.err.println("Ocurrió un error inesperado" + ex.getMessage());
            }       
        }else{
            throw new Exception("No existe email para enviar");
        }
    }

    @Override
    public void enviarEmailAprobarSolicitudDescarga(Object object, MultipartFile file) throws Exception {
        RespuestaSolicitudDescarga respuesta=(RespuestaSolicitudDescarga) object;
        List<EmailEnvio> oLista=repository.findByVigencia(true);
        if(!oLista.isEmpty()){
            EmailEnvio emailEnvio= oLista.get(0);    
            final String username = emailEnvio.getEmail(); 
            final String password = emailEnvio.getContrasenia(); 

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", emailEnvio.getHost());
            props.put("mail.smtp.port", emailEnvio.getPort());

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(respuesta.getSolicitudDescarga().getEmail())); 
                message.setSubject("Respuesta de solicitud de descarga");
                
                CorreoElectronico oCor=new CorreoElectronico();
                String correoHTML = oCor.aprobacionSolicitud(respuesta);
    
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(correoHTML, "text/html; charset=utf-8");

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
            
                if (file != null && !file.isEmpty()) {
                    MimeBodyPart attachmentPart = new MimeBodyPart();
                    DataSource source = new ByteArrayDataSource(file.getInputStream(), file.getContentType());
                    attachmentPart.setDataHandler(new DataHandler(source));
                    attachmentPart.setFileName(file.getOriginalFilename());
                    multipart.addBodyPart(attachmentPart);
                }

                
                //message.setContent(correoHTML, "text/html; charset=utf-8");
                
                message.setContent(multipart);
                Transport.send(message);
                System.out.println("El correo se ha enviado correctamente.");
            } catch (MessagingException e) {
                System.err.println("No se pudo enviar el correo" + e.getMessage());
                e.printStackTrace();
            } catch (Exception ex) {
                System.err.println("Ocurrió un error inesperado" + ex.getMessage());
                ex.printStackTrace();
            }       
        }else{
            throw new Exception("No existe email para enviar");
        }
    }
    
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        file.transferTo(convFile);
        return convFile;
    }

    @Override
    public void enviarEmailRechazarSolicitudDescarga(Object object) throws Exception {
        RespuestaSolicitudDescarga respuesta=(RespuestaSolicitudDescarga) object;
        List<EmailEnvio> oLista=repository.findByVigencia(true);
        if(!oLista.isEmpty()){
            EmailEnvio emailEnvio= oLista.get(0);    
            final String username = emailEnvio.getEmail(); 
            final String password = emailEnvio.getContrasenia(); 

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", emailEnvio.getHost());
            props.put("mail.smtp.port", emailEnvio.getPort());

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(respuesta.getSolicitudDescarga().getEmail())); 
                message.setSubject("Respuesta de solicitud de descarga");
                
                CorreoElectronico oCor=new CorreoElectronico();
                String correoHTML = oCor.rechazoSolicitud(respuesta);
    
                message.setContent(correoHTML, "text/html; charset=utf-8");
                
                Transport.send(message);
                System.out.println("El correo se ha enviado correctamente.");
            } catch (MessagingException e) {
                System.err.println("No se pudo enviar el correo" + e.getMessage());
            } catch (Exception ex) {
                System.err.println("Ocurrió un error inesperado" + ex.getMessage());
            }       
        }else{
            throw new Exception("No existe email para enviar");
        }
    }

    
}
