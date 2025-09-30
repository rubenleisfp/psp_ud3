package psp.ud3_3_2_1.tcp.basico;

import java.io.*;
import java.net.*;

// Clase que representa el cliente TCP
public class ClienteTCP {
    private String servidor;
    private int puerto;

    public ClienteTCP(String servidor, int puerto) {
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

            // Enviar mensaje al servidor
            out.println(mensaje);
            System.out.println("Mensaje enviado al servidor: " + mensaje);

            // Leer la respuesta del servidor
            String respuestaServidor = in.readLine();
            System.out.println("Respuesta del servidor: " + respuestaServidor);

        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e);
        }
    }


}
