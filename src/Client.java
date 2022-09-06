import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String HOST = "localhost";
    private Socket socket;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new Client().start();
    }

    private void start() {
        String message;
        do {
            System.out.print("Type a message: ");
            message = scanner.nextLine();
        } while( !message.equalsIgnoreCase("sair") );
    }

    public Client() {
        try {
            System.out.println("Connecting to server.");
            this.socket = new Socket(HOST, Server.PORT);
            System.out.println("Connected.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
