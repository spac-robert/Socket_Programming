import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            int a, b;
            a = Integer.parseInt(reader.readLine());
            b = Integer.parseInt(reader.readLine());

            System.out.println((a + b));
            writer.println((a + b));
            writer.flush();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
