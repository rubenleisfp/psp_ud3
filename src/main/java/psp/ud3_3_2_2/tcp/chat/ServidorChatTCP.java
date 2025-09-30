package psp.ud3_3_2_2.tcp.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ServidorChatTCP {
    private int puerto;
    private Set<PrintWriter> clientes = new HashSet<>(); // Para almacenar los flujos de salida de los clientes

    public ServidorChatTCP(int puerto) {
        this.puerto = puerto;
    }

    public void iniciar() {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor de chat escuchando en el puerto " + puerto);

            // Bucle para aceptar múltiples conexiones de clientes
            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado");

                // Crear un nuevo hilo para manejar la conexión del cliente
                new Thread(new ClienteHandler(clienteSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Error al conectar al servidor: " + e.getMessage());
        }
    }

    // Clase interna para manejar la conexión con cada cliente
    private class ClienteHandler implements Runnable {
        private Socket clienteSocket;
        private PrintWriter out;

        public ClienteHandler(Socket clienteSocket) {
            this.clienteSocket = clienteSocket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()))) {

                // Flujo de salida para el cliente
                out = new PrintWriter(clienteSocket.getOutputStream(), true);

                // Añadir al cliente a la lista de clientes
                synchronized (clientes) {
                    clientes.add(out);
                }

                String mensaje;
                // Leer mensajes del cliente y retransmitirlos a todos los demás
                while ((mensaje = in.readLine()) != null) {
                    System.out.println("Mensaje recibido: " + mensaje);
                    enviarMensajeATodos(mensaje, out);
                }
            } catch (IOException e) {
                System.err.println("Error al recibir mensajes del cliente: " + e.getMessage());
            } finally {
                try {
                    clienteSocket.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar la conexión del cliente: " + e.getMessage());
                }

                // Eliminar al cliente de la lista al desconectarse
                synchronized (clientes) {
                    clientes.remove(out);
                }
                System.out.println("Cliente desconectado");
            }
        }
    }

    // Método para enviar un mensaje a todos los clientes conectados
    private void enviarMensajeATodos(String mensaje, PrintWriter remitente) {
        synchronized (clientes) {
            for (PrintWriter cliente : clientes) {
                if (cliente != remitente) { // Evitar que el cliente que envió el mensaje lo reciba
                    cliente.println(mensaje);
                }
            }
        }
    }

    public static void main(String[] args) {
        ServidorChatTCP servidor = new ServidorChatTCP(12345);
        servidor.iniciar();
    }
}
