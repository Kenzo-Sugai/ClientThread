import java.io.*;
import java.util.*;
import java.net.*;

public class ThreadHTML implements Runnable {

    public static Thread readHTML;


    public void run() {

        File arq = new File("src/pagina.html");
        Scanner scannerArqReader = null;

        try {
            scannerArqReader = new Scanner(arq);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while(scannerArqReader.hasNextLine()) {

            String line = scannerArqReader.nextLine();

            if(line.indexOf("<link") != -1) {
                System.out.println("LINK FOUND!");
                System.out.println(line);

                ThreadTag stateTag = new ThreadTag(line);
                Thread tag = new Thread(stateTag);

                tag.start();
            }
            if(line.indexOf("<img") != -1) {
                System.out.println("IMG FOUND!");
                System.out.println(line);

                ThreadTag stateTag = new ThreadTag(line);
                Thread tag = new Thread(stateTag);

                tag.start();
            }
            if(line.indexOf("<script") != -1) {
                System.out.println("SCRIPT FOUND!");
                System.out.println(line);

                ThreadTag stateTag = new ThreadTag(line);
                Thread tag = new Thread(stateTag);

                tag.start();
            }if(line.indexOf("<getip") != -1) {
                System.out.println("GETIP FOUND!");
                System.out.println(line);

                ThreadTag stateTag = new ThreadTag(line);
                Thread tag = new Thread(stateTag);

                tag.start();
            }

           // System.out.println(line);

        }

        scannerArqReader.close();

    }

    public static void main(String[] args) throws FileNotFoundException {

        ThreadHTML readStateHTML = new ThreadHTML();

        readHTML = new Thread(readStateHTML);

        readHTML.start();

    }
}
