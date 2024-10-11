package hexlet.code.formatters;

import hexlet.code.DiffNode;
import java.util.List;

/**
 * Класс {@code PlainFormatter} предоставляет метод для вывод данных в stylish формате.
 * @author dk
 */
public class StylishFormatter implements Formatter {

    @Override
    public String format(List<DiffNode> diffNodes) {
        StringBuilder result = new StringBuilder("{\n");
        for (DiffNode node : diffNodes) {
            switch (node.getStatus()) {
                case ADDED:
                    result.append("  + ").append(node.getKey()).append(": ")
                            .append(String.valueOf(node.getNewValue())).append("\n");
                    break;
                case REMOVED:
                    result.append("  - ").append(node.getKey()).append(": ")
                            .append(String.valueOf(node.getOldValue())).append("\n");
                    break;
                case UNCHANGED:
                    result.append("    ").append(node.getKey()).append(": ")
                            .append(String.valueOf(node.getOldValue())).append("\n");
                    break;
                case UPDATED:
                    result.append("  - ").append(node.getKey()).append(": ")
                            .append(String.valueOf(node.getOldValue())).append("\n");
                    result.append("  + ").append(node.getKey()).append(": ")
                            .append(String.valueOf(node.getNewValue())).append("\n");
                    break;
                default:
                    break;
            }
        }
        result.append("}");
        return result.toString();
    }

}
