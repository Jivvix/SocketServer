import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


    public class SocketServer {
        public static void main(String[] args) throws IOException {
            ServerSocket serverSocket = new ServerSocket(9000);
            System.out.println("Открыт сокет на порту 9000, ждём подключения");
            while(true){
                Socket clientSocket = serverSocket.accept(); // открывает сокет и блокируется пока не будет подключения
                System.out.println("Подключен клиент");

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println("welcome to socket");
                String inputLine;
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

        }
    }
