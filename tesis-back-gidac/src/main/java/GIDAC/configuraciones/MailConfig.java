
package GIDAC.configuraciones;

import GIDAC.modelo.EmailEnvio;
import GIDAC.servicios.EmailEnvioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;


@Component
public class MailConfig {

    @Autowired
    private EmailEnvioService service;
     
    @Bean
    public JavaMailSender javaMailSender() {
        List<EmailEnvio> oLista=service.findByVigencia(true);
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        if(!oLista.isEmpty()){
            EmailEnvio emailEnvio= oLista.get(0);   
            mailSender.setHost(emailEnvio.getHost());
            mailSender.setPort(Integer.parseInt(emailEnvio.getPort()));
            mailSender.setUsername(emailEnvio.getEmail());
            mailSender.setPassword(emailEnvio.getContrasenia());
        }
        return mailSender;
    }

}