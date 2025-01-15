package psp.ud3_3_2_2.tcp.multithread;

public class AppServidorReverseTCP {

    public static void main(String[] args) {

            // Crear una instancia del servidor en el puerto 1234 e iniciarlo
            ServidorMultithreadTCP servidor = new ServidorMultithreadTCP(1234);
            servidor.iniciar();
    }
}
