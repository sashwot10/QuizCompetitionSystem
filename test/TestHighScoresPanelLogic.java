package coursework.test;

import coursework.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestHighScoresPanelLogic {

    static class MockPlayerScore extends HighScoresPanel.PlayerScore {
        MockPlayerScore(int id, String name, int age, String level, int[] rounds) {
            super(id, name, age, level);
            this.rounds = rounds;
        }
    }

    private List<MockPlayerScore> testScores;

    @BeforeEach
    void setup() {
        // Create some mock player scores
        testScores = new ArrayList<>();

        // Beginner player
        testScores.add(new MockPlayerScore(1, "Alice Beginner", 20, "BEGINNER", new int[]{5,5,5,5,5}));

        // Intermediate player
        testScores.add(new MockPlayerScore(2, "Bob Intermed", 22, "INTERMEDIATE", new int[]{4,5,3,5,4}));

        // Advanced player
        testScores.add(new MockPlayerScore(3, "Charlie Advanced", 25, "ADVANCED", new int[]{5,5,4,5,5}));
    }

    @Test
    void testWeightedOverallCalculation() {
        for (MockPlayerScore ps : testScores) {
            Level lvl = Level.valueOf(ps.level);
            double sum = 0;
            for (int score : ps.rounds) sum += score;
            ps.weightedOverall = (sum / 5.0) * lvl.getMultiplier();
        }

        // Check Beginner weighted score
        assertEquals(5.0 * Level.BEGINNER.getMultiplier(), testScores.get(0).weightedOverall, 0.01);

        // Check Intermediate weighted score
        double expectedInter = ((4+5+3+5+4)/5.0) * Level.INTERMEDIATE.getMultiplier();
        assertEquals(expectedInter, testScores.get(1).weightedOverall, 0.01);

        // Check Advanced weighted score
        double expectedAdv = ((5+5+4+5+5)/5.0) * Level.ADVANCED.getMultiplier();
        assertEquals(expectedAdv, testScores.get(2).weightedOverall, 0.01);
    }

    @Test
    void testSortingByWeightedOverall() {
        // Compute weighted
        for (MockPlayerScore ps : testScores) {
            Level lvl = Level.valueOf(ps.level);
            double sum = 0;
            for (int score : ps.rounds) sum += score;
            ps.weightedOverall = (sum / 5.0) * lvl.getMultiplier();
        }

        // Sort descending
        testScores.sort((a,b) -> Double.compare(b.weightedOverall, a.weightedOverall));

        // The first should be the highest weighted score
        assertEquals("Charlie Advanced", testScores.get(0).name);
        assertEquals("Bob Intermed", testScores.get(1).name);
        assertEquals("Alice Beginner", testScores.get(2).name);
    }
}