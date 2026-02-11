package coursework;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.sql.*;
import java.util.*;

/**
 * HighScoresPanel is responsible for displaying the leader board
 * and player performance statistics.
 *
 * <p>
 * This panel:
 * <ul>
 *     <li>Displays scores for 5 quiz rounds</li>
 *     <li>Shows weighted overall score based on player level</li>
 *     <li>Allows filtering by level (Beginner, Intermediate, Advanced, Overall)</li>
 *     <li>Displays score frequency distribution</li>
 * </ul>
 * </p>
 *
 * <p>
 * Only the most recent attempt per player per round is used
 * when calculating leader board results.
 * </p>
 */
public class HighScoresPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private MainGUI mainGUI;
    private Player player;
    private JTable table;
    private DefaultTableModel model;
    private JComboBox<String> cbLevelSelector;
    private JTextArea txtFrequency;

    /**
     * Constructs the HighScoresPanel and initializes UI components.
     *
     * @param mainGUI reference to main GUI controller
     * @param player  currently logged-in player
     */
    public HighScoresPanel(MainGUI mainGUI, Player player) {
        this.mainGUI = mainGUI;
        this.player = player;
        setLayout(null);

        int panelWidth = 1200;
        int tableHeight = 350;

        JLabel lblTitle = new JLabel("Leaderboard & Player Scores", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setBounds(0, 10, panelWidth, 30);
        add(lblTitle);

        cbLevelSelector = new JComboBox<>(new String[]{"BEGINNER", "INTERMEDIATE", "ADVANCED", "OVERALL"});
        cbLevelSelector.setBounds((panelWidth - 200) / 2, 50, 200, 25);
        add(cbLevelSelector);
        cbLevelSelector.addActionListener(e -> loadScores());

        String[] columns = {"Player ID", "Name", "Age", "Round 1", "Round 2", "Round 3", "Round 4", "Round 5", "Overall"};
        model = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        table.setShowGrid(true);
        table.setGridColor(Color.GRAY);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(50, 90, panelWidth - 100, tableHeight);
        scroll.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        add(scroll);

        txtFrequency = new JTextArea();
        txtFrequency.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtFrequency.setEditable(false);
        txtFrequency.setBorder(BorderFactory.createTitledBorder("Score Frequency"));
        txtFrequency.setBounds(50, 460, panelWidth - 100, 120);
        txtFrequency.setMargin(new Insets(5,5,5,5));
        add(txtFrequency);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds((panelWidth - 100) / 2, 600, 100, 35);
        add(btnBack);
        btnBack.addActionListener(e -> mainGUI.showPlayerHome(player));

        loadScores();
    }

    /**
     * Retrieves the fixed level of a player from the database.
     *
     * @param playerId ID of the player
     * @return level as a String (BEGINNER by default if error occurs)
     */
    private String getPlayerLevel(int playerId) {
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT level FROM players WHERE player_id = ?")) {
            ps.setInt(1, playerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString("level");
        } catch (Exception e) { e.printStackTrace(); }
        return "BEGINNER";
    }

    /**
     * Inner helper class used to store temporary player score data
     * before displaying it in the table.
     *
     * <p>
     * Each PlayerScore contains:
     * <ul>
     *     <li>Player ID</li>
     *     <li>Name</li>
     *     <li>Age</li>
     *     <li>Level</li>
     *     <li>Array of 5 round scores</li>
     *     <li>Weighted overall score</li>
     * </ul>
     * </p>
     */
    public static class PlayerScore {
        int id, age;
        public String name;
        public String level;
        public int[] rounds = new int[5];
        public double weightedOverall;

        /**
         * Constructs a PlayerScore object.
         */
        public PlayerScore(int id, String name, int age, String level) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.level = level;
        }
    }

    /**
     * Loads leader board scores from the database and populates the table.
     *
     * <p>
     * Logic:
     * <ul>
     *     <li>Fetch latest attempt per round per player</li>
     *     <li>Filter by selected level if needed</li>
     *     <li>Store scores in an array of size 5</li>
     *     <li>Calculate weighted overall score</li>
     *     <li>Sort if OVERALL selected</li>
     *     <li>Generate frequency distribution</li>
     * </ul>
     * </p>
     */
    private void loadScores() {
        model.setRowCount(0);
        txtFrequency.setText("");

        String selectedLevel = (String) cbLevelSelector.getSelectedItem();

        try (Connection con = DB.getConnection()) {
            Map<Integer, Integer> freqMap = new HashMap<>();
            List<PlayerScore> playerScores = new ArrayList<>();

            String sql;
            PreparedStatement ps;

            if (!"OVERALL".equalsIgnoreCase(selectedLevel)) {
                sql = """
                        SELECT p.player_id, p.first_name, p.last_name, p.age,
                               r.quiz_number, r.score
                        FROM players p
                        JOIN results r ON p.player_id = r.player_id
                        JOIN (
                            SELECT player_id, quiz_number, MAX(quiz_date) AS latest_date
                            FROM results
                            WHERE level = ?
                            GROUP BY player_id, quiz_number
                        ) latest
                          ON r.player_id = latest.player_id
                         AND r.quiz_number = latest.quiz_number
                         AND r.quiz_date = latest.latest_date
                        WHERE r.level = ?
                        ORDER BY p.player_id, r.quiz_number
                        """;
                ps = con.prepareStatement(sql);
                ps.setString(1, selectedLevel);
                ps.setString(2, selectedLevel);
            } else {
                sql = """
                        SELECT p.player_id, p.first_name, p.last_name, p.age,
                               r.quiz_number, r.score
                        FROM players p
                        JOIN results r ON p.player_id = r.player_id
                        JOIN (
                            SELECT player_id, quiz_number, MAX(quiz_date) AS latest_date
                            FROM results
                            GROUP BY player_id, quiz_number
                        ) latest
                          ON r.player_id = latest.player_id
                         AND r.quiz_number = latest.quiz_number
                         AND r.quiz_date = latest.latest_date
                        ORDER BY p.player_id, r.quiz_number
                        """;
                ps = con.prepareStatement(sql);
            }

            ResultSet rs = ps.executeQuery();
            Map<Integer, PlayerScore> tempMap = new HashMap<>();

            while (rs.next()) {
                int pid = rs.getInt("player_id");
                PlayerScore psObj = tempMap.get(pid);

                if (psObj == null) {
                    String name = rs.getString("first_name") + " " + rs.getString("last_name");
                    int age = rs.getInt("age");
                    String level = getPlayerLevel(pid);
                    psObj = new PlayerScore(pid, name, age, level);
                    tempMap.put(pid, psObj);
                }

                int qnum = rs.getInt("quiz_number") - 1;
                int score = rs.getInt("score");

                psObj.rounds[qnum] = score;
                freqMap.put(score, freqMap.getOrDefault(score, 0) + 1);
            }

            for (PlayerScore psObj : tempMap.values()) {
                double sum = 0;
                Level lvl = Level.valueOf(psObj.level);
                for (int score : psObj.rounds) sum += score;
                psObj.weightedOverall = (sum / 5.0) * lvl.getMultiplier();
                playerScores.add(psObj);
            }

            if ("OVERALL".equalsIgnoreCase(selectedLevel)) {
                playerScores.sort((a,b) -> Double.compare(b.weightedOverall, a.weightedOverall));
            }

            for (PlayerScore psObj : playerScores) {
                model.addRow(new Object[]{
                        psObj.id,
                        psObj.name,
                        psObj.age,
                        psObj.rounds[0],
                        psObj.rounds[1],
                        psObj.rounds[2],
                        psObj.rounds[3],
                        psObj.rounds[4],
                        String.format("%.2f", psObj.weightedOverall)
                });
            }

            txtFrequency.append("Score Frequency \nScore: ");
            for(int i=0;i<=5;i++) txtFrequency.append(i+" ");
            txtFrequency.append("\nFreq : ");
            for(int i=0;i<=5;i++) txtFrequency.append(freqMap.getOrDefault(i,0)+" ");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }
}