package coursework;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * QuizPanel handles the quiz-taking process for a player.
 *
 * <p>
 * Responsibilities:
 * <ul>
 *     <li>Loads questions from database based on player level</li>
 *     <li>Displays one question at a time</li>
 *     <li>Tracks correct answers</li>
 *     <li>Saves raw scores for each quiz round</li>
 *     <li>Calculates weighted score after 5 quizzes</li>
 * </ul>
 * </p>
 *
 * <p>
 * Important Design Decision:
 * Raw scores are stored in the database.
 * Level multipliers are applied only when calculating final or leaderboard scores.
 * </p>
 */
public class QuizPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private JLabel lblQuestion;
    private JRadioButton rbA, rbB, rbC, rbD;
    private JButton btnNext;
    private ButtonGroup bg;

    private List<Question> questions;
    private int currentIndex = 0;
    private int correctAnswers = 0;
    private int completedQuizzes = 0;
    private final int TOTAL_QUIZZES = 5;
    private int[] quizScores = new int[TOTAL_QUIZZES];

    private Player player;
    private MainGUI mainGUI;

    /**
     * Constructs the QuizPanel for a specific player.
     *
     * @param mainGUI reference to main GUI controller
     * @param player  currently logged-in player
     */
    public QuizPanel(MainGUI mainGUI, Player player) {
        this.player = player;
        this.mainGUI = mainGUI;

        int confirm = JOptionPane.showConfirmDialog(
                null,
                "You must complete 5 quizzes to have your score recorded.\nDo you want to start?",
                "Quiz Warning",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            SwingUtilities.invokeLater(() -> mainGUI.showPlayerHome(player));
            return;
        }

        setLayout(null);
        setBackground(Color.WHITE);

        lblQuestion = new JLabel("Question will appear here", SwingConstants.CENTER);
        lblQuestion.setFont(new Font("Arial", Font.BOLD, 16));
        lblQuestion.setBounds(50, 20, 500, 60);
        add(lblQuestion);

        rbA = new JRadioButton();
        rbB = new JRadioButton();
        rbC = new JRadioButton();
        rbD = new JRadioButton();

        int startY = 100;
        int spacing = 50;
        int optionWidth = 450;

        rbA.setBounds(50, startY, optionWidth, 30);
        rbB.setBounds(50, startY + spacing, optionWidth, 30);
        rbC.setBounds(50, startY + spacing * 2, optionWidth, 30);
        rbD.setBounds(50, startY + spacing * 3, optionWidth, 30);

        rbA.setFont(new Font("Arial", Font.PLAIN, 14));
        rbB.setFont(new Font("Arial", Font.PLAIN, 14));
        rbC.setFont(new Font("Arial", Font.PLAIN, 14));
        rbD.setFont(new Font("Arial", Font.PLAIN, 14));

        add(rbA); add(rbB); add(rbC); add(rbD);

        bg = new ButtonGroup();
        bg.add(rbA); bg.add(rbB); bg.add(rbC); bg.add(rbD);

        btnNext = new JButton("Next");
        btnNext.setBounds(225, startY + spacing * 4 + 10, 100, 35);
        btnNext.setFont(new Font("Arial", Font.BOLD, 14));
        add(btnNext);

        btnNext.addActionListener(ev -> handleNextButton());

        loadNewQuiz();
    }

    /**
     * Handles Next button click.
     * Checks answer, progresses quiz, and stores scores when completed.
     */
    private void handleNextButton() {
        checkAnswer();
        currentIndex++;

        if (currentIndex < questions.size()) {
            displayQuestion();
        } else {
            quizScores[completedQuizzes] = correctAnswers;
            saveQuizScore(completedQuizzes + 1, correctAnswers);

            JOptionPane.showMessageDialog(this,
                    "Quiz " + (completedQuizzes + 1) + " Completed!\n" +
                            "Correct Answers: " + correctAnswers + " / " + questions.size());

            completedQuizzes++;

            if (completedQuizzes < TOTAL_QUIZZES) {
                loadNewQuiz();
            } else {
                saveFinalScore();
                mainGUI.showPlayerHome(player);
            }
        }
    }

    /**
     * Loads a new quiz (5 random questions) based on player level.
     */
    private void loadNewQuiz() {
        questions = fetchQuestions(player.getLevel().name());
        if (questions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No questions available for your level!");
            mainGUI.showPlayerHome(player);
            return;
        }
        currentIndex = 0;
        correctAnswers = 0;
        displayQuestion();
    }

    /**
     * Displays the current question and answer options.
     */
    private void displayQuestion() {
        Question q = questions.get(currentIndex);
        lblQuestion.setText("<html><body style='width:450px'><b>Q" + (currentIndex + 1) + ":</b> " + q.getQuestionText() + "</body></html>");
        rbA.setText("A. " + q.getOptionA());
        rbB.setText("B. " + q.getOptionB());
        rbC.setText("C. " + q.getOptionC());
        rbD.setText("D. " + q.getOptionD());
        bg.clearSelection();
    }

    /**
     * Checks if selected answer matches correct option.
     */
    private void checkAnswer() {
        Question q = questions.get(currentIndex);
        String selected = null;

        if (rbA.isSelected()) selected = "A";
        else if (rbB.isSelected()) selected = "B";
        else if (rbC.isSelected()) selected = "C";
        else if (rbD.isSelected()) selected = "D";

        if (selected != null && selected.equalsIgnoreCase(q.getCorrectOption())) {
            correctAnswers++;
        }
    }

    /**
     * Fetches 5 random questions from the database for a given level.
     *
     * @param level difficulty level
     * @return list of Question objects
     */
    private List<Question> fetchQuestions(String level) {
        List<Question> list = new ArrayList<>();
        try (Connection con = DB.getConnection()) {
            String sql = "SELECT * FROM questions WHERE level=? ORDER BY RAND() LIMIT 5";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, level);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Question q = new Question(
                        rs.getInt("question_id"),
                        rs.getString("question_text"),
                        rs.getString("option_a"),
                        rs.getString("option_b"),
                        rs.getString("option_c"),
                        rs.getString("option_d"),
                        rs.getString("correct_option"),
                        rs.getString("level")
                );
                list.add(q);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching questions: " + e.getMessage());
        }
        return list;
    }

    /**
     * Saves raw quiz score into the results table.
     *
     * @param quizNumber round number (1–5)
     * @param score      number of correct answers
     */
    private void saveQuizScore(int quizNumber, int score) {
        try (Connection con = DB.getConnection()) {
            String sql = "INSERT INTO results (player_id, quiz_number, score, level) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, player.getPlayerId());
            ps.setInt(2, quizNumber);
            ps.setInt(3, score);
            ps.setString(4, player.getLevel().name());
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving quiz score: " + e.getMessage());
        }
    }

    /**
     * Calculates weighted score after all quizzes are completed.
     * Displays final result to the player.
     */
    private void saveFinalScore() {
        int totalCorrect = 0;
        for (int s : quizScores) totalCorrect += s;

        double averageCorrect = (double) totalCorrect / TOTAL_QUIZZES;
        double weightedScore = averageCorrect * player.getLevel().getMultiplier();

        JOptionPane.showMessageDialog(this,
                "All quizzes completed!\nWeighted Score: " + String.format("%.2f", weightedScore));
    }
}