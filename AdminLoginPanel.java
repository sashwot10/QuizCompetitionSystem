package coursework;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Panel for the admin login interface.
 * Provides fields for entering admin username and password,
 * and handles login verification logic.
 */

public class AdminLoginPanel extends JPanel {

    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public AdminLoginPanel(MainGUI mainGUI) {
        setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 50, 100, 25);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(150, 50, 150, 25);
        add(txtUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 100, 100, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 100, 150, 25);
        add(txtPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(150, 150, 150, 30);
        add(btnLogin);
        
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(150, 200, 150, 30);
        add(btnBack);


        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                if (checkCredentials(username, password)) {
                    JOptionPane.showMessageDialog(AdminLoginPanel.this,
                            "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    mainGUI.showAdminPanel(); // Switch to the real AdminPanel
                } else {
                    JOptionPane.showMessageDialog(AdminLoginPanel.this,
                            "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnBack.addActionListener(e -> mainGUI.showPlayerRegistration());
    
    }

    private boolean checkCredentials(String username, String password) {
        String sql = "SELECT * FROM admins WHERE username = ? AND password = ?";
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password); // later, use hashed passwords
            ResultSet rs = stmt.executeQuery();

            return rs.next(); // true if found
        } catch (Exception e) {
            System.out.println("Error checking admin credentials: " + e.getMessage());
        }
        return false;
    }
}