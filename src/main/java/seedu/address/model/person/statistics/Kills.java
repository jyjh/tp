package seedu.address.model.person.statistics;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Player's total number of kills.
 * Guarantees: immutable; is valid as declared in {@link #isValidKills(String)}
 */
public class Kills {
    public static final String MESSAGE_CONSTRAINTS =
            "Kills should be a non-negative integer";
    public static final String VALIDATION_REGEX = "\\d+";
    public final Integer value;

    /**
     * Constructs a {@code Kills}.
     *
     * @param kills A valid kills string.
     */
    public Kills(String kills) {
        requireNonNull(kills);
        checkArgument(isValidKills(kills), MESSAGE_CONSTRAINTS);
        value = Integer.parseInt(kills);
    }

    private Kills(int kills) {
        value = kills;
    }

    /**
     * Returns true if a given string is a valid kills count.
     */
    public static boolean isValidKills(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns a new Kills containing the sum of this Kills and the other Kills.
     * @param other the other kills
     * @return a new Kills containing the sum
     */
    public Kills add(Kills other) {
        return new Kills(this.value + other.value);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Kills)) {
            return false;
        }

        Kills otherKills = (Kills) other;
        return value.equals(otherKills.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
