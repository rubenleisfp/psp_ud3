package psp.ud3_3_3_1.udp.matematicas;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP {
    private String servidor;
    private int puerto;

    public ClienteUDP(String servidor, int puerto) {
        this.servidor = servidor;
        this.puerto = puerto;
    }

    public void iniciar() {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress direccionServidor = InetAddress.getByName(servidor);

            // Bucle para enviar mensajes múltiples al servidor
            Scanner scanner = new Scanner(System.in);

            System.out.print("Introduce una operacion: ");
            String opArg = scanner.nextLine();

            int numero1 = 1;
            int numero2 = 0;
            while (true) {
                System.out.print("Introduce el primero numero: ");
                String num1Arg = scanner.nextLine();
                try {
                    numero1 = Integer.valueOf(num1Arg);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("El primer numero debe ser un entero");
                }
            }

            while (true) {
                System.out.print("Introduce el segundo numero : ");
                String num2Arg = scanner.nextLine();
                try {
                    numero2 = Integer.valueOf(num2Arg);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("El segundo numero debe ser un entero");
                }
            }

            String mensaje = UtilidadesMensaje.crearMensaje(opArg, numero1, numero2);
            // Enviar el mensaje al servidor
            enviarMensaje(socket, direccionServidor, mensaje);
            recibirRespuesta(socket);
        } catch (IOException e) {
            e.printStackTrace();
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
