package psp.boletin.ud3.practica10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Conectado al servidor. Escribe tus mensajes:");

            // Hilo para enviar mensajes
            new Thread(() -> {
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    Scanner scanner = new Scanner(System.in);
                    while (!socket.isClosed()) {
                        String message = scanner.nextLine();
                        out.println(message);
                    }
                } catch (IOException e) {
                    System.out.println("Error al enviar mensajes: " + e.getMessage());
                }
            }).start();

            // Hilo para recibir mensajes
            new Thread(() -> {
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
