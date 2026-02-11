package coursework;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * Panel that displays the player login screen using Player ID.
 * Handles player authentication and navigation to the player home panel
 * after successful login.
 */
public class PlayerLoginPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField tfPlayerId;
    private JButton btnLogin, btnBack;
    private MainGUI mainGUI;

    public PlayerLoginPanel(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
        setLayout(null);

        JLabel lblTitle = new JLabel("Player Login");
        lblTitle.setBounds(160, 30, 150, 25);
        add(lblTitle);

        JLabel lblId = new JLabel("Player ID:");
        lblId.setBounds(80, 100, 100, 25);
        add(lblId);

        tfPlayerId = new JTextField();
        tfPlayerId.setBounds(160, 100, 180, 25);
        add(tfPlayerId);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(100, 160, 100, 30);
        add(btnLogin);

        btnBack = new JButton("Back");
        btnBack.setBounds(220, 160, 100, 30);
        add(btnBack);

        btnLogin.addActionListener(e -> loginPlayer());
        btnBack.addActionListener(e -> mainGUI.showPlayerRegistration());
    }

    private void loginPlayer() {
        String idText = tfPlayerId.getText().trim();

        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Player ID");
            return;
        }

        int playerId;
        try {
            playerId = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Player ID must be a number");
            return;
        }

        Player player = loadPlayer(playerId);

        if (player == null) {
            JOptionPane.showMessageDialog(this, "Player not found");
            return;
        }

        JOptionPane.showMessageDialog(this, "Welcome back, " + player.getFullName());
        mainGUI.showPlayerHome(player);
    }

    private Player loadPlayer(int playerId) {
        String sql = "SELECT * FROM players WHERE player_id = ?";

        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, playerId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Name name = new Name(
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );
                Level level = Level.valueOf(rs.getString("level"));
                int age = rs.getInt("age");

                return new Player(playerId, name, level, age);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Login error: " + e.getMessage());
        }

        return null;
    }
}