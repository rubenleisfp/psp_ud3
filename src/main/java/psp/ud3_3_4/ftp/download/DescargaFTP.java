package psp.ud3_3_4.ftp.download;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class DescargaFTP {

    private static final String DOWNLOAD_FILE_PATH = "src/main/resources/ftp/descargado_ftp.txt";
    private static final String REMOTE_FILE_NAME = "subida_ftp.txt"; // Nombre del fichero en DriveHQ

    public static void main(String[] args) {
        String servidor = "ftp.drivehq.com";
        int puerto = 21;
        String usuario = "rubenleisfp";
        String contrasenha = "MI_PASS";

        FTPClient clienteFTP = new FTPClient();

        try {
            clienteFTP.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));

            // 1) Primero conectar
            System.out.println("Intentando conectar al servidor FTP...");
            clienteFTP.connect(servidor, puerto);
            System.out.println("Conexión establecida con el servidor FTP.");

            // 2) Después activar pasivo
            clienteFTP.enterLocalPassiveMode();

            // 3) Login
            boolean login = clienteFTP.login(usuario, contrasenha);
            if (login) {
                System.out.println("Conectado al servidor FTP.");

                // 4) Tipo de archivo
                clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);

                // 5) Descargar archivo
                File archivoDescargado = new File(DOWNLOAD_FILE_PATH);
                try (FileOutputStream fos = new FileOutputStream(archivoDescargado)) {

                    boolean exito = clienteFTP.retrieveFile(REMOTE_FILE_NAME, fos);

                    if (exito) {
                        System.out.println("Archivo descargado correctamente: " + archivoDescargado.getAbsolutePath());
                    } else {
                        System.out.println("Error al descargar archivo. Respuesta: " + clienteFTP.getReplyString());
                    }
                }

                clienteFTP.logout();
            } else {
                System.out.println("No se pudo iniciar sesión en el servidor FTP.");
            }
        } catch (IOException e) {
            System.err.println("Error al descargar el archivo: " + e.getMessage());
        } finally {
            try {
                if (clienteFTP.isConnected()) {
                    clienteFTP.disconnect();
                    System.out.println("Desconectado del servidor FTP.");
                }
            } catch (IOException e) {
                System.err.println("Error al desconectar del servidor FTP: " + e.getMessage());
            }
        }
    }
}
