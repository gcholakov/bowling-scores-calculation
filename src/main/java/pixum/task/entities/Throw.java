package pixum.task.entities;

import lombok.Getter;

import java.text.MessageFormat;

/**
 * Represents a throw.
 */
public class Throw {

    /**
     * Maximum pins for one throw.
     */
    private static final int MAX_SCORED = 10;
    /**
     * Minimum pins for one throw.
     */
    private static final int MIN_SCORED = 0;

    /**
     * Pins fallen in the throw.
     */
    @Getter
    private final int pins;

    public Throw(int pins) {
        validateScoredValue(pins);
        this.pins = pins;
    }

    /**
     * Simple validation for maximum/minimum pins fallen in a throw.
     *
     * @param pinsFallen value to validate
     */
    private void validateScoredValue(int pinsFallen) {
        if (pinsFallen < MIN_SCORED || pinsFallen > MAX_SCORED)
            throw new IllegalArgumentException(
                    MessageFormat.format("Pins number {0} not between {1} and {2}!",
                            pinsFallen, MIN_SCORED, MAX_SCORED));
    }

    @Override
    public String toString() {
        return String.valueOf(pins);
    }
}
