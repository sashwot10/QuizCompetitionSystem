package coursework;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The ViewReportsPanel class represents the administrative reports panel
 * of the quiz competition system.
 *
 * <p>This panel displays:</p>
 * <ul>
 *     <li>Total number of registered players</li>
 *     <li>Player statistics (average, highest, lowest scores)</li>
 *     <li>Highest scorer per level (only players who completed 5 quizzes)</li>
 *     <li>Score frequency distribution</li>
 * </ul>
 *
 * <p>The class retrieves data from the MySQL database using JDBC and
 * presents the results in styled JTable components.</p>
 *
 * @author
 */
public class ViewReportsPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs the ViewReportsPanel and loads all statistical reports
     * from the database.
     *
     * <p>This constructor initializes the layout, creates all tables,
     * executes the required SQL queries, and populates the report data.</p>
     *
     * @param mainGUI Reference to the main GUI for navigation purposes
     */
    public ViewReportsPanel(MainGUI mainGUI) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JLabel lblTitle = new JLabel("Player Reports & Quiz Statistics", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitle, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        add(centerPanel, BorderLayout.CENTER);

        JLabel lblTotalPlayers = new JLabel("Total Players: ");
        lblTotalPlayers.setFont(new Font("Arial", Font.BOLD, 13));
        lblTotalPlayers.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(lblTotalPlayers);

        centerPanel.add(Box.createVerticalStrut(10));

        String[] columns = {
                "Player ID", "Name", "Level",
                "Quizzes Played", "Average", "Highest", "Lowest"
        };

        DefaultTableModel statsModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        JTable statsTable = createStyledTable(statsModel);
        JScrollPane statsScroll = new JScrollPane(statsTable);
        statsScroll.setPreferredSize(new Dimension(800, 200));
        centerPanel.add(statsScroll);

        centerPanel.add(Box.createVerticalStrut(15));

        JLabel lblTop = new JLabel("Highest Scorer Per Level (Completed 5 Quizzes)");
        lblTop.setFont(new Font("Arial", Font.BOLD, 14));
        lblTop.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(lblTop);

        DefaultTableModel topModel = new DefaultTableModel(
                new String[]{"Level", "Player Name", "Average Score"}, 0
        ) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        JTable topTable = createStyledTable(topModel);
        JScrollPane topScroll = new JScrollPane(topTable);
        topScroll.setPreferredSize(new Dimension(600, 120));
        centerPanel.add(topScroll);

        centerPanel.add(Box.createVerticalStrut(15));

        JLabel lblFreq = new JLabel("Score Frequency (All Attempts)");
        lblFreq.setFont(new Font("Arial", Font.BOLD, 14));
        lblFreq.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(lblFreq);

        DefaultTableModel freqModel = new DefaultTableModel(
                new String[]{"Score", "Frequency"}, 0
        ) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        JTable freqTable = createStyledTable(freqModel);
        JScrollPane freqScroll = new JScrollPane(freqTable);
        freqScroll.setPreferredSize(new Dimension(300, 140));
        centerPanel.add(freqScroll);

        JButton btnBack = new JButton("Back");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnBack);
        add(bottomPanel, BorderLayout.SOUTH);

        btnBack.addActionListener(e -> mainGUI.showAdminPanel());

        try (Connection con = DB.getConnection()) {

            ResultSet rsCount = con.createStatement()
                    .executeQuery("SELECT COUNT(*) AS total FROM players");
            if (rsCount.next()) {
                lblTotalPlayers.setText("Total Players: " + rsCount.getInt("total"));
            }

            String sqlStats = """
                SELECT r.player_id, p.first_name, p.last_name, r.level,
                       COUNT(*) AS quizzes_played,
                       AVG(r.score) AS avg_score,
                       MAX(r.score) AS max_score,
                       MIN(r.score) AS min_score
                FROM results r
                JOIN players p ON r.player_id = p.player_id
                GROUP BY r.player_id, r.level
                ORDER BY r.level, avg_score DESC
            """;

            ResultSet rsStats = con.createStatement().executeQuery(sqlStats);
            while (rsStats.next()) {
                statsModel.addRow(new Object[]{
                        rsStats.getInt("player_id"),
                        rsStats.getString("first_name") + " " + rsStats.getString("last_name"),
                        rsStats.getString("level"),
                        rsStats.getInt("quizzes_played"),
                        String.format("%.2f", rsStats.getDouble("avg_score")),
                        rsStats.getDouble("max_score"),
                        rsStats.getDouble("min_score")
                });
            }

            String sqlTop = """
                SELECT r.level,
                       CONCAT(p.first_name, ' ', p.last_name) AS name,
                       AVG(r.score) AS avg_score
                FROM results r
                JOIN players p ON r.player_id = p.player_id
                GROUP BY r.player_id, r.level
                HAVING COUNT(*) >= 5
                   AND COUNT(*) % 5 = 0
                ORDER BY r.level, avg_score DESC
            """;

            ResultSet rsTop = con.createStatement().executeQuery(sqlTop);
            Map<String, Boolean> levelSeen = new HashMap<>();

            while (rsTop.next()) {
                String level = rsTop.getString("level");
                if (!levelSeen.containsKey(level)) {
                    levelSeen.put(level, true);
                    topModel.addRow(new Object[]{
                            level,
                            rsTop.getString("name"),
                            String.format("%.2f", rsTop.getDouble("avg_score"))
                    });
                }
            }

            ResultSet rsFreq = con.createStatement()
                    .executeQuery("SELECT score, COUNT(*) FROM results GROUP BY score ORDER BY score");

            while (rsFreq.next()) {
                freqModel.addRow(new Object[]{
                        rsFreq.getInt(1),
                        rsFreq.getInt(2)
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading reports: " + e.getMessage());
        }
    }

    /**
     * Creates a styled JTable with consistent formatting.
     *
     * <p>This method applies:</p>
     * <ul>
     *     <li>Row height and font styling</li>
     *     <li>Grid visibility</li>
     *     <li>Centered cell alignment</li>
     * </ul>
     *
     * @param model The DefaultTableModel containing table data
     * @return A formatted JTable instance
     */
    private JTable createStyledTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setRowHeight(26);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        table.setShowGrid(true);
        table.setGridColor(Color.LIGHT_GRAY);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }
        return table;
    }
}