package nfs;

public interface CommandFactory {
    ProcessBuilder createDirectory(String s);
    ProcessBuilder createFile(String s1, String s2);
    ProcessBuilder readDirectory(String s);
    ProcessBuilder renameFile(String s1, String s2);
    ProcessBuilder removeFile(String s);

}
