package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffTree;
import java.util.List;

/**
 * Класс {@code JsonFormatter} предоставляет метод для сериализации данных в JSON.
 * @author dk
 */
public class JsonFormatter implements Formatter {
    /**
     * Преобразует список List<DiffNode> в представление JSON и выводит его в человекочитаемом виде.
     */
    @Override
    public String format(List<DiffTree> diffTrees) {
        try {
            return (new ObjectMapper()).writerWithDefaultPrettyPrinter().writeValueAsString(diffTrees);
        } catch (Exception e) {
            System.out.println("Serializarion error to JSON: " + e.getMessage());
        }
        return "";
    }

}
