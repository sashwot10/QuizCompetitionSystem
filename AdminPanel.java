package coursework;

import javax.swing.*;
import java.awt.*;

/**
 * Main admin dashboard panel.
 * Provides access to player management, question management,
 * and report viewing functionalities for the admin user.
 */

public class AdminPanel extends JPanel {

    public AdminPanel(MainGUI mainGUI) {
        setLayout(null);

        int panelWidth = 500;
        int btnWidth = 220, btnHeight = 40, yStart = 80, yGap = 50;

        // Title centered
        JLabel lblTitle = new JLabel("Admin Panel", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(0, 20, panelWidth, 30);
        add(lblTitle);

        // Buttons centered horizontally
        String[] btnLabels = {"Add Question", "Update Question", "Delete Question", "View Questions", "View Reports", "Logout"};
        Runnable[] actions = {
                () -> mainGUI.showAddQuestion(),
                () -> mainGUI.showUpdateQuestion(),
                () -> mainGUI.showDeleteQuestion(),
                () -> mainGUI.showViewQuestions(),
                () -> mainGUI.showViewReports(),
                () -> mainGUI.showPlayerRegistration()
        };

        for (int i = 0; i < btnLabels.length; i++) {
            JButton btn = new JButton(btnLabels[i]);
            btn.setBounds((panelWidth - btnWidth) / 2, yStart + i * yGap, btnWidth, btnHeight);
            final int idx = i;
            btn.addActionListener(e -> actions[idx].run());
            add(btn);
        }
    }
}