package ro.robert;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            serverSocket.setReuseAddress(true);
            Socket client;
            while (true) {
                //socket object to receive incoming client request
                client = serverSocket.accept();

                //Display the new client is connected to server
                System.out.println("New client connected " + client.getInetAddress().getHostAddress());

                //create a new thread object
                ClientHandler clientSocket = new ClientHandler(client);

                //This thread will handle the client separately
                new Thread(clientSocket).start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
