package psp.ud3_3_2_1.tcp.reverse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
            //TODO
            // 1.- Debera utilizarse Scanner para pedir una palabra al usuario.
            String text="";

            Scanner sc = new Scanner(System.in);
            do {
                System.out.println("Introduzca una palabra:");
                System.out.flush(); //Asegura que la linea se imprima antes de recibir la entrada
                text = sc.nextLine();

                // 2.- Una vez pedida habrá que enviarla por el conducto out hacia el servidor
                out.println(text);

                // 3.- Una vez recibida la palabra del servidor, debera imprimirse en pantalla
                String respuesta = in.readLine();
                System.out.println("Respuesta del servidor:" + respuesta);
                // 4.- El proceso se repetira hasta que el usuario introduzca la palabra "bye"


                // Enviar mensaje al servidor
                out.println(mensaje);
                System.out.println("Mensaje enviado al servidor: " + mensaje);

                // Leer la respuesta del servidor
                String respuestaServidor = in.readLine();
                System.out.println("Respuesta del servidor: " + respuestaServidor);
            } while (!text.equals("bye"));

        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e);
        }
    }


}
