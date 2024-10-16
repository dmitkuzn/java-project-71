package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
            description = "Compares two configuration files and shows a difference.",
            version = "gendiff v.0.0.1")
public final class App implements Callable {

    @Option(names = { "-f", "--format" }, defaultValue = "stylish", paramLabel = "format",
                        description = "output format [default: stylish]")
    private String formatName;
    @Parameters(index = "0", defaultValue = "", paramLabel = "filepath1", description = "path to first file")
    private String filePath1;
    @Parameters(index = "1", defaultValue = "", paramLabel = "filepath2", description = "path to second file")
    private String filePath2;

    @Override
    public String call() throws Exception {
        System.out.println(Differ.generate(filePath1, filePath2, formatName));
        return "Executed";
    }

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(new App());
        if (args.length == 0) {
            commandLine.usage(System.out);
            return;
        }
        try {
            commandLine.call(new App(), args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
