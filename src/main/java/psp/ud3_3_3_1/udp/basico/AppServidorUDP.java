package psp.ud3_3_3_1.udp.basico;

public class AppServidorUDP {

    public static void main(String[] args) {
        ServidorUDP servidor = new ServidorUDP(12345);
        servidor.iniciar();
    }
}
