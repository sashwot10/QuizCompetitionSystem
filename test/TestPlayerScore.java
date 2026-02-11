package coursework.test;

import coursework.HighScoresPanel.PlayerScore;
import coursework.Level;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestPlayerScore {

    @Test
    public void testWeightedOverall() {
        PlayerScore ps = new PlayerScore(1, "John Doe", 20, "INTERMEDIATE");
        ps.rounds[0] = 5;
        ps.rounds[1] = 4;
        ps.rounds[2] = 3;
        ps.rounds[3] = 5;
        ps.rounds[4] = 4;

        // compute weighted overall manually
        double sum = 0;
        Level lvl = Level.valueOf(ps.level);
        for (int score : ps.rounds) sum += score;
        ps.weightedOverall = (sum / 5.0) * lvl.getMultiplier();

        assertEquals((5+4+3+5+4)/5.0 * 0.7, ps.weightedOverall);
    }

    @Test
    public void testPerfectScoreAdvanced() {
        PlayerScore ps = new PlayerScore(2, "Alice Green", 22, "ADVANCED");
        for (int i=0; i<5; i++) ps.rounds[i] = 5;

        double sum = 0;
        Level lvl = Level.valueOf(ps.level);
        for (int score : ps.rounds) sum += score;
        ps.weightedOverall = (sum / 5.0) * lvl.getMultiplier();

        assertEquals(5 * 1.0, ps.weightedOverall);
    }
}