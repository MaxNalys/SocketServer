import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Server {

    private ServerSocket serverSocket;

    Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.startServer();

    }

    public void startServer() throws IOException, InterruptedException {
        while (!serverSocket.isClosed()) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client has connected");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String outMessage = "";
            String inMessage;
            Scanner scanner = new Scanner(System.in);
            while ((inMessage = reader.readLine()) != null) {
                System.out.println(inMessage);
                System.out.println("Enter smth:");
                outMessage = scanner.nextLine();
                if (outMessage.equals("/quit")) {
                    break;
                }
                if (outMessage.charAt(0) != inMessage.charAt(inMessage.length() - 1)) {
                    System.out.println("Wrong word:");
                    break;
                }
                writer.write(outMessage);
                writer.newLine();
                writer.flush();

            }
            writer.close();
            reader.close();
            clientSocket.close();
            closeServer();
        }
    }

    public void closeServer() throws IOException {
        serverSocket.close();
    }
}
