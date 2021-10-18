package ro.robert;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 6666);
            Scanner keyboard = new Scanner(System.in);
            Scanner receiveInput = new Scanner(socket.getInputStream());
            PrintWriter output = new PrintWriter(socket.getOutputStream());

//            System.out.println("Write your username: ");
//            String name = keyboard.next();

            String message = null;
            while (!"exit".equalsIgnoreCase(message)) {
                //read from user
                message = keyboard.nextLine();
                //sending the user input to server
                output.println(message);
                output.flush();

                //Displaying server reply
                System.out.println("Server replied: " + receiveInput.nextLine());

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
