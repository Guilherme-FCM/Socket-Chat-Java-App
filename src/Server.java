import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;

public class Server {
    public static final int PORT = 8000;
    private ServerSocket serverSocket;
    private List<SocketClient> clients = new LinkedList<>();

    public static void main(String[] args) {
        new Server().start();
    }

    private void start() {
        System.out.println("Server listen on port " + PORT);
        try {
            while (true) {
                SocketClient socketClient = new SocketClient(serverSocket.accept());
                clients.add(socketClient);
                System.out.println("Client " + socketClient.getInetAddress() + " connected.");

                new Thread(() -> this.messageLoop(socketClient)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void messageLoop(SocketClient socketClient) {
        String message = "";
        do {
            message = socketClient.getMessage();
            System.out.println("Client " + socketClient.getInetAddress() + ": " + message);
            this.sendMessageToClients(message);
        } while (!message.isEmpty());
    }

    private void sendMessageToClients(String message){
        for (SocketClient client : clients)
            client.sendMessage(message);
    }

    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
