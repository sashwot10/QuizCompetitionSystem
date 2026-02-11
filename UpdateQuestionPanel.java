package coursework;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Panel that allows updating an existing quiz question.
 * The question is loaded using its ID via a database SELECT query.
 * Users can edit the question text, options (A-D), correct answer, and difficulty level.
 */
public class UpdateQuestionPanel extends JPanel {

    private JTextField txtId, txtQuestion, txtOptionA, txtOptionB, txtOptionC, txtOptionD, txtCorrect;
    private JComboBox<String> cmbLevel;

    public UpdateQuestionPanel(MainGUI mainGUI) {
        setLayout(null);

        // Title
        JLabel lblTitle = new JLabel("Update Question");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(0, 10, 800, 30);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle);

        int labelX = 50, fieldX = 180, widthField = 400, heightField = 30, gapY = 50;
        int startY = 60;

        // ID
        JLabel lblId = new JLabel("Question ID:");
        lblId.setBounds(labelX, startY, 120, heightField);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(fieldX, startY, 100, heightField);
        add(txtId);

        JButton btnLoad = new JButton("Load");
        btnLoad.setBounds(fieldX + 120, startY, 100, heightField);
        add(btnLoad);

        // Question Text
        JLabel lblQ = new JLabel("Question:");
        lblQ.setBounds(labelX, startY + gapY, 120, heightField);
        add(lblQ);

        txtQuestion = new JTextField();
        txtQuestion.setBounds(fieldX, startY + gapY, widthField, heightField);
        add(txtQuestion);

        // Options A-D
        JLabel lblA = new JLabel("Option A:");
        lblA.setBounds(labelX, startY + 2*gapY, 120, heightField);
        add(lblA);
        txtOptionA = new JTextField(); txtOptionA.setBounds(fieldX, startY + 2*gapY, 180, heightField); add(txtOptionA);

        JLabel lblB = new JLabel("Option B:");
        lblB.setBounds(labelX, startY + 3*gapY, 120, heightField);
        add(lblB);
        txtOptionB = new JTextField(); txtOptionB.setBounds(fieldX, startY + 3*gapY, 180, heightField); add(txtOptionB);

        JLabel lblC = new JLabel("Option C:");
        lblC.setBounds(labelX, startY + 4*gapY, 120, heightField);
        add(lblC);
        txtOptionC = new JTextField(); txtOptionC.setBounds(fieldX, startY + 4*gapY, 180, heightField); add(txtOptionC);

        JLabel lblD = new JLabel("Option D:");
        lblD.setBounds(labelX, startY + 5*gapY, 120, heightField);
        add(lblD);
        txtOptionD = new JTextField(); txtOptionD.setBounds(fieldX, startY + 5*gapY, 180, heightField); add(txtOptionD);

        // Correct option
        JLabel lblCorrect = new JLabel("Correct (A-D):");
        lblCorrect.setBounds(labelX, startY + 6*gapY, 120, heightField);
        add(lblCorrect);

        txtCorrect = new JTextField();
        txtCorrect.setBounds(fieldX, startY + 6*gapY, 50, heightField);
        add(txtCorrect);

        // Level
        JLabel lblLevel = new JLabel("Level:");
        lblLevel.setBounds(labelX, startY + 7*gapY, 120, heightField);
        add(lblLevel);

        cmbLevel = new JComboBox<>(new String[]{"BEGINNER", "INTERMEDIATE", "ADVANCED"});
        cmbLevel.setBounds(fieldX, startY + 7*gapY, 180, heightField);
        add(cmbLevel);

        // Buttons
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(200, startY + 8*gapY, 150, 35);
        add(btnUpdate);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(400, startY + 8*gapY, 150, 35);
        add(btnBack);

        // Load question by ID
        btnLoad.addActionListener(e -> {
            String idText = txtId.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Question ID");
                return;
            }

            try (Connection con = DB.getConnection()) {
                String sql = "SELECT * FROM questions WHERE question_id=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(idText));
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    txtQuestion.setText(rs.getString("question_text"));
                    txtOptionA.setText(rs.getString("option_a"));
                    txtOptionB.setText(rs.getString("option_b"));
                    txtOptionC.setText(rs.getString("option_c"));
                    txtOptionD.setText(rs.getString("option_d"));
                    txtCorrect.setText(rs.getString("correct_option"));
                    cmbLevel.setSelectedItem(rs.getString("level"));
                } else {
                    JOptionPane.showMessageDialog(this, "Question ID not found");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error loading question: " + ex.getMessage());
            }
        });

        // Update question
        btnUpdate.addActionListener(e -> {
            String idText = txtId.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Question ID first");
                return;
            }

            try (Connection con = DB.getConnection()) {
                String sql = "UPDATE questions SET question_text=?, option_a=?, option_b=?, option_c=?, option_d=?, correct_option=?, level=? WHERE question_id=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, txtQuestion.getText());
                ps.setString(2, txtOptionA.getText());
                ps.setString(3, txtOptionB.getText());
                ps.setString(4, txtOptionC.getText());
                ps.setString(5, txtOptionD.getText());
                ps.setString(6, txtCorrect.getText().toUpperCase());
                ps.setString(7, cmbLevel.getSelectedItem().toString());
                ps.setInt(8, Integer.parseInt(idText));

                int rows = ps.executeUpdate();
                JOptionPane.showMessageDialog(this, rows > 0 ? "Updated successfully" : "ID not found");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Update failed: " + ex.getMessage());
            }
        });

        btnBack.addActionListener(e -> mainGUI.showAdminPanel());
    }
}