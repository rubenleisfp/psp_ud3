package psp.boletin.ud3.practica10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Servidor iniciado. Esperando clientes...");

        Socket client1 = serverSocket.accept();
        System.out.println("Cliente 1 conectado.");

        Socket client2 = serverSocket.accept();
        System.out.println("Cliente 2 conectado.");

        new Thread(new ClientHandler(client1, client2)).start();
        new Thread(new ClientHandler(client2, client1)).start();
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
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(otherClientSocket.getOutputStream(), true)
        ) {
            String message;
            while ((message = in.readLine()) != null) {
                out.println(message);
            }
        } catch (IOException e) {
            System.out.println("Conexión cerrada: " + e.getMessage());
        }
    }
}
