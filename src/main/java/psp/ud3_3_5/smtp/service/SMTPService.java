package psp.ud3_3_5.smtp.service;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class SMTPService {
    private final String host;
    private final int puerto;
    private final String usuario;
    private final String contraseña;

    public SMTPService(String host, int puerto, String usuario, String contraseña) {
        this.host = host;
        this.puerto = puerto;
        this.usuario = usuario;
        this.contraseña = contraseña;
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
                return new PasswordAuthentication(usuario, contraseña);
            }
        });
    }

    public void sendEmail(String remitente, String destinatario, String asunto, String contenido) throws MessagingException {
        Session sesion = createSession();

        // Crear el mensaje
        Message mensaje = new MimeMessage(sesion);
        mensaje.setFrom(new InternetAddress(remitente));
        mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
        mensaje.setSubject(asunto);
        mensaje.setText(contenido);

        // Enviar el mensaje
        Transport.send(mensaje);
    }
}
