package comm;

import nfs.CommandProcessBuilder;
import nfs.WindowsCommands;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class DataServer {
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(6000);
            ProcessBuilder builder = new ProcessBuilder();

            System.out.println("SERVER LISTENING...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Conexão aceita");
            Scanner clientInput = new Scanner(clientSocket.getInputStream());
            PrintWriter clientOutput = new PrintWriter(clientSocket.getOutputStream());
            WindowsCommands windowsCommands = new WindowsCommands();
            CommandProcessBuilder commandProcessBuilder = new CommandProcessBuilder();
            while(clientInput.hasNext()){

                String command = clientInput.nextLine();
                if (command.equals("dir")){

                    builder = windowsCommands.readDirectory("D:");
                    commandProcessBuilder.exec(builder,clientOutput);}



/*                boolean isWindows = System.getProperty("os.name").
                        toLowerCase().startsWith("windows");

                if (isWindows){
                    WindowsCommands windowsCommands = new WindowsCommands();
                    CommandProcessBuilder commandProcessBuilder = new CommandProcessBuilder();
                    //builder = windowsCommands.readDirectory("D:");
                    //commandProcessBuilder.exec(builder,clientOutput);
                    //builder = windowsCommands.createDirectory("D:\\pastadeteste\\novapasta");
                    //builder = windowsCommands.createFile("Tudo novo de novo","teste.txt");
                    //builder = windowsCommands.renameFile("teste.txt","novoteste.txt");
                    builder = windowsCommands.removeFile("novoteste.txt");
                    commandProcessBuilder.exec(builder,clientOutput);

                }*/

            }

/*            while (clientInput.hasNext()){
                System.out.println(clientInput.nextLine());
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
