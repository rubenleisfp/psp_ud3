package psp.ud3_3_4.ftp.download;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class DescargaFTP {

    private static final String DOWNLOAD_FILE_PATH = "src/main/resources/ftp/descargado_ftp.txt"; // Ruta local donde se guardará el archivo descargado
    private static final String REMOTE_FILE_NAME = "subir_ftp.txt"; // Nombre del archivo en el servidor

    public static void main(String[] args) {
        String servidor = "127.0.0.1"; // Dirección del servidor FTPS
        int puerto = 21; // Puerto estándar para FTPS
        String usuario = "alumno1";
        String contrasenha = "12345";

        // Crear el cliente FTPS
        FTPClient clienteFTP = new FTPClient();

        try {
            // Activar logging para depuración
            clienteFTP.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));

            // Configurar modo pasivo o activo según sea necesario
            clienteFTP.enterLocalPassiveMode();

            // Conectarse al servidor FTPS
            System.out.println("Intentando conectar al servidor FTPS...");
            clienteFTP.connect(servidor, puerto);
            System.out.println("Conexión establecida con el servidor FTPS.");

            // Iniciar sesión
            boolean login = clienteFTP.login(usuario, contrasenha);
            if (login) {
                System.out.println("Conectado al servidor FTPS.");

                //clienteFTPS.execPROT("P");
                // Configurar el tipo de archivo
                clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);

                // Descargar un archivo del servidor
                File archivoDescargado = new File(DOWNLOAD_FILE_PATH);
                try (FileOutputStream fos = new FileOutputStream(archivoDescargado)) {
                    boolean exito = clienteFTP.retrieveFile(REMOTE_FILE_NAME, fos);
                    if (exito) {
                        System.out.println("Archivo descargado correctamente: " + archivoDescargado.getAbsolutePath());
                    } else {
                        System.out.println("Error al descargar el archivo.");
                    }
                }

                // Cerrar sesión
                clienteFTP.logout();
            } else {
                System.out.println("No se pudo iniciar sesión en el servidor FTPS.");
            }
        } catch (IOException e) {
            System.err.println("Error al descargar el archivo: " + e.getMessage());
        } finally {
            try {
                if (clienteFTP.isConnected()) {
                    clienteFTP.disconnect();
                    System.out.println("Desconectado del servidor FTPS.");
                }
            } catch (IOException e) {
                System.err.println("Error al desconectar del servidor FTPS: " + e.getMessage());
            }
        }
    }
}
