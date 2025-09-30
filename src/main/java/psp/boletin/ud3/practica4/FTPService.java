package psp.boletin.ud3.practica4;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FTPService {
    private final String servidor;
    private final int puerto;
    private final String usuario;
    private final String contrasenha;
    private final FTPClient clienteFTP;

    public FTPService(String servidor, int puerto, String usuario, String contrasenha) {
        this.servidor = servidor;
        this.puerto = puerto;
        this.usuario = usuario;
        this.contrasenha = contrasenha;
        this.clienteFTP = new FTPClient();
    }

    public void connect() throws IOException {
        // Activar logging para depuración
        clienteFTP.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));
        clienteFTP.enterLocalPassiveMode();

        // Conectar al servidor
        System.out.println("Intentando conectar al servidor FTP...");
        clienteFTP.connect(servidor, puerto);
        if (clienteFTP.login(usuario, contrasenha)) {
            System.out.println("Conectado al servidor FTP.");
        } else {
            throw new IOException("No se pudo iniciar sesión en el servidor FTP.");
        }
    }


    public List<String> listNames() throws IOException {
        return Arrays.stream(clienteFTP.listNames()).toList();
    }

    public boolean uploadFile(String localFilePath, String remoteFilePath) throws IOException {
        File localFile = new File(localFilePath);
        try (FileInputStream fis = new FileInputStream(localFile)) {
            clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);
            boolean success = clienteFTP.storeFile(remoteFilePath, fis);
            if (success) {
                System.out.println("Archivo subido correctamente: " + localFilePath + " -> " + remoteFilePath);
            } else {
                System.out.println("Error al subir el archivo: " + localFilePath);
            }
            return success;
        }
    }

    public boolean downloadFile(String remoteFilePath, String localFilePath) throws IOException {
        File localFile = new File(localFilePath);
        try (FileOutputStream fos = new FileOutputStream(localFile)) {
            clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);
            boolean success = clienteFTP.retrieveFile(remoteFilePath, fos);
            if (success) {
                System.out.println("Archivo descargado correctamente: " + remoteFilePath + " -> " + localFilePath);
            } else {
                System.out.println("Error al descargar el archivo: " + remoteFilePath);
            }
            return success;
        }
    }

    public  List<FileData> getFileData(String remotePath) throws IOException {
        //1.- Itera sobre la lista de ficheros obtenidos del FTP: https://commons.apache.org/proper/commons-net/apidocs/org/apache/commons/net/ftp/FTPClient.html#listFiles()
        //2.- Por cada fichero, crea un objeto FileData
        //3.- Devuelve una lista de FileData
        throw new UnsupportedOperationException("A completar por el alumno");
    }

    public void disconnect() throws IOException {
        if (clienteFTP.isConnected()) {
            clienteFTP.logout();
            clienteFTP.disconnect();
            System.out.println("Desconectado del servidor FTP.");
        }
    }
}
