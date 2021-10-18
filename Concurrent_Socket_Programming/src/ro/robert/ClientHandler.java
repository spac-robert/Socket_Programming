package ro.robert;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        PrintWriter output = null;
        Scanner input = null;
        try {
            //get the output stream of client
            output = new PrintWriter(clientSocket.getOutputStream());

            //get the input stream of client
            input = new Scanner(clientSocket.getInputStream());

            String message;
            while ((message = input.nextLine()) != null) {
                //writing the received message from client
                System.out.println("Sent from client: " + message);
                output.println(message);
                output.flush();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
