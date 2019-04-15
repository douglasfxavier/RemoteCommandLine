package nfs;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.concurrent.Executors;

public class CommandProcessBuilder {

    public void exec(ProcessBuilder builder, PrintWriter out) throws IOException, InterruptedException {

        builder.directory(new File(System.getProperty("user.home")));
        Process process = builder.start();

        StreamGobbler streamGobbler =
                new StreamGobbler(process.getInputStream(), out::println);
        Executors.newSingleThreadExecutor().submit(streamGobbler);

        int exitCode = process.waitFor();
        assert exitCode == 0;
        out.println("ENDINPUT");
        out.flush();

    }
}
