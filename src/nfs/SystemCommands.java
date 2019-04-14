package nfs;

public abstract class SystemCommands implements CommandFactory{

    @Override
    public abstract ProcessBuilder createDirectory(String s);

    @Override
    public abstract ProcessBuilder createFile(String s1, String s2);

    @Override
    public abstract ProcessBuilder readDirectory(String s);

    @Override
    public abstract ProcessBuilder renameFile(String s1, String s2);

    @Override
    public abstract ProcessBuilder removeFile(String s);
}
