package nfs;

public class WindowsCommands extends SystemCommands {

    ProcessBuilder builder;

    public WindowsCommands() {
        this.builder = new ProcessBuilder();
    }

    @Override
    public ProcessBuilder createDirectory(String folderName) {
        builder.command("cmd.exe","/c","mkdir",folderName);
        return builder;
    }

    @Override
    public ProcessBuilder createFile(String texto, String fileName) {
        builder.command("cmd.exe","/c","echo", texto, ">",fileName);
        return builder;
    }

    @Override
    public ProcessBuilder readDirectory(String directory) {
        builder.command("cmd.exe", "/c","dir " + directory);
        return builder;
    }

    @Override
    public ProcessBuilder renameFile(String currentName, String newName) {
        builder.command("cmd.exe", "/c","ren ", currentName, newName);
        return builder;
    }

    @Override
    public ProcessBuilder removeFile(String fileName) {
        builder.command("cmd.exe", "/c","del ", fileName);
        return builder;
   }
}
