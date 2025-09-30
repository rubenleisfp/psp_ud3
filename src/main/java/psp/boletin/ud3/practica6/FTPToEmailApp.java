package psp.boletin.ud3.practica6;

import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.Scanner;

public class FTPToEmailApp {

    public static void main(String[] args) {
        // Configuración del servidor FTP
        String ftpServidor = "127.0.0.1"; // Cambia esto por tu servidor FTP
        int ftpPuerto = 21;
        String ftpUsuario = "alumno1";
        String ftpContrasenha = "12345";

        // Configuración del servidor SMTP
        String smtpHost = "sandbox.smtp.mailtrap.io";
        int smtpPuerto = 587;
        String smtpUsuario = "CAMBIAR_POR_MI_USER";
        String smtpContrasenha = "CAMBIAR_POR_MI_PASS";

        // Crear instancias de servicios
        FTPService ftpService = new FTPService(ftpServidor, ftpPuerto, ftpUsuario, ftpContrasenha);
        SMTPService smtpService = new SMTPService(smtpHost, smtpPuerto, smtpUsuario, smtpContrasenha);

        Scanner scanner = new Scanner(System.in);

        throw new UnsupportedOperationException("A implementar por el alumno");

    }
}
