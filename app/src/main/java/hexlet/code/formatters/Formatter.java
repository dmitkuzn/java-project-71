package hexlet.code.formatters;

import hexlet.code.DiffNode;

import java.util.List;

public interface Formatter {
    String format(List<DiffNode> diffNodes);
}
