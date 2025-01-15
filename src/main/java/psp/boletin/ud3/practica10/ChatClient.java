package psp.boletin.ud3.practica10;

import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Conectado al servidor. Escribe tus mensajes:");

            // Hilo para enviar mensajes
            new Thread(() -> {
                //TODO
                //1. Crear un printWriter a partir del socket
                //2. Pedir datos por teclado al usuario, mientras el socket no esté cerrado
                //3. Enviar el mensaje que habeis recibido por consola a través del printWriter
                throw new UnsupportedOperationException("A completar por el alumno");

            }).start();

            // Hilo para recibir mensajes
            new Thread(() -> {
                //TODO
                //1. Crear bufferReader a partir del inputStream del socket
                //2. Mientras la lectura de lineas del mensajes no sea nula, realizamos un bucle
                //3. Dentro del bucle imprimimos el mensaje recibido
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Mensaje recibido: " + message);
                    }
                } catch (SocketException e) {
                    System.out.println("Conexión cerrada por el servidor.");
                } catch (IOException e) {
                    System.out.println("Error al recibir mensajes: " + e.getMessage());
                }
            }).start();
        } catch (IOException e) {
            System.out.println("Error al conectar con el servidor: " + e.getMessage());
        }
    }
}
