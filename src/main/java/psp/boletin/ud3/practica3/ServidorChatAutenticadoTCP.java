package psp.boletin.ud3.practica3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorChatAutenticadoTCP {
    private int puerto;

    public ServidorChatAutenticadoTCP(int puerto) {
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
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }

    // Clase interna para manejar la conexión con cada cliente
    private class ClienteHandler implements Runnable {
        private Socket clienteSocket;
        private PrintWriter out;

        private boolean esPrimerMensaje=true;

        public ClienteHandler(Socket clienteSocket) {
            this.clienteSocket = clienteSocket;
        }

        @Override
        public void run() {
            boolean isUserValid =false;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()))) {

                // Flujo de salida para el cliente
                out = new PrintWriter(clienteSocket.getOutputStream(), true);


                String mensaje;
                // Leer mensajes del cliente y retransmitirlos a todos los demás
                while ((mensaje = in.readLine()) != null) {
                    System.out.println("Mensaje recibido: " + mensaje);
                    if (esPrimerMensaje) {
                        esPrimerMensaje=false;
                        isUserValid= comprobarCredenciales(mensaje);
                        if (isUserValid) {
                            ClientesRegistrados.agregarCliente(out);
                        } else {
                            out.println("Autenticacion fallido. No podra ni enviar ni recibir mensajes en el chat grupal");
                        }
                    } else {
                        if (isUserValid) {
                            enviarMensajeATodos(mensaje, out);
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Error al recibir mensajes del cliente: " + e.getMessage());
            } finally {
                try {
                    clienteSocket.close();
                } catch (IOException e) {
                    System.err.println("Error al conectar al servidor: " + e.getMessage());
                }

                // Eliminar al cliente de la lista al desconectarse
                if (isUserValid) {
                    ClientesRegistrados.eliminarCliente(out);
                }
                System.out.println("Cliente desconectado");
            }
        }

        private boolean comprobarCredenciales(String mensaje) {
            String [] credenciales = mensaje.split(":");
            String username = credenciales[0];
            String password = credenciales[1];
            boolean isValid = ClientesRegistrados.credencialesValidas(username,password);
            return isValid;
        }
    }

    // Método para enviar un mensaje a todos los clientes conectados
    private void enviarMensajeATodos(String mensaje, PrintWriter remitente) {
        synchronized (ClientesRegistrados.getClientes()) {
            for (PrintWriter cliente : ClientesRegistrados.getClientes()) {
                if (cliente != remitente) { // Evitar que el cliente que envió el mensaje lo reciba
                    cliente.println(mensaje);
                }
            }
        }
    }

    public static void main(String[] args) {
        ServidorChatAutenticadoTCP servidor = new ServidorChatAutenticadoTCP(12345);
        servidor.iniciar();
    }
}
