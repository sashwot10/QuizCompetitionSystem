package coursework.test;

import coursework.Name;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestName {

    @Test
    public void testInitials() {
        Name n = new Name("John", "Doe");
        assertEquals("JD", n.getInitials());
    }

    @Test
    public void testFullName() {
        Name n = new Name("Alice", "Smith");
        assertEquals("Alice Smith", n.getFullName());
    }
}