package psp.ud3_3_2_1.tcp.basico;

public class AppClienteTCP {


        public static void main(String[] args) {
            // Crear una instancia del cliente y conectarse al servidor
            ClienteTCP cliente = new ClienteTCP("localhost", 1234);
            cliente.conectarYEnviar("Hola desde el cliente");
        }

}
