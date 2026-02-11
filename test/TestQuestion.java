package coursework.test;

import coursework.Question;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestQuestion {

    @Test
    public void testCorrectOption() {
        Question q = new Question(1, "What is 2+2?", "3", "4", "5", "6", "B", "BEGINNER");
        assertEquals("B", q.getCorrectOption());
    }

    @Test
    public void testQuestionText() {
        Question q = new Question(2, "Capital of France?", "London", "Berlin", "Paris", "Rome", "C", "BEGINNER");
        assertEquals("Capital of France?", q.getQuestionText());
    }
}