package hexlet.code;

import java.util.Objects;

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

    public String getKey() {
        return key;
    }

    public Status getStatus() {
        return status;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DiffNode diffNode = (DiffNode) o;

        if (!Objects.equals(key, diffNode.key)) {
            return false;
        }
        if (status != diffNode.status) {
            return false;
        }
        if (!Objects.equals(oldValue, diffNode.oldValue)) {
            return false;
        }
        return Objects.equals(newValue, diffNode.newValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, status, oldValue, newValue);
    }

}
