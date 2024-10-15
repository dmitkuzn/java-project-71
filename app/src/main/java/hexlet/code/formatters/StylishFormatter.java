package hexlet.code.formatters;

import hexlet.code.DiffTree;
import java.util.List;

/**
 * Класс {@code PlainFormatter} предоставляет метод для вывод данных в stylish формате.
 * @author dk
 */
public class StylishFormatter implements Formatter {

    /**
     * Преобразует список List<DiffNode> в представление stylish:
     * {
     *     chars1: [a, b, c]
     *   - chars2: [d, e, f]
     *   + chars2: false
     *   - checked: false.
     * }
     */
    @Override
    public String format(List<DiffTree> diffTrees) {
        StringBuilder result = new StringBuilder("{\n");
        for (DiffTree node : diffTrees) {
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
