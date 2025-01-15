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

        try {
            // Conectarse al servidor FTP
            ftpService.connect();

            // Descargar archivo
            System.out.print("Introduce la ruta remota del archivo en el servidor FTP: ");
            //String remoteFilePath = scanner.nextLine();
            String remoteFilePath = "archivo_servidor.txt";
            System.out.println(remoteFilePath);

            System.out.print("Introduce la ruta local donde se guardará el archivo: ");
            //String localFilePath = scanner.nextLine();
            String localFilePath = "src/main/resources/ftp/archivo_servidor.txt";
            System.out.println(remoteFilePath);

            if (ftpService.downloadFile(remoteFilePath, localFilePath)) {
                System.out.println("Archivo descargado correctamente desde el servidor FTP.");

                // Pedir información del correo
                System.out.print("Introduce el remitente del correo: ");
//                String remitente = scanner.nextLine();
                String remitente = "noreply@chios.com";
                System.out.println(remitente);

                System.out.print("Introduce el destinatario del correo: ");
  //              String destinatario = scanner.nextLine();
                String destinatario = "alumnos@chios.com";
                System.out.println(destinatario);

                System.out.print("Introduce el asunto del correo: ");
    //            String asunto = scanner.nextLine();
                String asunto = "Todos aprobados!";
                System.out.println(asunto);

                System.out.print("Introduce el contenido del correo: ");
                //String contenido = scanner.nextLine();
                String contenido = "Era bromita!";
                System.out.println(contenido);

                // Enviar el archivo descargado como adjunto
                smtpService.sendEmailWithAttachment(remitente, destinatario, asunto, contenido, localFilePath);
                System.out.println("Correo enviado exitosamente con el archivo adjunto.");
            } else {
                System.out.println("Error al descargar el archivo desde el servidor FTP.");
            }

            // Desconectar del servidor FTP
            ftpService.disconnect();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
