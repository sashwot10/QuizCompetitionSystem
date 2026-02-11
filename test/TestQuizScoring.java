package coursework.test;

import coursework.Level;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestQuizScoring {

    private double computeWeightedScore(int[] quizScores, Level level) {
        int totalCorrect = 0;
        for (int s : quizScores) totalCorrect += s;
        double averageCorrect = (double) totalCorrect / quizScores.length;
        return averageCorrect * level.getMultiplier();
    }

    @Test
    public void testQuizScoreBeginner() {
        int[] scores = {5,5,4,3,5};
        double weighted = computeWeightedScore(scores, Level.BEGINNER);
        assertEquals((5+5+4+3+5)/5.0 * 0.5, weighted);
    }

    @Test
    public void testQuizScoreAdvanced() {
        int[] scores = {4,4,4,4,4};
        double weighted = computeWeightedScore(scores, Level.ADVANCED);
        assertEquals(4 * 1.0, weighted);
    }
}