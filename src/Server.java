import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static final int PORT = 8000;
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        new Server().start();
    }

    private void start() {
        System.out.println("Server listen on port " + PORT);
        try {
            while (true) {
                SocketClient socketClient = new SocketClient(serverSocket.accept());
                System.out.println("Client " + socketClient.getInetAddress() + " connected.");

                new Thread(() -> messageLoop(socketClient)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void messageLoop(SocketClient socketClient) {
        String message = "";
        do {
            message = socketClient.getMessage();
            System.out.println("Client " + socketClient.getInetAddress() + ": " + message);
        } while (!message.isEmpty());
    }

    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
