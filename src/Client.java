import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {
    private static final String HOST = "localhost";
    private final SocketClient socketClient;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new Client().start();
    }

    private void start() {
        new Thread(this).start();
        String message;
        do {
            System.out.print("Type a message: ");
            message = scanner.nextLine();
            this.socketClient.sendMessage(message);
        } while( !message.equalsIgnoreCase("sair") );
    }

    public Client() {
        try {
            System.out.println("Connecting to server.");
            this.socketClient = new SocketClient(
                new Socket(HOST, Server.PORT)
            );
            System.out.println("Connected.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run(){
        String message;
        while ((message = this.socketClient.getMessage()) != null)
            System.out.println("Mensagem recebida: " + message);
    }
}
