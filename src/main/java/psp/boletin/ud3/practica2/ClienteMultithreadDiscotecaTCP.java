package psp.boletin.ud3.practica2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

// Clase que representa el cliente TCP
public class ClienteMultithreadDiscotecaTCP {
    private String servidor;
    private int puerto;

    public ClienteMultithreadDiscotecaTCP(String servidor, int puerto) {
        this.servidor = servidor;
        this.puerto = puerto;
    }

    // Método para conectarse al servidor y enviar un mensaje
    public void conectarYEnviar() {
        try (Socket socket = new Socket(servidor, puerto);


             // Crear flujo de salida para enviar el mensaje al servidor
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

             // Crear el flujo de entrada para recibir información del servidor
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            System.out.println("Conectado al servidor");

            String text = "";
            Scanner scanner = new Scanner(System.in);


                System.out.print("Introduzca la palabra clave: ");
                System.out.flush(); // Asegura que la línea se imprima antes de recibir la entrada

                text = scanner.nextLine(); // Entrada del usuario

                // Enviar mensaje al servidor
                out.println(text);
                // Leer la respuesta del servidor
                String respuestaServidor = in.readLine();
                System.out.println("Respuesta del servidor: " + respuestaServidor);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
