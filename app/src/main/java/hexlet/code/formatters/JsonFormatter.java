package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffNode;
import java.util.List;

/**
 * Класс {@code JsonFormatter} предоставляет метод для сериализации данных в JSON.
 * @author dk
 */
public class JsonFormatter implements Formatter {

    @Override
    public String format(List<DiffNode> diffNodes) {
        try {
            return (new ObjectMapper()).writerWithDefaultPrettyPrinter().writeValueAsString(diffNodes);
        } catch (Exception e) {
            System.out.println("Serializarion error to JSON: " + e.getMessage());
        }
        return "";
    }

}
