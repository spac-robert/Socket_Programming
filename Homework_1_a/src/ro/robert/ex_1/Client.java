package ro.robert.ex_1;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 6666);
            Scanner keyboard = new Scanner(System.in);
            Scanner scanner = new Scanner(socket.getInputStream());
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

            ArrayList<Integer> arrayList = readList(keyboard);
            printWriter.println(arrayList.size());
            printWriter.flush();
            for (Integer x : arrayList) {
                printWriter.println(x);
                printWriter.flush();
            }
            int sum = scanner.nextInt();
            System.out.println(sum);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static ArrayList<Integer> readList(Scanner keyboard) {
        int n = keyboard.nextInt();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arrayList.add(keyboard.nextInt());
        }
        return arrayList;
    }
}