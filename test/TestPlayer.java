package coursework.test;

import coursework.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestPlayer {

    @Test
    public void testGetFullName() {
        Player p = new Player(1, new Name("John", "Doe"), Level.BEGINNER, 20);
        assertEquals("John Doe", p.getFullName());
    }

    @Test
    public void testShortDetailsFormat() {
        Player p = new Player(2, new Name("Alice", "Green"), Level.INTERMEDIATE, 25);
        assertEquals("CN 2 (AG) has overall score 0.", p.getShortDetails());
    }

    @Test
    public void testFullDetailsFormat() {
        Player p = new Player(3, new Name("Bob", "Smith"), Level.ADVANCED, 30);
        String expected = "Player ID 3, name Bob Smith, age 30. Bob is a ADVANCED level player and has an overall score of 0.0.";
        assertEquals(expected, p.getFullDetails());
    }
}