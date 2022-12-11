import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    private Socket clientSocket;

    Client(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 1234);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String outputMess;
        String inputMess="A";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter smth:");
        outputMess = scanner.nextLine();
        writer.write(outputMess);
        writer.newLine();
        writer.flush();
        while ((inputMess = reader.readLine()) != null) {
            System.out.println(inputMess);
            System.out.println("Enter smth:");
            outputMess = scanner.nextLine();
            if (outputMess.equals("/quit")) {
                break;
            }
            if (outputMess.charAt(0) != inputMess.charAt(inputMess.length() - 1)) {
                System.out.println("Wrong word:");
                break;
            }
            writer.write(outputMess);
            writer.newLine();
            writer.flush();
        }

        writer.close();
        reader.close();
        clientSocket.close();
    }
}
