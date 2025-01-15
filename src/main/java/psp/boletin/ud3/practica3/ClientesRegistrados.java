package psp.boletin.ud3.practica3;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientesRegistrados {

    private static List<PrintWriter> clientes = new ArrayList<>();
    public static List<PrintWriter> getClientes() {
        return clientes;
    }

    private static Map<String, String> getCredenciales() {
        Map<String,String> credenciales = new HashMap<>();
        credenciales.put("ABEL","123");
        credenciales.put("BEA","321");
        return credenciales;
    }

    public static boolean credencialesValidas(String username, String password) {
        boolean isValid = false;
        String passwordCredencial = getCredenciales().get(username);
        if (passwordCredencial != null && passwordCredencial.equals(password)) {
            isValid = true;
        }
        return isValid;
    }

    public synchronized static void  agregarCliente(PrintWriter pwCliente) {
        clientes.add(pwCliente);
    }

    public synchronized static void  eliminarCliente(PrintWriter pwCliente) {
        clientes.remove(pwCliente);
    }

}
