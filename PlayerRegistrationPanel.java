package coursework;

import java.sql.Connection;

import javax.swing.*;

/**
 * Initial panel displayed in the main frame.
 * Provides buttons for player registration, player login, and admin login.
 */
public class PlayerRegistrationPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private JTextField tfFirstName, tfLastName, tfAge;
    private JComboBox<Level> cbLevel;
    private JButton btnRegister, btnAdminLogin;

    private MainGUI mainGUI;

    public PlayerRegistrationPanel(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
        setLayout(null);

        JLabel lblTitle = new JLabel("Player Registration");
        lblTitle.setBounds(150, 20, 200, 25);
        add(lblTitle);

        // First Name
        add(new JLabel("First Name:")).setBounds(50, 60, 100, 25);
        tfFirstName = new JTextField();
        tfFirstName.setBounds(150, 60, 200, 25);
        add(tfFirstName);

        // Last Name
        add(new JLabel("Last Name:")).setBounds(50, 100, 100, 25);
        tfLastName = new JTextField();
        tfLastName.setBounds(150, 100, 200, 25);
        add(tfLastName);

        // Age
        add(new JLabel("Age:")).setBounds(50, 140, 100, 25);
        tfAge = new JTextField();
        tfAge.setBounds(150, 140, 200, 25);
        add(tfAge);

        // Level
        add(new JLabel("Level:")).setBounds(50, 180, 100, 25);
        cbLevel = new JComboBox<>(Level.values());
        cbLevel.setBounds(150, 180, 200, 25);
        add(cbLevel);

     

        // Buttons
        btnRegister = new JButton("Register");
        btnRegister.setBounds(100, 260, 120, 30);
        add(btnRegister);

        btnAdminLogin = new JButton("Admin Login");
        btnAdminLogin.setBounds(230, 260, 120, 30);
        add(btnAdminLogin);
        
        JButton btnPlayerLogin = new JButton("Player Login");
        btnPlayerLogin.setBounds(165, 300, 150, 30);
        add(btnPlayerLogin);

        btnPlayerLogin.addActionListener(e -> mainGUI.showPlayerLogin());

        // Button actions
        btnRegister.addActionListener(e -> registerPlayer());
        btnAdminLogin.addActionListener(e -> mainGUI.showAdminLogin());
    }

    private void registerPlayer() {
        String firstName = tfFirstName.getText().trim();
        String lastName = tfLastName.getText().trim();
        String ageText = tfAge.getText().trim();
        Level level = (Level) cbLevel.getSelectedItem();
       

        if (firstName.isEmpty() || lastName.isEmpty() || ageText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Age must be a number.");
            return;
        }

        Name name = new Name(firstName, lastName);
        Player player = new Player(0, name, level, age); // ID will be assigned by DB

        try (Connection conn = DB.getConnection()) {
            String sql = "INSERT INTO players (first_name, last_name, level, age) VALUES (?, ?, ?, ?)";
            java.sql.PreparedStatement stmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, level.name());
            stmt.setInt(4, age);
            stmt.executeUpdate();

            // Fetch auto-generated ID
            java.sql.ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                int newId = keys.getInt(1);
                player.setPlayerId(newId);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving player: " + e.getMessage());
            return;
        }

        JOptionPane.showMessageDialog(this, "Registered successfully! Your ID: " + player.getPlayerId());
        mainGUI.showPlayerHome(player);
    }
}