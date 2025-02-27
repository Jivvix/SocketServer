import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;


public class Server {
    List<Client> clients;
    public void run () {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9000);
        } catch (IOException e) {
            System.out.println("Failed to open socket" + e.getMessage());
            System.exit(1);
        }
        System.out.println("Открыт сокет на порту 9000, ждём подключения");
        while(true){
            Socket clientSocket = null; // открывает сокет и блокируется пока не будет подключения
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("Failed to accept client" + e.getMessage());
                continue;
            }
            System.out.println("Client accepted");

            BufferedReader in = null;
            PrintWriter out = null;
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                System.out.println("Failed to get stream" + e.getMessage());
                continue;
            }
            out.println("welcome to socket");
            String inputLine;
            try{
                while ((inputLine = in.readLine()) != null) {
                    out.println("String recieved: " + inputLine);
                    if(inputLine.equals("exit")){
                        System.exit(0);
                    }
                    else if(inputLine.equals("close")){
                        clientSocket.close();
                        break;
                    }
                }
            }
            catch (IOException e){
                System.out.println(e.getMessage());
                continue;
            }

        }

    }

}
