package psp.ud3_3_2_2.tcp.multithread;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

// Clase que representa el cliente TCP
public class ClienteReverseTCP {
    private String servidor;
    private int puerto;

    public ClienteReverseTCP(String servidor, int puerto) {
        this.servidor = servidor;
        this.puerto = puerto;
    }

    // Método para conectarse al servidor y enviar un mensaje
    public void conectarYEnviar(String mensaje) {
        try (Socket socket = new Socket(servidor, puerto);

             // Crear flujo de salida para enviar el mensaje al servidor
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

             // Crear el flujo de entrada para recibir información del servidor
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            System.out.println("Conectado al servidor");

            String text = "";
            Scanner scanner = new Scanner(System.in);
            do {

                System.out.print("Introduzca una palabra: ");
                System.out.flush(); // Asegura que la línea se imprima antes de recibir la entrada

                text = scanner.nextLine(); // Entrada del usuario

                // Enviar mensaje al servidor
                out.println(text);
                // Leer la respuesta del servidor
                String respuestaServidor = in.readLine();
                System.out.println("Respuesta del servidor: " + respuestaServidor);
            } while (!text.equals("bye"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}