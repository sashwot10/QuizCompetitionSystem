package coursework;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Panel that displays the player details after login.
 * Shows information such as player ID, full name, age, level, and overall score.
 */
public class PlayerDetailsPanel extends JPanel {


    private static final long serialVersionUID = 1L;

    public PlayerDetailsPanel(MainGUI mainGUI, Player player) {
        setLayout(null);

        // --- Title ---
        JLabel lblTitle = new JLabel("My Details", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(0, 20, 450, 30); // Centered horizontally
        add(lblTitle);

        // --- Details area ---
        JTextArea taDetails = new JTextArea(player.getFullDetails());
        taDetails.setEditable(false);
        taDetails.setFont(new Font("Monospaced", Font.PLAIN, 14));
        taDetails.setLineWrap(true);
        taDetails.setWrapStyleWord(true);

        // Add a border & padding for a card-like look
        taDetails.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                new EmptyBorder(10, 10, 10, 10)
        ));

        JScrollPane scroll = new JScrollPane(taDetails);
        scroll.setBounds(50, 70, 350, 200); // Same size, adds scroll if needed
        add(scroll);

        // --- Back button centered below details ---
        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.PLAIN, 14));
        btnBack.setBounds(150, 290, 150, 35);
        add(btnBack);

        btnBack.addActionListener(e -> mainGUI.showPlayerHome(player));
    }
}