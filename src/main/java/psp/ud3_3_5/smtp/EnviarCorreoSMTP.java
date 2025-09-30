package psp.ud3_3_5.smtp;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class EnviarCorreoSMTP {

    public static void main(String[] args) {
        // Configuración del servidor SMTP
        String host ="sandbox.smtp.mailtrap.io"; // Cambia esto por tu servidor SMTP
        String puerto = "587";
        String usuario = "CAMBIAR_POR_MI_USUARIO";
        String contrasenha = "CAMBIAR_POR_MI_PASS";

        // Propiedades de la conexión
        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", host);
        propiedades.put("mail.smtp.port", puerto);

        // Crear sesión
        Session sesion = Session.getInstance(propiedades, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, contrasenha);
            }
        });

        try {
            // Crear mensaje
            Message mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress("remitente@ejemplo.com"));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse("rubenleis@gmail.com"));
            mensaje.setSubject("Prueba de SMTP");
            mensaje.setText("Este es un correo de prueba enviado desde Java usando SMTP.");

            // Enviar el mensaje
            Transport.send(mensaje);
            System.out.println("Correo enviado exitosamente.");
        } catch (MessagingException e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}
