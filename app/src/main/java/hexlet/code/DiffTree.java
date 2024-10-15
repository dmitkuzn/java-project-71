package hexlet.code;

import lombok.Getter;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Getter
@EqualsAndHashCode
public class DiffTree {
    private String key;
    private Status status;
    private Object oldValue;
    private Object newValue;

    public enum Status {
        ADDED,
        REMOVED,
        UPDATED,
        UNCHANGED
    }

    public DiffTree() {
    }

    public DiffTree(String key, Status status, Object oldValue, Object newValue) {
        this.key = key;
        this.status = status;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public static ArrayList<DiffTree> buildDiffTree(Set<String> allKeys,
                                                        Map<String, Object> mapOne, Map<String, Object> mapTwo) {
        ArrayList<DiffTree> diffTree = new ArrayList<DiffTree>();
        for (String key : allKeys) {
            boolean inMap1 = mapOne.containsKey(key);
            boolean inMap2 = mapTwo.containsKey(key);
            if (inMap1 && !inMap2) {
                diffTree.add(new DiffTree(key, DiffTree.Status.REMOVED, mapOne.get(key), null));
            } else if (!inMap1 && inMap2) {
                diffTree.add(new DiffTree(key, DiffTree.Status.ADDED, null, mapTwo.get(key)));
            } else {
                Object val1 = mapOne.get(key);
                Object val2 = mapTwo.get(key);
                if (!Objects.equals(val1, val2)) {
                    diffTree.add(new DiffTree(key, DiffTree.Status.UPDATED, val1, val2));
                } else {
                    diffTree.add(new DiffTree(key, DiffTree.Status.UNCHANGED, val1, val2));
                }
            }
        }
        return diffTree;
    }

}
