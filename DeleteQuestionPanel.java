package coursework;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Panel that allows deletion of a quiz question from the database.
 * Users can specify the question ID to remove, and the panel executes
 * a DELETE query to remove the question from the system.
 */

public class DeleteQuestionPanel extends JPanel {

    public DeleteQuestionPanel(MainGUI mainGUI) {
        setLayout(null);

        JLabel lbl = new JLabel("Question ID:");
        lbl.setBounds(80, 80, 100, 25);
        add(lbl);

        JTextField txtId = new JTextField();
        txtId.setBounds(180, 80, 100, 25);
        add(txtId);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(80, 130, 100, 30);
        add(btnDelete);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(190, 130, 100, 30);
        add(btnBack);

        btnDelete.addActionListener(e -> {
            try (Connection con = DB.getConnection()) {
                String sql = "DELETE FROM questions WHERE question_id=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(txtId.getText()));
                int rows = ps.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        rows > 0 ? "Deleted successfully" : "ID not found");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Delete failed");
            }
        });

        btnBack.addActionListener(e -> mainGUI.showAdminPanel());
    }
}