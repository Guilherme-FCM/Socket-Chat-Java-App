import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClient {
    private final BufferedReader in;
    private final PrintWriter out;
    private final Socket socket;

    public SocketClient(Socket socket) {
        this.socket = socket;

        try {
            this.in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );
            this.out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean sendMessage(String message){
        this.out.println(message);
        return !out.checkError();
    }

    public String getMessage(){
        try {
            return in.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    public InetAddress getInetAddress(){
        return socket.getInetAddress();
    }
}
