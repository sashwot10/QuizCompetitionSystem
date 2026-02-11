package coursework;

/**
 * The Question class represents a quiz question retrieved from the database.
 * 
 * <p>
 * Each question contains:
 * </p>
 * <ul>
 *     <li>A unique question ID</li>
 *     <li>The question text</li>
 *     <li>Four possible answer options (A, B, C, D)</li>
 *     <li>The correct option</li>
 *     <li>The difficulty level of the question</li>
 * </ul>
 * 
 * This class is used by the QuizPanel to display questions
 * and validate user answers.
 */
public class Question {
    private int questionId;
    private String questionText, optionA, optionB, optionC, optionD, correctOption, level;

    /**
     * Constructs a Question object.
     *
     * @param id       the unique question ID
     * @param q        the question text
     * @param a        option A
     * @param b        option B
     * @param c        option C
     * @param d        option D
     * @param correct  the correct option (A, B, C, or D)
     * @param lvl      the difficulty level of the question
     */
    public Question(int id, String q, String a, String b, String c, String d, String correct, String lvl) {
        this.questionId = id;
        this.questionText = q;
        this.optionA = a;
        this.optionB = b;
        this.optionC = c;
        this.optionD = d;
        this.correctOption = correct;
        this.level = lvl;
    }

    /**
     * Returns the question ID.
     *
     * @return question ID
     */
    public int getQuestionId() { return questionId; }

    /**
     * Returns the question text.
     *
     * @return question text
     */
    public String getQuestionText() { return questionText; }

    /**
     * Returns option A.
     *
     * @return option A text
     */
    public String getOptionA() { return optionA; }

    /**
     * Returns option B.
     *
     * @return option B text
     */
    public String getOptionB() { return optionB; }

    /**
     * Returns option C.
     *
     * @return option C text
     */
    public String getOptionC() { return optionC; }

    /**
     * Returns option D.
     *
     * @return option D text
     */
    public String getOptionD() { return optionD; }

    /**
     * Returns the correct option.
     *
     * @return correct option (A, B, C, or D)
     */
    public String getCorrectOption() { return correctOption; }

    /**
     * Returns the difficulty level of the question.
     *
     * @return level as a String
     */
    public String getLevel() { return level; }
}