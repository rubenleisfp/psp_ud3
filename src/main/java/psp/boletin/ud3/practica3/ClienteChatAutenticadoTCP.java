package psp.boletin.ud3.practica3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteChatAutenticadoTCP {
    private String servidor;
    private int puerto;

    public ClienteChatAutenticadoTCP(String servidor, int puerto) {
        this.servidor = servidor;
        this.puerto = puerto;
    }

    public void iniciar() {
        try (Socket socket = new Socket(servidor, puerto);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Crear un hilo para leer mensajes del servidor (otros clientes)
            new Thread(new LeerMensajes(in)).start();

            // Captura el input del usuario y lo envía al servidor
            Scanner scanner = new Scanner(System.in);
            String mensaje;
            System.out.println("Escribe tus mensajes (escribe 'bye' para salir):");
            do {
                mensaje = scanner.nextLine();
                out.println(mensaje); // Enviar mensaje al servidor
            } while (!mensaje.equalsIgnoreCase("bye"));

        } catch (IOException e) {
            System.err.println("Error al conectar al servidor: " + e.getMessage());
        }
    }

    // Clase para leer los mensajes del servidor (mensajes de otros clientes)
    private static class LeerMensajes implements Runnable {
        private BufferedReader in;

        public LeerMensajes(BufferedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            String mensaje;
            try {
                // Leer mensajes del servidor y mostrarlos en la consola
                while ((mensaje = in.readLine()) != null) {
                    System.out.println(mensaje);
                }
            } catch (IOException e) {
                System.err.println("Error al recibir mensajes del servidor: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        ClienteChatAutenticadoTCP cliente = new ClienteChatAutenticadoTCP("localhost", 12345);
        cliente.iniciar();
    }
}
