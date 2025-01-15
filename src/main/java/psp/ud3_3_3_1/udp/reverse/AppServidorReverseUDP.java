package psp.ud3_3_3_1.udp.reverse;

public class AppServidorReverseUDP {

    public static void main(String[] args) {
        ServidorReverseUDP servidor = new ServidorReverseUDP(12345);
        servidor.iniciar();
    }
}
