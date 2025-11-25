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
       throw new UnsupportedOperationException("A completar por el estudiante");
    }
}
