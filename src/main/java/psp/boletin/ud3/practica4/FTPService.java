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

        // Logging para depuración
        clienteFTP.addProtocolCommandListener(
                new PrintCommandListener(new PrintWriter(System.out), true)
        );

        //DriveHQ exige PASV en el cliente
        clienteFTP.enterLocalPassiveMode();

        System.out.println("Intentando conectar al servidor FTP...");
        clienteFTP.connect(servidor, puerto);

        if (!clienteFTP.login(usuario, contrasenha)) {
            throw new IOException("No se pudo iniciar sesión en el servidor FTP.");
        }

        System.out.println("Conectado al servidor FTP.");

        // Tipo binario obligatorio para cualquier archivo
        clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);
    }

    public List<String> listNames() throws IOException {
        String[] names = clienteFTP.listNames();

        if (names == null) {
            System.out.println("El servidor devolvió NULL para listNames(). Puede ser un directorio vacío.");
            return List.of();
        }

        return Arrays.asList(names);
    }

    public boolean uploadFile(String localFilePath, String remoteFilePath) throws IOException {
        File localFile = new File(localFilePath);

        if (!localFile.exists()) {
            System.out.println("El archivo no existe: " + localFilePath);
            return false;
        }

        try (FileInputStream fis = new FileInputStream(localFile)) {

            // DriveHQ necesita modo binario
            clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);

            System.out.println("Subiendo archivo: " + localFilePath);

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

            System.out.println("Descargando archivo: " + remoteFilePath);

            boolean success = clienteFTP.retrieveFile(remoteFilePath, fos);

            if (success) {
                System.out.println("Archivo descargado correctamente: " + remoteFilePath +
                        " -> " + localFilePath);
            } else {
                System.out.println("Error al descargar el archivo: " + remoteFilePath);
            }

            return success;
        }
    }

    public List<FileData> getFileData(String remotePath) throws IOException {
        FTPFile[] ftpFiles = clienteFTP.listFiles(remotePath);

        List<FileData> fileDataList = new ArrayList<>();

        for (FTPFile file : ftpFiles) {
            if (file.isFile()) {
                FileData fileData = new FileData();
                fileData.setName(file.getName());

                LocalDate localDate = file.getTimestamp().getTime()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();

                fileData.setLocalDate(localDate);
                fileData.setSize(file.getSize());

                fileDataList.add(fileData);
            }
        }

        return fileDataList;
    }

    public void disconnect() throws IOException {
        if (clienteFTP.isConnected()) {
            clienteFTP.logout();
            clienteFTP.disconnect();
            System.out.println("Desconectado del servidor FTP.");
        }
    }
}
