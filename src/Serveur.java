import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(83);
            Socket s = ss.accept();
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String msg = br.readLine();
            System.out.println(msg);
            PrintWriter pr = new PrintWriter(os,true);
            pr.println("Bien recu !");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
