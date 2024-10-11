package hexlet.code.formatters;

import hexlet.code.DiffNode;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Класс {@code PlainFormatter} предоставляет метод для вывод данных в plain формате.
 * @author dk
 */
public class PlainFormatter implements Formatter {

    /**
     * Преобразует список List<DiffNode> в представление plain:
     * Property 'chars2' was updated. From [complex value] to false
     * Property 'checked' was updated. From false to true
     * Property 'default' was updated. From null to [complex value]
     */
    @Override
    public String format(List<DiffNode> diffNodes) {
        StringJoiner joiner = new StringJoiner("\n");
        for (DiffNode node : diffNodes) {
            String property = node.getKey();
            switch (node.getStatus()) {
                case ADDED:
                    joiner.add(String.format("Property '%s' was added with value: %s",
                            property, formatValue(node.getNewValue())));
                    break;
                case REMOVED:
                    joiner.add(String.format("Property '%s' was removed", property));
                    break;
                case UPDATED:
                    joiner.add(String.format("Property '%s' was updated. From %s to %s",
                            property, formatValue(node.getOldValue()), formatValue(node.getNewValue())));
                    break;
                case UNCHANGED:
                    // Nothing
                    break;
                default:
                    System.out.println("Unknown status: " + node.getStatus());
            }
        }
        return joiner.toString();
    }

    private String formatValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof Map<?, ?> || value instanceof List<?>) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return value.toString();
        }
    }
}
