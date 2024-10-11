package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public final class Parser {
    private ObjectMapper objectMapper;

    public Parser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Map<String, Object> parse(String fileContent) throws Exception {
        return objectMapper.readValue(fileContent, new TypeReference<Map<String, Object>>() { });
    }
}
