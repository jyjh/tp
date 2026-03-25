package seedu.address.model.person.statistics;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Player's total number of assists.
 * Guarantees: immutable; is valid as declared in {@link #isValidAssists(String)}
 */
public class Assists {
    public static final String MESSAGE_CONSTRAINTS =
            "Assists should be a non-negative integer";
    public static final String VALIDATION_REGEX = "\\d+";
    public final Integer value;

    /**
     * Constructs a {@code Assists}.
     *
     * @param assists A valid assists string.
     */
    public Assists(String assists) {
        requireNonNull(assists);
        checkArgument(isValidAssists(assists), MESSAGE_CONSTRAINTS);
        value = Integer.parseInt(assists);
    }

    private Assists(int assists) {
        value = assists;
    }

    /**
     * Returns true if a given string is a valid assists count.
     */
    public static boolean isValidAssists(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns a new Assists containing the sum of this Assists and the other Assists.
     * @param other the other assists
     * @return a new Assists containing the sum
     */
    public Assists add(Assists other) {
        return new Assists(this.value + other.value);
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
        if (!(other instanceof Assists)) {
            return false;
        }

        Assists otherAssists = (Assists) other;
        return value.equals(otherAssists.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
