package coursework;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Panel that allows adding a new quiz question to the database.
 * Users can enter the question text, options A-D, the correct answer,
 * and the difficulty level. On submission, the panel executes an
 * INSERT query to save the new question.
 */

public class AddQuestionPanel extends JPanel {

    public AddQuestionPanel(MainGUI mainGUI) {
        setLayout(null);

        int panelWidth = 600;
        int fieldWidth = 350;
        int labelWidth = 120;
        int xLabel = (panelWidth - fieldWidth - labelWidth) / 2;
        int xField = xLabel + labelWidth + 10;
        int y = 30;
        int yGap = 40;

        // Title
        JLabel lblTitle = new JLabel("Add New Question", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setBounds(0, 10, panelWidth, 30);
        add(lblTitle);

        // Labels and fields
        JLabel lblQuestion = new JLabel("Question:");
        lblQuestion.setBounds(xLabel, y, labelWidth, 25);
        add(lblQuestion);
        JTextField txtQuestion = new JTextField();
        txtQuestion.setBounds(xField, y, fieldWidth, 25);
        add(txtQuestion);
        y += yGap;

        JLabel lblA = new JLabel("Option A:");
        lblA.setBounds(xLabel, y, labelWidth, 25);
        add(lblA);
        JTextField txtA = new JTextField();
        txtA.setBounds(xField, y, fieldWidth, 25);
        add(txtA);
        y += yGap;

        JLabel lblB = new JLabel("Option B:");
        lblB.setBounds(xLabel, y, labelWidth, 25);
        add(lblB);
        JTextField txtB = new JTextField();
        txtB.setBounds(xField, y, fieldWidth, 25);
        add(txtB);
        y += yGap;

        JLabel lblC = new JLabel("Option C:");
        lblC.setBounds(xLabel, y, labelWidth, 25);
        add(lblC);
        JTextField txtC = new JTextField();
        txtC.setBounds(xField, y, fieldWidth, 25);
        add(txtC);
        y += yGap;

        JLabel lblD = new JLabel("Option D:");
        lblD.setBounds(xLabel, y, labelWidth, 25);
        add(lblD);
        JTextField txtD = new JTextField();
        txtD.setBounds(xField, y, fieldWidth, 25);
        add(txtD);
        y += yGap;

        JLabel lblCorrect = new JLabel("Correct (A-D):");
        lblCorrect.setBounds(xLabel, y, labelWidth, 25);
        add(lblCorrect);
        JTextField txtCorrect = new JTextField();
        txtCorrect.setBounds(xField, y, 50, 25);
        add(txtCorrect);
        y += yGap;

        JLabel lblLevel = new JLabel("Level:");
        lblLevel.setBounds(xLabel, y, labelWidth, 25);
        add(lblLevel);
        JComboBox<String> cmbLevel = new JComboBox<>(new String[]{"BEGINNER", "INTERMEDIATE", "ADVANCED"});
        cmbLevel.setBounds(xField, y, 150, 25);
        add(cmbLevel);
        y += yGap + 10;

        // Buttons centered
        JButton btnSave = new JButton("Save Question");
        JButton btnBack = new JButton("Back");
        int btnWidth = 150, btnHeight = 35, gap = 20;
        int btnX = (panelWidth - (btnWidth * 2 + gap)) / 2;
        btnSave.setBounds(btnX, y, btnWidth, btnHeight);
        btnBack.setBounds(btnX + btnWidth + gap, y, btnWidth, btnHeight);
        add(btnSave);
        add(btnBack);

        btnSave.addActionListener(e -> {
            try (Connection con = DB.getConnection()) {
                String sql = "INSERT INTO questions " +
                        "(question_text, option_a, option_b, option_c, option_d, correct_option, level) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, txtQuestion.getText());
                ps.setString(2, txtA.getText());
                ps.setString(3, txtB.getText());
                ps.setString(4, txtC.getText());
                ps.setString(5, txtD.getText());
                ps.setString(6, txtCorrect.getText().toUpperCase());
                ps.setString(7, cmbLevel.getSelectedItem().toString());

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Question saved!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error saving question");
            }
        });

        btnBack.addActionListener(e -> mainGUI.showAdminPanel());
    }
}