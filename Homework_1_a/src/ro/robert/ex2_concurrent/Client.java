package ro.robert.ex2_concurrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private static BufferedReader scannerKeyboard;
    private static BufferedReader reader;
    private static PrintWriter writer;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 6666);
            scannerKeyboard = new BufferedReader(new InputStreamReader(System.in));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

            while (true) {
                writeToServer();
                listenToServer();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listenToServer() {
        try {
            int result = Integer.parseInt(reader.readLine());
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToServer() {
        try {
            int number = Integer.parseInt(scannerKeyboard.readLine());
            writer.println(number);
            writer.flush();
            for (int i = 0; i < number; i++) {
                writer.println(Integer.parseInt(scannerKeyboard.readLine()));
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
