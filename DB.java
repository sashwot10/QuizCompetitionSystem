package coursework;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * The DB class is responsible for establishing a connection
 * to the MySQL database using JDBC.
 *
 * <p>
 * This class provides a single static method {@code getConnection()}
 * which loads the MySQL JDBC driver and returns a Connection object.
 * </p>
 *
 * <p>
 * The database used in this coursework is:
 * <ul>
 *     <li>Database Name: CompetitionDB</li>
 *     <li>Host: localhost</li>
 *     <li>Port: 3306</li>
 * </ul>
 * </p>
 *
 * This class is used by Player, QuizPanel, HighScoresPanel,
 * and other classes that require database access.
 */
public class DB {

    /**
     * Establishes and returns a connection to the MySQL database.
     *
     * @return a Connection object to CompetitionDB
     * @throws Exception if the driver is not found or the connection fails
     */
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/CompetitionDB",
            "root",
            ""
        );

        return con;
    }
}