package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeparateClassesTest {
    @Test
    public void testDiffNode() {
        DiffNode node1 = new DiffNode("key1", DiffNode.Status.ADDED, null, "value1");
        DiffNode node2 = new DiffNode("key1", DiffNode.Status.ADDED, null, "value1");
        assertEquals(node1, node2);
        assertEquals("key1", node1.getKey());
        assertEquals(DiffNode.Status.ADDED, node1.getStatus());
        assertEquals(null, node1.getOldValue());
        assertEquals("value1", node1.getNewValue());
        assertEquals(node1.hashCode(), node2.hashCode());
    }
}
