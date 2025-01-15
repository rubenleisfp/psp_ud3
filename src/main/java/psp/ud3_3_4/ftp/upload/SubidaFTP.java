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
    private static final String REMOTE_FILE_PATH  = "/subir.ftp.txt";

    public static void main(String[] args) {
        String servidor = "127.0.0.1"; // Cambia esto por tu servidor FTP
        int puerto = 21; // Asegúrate de que sea el correcto
        String usuario = "alumno1";
        String contraseña = "12345";

        FTPClient clienteFTP = new FTPClient();

        try {
            // Activar logging para depuración
            clienteFTP.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));

            // Configurar modo pasivo o activo según sea necesario
            clienteFTP.enterLocalPassiveMode();

            // Conectarse al servidor FTP
            System.out.println("Intentando conectar al servidor FTP...");
            clienteFTP.connect(servidor, puerto);
            System.out.println("Conexión establecida con el servidor FTP.");

            // Establecer tipo de archivo
            clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);

            // Iniciar sesión
            boolean login = clienteFTP.login(usuario, contraseña);
            if (login) {
                System.out.println("Conectado al servidor FTP.");

                // Subir un archivo
                File archivo = new File(LOCAL_FILE_PATH);
                try (FileInputStream fis = new FileInputStream(archivo)) {
                    boolean exito = clienteFTP.storeFile(REMOTE_FILE_PATH, fis);
                    if (exito) {
                        System.out.println("Archivo subido correctamente.");
                    } else {
                        System.out.println("Error al subir el archivo.");
                    }
                }

                // Cerrar sesión
                clienteFTP.logout();
            } else {
                System.out.println("No se pudo iniciar sesión en el servidor FTP.");
            }
        } catch (IOException e) {
            System.out.println("Error al subir por FTP: " + e);
        } finally {
            try {
                if (clienteFTP.isConnected()) {
                    clienteFTP.disconnect();
                    System.out.println("Desconectado del servidor FTP.");
                }
            } catch (IOException e) {
                System.out.println("Error al subir por FTP: " + e);
            }
        }
    }
}
