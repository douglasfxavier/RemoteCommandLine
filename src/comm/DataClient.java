package comm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class DataClient {

    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost",6000);

            PrintWriter clientOutput = new PrintWriter( clientSocket.getOutputStream(),true);
            BufferedReader clientInput = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            Scanner keyBoard = new Scanner(System.in);
            System.out.println("Conectado ao servidor");

            while( clientSocket.isConnected()) {
                System.out.print('>');
                String command = keyBoard.nextLine();
                clientOutput.println(command);
                clientOutput.flush();

                String inputLine;
                while ((inputLine = clientInput.readLine())!=null){
                    if (inputLine.equals("ENDINPUT")) break;
                    System.out.println(inputLine);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
