package ro.robert;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader input;
    private BufferedWriter output;
    private String username;

    public Client(Socket socket, String username) {
        try {
            this.socket = socket;
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.username = username;
        } catch (IOException e) {
            closeEverything(socket, input, output);
        }
    }

    public void sendMessage() {
        try {
            output.write(username);
            output.newLine();
            output.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String messageToSend = scanner.nextLine();
                output.write(username + " : " + messageToSend);
                output.newLine();
                output.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, input, output);
        }
    }

    private void closeEverything(Socket socket, BufferedReader input, BufferedWriter output) {
        try {
            if (socket != null) {
                socket.close();
            }
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void listenForMassage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;
                while (socket.isConnected()) {
                    try {
                        msgFromGroupChat = input.readLine();
                        System.out.println(msgFromGroupChat);
                    } catch (IOException e) {
                        closeEverything(socket, input, output);

                    }
                }
            }
        }).start();
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the username for the group chat: ");
        String username = scanner.nextLine();
        Socket socket = new Socket("localhost",6666);
        Client client = new Client(socket,username);
        client.listenForMassage();
        client.sendMessage();
    }
}
