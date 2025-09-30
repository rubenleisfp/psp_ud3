package psp.boletin.ud3.practica3;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientesRegistrados {

    //Lista de clientes
    private static List<PrintWriter> clientes = new ArrayList<>();
    public static List<PrintWriter> getClientes() {
        return clientes;
    }

    /**
     * Obtiene las credenciales de los usuarios
     * Estas credenciales en este ejemplo son estaticas
     * pero en una aplicacion real estas credenciales
     * se obtendrian de la base de datos
     * @return
     */
    private static Map<String, String> getCredenciales() {
        Map<String,String> credenciales = new HashMap<>();
        credenciales.put("ABEL","123");
        credenciales.put("BEA","321");
        return credenciales;
    }

    /**
     * Comprueba si las credenciales son correctas
     * @param username usuario
     * @param password  password
     * @return
     */
    public static boolean credencialesValidas(String username, String password) {
        boolean isValid = false;
        String passwordCredencial = getCredenciales().get(username);
        if (passwordCredencial != null && passwordCredencial.equals(password)) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * Agrega un nuevo cliente
     * @param pwCliente
     */
    public synchronized static void  agregarCliente(PrintWriter pwCliente) {
        clientes.add(pwCliente);
    }

    /**
     * Elimina un cliente
     * @param pwCliente
     */
    public synchronized static void  eliminarCliente(PrintWriter pwCliente) {
        clientes.remove(pwCliente);
    }

}
