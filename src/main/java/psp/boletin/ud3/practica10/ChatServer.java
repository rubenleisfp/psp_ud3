package psp.boletin.ud3.practica10;

import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Servidor iniciado. Esperando clientes...");

        Socket client1 = serverSocket.accept();
        System.out.println("Cliente 1 conectado.");

        Socket client2 = serverSocket.accept();
        System.out.println("Cliente 2 conectado.");


        throw new UnsupportedOperationException("A implementar por el alumno");
        //TODO
        // Hay que crear 2 threads, en el que le pasemos un clientHandler con el cliente 1 y cliente en para el primer thread
        // para el segundo thread le pasaremos la inversa, cliente 2 y cliente1
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private Socket otherClientSocket;

    public ClientHandler(Socket clientSocket, Socket otherClientSocket) {
        this.clientSocket = clientSocket;
        this.otherClientSocket = otherClientSocket;
    }

    @Override
    public void run() {

        //1. Creamos un bufferedReader a partir de clienteSocket
        //2. Creamos un printwriter para otherClientSocket
        //3. Hacemos un bucle mientras las lineas de entrada (1) sea distintas de nulo
        //4. En el bucle anterior debemos enviar mensajes al printWriter (2)

        throw new UnsupportedOperationException("A implementar por el alumno");
    }

}
