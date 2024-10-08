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
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
            description = "Compares two configuration files and shows a difference.",
            version = "gendiff v.0.0.1")
public class App implements Callable{

    @Option(names = { "-f", "--format" }, paramLabel = "format", description = "output format [default: stylish]")
    String outputFormat;
    @Parameters(index = "0", defaultValue = "", paramLabel = "filepath1", description = "path to first file")
    String filePath1;
    @Parameters(index = "1", defaultValue = "", paramLabel = "filepath2", description = "path to second file")
    String filePath2;

    @Override
    public String call() throws Exception {
        System.out.println("Hello World!");
        System.out.println("outputFormat="+outputFormat);
        System.out.println("filePath1="+filePath1);
        System.out.println("filePath2="+filePath2);
        System.out.println(Differ.generate(filePath1, filePath2));
        return "Executed";
    }

    public static void main(String[] args) {
        try {
            CommandLine.call(new App(), args);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
