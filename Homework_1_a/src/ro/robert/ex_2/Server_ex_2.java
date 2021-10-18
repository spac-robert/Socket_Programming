package ro.robert.ex_2;

import java.net.ServerSocket;
import java.net.Socket;

public class Server_ex_2 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
