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

                // Crear flujo de entrada para recibir el mensaje del cliente
                BufferedReader in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

                PrintWriter out = new PrintWriter(clienteSocket.getOutputStream(), true);

                String text = "";
                do {
                    // Leer el mensaje del cliente
                    text = in.readLine();

                    // Revertir el mensaje
                    String reverseText = new StringBuilder(text).reverse().toString();

                    // Crear flujo de salida para enviar la respuesta al cliente
                    out.println( reverseText);
                } while (!text.equals("bye"));

                // Cerrar la conexión con el cliente
                clienteSocket.close();
                System.out.println("Cliente desconectado");

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
