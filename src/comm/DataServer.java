package comm;

import nfs.CommandProcessBuilder;
import nfs.SystemCommands;
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
            CommandProcessBuilder commandProcessBuilder = new CommandProcessBuilder();


            SystemCommands systemCommands = null;
            boolean isWindows = System.getProperty("os.name").
                    toLowerCase().startsWith("windows");
            if (isWindows){
                systemCommands = new WindowsCommands();
            }

            System.out.println("Server listening...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Conexão aceita!");
            Scanner clientInput = new Scanner(clientSocket.getInputStream());
            PrintWriter clientOutput = new PrintWriter(clientSocket.getOutputStream());

            while(clientInput.hasNextLine()){

                String[] commands = clientInput.nextLine().split(" ");

                if (commands.length <=1){
                    clientOutput.println("Parâmetros insuficientes");
                    clientOutput.flush();
                }

                switch(commands[0].toUpperCase()) {
                    case "READDIR":
                        builder = systemCommands.readDirectory(commands[1]);
                        break;
                    case "CREATEDIR":
                        String folderName = commands[1];
                        builder = systemCommands.createDirectory(folderName);
                        clientOutput.println(String.format("A pasta %s foi criada com sucesso.",folderName));
                        clientOutput.flush();
                        break;
                    case "CREATEFILE":
                        builder = systemCommands.createFile(commands[1],commands[2]);
                        break;
                    case "RENAMEFILE":
                        if (commands.length <=2){
                            clientOutput.println("Parâmetros insuficientes");
                            clientOutput.flush();
                        }
                        builder = systemCommands.renameFile(commands[1],commands[2]);
                        break;
                    case "REMOVEFILE":
                        builder = systemCommands.removeFile(commands[1]);
                        break;
                    default:
                        clientOutput.println("Comando não encontrado!");
                        clientOutput.flush();
                }
                commandProcessBuilder.exec(builder, clientOutput);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
