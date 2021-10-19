package ro.robert.ex2_concurrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Echo implements Runnable {
    private BufferedReader reader;
    private PrintWriter writer;
    private Socket socket;

    public Echo(Socket socket) {
        try {
            this.socket = socket;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while (true) {

            write(listen());
        }
    }

    private void write(int number) {
        writer.println(number);
        writer.flush();
    }

    private int listen() {
        int s = 0;
        try {
            int number = Integer.parseInt(reader.readLine());
            for (int i = 0; i < number; i++) {
                s = makeSum(s, Integer.parseInt(reader.readLine()));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return s;
    }

    private int makeSum(int s, int parseInt) {
        return s + parseInt;
    }
}
