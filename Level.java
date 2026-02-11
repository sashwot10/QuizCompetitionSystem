package coursework;

/**
 * The Level enum represents the difficulty level of a player.
 * 
 * <p>
 * Each level has an associated multiplier used to calculate
 * weighted scores in the leaderboard.
 * </p>
 * 
 * <ul>
 *     <li>BEGINNER - multiplier 0.5</li>
 *     <li>INTERMEDIATE - multiplier 0.7</li>
 *     <li>ADVANCED - multiplier 1.0</li>
 * </ul>
 */
public enum Level {
    BEGINNER(0.5),
    INTERMEDIATE(0.7),
    ADVANCED(1.0);

    private double multiplier;

    /**
     * Constructs a Level with a score multiplier.
     *
     * @param multiplier the weight applied to the raw score
     */
    Level(double multiplier) {
        this.multiplier = multiplier;
    }

    /**
     * Returns the multiplier associated with the level.
     *
     * @return score multiplier
     */
    public double getMultiplier() {
        return multiplier;
    }
}