package psp.ud3_3_2_1.tcp.basico;

public class AppServidorTCP {

    public static void main(String[] args) {

            // Crear una instancia del servidor en el puerto 1234 e iniciarlo
            ServidorTCP servidor = new ServidorTCP(1234);
            servidor.iniciar();
    }
}
