import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServeurThread extends Thread{
    private Socket s;
    private List<Socket> socketList = new ArrayList<>();
    private int nbMagique;
    public ServeurThread(Socket s, List<Socket> socketList, int nbMagique) {
        this.s = s;
        this.socketList = socketList;
        this.nbMagique = nbMagique;
    }

    @Override
    public void run() {
        try {
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            PrintWriter pr = new PrintWriter(os,true);
            int nb = 0;
            pr.println("Veuillez entrer votre NOM : ");
            String nom = br.readLine();
            String brMsg = null;
            pr.println("Veuillez tenter un nombre : ");
            do{
                try{
                    if ((brMsg = br.readLine()) != null){
                        nb = Integer.parseInt(brMsg);
                        if(nb == nbMagique){
                            pr.println("Bravo ! vous avez trouver le nombre magique ( "+nbMagique+" )");
                            for (Socket sJ:socketList) {
                                if(s != sJ){
                                    OutputStream osj = sJ.getOutputStream();
                                    PrintWriter prj = new PrintWriter(osj,true);
                                    prj.println("[ GAME OVER ] "+"Le joueur ( "+nom+" ) a trouve le nombre magique (" + nbMagique +")");
                                    s.close();
                                }
                            }
                        } else if (nb > nbMagique) {
                            pr.println("Veuillez saisir un nombre inferieur !");
                        } else {
                            pr.println("Veuillez saisir un nombre superieur !");
                        }
                    }
                }catch (NumberFormatException e){
                    pr.println("Veuillez saisir un nombre !!!");
                }catch (IOException e){
                    e.printStackTrace();
                }
            } while (nbMagique != nb);
            br.close();
            for (Socket sd:socketList) {
                sd.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
