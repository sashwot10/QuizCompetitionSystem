package coursework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * The Player class represents a competitor in the quiz system.
 * 
 * <p>
 * Each Player has a unique player ID, a Name object, a difficulty Level,
 * and an age. The class also provides database interaction methods
 * to save and retrieve player information using JDBC.
 * </p>
 * 
 * <p>
 * The overall score of a player is calculated dynamically from
 * the results table in the database using the average score
 * of all completed quizzes.
 * </p>
 * 
 * @author
 */
public class Player {

    /** Unique identifier for the player */
    private int playerId;

    /** Name object containing first and last name */
    private Name name;

    /** Difficulty level of the player */
    private Level level;

    /** Age of the player */
    private int age;

    /**
     * Constructs a Player object with the specified details.
     *
     * @param playerId unique player ID
     * @param name     Name object containing first and last name
     * @param level    difficulty level of the player
     * @param age      age of the player
     */
    public Player(int playerId, Name name, Level level, int age) {
        this.playerId = playerId;
        this.name = name;
        this.level = level;
        this.age = age;
    }

    /**
     * Returns the player ID.
     *
     * @return player ID
     */
    public int getPlayerId() { return playerId; }

    /**
     * Sets the player ID.
     *
     * @param playerId new player ID
     */
    public void setPlayerId(int playerId) { this.playerId = playerId; }

    /**
     * Returns the Name object of the player.
     *
     * @return Name object
     */
    public Name getName() { return name; }

    /**
     * Sets the Name object of the player.
     *
     * @param name new Name object
     */
    public void setName(Name name) { this.name = name; }

    /**
     * Returns the difficulty level of the player.
     *
     * @return Level enum value
     */
    public Level getLevel() { return level; }

    /**
     * Sets the difficulty level of the player.
     *
     * @param level new Level value
     */
    public void setLevel(Level level) { this.level = level; }

    /**
     * Returns the age of the player.
     *
     * @return age
     */
    public int getAge() { return age; }

    /**
     * Sets the age of the player.
     *
     * @param age new age value
     */
    public void setAge(int age) { this.age = age; }

    /**
     * Calculates and returns the overall score of the player.
     * 
     * <p>
     * The score is computed as the average of all quiz scores
     * stored in the results table for this player.
     * </p>
     *
     * @return average quiz score, or 0 if no scores exist
     */
    public double getOverallScore() {
        double avgScore = 0;
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "SELECT AVG(score) AS avg_score FROM results WHERE player_id=?")) {
            ps.setInt(1, this.playerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                avgScore = rs.getDouble("avg_score");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return avgScore;
    }

    /**
     * Returns the full name of the player.
     *
     * @return full name as "FirstName LastName"
     */
    public String getFullName() {
        return name.getFirstName() + " " + name.getLastName();
    }

    /**
     * Returns detailed information about the player.
     *
     * @return formatted full details string
     */
    public String getFullDetails() {
        return "Player ID " + playerId + ", name " + getFullName() +
               ", age " + age + ". " + name.getFirstName() + " is a " + level +
               " level player and has an overall score of " + getOverallScore() + ".";
    }

    /**
     * Returns a short summary of the player.
     *
     * @return formatted short details string
     */
    public String getShortDetails() {
        return "CN " + playerId + " (" + name.getInitials() +
               ") has overall score " + (int)getOverallScore() + ".";
    }

    /**
     * Saves the player to the database.
     * 
     * <p>
     * Inserts a new record into the players table and retrieves
     * the auto-generated player ID.
     * </p>
     */
    public void saveToDB() {
        try (Connection conn = DB.getConnection()) {
            String sql = "INSERT INTO players (first_name, last_name, level, age) VALUES (?, ?, ?, ?)";
            java.sql.PreparedStatement stmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name.getFirstName());
            stmt.setString(2, name.getLastName());
            stmt.setString(3, level.name());
            stmt.setInt(4, age);
            stmt.executeUpdate();

            java.sql.ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                this.playerId = keys.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Error saving player: " + e.getMessage());
        }
    }

    /**
     * Loads a player from the database using the player ID.
     *
     * @param playerId ID of the player to load
     * @return Player object if found, otherwise null
     */
    public static Player loadFromDB(int playerId) {
        String sql = "SELECT * FROM players WHERE player_id = ?";

        try (Connection conn = DB.getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, playerId);
            java.sql.ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Name name = new Name(rs.getString("first_name"), rs.getString("last_name"));
                Level level = Level.valueOf(rs.getString("level"));
                int age = rs.getInt("age");
                return new Player(playerId, name, level, age);
            }

        } catch (Exception e) {
            System.out.println("Error loading player: " + e.getMessage());
        }

        return null;
    }

    /**
     * Checks whether a player exists in the database.
     *
     * @param playerId ID to check
     * @return true if player exists, false otherwise
     */
    public static boolean playerExists(int playerId) {
        String sql = "SELECT player_id FROM players WHERE player_id = ?";

        try (Connection conn = DB.getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, playerId);
            java.sql.ResultSet rs = stmt.executeQuery();

            return rs.next();
        } catch (Exception e) {
            System.out.println("Error checking player existence: " + e.getMessage());
        }

        return false;
    }
}