package coursework;

import javax.swing.*;
import java.awt.*;

/**
 * Panel that displays the player home screen after login.
 * Shows player details, navigation buttons for quizzes, high scores, and player detail.
 * It also has a logout button.
 */


public class PlayerHomePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public PlayerHomePanel(MainGUI mainGUI, Player player) {
        setLayout(null);
        setBackground(Color.WHITE);

        // --- Title ---
        JLabel lblTitle = new JLabel("Player Home", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setBounds(50, 20, 400, 40);
        add(lblTitle);

        // --- Buttons ---
        int btnWidth = 200;
        int btnHeight = 40;
        int startX = 150;
        int startY = 90;
        int spacing = 60;

        JButton btnPlayQuiz = new JButton("Play Quiz");
        btnPlayQuiz.setBounds(startX, startY, btnWidth, btnHeight);
        btnPlayQuiz.setFont(new Font("Arial", Font.PLAIN, 16));
        add(btnPlayQuiz);

        JButton btnHighScores = new JButton("High Scores");
        btnHighScores.setBounds(startX, startY + spacing, btnWidth, btnHeight);
        btnHighScores.setFont(new Font("Arial", Font.PLAIN, 16));
        add(btnHighScores);

        JButton btnViewDetails = new JButton("My Details");
        btnViewDetails.setBounds(startX, startY + spacing * 2, btnWidth, btnHeight);
        btnViewDetails.setFont(new Font("Arial", Font.PLAIN, 16));
        add(btnViewDetails);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(startX, startY + spacing * 3, btnWidth, btnHeight);
        btnLogout.setFont(new Font("Arial", Font.PLAIN, 16));
        add(btnLogout);

        // --- Button actions ---
        btnPlayQuiz.addActionListener(e -> mainGUI.showQuizPanel(player));
        btnHighScores.addActionListener(e -> mainGUI.showHighScoresPanel(player));
        btnViewDetails.addActionListener(e -> mainGUI.showPlayerDetailsPanel(player));
        btnLogout.addActionListener(e -> mainGUI.showPlayerRegistration());
    }
}