package ro.robert.ex_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_ex_2 {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream());

                String string = reader.readLine();
                writer.println(numberOfSpaces(string));
                writer.flush();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static int numberOfSpaces(String string) {
        int number = 0;
        for (int i = 0; i < string.length() && string.charAt(i) != '\0'; i++) {
            if (string.charAt(i) == ' ') {
                number++;
            }
        }
        return number;
    }
}
