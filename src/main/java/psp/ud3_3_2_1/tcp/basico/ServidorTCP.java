package psp.ud3_3_2_1.tcp.basico;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Clase que representa el servidor TCP
public class ServidorTCP {
    private int puerto;

    public ServidorTCP(int puerto) {
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

                // Get the current date and time
                LocalDateTime now = LocalDateTime.now();

                // Define the format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

               // Format the current date and time
                String formattedNow = now.format(formatter);

                out.println(formattedNow);

                // Cerrar la conexión con el cliente
                clienteSocket.close();
                System.out.println("Cliente desconectado");

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
