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
        throw new UnsupportedOperationException("A implementar por el alumno");
    }

    /**
     * Dado un texto contara el numero de vocales que tiene de cada tipo
     *
     * @param text
     * @return
     */
    public Map<String, Integer> contarVocales(String text) {
        throw new UnsupportedOperationException("A implementar por el alumno");
    }
}
