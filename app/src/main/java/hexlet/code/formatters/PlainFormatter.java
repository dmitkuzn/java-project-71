package hexlet.code.formatters;

import hexlet.code.DiffNode;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class PlainFormatter implements Formatter {
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
                    throw new IllegalArgumentException("Unknown status: " + node.getStatus());
            }
        }
        return joiner.toString();
    }

    private String formatValue(Object value) {
        if (isComplexValue(value)) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else if (value == null) {
            return "null";
        } else {
            return value.toString();
        }
    }

    private boolean isComplexValue(Object value) {
        return value instanceof Map<?, ?> || value instanceof List<?>;
    }
}
