package ro.robert.ex_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client_ex_2 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 6666);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            String string = scanner.readLine();
            writer.println(string);
            writer.flush();

            int number = Integer.parseInt(reader.readLine());
            System.out.println(number);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
