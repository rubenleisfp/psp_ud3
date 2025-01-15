package psp.boletin.ud3.practica5;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class SMTPService {
    private final String host;
    private final int puerto;
    private final String usuario;
    private final String contrasenha;

    public SMTPService(String host, int puerto, String usuario, String contrasenha) {
        this.host = host;
        this.puerto = puerto;
        this.usuario = usuario;
        this.contrasenha = contrasenha;
    }

    private Session createSession() {
        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", host);
        propiedades.put("mail.smtp.port", String.valueOf(puerto));

        return Session.getInstance(propiedades, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, contrasenha);
            }
        });
    }

    public void sendEmailWithAttachment(String remitente, String destinatario, String asunto, String contenido, String filePath)
            throws MessagingException, IOException {
       throw new UnsupportedOperationException("A implementar por el alumno");
    }
}
