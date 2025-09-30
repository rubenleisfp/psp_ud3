package psp.ud3_3_3_1.udp.matematicas;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP {
    private int puerto;

    public ServidorUDP(int puerto) {
        this.puerto = puerto;
    }

    public void iniciar() {
        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            System.out.println("Servidor UDP escuchando en el puerto " + puerto);

            while (true) {
                recibirYResponder(socket); // Método dedicado para manejar los mensajes
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }

    // Método que recibe mensajes del cliente y envía una respuesta
    private void recibirYResponder(DatagramSocket socket) throws IOException {
        byte[] bufferRecibo = new byte[1024];
        //Defimos el paquete a recibir en el servidor con un tamaño de 1024 bytes.
        DatagramPacket paqueteRecibo = new DatagramPacket(bufferRecibo, bufferRecibo.length);

        // Recibir el mensaje del cliente
        socket.receive(paqueteRecibo);
        // Convertimos el paquete recibido a un String
        String mensajeRecibido = new String(paqueteRecibo.getData(), 0, paqueteRecibo.getLength());
        System.out.println("Mensaje recibido del cliente: " + mensajeRecibido);

        // Preparar la respuesta
        String respuesta = UtilidadesMensaje.procesarMensaje(mensajeRecibido);

        //Convertimos el mensaje de String a un array de bytes
        byte[] bufferEnvio = respuesta.getBytes();

        // Enviar la respuesta al cliente
        DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, paqueteRecibo.getAddress(), paqueteRecibo.getPort());
        socket.send(paqueteEnvio);
        System.out.println("Respuesta enviada al cliente: " + respuesta);
    }


}
