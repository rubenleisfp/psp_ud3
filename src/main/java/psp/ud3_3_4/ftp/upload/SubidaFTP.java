package psp.ud3_3_4.ftp.upload;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class SubidaFTP {

    private static final String LOCAL_FILE_PATH  = "src/main/resources/ftp/subir_ftp.txt";
    private static final String REMOTE_FILE_PATH  = "subir_ftp.txt";

    public static void main(String[] args) {
        String servidor = "ftp.drivehq.com";
        int puerto = 21;
        String usuario = "rubenleisfp";
        String contrasenha = "MI_PASS";

        FTPClient clienteFTP = new FTPClient();

        try {
            clienteFTP.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));

            // 1) Conectar primero
            System.out.println("Intentando conectar al servidor FTP...");
            clienteFTP.connect(servidor, puerto);
            System.out.println("Conexión establecida con el servidor FTP.");

            // 2) Solo después activar pasivo
            clienteFTP.enterLocalPassiveMode();

            // Tipo de archivo
            clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);

            // 3) Login
            if (clienteFTP.login(usuario, contrasenha)) {
                System.out.println("Conectado al servidor FTP.");

                // 4) Subir archivo
                File archivo = new File(LOCAL_FILE_PATH);
                try (FileInputStream fis = new FileInputStream(archivo)) {
                    boolean ok = clienteFTP.storeFile(REMOTE_FILE_PATH, fis);
                    if (ok) {
                        System.out.println("Archivo subido correctamente.");
                    } else {
                        System.out.println("Error al subir archivo. Respuesta: " + clienteFTP.getReplyString());
                    }
                }

                clienteFTP.logout();
            } else {
                System.out.println("No se pudo iniciar sesión en el servidor FTP.");
            }

        } catch (IOException e) {
            System.err.println("Error FTP: " + e.getMessage());
        } finally {
            try {
                if (clienteFTP.isConnected()) {
                    clienteFTP.disconnect();
                    System.out.println("Desconectado del servidor FTP.");
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }
}
