import java.io.IOException;
import java.net.ServerSocket;
import java.util.Iterator;
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
        try {
            while (true) {
                SocketClient client = new SocketClient(serverSocket.accept());
                clients.add(client);
                System.out.println("Client " + client.getInetAddress() + " connected.");

                new Thread(() -> this.messageLoop(client)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void messageLoop(SocketClient client) {
        String message;
        do {
            message = client.getMessage();
            System.out.println("Client " + client.getInetAddress() + ": " + message);
            this.sendMessageToClients(client, message);
        } while (!message.isEmpty());
        client.close();
    }

    private void sendMessageToClients(SocketClient sender, String message){
        Iterator<SocketClient> iterator = clients.iterator();
        while(iterator.hasNext()){
            SocketClient client = iterator.next();
            if(!client.equals(sender)) {
                boolean result = client.sendMessage(message);
                if (! result) iterator.remove();
            }
        }
    }

    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server listen on port " + PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
