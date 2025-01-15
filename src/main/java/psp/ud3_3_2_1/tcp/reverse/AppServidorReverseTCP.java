package psp.ud3_3_2_1.tcp.reverse;

public class AppServidorReverseTCP {

    public static void main(String[] args) {

            // Crear una instancia del servidor en el puerto 1234 e iniciarlo
            ServidorReverseTCP servidor = new ServidorReverseTCP(1234);
            servidor.iniciar();
    }
}
