package psp.boletin.ud3.practica2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// Clase que representa el servidor TCP
public class ServidorMultithreadDiscotecaTCP {
    private int puerto;

    private int numCliente = 0;

    public ServidorMultithreadDiscotecaTCP(int puerto) {
        this.puerto = puerto;
    }

    // Método para iniciar el servidor
    public void iniciar() {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            // Bucle infinito para aceptar múltiples conexiones de clientes
            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado");

                // Crear un nuevo hilo para manejar cada conexión de cliente
                ClienteHandler clienteHandler = new ClienteHandler(clienteSocket);
                new Thread(clienteHandler).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Clase interna para manejar la conexión con cada cliente en un hilo separado
    private static class ClienteHandler implements Runnable {
        private Socket clienteSocket;
        private static int numCliente = 0;

        private static final String CLAVE = "COIROS";

        public ClienteHandler(Socket clienteSocket) {
            this.clienteSocket = clienteSocket;

        }

        @Override
        public void run() {
            throw new UnsupportedOperationException("A implementar por el alumno");
        }
    }

    public static void main(String[] args) {
        // Crear una instancia del servidor en el puerto 1234 e iniciarlo
        ServidorMultithreadDiscotecaTCP servidor = new ServidorMultithreadDiscotecaTCP(1234);
        servidor.iniciar();
    }
}
