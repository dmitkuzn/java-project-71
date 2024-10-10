package hexlet.code.formatters;

import hexlet.code.DiffNode;
import java.util.List;
import java.util.Map;

public class StylishFormatter implements Formatter {
    private static final int INDENT_SIZE = 4;

    @Override
    public String format(List<DiffNode> diffNodes) {
        StringBuilder result = new StringBuilder("{\n");
        for (DiffNode node : diffNodes) {
            String indent = " ".repeat(INDENT_SIZE - 2);
            switch (node.getStatus()) {
                case ADDED:
                    result.append(indent).append("+ ").append(node.getKey()).append(": ")
                            .append(formatValue(node.getNewValue())).append("\n");
                    break;
                case REMOVED:
                    result.append(indent).append("- ").append(node.getKey()).append(": ")
                            .append(formatValue(node.getOldValue())).append("\n");
                    break;
                case UNCHANGED:
                    result.append(indent).append("  ").append(node.getKey()).append(": ")
                            .append(formatValue(node.getOldValue())).append("\n");
                    break;
                case UPDATED:
                    result.append(indent).append("- ").append(node.getKey()).append(": ")
                            .append(formatValue(node.getOldValue())).append("\n");
                    result.append(indent).append("+ ").append(node.getKey()).append(": ")
                            .append(formatValue(node.getNewValue())).append("\n");
                    break;
                default:
                    break;
            }
        }
        result.append("}");
        return result.toString();
    }

    private String formatValue(Object value) {
        if (value instanceof Map || value instanceof List) {
            return value.toString();
        }
        return String.valueOf(value);
    }
}
