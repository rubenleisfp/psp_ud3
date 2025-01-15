package psp.boletin.ud3.practica1;

public class AppServidorVocalesTCP {

    public static void main(String[] args) {

            // Crear una instancia del servidor en el puerto 1234 e iniciarlo
            ServidorVocalesTCP servidor = new ServidorVocalesTCP(1234);
            servidor.iniciar();
    }
}
