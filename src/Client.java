import java.io.IOException;
import java.net.Socket;

public class Client {
    private static final String HOST = "localhost";
    private Socket socket;

    public static void main(String[] args) {
        new Client();
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