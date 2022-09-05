import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8000;
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        new Server().start();
    }

    private void start() {
        System.out.println("Servidor escutando na porta " + PORT);
        try {
            while (true) {
                Socket socket = serverSocket.accept();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
