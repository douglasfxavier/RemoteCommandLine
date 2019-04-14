package comm;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class DataClient {

    public static void main(String[] args) {
        try {
            Socket serverSocket = new Socket("localhost",6000);

            PrintWriter serverOutput = new PrintWriter(serverSocket.getOutputStream());
            Scanner serverInput = new Scanner(serverSocket.getInputStream());
            Scanner keyBoard = new Scanner(System.in);
            System.out.println("Conectado ao servidor");

            while(true) {
                System.out.print("> ");
                String command = keyBoard.nextLine();
                serverOutput.println(command);
                serverOutput.flush();

//                while (serverInput.hasNextLine()){
//                    System.out.println(serverInput.nextLine());
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
