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

public class Differ {
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        Path absFilePath1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path absFilePath2 = Paths.get(filePath2).toAbsolutePath().normalize();
        StringBuilder diffResult = new StringBuilder("");

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
            // Сравниваем значения по каждому ключу
            diffResult.append("{\n");
            for (String key : allKeys) {
                boolean inMap1 = map1.containsKey(key);
                boolean inMap2 = map2.containsKey(key);
                if (inMap1 && !inMap2) {
                    diffResult.append(String.format("  - %s: %s\n", key, map1.get(key)));
                } else if (!inMap1 && inMap2) {
                    diffResult.append(String.format("  + %s: %s\n", key, map2.get(key)));
                } else {
                    Object val1 = map1.get(key);
                    Object val2 = map2.get(key);
                    if (!Objects.equals(val1, val2)) {
                        diffResult.append(String.format("  - %s: %s\n", key, val1));
                        diffResult.append(String.format("  + %s: %s\n", key, val2));
                    } else {
                        diffResult.append(String.format("    %s: %s\n", key, val1));
                    }
                }
            }
            diffResult.append("}");
        } else {
            System.out.println("One or Bth files not found!");
            return null;
        }
        return diffResult.toString();
    }
}
