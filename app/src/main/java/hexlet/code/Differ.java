package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
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
                Object value1 = map1.get(key);
                Object value2 = map2.get(key);

                if (value1 != null && value2 != null && value1.equals(value2)) {
                    // Ключ и значение одинаковы в обоих файлах
                    diffResult.append("    ").append(key).append(": ").append(value1).append("\n");
                } else {
                    if (value1 != null) {
                        // Ключ присутствует только в первом файле или значения разные
                        diffResult.append("  - ").append(key).append(": ").append(value1).append("\n");
                    }
                    if (value2 != null) {
                        // Ключ присутствует только во втором файле или значения разные
                        diffResult.append("  + ").append(key).append(": ").append(value2).append("\n");
                    }
                }
            }
            diffResult.append("}");
        } else {
            return null;
        }
        return diffResult.toString();
    }
}
