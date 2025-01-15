package psp.ud3_3_3_1.udp.matematicas;

public class AppClienteUDP {

    public static void main(String[] args) {
        ClienteUDP cliente = new ClienteUDP("localhost", 12345);
        cliente.iniciar();
    }
}
