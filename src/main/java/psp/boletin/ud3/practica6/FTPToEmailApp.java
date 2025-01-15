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
        String ftpContraseña = "12345";

        // Configuración del servidor SMTP
        String smtpHost = "sandbox.smtp.mailtrap.io";
        int smtpPuerto = 587;
        String smtpUsuario = "637cea7bdda3ed";
        String smtpContraseña = "6d6a8e5193f986";

        // Crear instancias de servicios
        FTPService ftpService = new FTPService(ftpServidor, ftpPuerto, ftpUsuario, ftpContraseña);
        SMTPService smtpService = new SMTPService(smtpHost, smtpPuerto, smtpUsuario, smtpContraseña);

        Scanner scanner = new Scanner(System.in);

        throw new UnsupportedOperationException("A implementar por el alumno");

    }
}
