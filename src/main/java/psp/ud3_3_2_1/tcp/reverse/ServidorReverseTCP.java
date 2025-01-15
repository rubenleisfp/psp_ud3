package psp.ud3_3_2_1.tcp.reverse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// Clase que representa el servidor TCP
public class ServidorReverseTCP {
    private int puerto;

    public ServidorReverseTCP(int puerto) {
        this.puerto = puerto;
    }

    // Método para iniciar el servidor
    public void iniciar() {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            while (true) {
                // Esperar a que un cliente se conecte
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado");
                System.out.println("Cliente conectado");

                // Crear flujo de entrada para recibir el mensaje del cliente
                BufferedReader in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

                // Leer el mensaje del cliente
                String mensajeCliente = in.readLine();
                System.out.println("Mensaje recibido del cliente: " + mensajeCliente);

                // Convertir el mensaje a mayúsculas
                String mensajeModificado = mensajeCliente.toUpperCase();

                // Crear flujo de salida para enviar la respuesta al cliente
                PrintWriter out = new PrintWriter(clienteSocket.getOutputStream(), true);

                out.println(mensajeModificado);

                // Cerrar la conexión con el cliente
                clienteSocket.close();
                System.out.println("Cliente desconectado");

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
