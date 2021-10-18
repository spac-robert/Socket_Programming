package ro.robert;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader input;
    private BufferedWriter output;
    private String clientUsername;

    public ClientHandler(Socket client) {
        try {
            socket = client;
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            clientHandlers.add(this);
            clientUsername = input.readLine();
            broadcastMessage("SERVER: " + clientUsername + " has entered the chat");
        } catch (IOException e) {
            closeEverything(socket, input, output);
        }
    }

    private void broadcastMessage(String messageTo) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (!clientHandler.clientUsername.equals(clientUsername)) {
                    clientHandler.output.write(messageTo);
                    clientHandler.output.newLine();
                    clientHandler.output.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, input, output);
            }
        }
    }

    @Override
    public void run() {

        String messageFromClient;
        while (socket.isConnected()) {
            try {
                messageFromClient = input.readLine();
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                closeEverything(socket, input, output);
                break;
            }
        }
    }

    private void closeEverything(Socket socket, BufferedReader input, BufferedWriter output) {
        removeClientHandler();
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
            e.printStackTrace();
        }
    }

    public void removeClientHandler() {
        clientHandlers.remove(this);
        broadcastMessage("SERVER: " + clientUsername + " has left the chat!");
    }
}
