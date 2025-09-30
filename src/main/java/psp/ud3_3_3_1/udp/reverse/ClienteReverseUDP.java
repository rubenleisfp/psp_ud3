package psp.ud3_3_3_1.udp.reverse;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteReverseUDP {
    private String servidor;
    private int puerto;

    public ClienteReverseUDP(String servidor, int puerto) {
        this.servidor = servidor;
        this.puerto = puerto;
    }

    public void iniciar() {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress direccionServidor = InetAddress.getByName(servidor);

            // Bucle para enviar mensajes múltiples al servidor
            Scanner scanner = new Scanner(System.in);
            String mensaje;
            do {
                System.out.print("Introduce un mensaje (escribe 'bye' para salir): ");
                mensaje = scanner.nextLine();

                // Enviar el mensaje al servidor
                enviarMensaje(socket, direccionServidor, mensaje);

                // Recibir respuesta del servidor si el mensaje no es "bye"
                if (!mensaje.equalsIgnoreCase("bye")) {
                    recibirRespuesta(socket);
                }

            } while (!mensaje.equalsIgnoreCase("bye"));

        } catch (IOException e) {
            System.err.println("Error al conectar al servidor: " + e.getMessage());
        }
    }

    // Método para enviar un mensaje al servidor
    private void enviarMensaje(DatagramSocket socket, InetAddress direccionServidor, String mensaje) throws IOException {
        byte[] bufferEnvio = mensaje.getBytes();
        DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, direccionServidor, puerto);
        socket.send(paqueteEnvio);
        System.out.println("Mensaje enviado al servidor: " + mensaje);
    }

    // Método para recibir la respuesta del servidor
    private void recibirRespuesta(DatagramSocket socket) throws IOException {
        byte[] bufferRecibo = new byte[1024];  // Buffer suficientemente grande para recibir la respuesta
        DatagramPacket paqueteRecibo = new DatagramPacket(bufferRecibo, bufferRecibo.length);
        socket.receive(paqueteRecibo);

        String respuestaServidor = new String(paqueteRecibo.getData(), 0, paqueteRecibo.getLength());
        System.out.println("Respuesta del servidor: " + respuestaServidor);
    }


}
