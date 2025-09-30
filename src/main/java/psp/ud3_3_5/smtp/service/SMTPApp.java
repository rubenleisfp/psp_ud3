package psp.ud3_3_5.smtp.service;

public class SMTPApp {

    public static void main(String[] args) {
        SMTPApp smtpApp = new SMTPApp();
        smtpApp.run();
    }

    public void run() {
        // Configuración del servidor SMTP
        String host = "sandbox.smtp.mailtrap.io"; // Cambia esto por tu servidor SMTP
        int puerto = 587;
        String usuario = "CAMBIAR_POR_MI_USER";
        String contrasenha = "CAMBIAR_POR_MI_PASS";

        // Crear instancia de SMTPService
        SMTPService smtpService = new SMTPService(host, puerto, usuario, contrasenha);

        // Datos del correo
        String remitente = "remitente@ejemplo.com";
        String destinatario = "rubenleis@gmail.com";
        String asunto = "Prueba de SMTP";
        String contenido = "Este es un correo de prueba enviado desde Java usando SMTP.";

        // Enviar el correo
        try {
            smtpService.sendEmail(remitente, destinatario, asunto, contenido);
            System.out.println("Correo enviado exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}
