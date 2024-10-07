package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.ArrayList;
import java.util.Arrays;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
            description = "Compares two configuration files and shows a difference.",
            version = "gendiff v.0.0.1")
public class App implements Runnable{

    @Option(names = { "-f", "--format" }, paramLabel = "format", description = "output format [default: stylish]")
    String outputFormat;
    @Parameters(index = "0", defaultValue = "", paramLabel = "filepath1", description = "path to first file")
    String filePath1;
    @Parameters(index = "1", defaultValue = "", paramLabel = "filepath2", description = "path to second file")
    String filePath2;

    @Override
    public void run() {
        System.out.println("Hello World!");
        System.out.println("outputFormat="+outputFormat);
        System.out.println("filePath1="+filePath1);
        System.out.println("filePath2="+filePath2);
    }

    public static void main(String[] args) {
        App app = new App();
        //int exitCode = new CommandLine(app.execute(args));
        CommandLine.run(app, args);
        Path absFilePath1 = Paths.get(app.filePath1).toAbsolutePath().normalize();
        Path absFilePath2 = Paths.get(app.filePath2).toAbsolutePath().normalize();
        try {
            if (Files.exists(absFilePath1) && Files.exists(absFilePath2)) {
                String fileContent1 = Files.readString(absFilePath1);
                String fileContent2 = Files.readString(absFilePath2);
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> resultMap1 = objectMapper.readValue(fileContent1, Map.class);
                System.out.println(resultMap1);
                Map<String, Object> resultMap2 = objectMapper.readValue(fileContent2, Map.class);
                System.out.println(resultMap2);
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
