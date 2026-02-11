package coursework;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * Panel that allows viewing existing quiz question.
 * The question is loaded  via a database SELECT query.
 * Users can view the question text, options (A-D), correct answer, and difficulty level.
 */

public class ViewQuestionsPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    public ViewQuestionsPanel(MainGUI mainGUI) {
        setLayout(null);

        // Title label
        JLabel lbl = new JLabel("All Questions", SwingConstants.CENTER);
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD, 18f));
        lbl.setBounds(0, 10, 600, 30);  // Centered
        add(lbl);

        // Table columns
        String[] cols = {"ID", "Question", "A", "B", "C", "D", "Correct", "Level"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        table.setRowHeight(24); // Slightly bigger rows
        table.setGridColor(Color.GRAY);
        table.setShowGrid(true);

        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 14f));

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 50, 1260, 700);  // Margin from edges
        add(sp);

        // Adjust column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(40);   // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(400);  // Question
        table.getColumnModel().getColumn(2).setPreferredWidth(80);   // A
        table.getColumnModel().getColumn(3).setPreferredWidth(80);   // B
        table.getColumnModel().getColumn(4).setPreferredWidth(80);   // C
        table.getColumnModel().getColumn(5).setPreferredWidth(80);   // D
        table.getColumnModel().getColumn(6).setPreferredWidth(90);   // Correct
        table.getColumnModel().getColumn(7).setPreferredWidth(120);   // Level

     // Assuming your JScrollPane is named 'sp'
        int tableWidth = sp.getWidth();
        int tableX = sp.getX();
        int btnWidth = 120;
        int btnHeight = 35;

        // Back button centered below the table
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(tableX + (tableWidth - btnWidth) / 2, sp.getY() + sp.getHeight() + 15, btnWidth, btnHeight);
        add(btnBack);

        btnBack.addActionListener(e -> mainGUI.showAdminPanel());
        // Load data
        loadQuestions();
    }

    private void loadQuestions() {
        model.setRowCount(0); // Clear existing rows
        try (Connection con = DB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(
                     "SELECT * FROM questions ORDER BY " +
                     "CASE level " +
                     "WHEN 'BEGINNER' THEN 1 " +
                     "WHEN 'INTERMEDIATE' THEN 2 " +
                     "WHEN 'ADVANCED' THEN 3 END, question_id"
             )) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("question_id"),
                        rs.getString("question_text"),
                        rs.getString("option_a"),
                        rs.getString("option_b"),
                        rs.getString("option_c"),
                        rs.getString("option_d"),
                        rs.getString("correct_option"),
                        rs.getString("level")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading questions: " + e.getMessage());
        }
    }
}