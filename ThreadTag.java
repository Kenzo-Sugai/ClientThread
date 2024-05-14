import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;

public class ThreadTag implements Runnable {

    public String line;

    public ThreadTag(String line) {

        this.line = line;
    }

    public void run(){

        String aux = "";
        String findTag = "";

        boolean href = false;
        boolean src = false;
        boolean site = false;

        boolean lock = false;

        for(int i = 0; i < this.line.length(); i++){

            findTag += this.line.charAt(i);

            href = findTag.indexOf("href") != -1 ? true : false;
            src = findTag.indexOf("src") != -1 ? true : false;
            site = findTag.indexOf("site") != -1 ? true : false;

            if(this.line.charAt(i) == '"' && (src || href || site)){
                if(lock) lock = false;
                else lock = true;

                if(!lock) break;
            }

            if(lock) {
                if(this.line.charAt(i) != '"') aux += this.line.charAt(i);

            }

        }

        System.out.println(aux);

        if(site){
            try {
                InetAddress ip = InetAddress.getByName(new URL("https://"+aux).getHost());
                System.out.println("O IP é "+ip);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {

            File arqParser = new File("src/" + aux);

            if (arqParser.exists()) {
                System.out.println(arqParser.length() + " bytes");
            } else {
                System.out.println("Arquivo nao encontrado");
            }
        }
    }
}
