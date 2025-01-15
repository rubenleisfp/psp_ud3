package psp.ud3_3_2_1.tcp.reverse;

public class AppClienteReverseTCP {


        public static void main(String[] args) {
            // Crear una instancia del cliente y conectarse al servidor
            ClienteReverseTCP cliente = new ClienteReverseTCP("localhost", 1234);
            cliente.conectarYEnviar("Hola desde el cliente");
        }

}
