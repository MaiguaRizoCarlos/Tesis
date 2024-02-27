package GIDAC.servicios.impl;


import GIDAC.controladores.cValidaciones;
import GIDAC.modelo.CorreoElectronico;
import GIDAC.modelo.EmailEnvio;
import GIDAC.modelo.RespuestaSolicitudDescarga;
import GIDAC.modelo.TiempoEdicionDato;
import GIDAC.modelo.Usuario;
import GIDAC.repositorios.EmailEnvioRepository;
import GIDAC.repositorios.TiempoEdicionDatoRepository;
import GIDAC.servicios.EmailEnvioService;
import GIDAC.servicios.TiempoEdicionDatoService;
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
public class TiempoEdicionDatoServiceImpl implements TiempoEdicionDatoService {

    @Autowired
    private TiempoEdicionDatoRepository repository;

    
    @Override
    public TiempoEdicionDato save(Object objeto) {
        TiempoEdicionDato oA=(TiempoEdicionDato) objeto;
        cValidaciones ov = new cValidaciones();
        oA.setFechaCreacion(ov.fechaActual());
        return repository.save(oA);
    }
    
    @Override
    public TiempoEdicionDato findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List findAll() {
        return repository.findAll();
    }

    @Override
    public TiempoEdicionDato update(Object objeto) {
        TiempoEdicionDato oA=(TiempoEdicionDato) objeto;
        TiempoEdicionDato oC=repository.findById(oA.getIdTiempoEdicionDato()).orElse(null);
        oA.setFechaCreacion(oC.getFechaCreacion());
        cValidaciones ov = new cValidaciones();
        oA.setFechaActualizacion(ov.fechaActual());
        oA.setVigencia(oC.isVigencia());
        return repository.save(oA);
    }

    @Override
    public void delete(Integer id) {
        TiempoEdicionDato oA=repository.findById(id).orElse(null);
        cValidaciones ov = new cValidaciones();
        oA.setFechaActualizacion(ov.fechaActual());
        oA.setVigencia(false);
        repository.save(oA);
    }

    @Override
    public void restore(Integer id) {
        TiempoEdicionDato oA=repository.findById(id).orElse(null);
        cValidaciones ov = new cValidaciones();
        oA.setFechaActualizacion(ov.fechaActual());
        oA.setVigencia(true);
        repository.save(oA);
    }

    @Override
    public List<TiempoEdicionDato> findByVigencia(Boolean vigencia) {
        return repository.findByVigencia(vigencia);
    }
    
}
