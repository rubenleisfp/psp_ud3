package psp.ud3_3_2_2.tcp.multithread;

public class AppClienteReverseTCP {


        public static void main(String[] args) {
            // Crear una instancia del cliente y conectarse al servidor
            ClienteReverseTCP cliente = new ClienteReverseTCP("localhost", 1234);
            cliente.conectarYEnviar("Hola desde el cliente");
        }

}
