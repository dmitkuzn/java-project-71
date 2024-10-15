package hexlet.code.formatters;

import hexlet.code.DiffTree;

import java.util.List;

public interface Formatter {
    String format(List<DiffTree> diffTrees);
}
