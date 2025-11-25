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
        throw new UnsupportedOperationException("A completar por el estudiante");
    }
}
