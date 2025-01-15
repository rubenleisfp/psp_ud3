package psp.boletin.ud3.practica1;

public class AppClienteVocalesTCP {


        public static void main(String[] args) {
            // Crear una instancia del cliente y conectarse al servidor
            ClienteVocalesTCP cliente = new ClienteVocalesTCP("localhost", 1234);
            cliente.conectarYEnviar("Hola desde el cliente");
        }

}
