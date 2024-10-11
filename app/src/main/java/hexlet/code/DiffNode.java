package hexlet.code;

import lombok.Getter;
import lombok.EqualsAndHashCode;

@Getter
@EqualsAndHashCode
public class DiffNode {
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

    public DiffNode(String key, Status status, Object oldValue, Object newValue) {
        this.key = key;
        this.status = status;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
}
