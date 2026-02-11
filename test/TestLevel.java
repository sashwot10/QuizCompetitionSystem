package coursework.test;

import coursework.Level;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestLevel {

    @Test
    public void testMultiplier() {
        assertEquals(0.5, Level.BEGINNER.getMultiplier());
        assertEquals(0.7, Level.INTERMEDIATE.getMultiplier());
        assertEquals(1.0, Level.ADVANCED.getMultiplier());
    }

    @Test
    public void testEnumName() {
        assertEquals("ADVANCED", Level.ADVANCED.name());
    }
}