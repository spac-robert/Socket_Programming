import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 6666);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            int a = Integer.parseInt(scanner.readLine());
            writer.println(a);
            a = Integer.parseInt(scanner.readLine());
            writer.println(a);
            writer.flush();
            System.out.println(Integer.parseInt(reader.readLine()));

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
