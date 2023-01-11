import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost",83);
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            PrintWriter pr = new PrintWriter(os,true);
            pr.println("Hicham");
            String msg = br.readLine();
            System.out.println(msg);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
