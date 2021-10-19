package ro.robert.ex2_concurrent;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                new Thread(new Echo(socket)).start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
