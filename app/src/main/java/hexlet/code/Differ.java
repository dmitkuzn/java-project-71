package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.FormatterFactory;


public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        return Differ.generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        Path absFilePath1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path absFilePath2 = Paths.get(filePath2).toAbsolutePath().normalize();

        if (!Files.exists(absFilePath1) || !Files.exists(absFilePath2)) {
            System.out.println("One or more files not found!");
            return null;
        }

        Map<String, Object> map1 = Parser.parse(absFilePath1);
        Map<String, Object> map2 = Parser.parse(absFilePath2);

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        List<DiffTree> diffTrees = DiffTree.buildDiffTree(allKeys, map1, map2);

        Formatter formatter = FormatterFactory.getFormatter(formatName);
        return formatter.format(diffTrees);
    }
}
