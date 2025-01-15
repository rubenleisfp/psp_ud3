package psp.ud3_3_2_2.tcp.multithread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// Clase que representa el servidor TCP
public class ServidorMultithreadTCP {
    private int puerto;

    public ServidorMultithreadTCP(int puerto) {
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

        public ClienteHandler(Socket clienteSocket) {
            this.clienteSocket = clienteSocket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clienteSocket.getOutputStream(), true)) {

                String text;
                do {
                    // Leer el mensaje del cliente
                    text = in.readLine();

                    // Convertir el mensaje a mayúsculas
                    String reverseText = new StringBuilder(text).reverse().toString();

                    // Enviar la respuesta modificada al cliente
                    out.println(reverseText);
                } while (!text.equals("bye"));

                System.out.println("Cliente desconectado");

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clienteSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
