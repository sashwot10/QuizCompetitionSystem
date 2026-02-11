package coursework;

import java.awt.EventQueue;
import javax.swing.JFrame;


/**
 * Main application window that serves as the central GUI controller.
 * Handles navigation between different panels such as:
 * - Player home
 * - Quiz panel
 * - High scores panel
 * - Admin panel
 * - Report viewing panels
 * 
 * Initializes the JFrame and switches displayed content based on user actions.
 */

public class MainGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    public MainGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        showPlayerRegistration();
    }

    // --- Player Panels ---
    public void showPlayerRegistration() {
        setContentPane(new PlayerRegistrationPanel(this));
        refresh();
    }

    public void showPlayerHome(Player player) {
        setContentPane(new PlayerHomePanel(this, player));
        refresh();
    }

    public void showQuizPanel(Player player) {
        setContentPane(new QuizPanel(this, player));
        refresh();
    }

    public void showHighScoresPanel(Player player) {
        setContentPane(new HighScoresPanel(this, player));
        refresh();
    }

    public void showPlayerDetailsPanel(Player player) {
        setContentPane(new PlayerDetailsPanel(this, player));
        refresh();
    }
    
    public void showPlayerLogin() {
        setContentPane(new PlayerLoginPanel(this));
        refresh();
    }

    // --- Admin Panels ---
    public void showAdminLogin() {
        setContentPane(new AdminLoginPanel(this));
        refresh();
    }

    public void showAdminPanel() {
        setContentPane(new AdminPanel(this));
        refresh();
    }

    public void showAddQuestion() {
        setContentPane(new AddQuestionPanel(this));
        refresh();
    }

    public void showViewQuestions() {
        setContentPane(new ViewQuestionsPanel(this));
        refresh();
    }
    
    public void showViewReports() {
        setContentPane(new ViewReportsPanel(this));
        refresh();
    }

    public void showUpdateQuestion() {
        setContentPane(new UpdateQuestionPanel(this));
        refresh();
    }

    public void showDeleteQuestion() {
        setContentPane(new DeleteQuestionPanel(this));
        refresh();
    }
    

    private void refresh() {
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainGUI frame = new MainGUI();
            frame.setVisible(true);
        });
    }
}