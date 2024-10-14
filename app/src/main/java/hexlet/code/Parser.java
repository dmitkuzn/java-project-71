package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Path;
import java.util.Map;
import java.nio.file.Files;

public final class Parser {

    public static Map<String, Object> parse(Path absFilePath) throws Exception {
        String fileContent = Files.readString(absFilePath);
        ObjectMapper objectMapper;
        if (absFilePath.toString().endsWith(".json")) {
            objectMapper = new ObjectMapper();
        } else if (absFilePath.toString().endsWith(".yml") || absFilePath.toString().endsWith(".yaml")) {
            objectMapper = new ObjectMapper(new YAMLFactory());
        } else {
            System.out.println("Unknown file extension!");
            return null;
        }
        return objectMapper.readValue(fileContent, new TypeReference<Map<String, Object>>() { });
    }
}
