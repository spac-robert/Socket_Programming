package ro.robert.ex_1;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            Scanner scanner = new Scanner(socket.getInputStream());
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

            int sum = getSum(scanner);
            System.out.println(sum);
            printWriter.println(sum);
            printWriter.flush();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static int getSum(Scanner scanner) {
        int sum = 0;
        int n = scanner.nextInt();
        while (n != 0) {
            sum = sum + scanner.nextInt();
            n--;
        }
        return sum;
    }
}
