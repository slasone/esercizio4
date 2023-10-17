package server;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class AppServer
{
    static int port = 6789;

    public static void main(String[] args){
        try{
            ServerSocket serverSocket;
            try{
                serverSocket = new ServerSocket(port);
            }catch (IOException e) {
                e.printStackTrace();
                return;
            }

            System.out.println("Connessione accettata.");
            
            for(;;){
                Server server = new Server();
                server.accept(serverSocket);
                SendImg img = new SendImg(server);
                img.start();
            }
        
        }catch(Exception e){}
    }

    public static class SendImg extends Thread{

        private Server server;
        protected static File file;

        public SendImg(Server server){
            this.server = server;
        }

        @Override
        public void run(){
            String nomeFile = "src/main/resources/in/java.jpg"; 
            ServerSocket server = new ServerSocket(6789);
            System.out.println("Server in ascolto sulla porta 6789...");
            Socket socket = server.accept();
            System.out.println("Connessione accettata.");
            
            try{
                FileInputStream reader = new FileInputStream("./images/messi-ronaldo.jpg");
                ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());

        
                FileInputStream reader = new FileInputStream(nomeFile);
            }catch(IOException e){}


            byte[] buffer = new byte[1024];
            int readLen;
            while((readLen = reader.read(buffer)) > 0){
                writer.write(buffer, 0, readLen);
            }
        }
    }  
}


       