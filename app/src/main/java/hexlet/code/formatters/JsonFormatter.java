package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffNode;

import java.util.List;

public class JsonFormatter implements Formatter {

    @Override
    public String format(List<DiffNode> diffNodes) {
        try {
            return (new ObjectMapper()).writerWithDefaultPrettyPrinter().writeValueAsString(diffNodes);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка при сериализации в JSON: " + e.getMessage(), e);
        }
    }

}
