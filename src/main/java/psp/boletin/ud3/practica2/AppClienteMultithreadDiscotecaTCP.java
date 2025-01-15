package psp.boletin.ud3.practica2;

import psp.ud3_3_2_1.tcp.reverse.ClienteReverseTCP;

public class AppClienteMultithreadDiscotecaTCP {


        public static void main(String[] args) {
            // Crear una instancia del cliente y conectarse al servidor
            ClienteMultithreadDiscotecaTCP cliente = new ClienteMultithreadDiscotecaTCP("localhost", 1234);
            cliente.conectarYEnviar();
        }

}
