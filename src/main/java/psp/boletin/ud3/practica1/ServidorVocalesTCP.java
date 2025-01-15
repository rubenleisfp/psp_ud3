package psp.boletin.ud3.practica1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

// Clase que representa el servidor TCP
public class ServidorVocalesTCP {
    private int puerto;

    public ServidorVocalesTCP(int puerto) {
        this.puerto = puerto;
    }

    // Método para iniciar el servidor
    public void iniciar() {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            while (true) {
                // Esperar a que un cliente se conecte
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado");

                // Crear flujo de entrada para recibir el mensaje del cliente
                BufferedReader in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

                PrintWriter out = new PrintWriter(clienteSocket.getOutputStream(), true);

                String text = "";
                do {
                    // Leer el mensaje del cliente
                    text = in.readLine();
                    Map<String, Integer> numVocales= this.contarVocales(text);
                    out.println( numVocales);
                } while (!text.equals("bye"));

                // Cerrar la conexión con el cliente
                clienteSocket.close();
                System.out.println("Cliente desconectado");

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Integer> contarVocales(String text) {

        // Crear un mapa para almacenar la cuenta de cada vocal
        Map<String, Integer> vocalesContador = new HashMap<>();

        // Inicializar el mapa con las vocales y un contador inicial de 0
        vocalesContador.put("a", 0);
        vocalesContador.put("e", 0);
        vocalesContador.put("i", 0);
        vocalesContador.put("o", 0);
        vocalesContador.put("u", 0);

        // Convertir el texto a minúsculas para evitar problemas de mayúsculas/minúsculas
        text = text.toLowerCase();

        // Recorrer el texto carácter por carácter
        for (char c : text.toCharArray()) {
            // Comprobar si el carácter es una vocal
            if (vocalesContador.containsKey(String.valueOf(c))) {
                // Incrementar el contador correspondiente en el mapa
                vocalesContador.put(String.valueOf(c), vocalesContador.get(String.valueOf(c)) + 1);
            }
        }

        return vocalesContador;
    }
}
