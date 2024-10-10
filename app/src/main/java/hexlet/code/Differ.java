package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.FormatterFactory;


public class Differ {
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        Path absFilePath1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path absFilePath2 = Paths.get(filePath2).toAbsolutePath().normalize();

        List<DiffNode> diffNodes = new ArrayList<>();
        if (Files.exists(absFilePath1) && Files.exists(absFilePath2)) {
            String fileContent1 = Files.readString(absFilePath1);
            String fileContent2 = Files.readString(absFilePath2);

            ObjectMapper objectMapper;
            if (absFilePath1.toString().endsWith(".json")) {
                objectMapper = new ObjectMapper();
            } else if (absFilePath1.toString().endsWith(".yml") || absFilePath1.toString().endsWith(".yaml")) {
                objectMapper = new ObjectMapper(new YAMLFactory());
            } else {
                System.out.println("Unknown file extension!");
                return null;
            }
            Parser parser = new Parser(objectMapper);

            Map<String, Object> map1 = parser.parse(fileContent1);
            Map<String, Object> map2 = parser.parse(fileContent2);

            Set<String> allKeys = new TreeSet<>();
            allKeys.addAll(map1.keySet());
            allKeys.addAll(map2.keySet());

            for (String key : allKeys) {
                boolean inMap1 = map1.containsKey(key);
                boolean inMap2 = map2.containsKey(key);
                if (inMap1 && !inMap2) {
                    diffNodes.add(new DiffNode(key, DiffNode.Status.REMOVED, map1.get(key), null));
                } else if (!inMap1 && inMap2) {
                    diffNodes.add(new DiffNode(key, DiffNode.Status.ADDED, null, map2.get(key)));
                } else {
                    Object val1 = map1.get(key);
                    Object val2 = map2.get(key);
                    if (!Objects.equals(val1, val2)) {
                        diffNodes.add(new DiffNode(key, DiffNode.Status.UPDATED, val1, val2));
                    } else {
                        diffNodes.add(new DiffNode(key, DiffNode.Status.UNCHANGED, val1, val2));
                    }
                }
            }
        } else {
            System.out.println("One or more files not found!");
            return null;
        }
        Formatter formatter = FormatterFactory.getFormatter(formatName);
        return formatter.format(diffNodes);
    }
}
