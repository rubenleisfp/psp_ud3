package psp.live;

import java.net.*;
import java.io.*;
 
/**
 * This program demonstrates a simple TCP/IP socket client.
 *
 * @author www.codejava.net
 */
public class TimeClient {

    //10.42.69.68:9090
    //https://www.codejava.net/java-se/networking/java-socket-server-examples-tcp-ip
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = Integer.parseInt("9090");
 
        try (Socket socket = new Socket(hostname, port)) {
 
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            String time = reader.readLine();
 
            System.out.println(time);
 
 
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}