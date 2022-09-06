import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
                Socket socket = serverSocket.accept();
                System.out.println("Client " + socket.getInetAddress() + " connected.");

                SocketClient socketClient = new SocketClient(socket);
                String message = socketClient.getMessage();
                System.out.println("Client " + socket.getInetAddress() + ": " + message);
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
