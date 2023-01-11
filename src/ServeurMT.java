import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServeurMT {
    public static void main(String[] args) {
        List<Socket> socketList = new ArrayList();
        int nbMagique = new Random().nextInt(20);
        try {
            ServerSocket ss = new ServerSocket(8090);
            while (true) {
                Socket s = ss.accept();
                socketList.add(s);
                ServeurThread st = new ServeurThread(s,socketList,nbMagique);
                st.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
