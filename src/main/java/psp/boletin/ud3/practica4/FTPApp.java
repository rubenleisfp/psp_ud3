package psp.boletin.ud3.practica4;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FTPApp {


    FTPService ftpService;

    public static void main(String[] args) {
        FTPApp ftpApp = new FTPApp();
        ftpApp.run();
    }

    private void mostrarMenu() {
        System.out.println("\n=== Menú de Operaciones FTP ===");
        System.out.println("1. Listar archivos en el servidor");
        System.out.println("2. Subir un archivo al servidor");
        System.out.println("3. Descargar un archivo del servidor");
        System.out.println("4. Mostrar tamaño total de ficheros del raiz");
        System.out.println("5. Salir");
        System.out.print("Elige una opción: ");
    }

    public void run() {
        String servidor = "127.0.0.1"; // Cambia esto por tu servidor FTP
        int puerto = 21; // FTPS usa el mismo puerto que FTP por defecto
        String usuario = "alumno1";
        String contraseña = "12345";
        // Crear instancia de FTPService
        ftpService = new FTPService(servidor, puerto, usuario, contraseña);

        try {
            // Conectar al servidor FTP
            ftpService.connect();

            // Crear un menú interactivo
            Scanner scanner = new Scanner(System.in);

            String remoteFilePath;
            String localFilePath;
            int opcion;
            do {
                mostrarMenu();
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        System.out.println("\nArchivos en el servidor:");
                        List<String> fileNames = ftpService.listNames();
                        for (String fileName: fileNames) {
                            System.out.println("FileName: " + fileName);
                        }
                        break;
                    case 2:
                        System.out.print("\nIntroduce la ruta local del archivo a subir: ");
                        localFilePath = scanner.nextLine();
                        System.out.print("Introduce la ruta remota donde se subirá el archivo: ");
                        remoteFilePath = scanner.nextLine();


                        if (ftpService.uploadFile(localFilePath, remoteFilePath)) {
                            System.out.println("Archivo subido correctamente.");
                        } else {
                            System.out.println("Error al subir el archivo.");
                        }

                        break;
                    case 3:
                        System.out.print("\nIntroduce la ruta remota del archivo a descargar: ");
                        remoteFilePath = scanner.nextLine();
                        System.out.print("Introduce la ruta local donde se guardará el archivo: ");
                        localFilePath = scanner.nextLine();


                        if (ftpService.downloadFile(remoteFilePath, localFilePath)) {
                            System.out.println("Archivo descargado correctamente.");
                        } else {
                            System.out.println("Error al descargar el archivo.");
                        }

                        break;
                    case 4:
                        List<FileData> fileDataList = ftpService.getFileData("/");
                        long totalSize = 0;
                        for (FileData fileData: fileDataList){
                            totalSize = totalSize+fileData.getSize();
                        }
                        System.out.println("Tamaño total de los ficheros raiz:" + totalSize);
                        break;
                    case 5:
                        System.out.println("Saliendo de la aplicación...");
                        break;
                    default:
                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            } while (opcion != 5);

            // Desconectar del servidor
            ftpService.disconnect();
        } catch (IOException e) {
            System.err.println("Error al trabajar con el servidor FTP: " + e.getMessage());
        }
    }


}
