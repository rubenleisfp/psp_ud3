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

    public void sendEmailWithAttachment(String remitente, String destinatario, String asunto, String contenido, String filePath)
            throws MessagingException, IOException {
        // Crear sesión
        Session sesion = createSession();

        // Crear el mensaje
        MimeMessage mensaje = new MimeMessage(sesion);
        mensaje.setFrom(new InternetAddress(remitente));
        mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
        mensaje.setSubject(asunto);

        // Crear el cuerpo del mensaje
        MimeBodyPart textoParte = new MimeBodyPart();
        textoParte.setText(contenido);

        // Crear la parte del adjunto
        MimeBodyPart adjuntoParte = new MimeBodyPart();
        File archivo = new File(filePath);

        // Validar si el archivo existe antes de adjuntarlo
        if (!archivo.exists() || !archivo.isFile()) {
            throw new IOException("El archivo no existe o no es válido: " + filePath);
        }
        adjuntoParte.attachFile(archivo);

        // Combinar texto y adjunto en un multipart
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textoParte);
        multipart.addBodyPart(adjuntoParte);

        // Establecer el contenido del mensaje
        mensaje.setContent(multipart);

        // Enviar el mensaje
        Transport.send(mensaje);

        System.out.println("Correo enviado con adjunto: " + archivo.getName());
    }
}
