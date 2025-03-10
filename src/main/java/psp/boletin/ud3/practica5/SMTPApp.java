package psp.boletin.ud3.practica5;

public class SMTPApp {

    private static final String ATTACHMENT_FILE_PATH = "src/main/resources/smtp/fichero_adjunto.txt";

    public static void main(String[] args) {
        SMTPApp smtpApp = new SMTPApp();
        smtpApp.run();
    }

    public void run() {
        // Configuración del servidor SMTP
        String host = "sandbox.smtp.mailtrap.io"; // Cambia esto por tu servidor SMTP
        int puerto = 587;
        String usuario = "MI_USER";
        String contraseña = "MI_PASS";

        // Crear instancia de SMTPService
        SMTPService smtpService = new SMTPService(host, puerto, usuario, contraseña);

        // Datos del correo
        String remitente = "remitente@ejemplo.com";
        String destinatario = "rubenleis@gmail.com";
        String asunto = "Prueba de SMTP";
        String contenido = "Este es un correo de prueba enviado desde Java usando SMTP.";

        // Enviar el correo
        try {
            smtpService.sendEmailWithAttachment(remitente, destinatario, asunto, contenido,ATTACHMENT_FILE_PATH);
            System.out.println("Correo enviado exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}
