package psp.ud3_3_3_1.udp.reverse;

public class AppClienteReverseUDP {

    public static void main(String[] args) {
        ClienteReverseUDP cliente = new ClienteReverseUDP("localhost", 12345);
        cliente.iniciar();
    }
}
