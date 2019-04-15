package comm;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class DataClient {

    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost",6000);

            PrintWriter clientOutput = new PrintWriter( clientSocket.getOutputStream());
            Scanner clientInput = new Scanner( clientSocket.getInputStream());
            Scanner keyBoard = new Scanner(System.in);
            System.out.println("Conectado ao servidor");

            while( clientSocket.isConnected()) {
                System.out.print('>');
                String command = keyBoard.nextLine();
                clientOutput.println(command);
                clientOutput.flush();

                while (clientInput.hasNextLine()){
                    System.out.println(clientInput.nextLine());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
