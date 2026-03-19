package seedu.address.model.person.statistics;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Player's total number of deaths.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeaths(String)}
 */
public class Deaths {
    public static final String MESSAGE_CONSTRAINTS =
            "Deaths should be a non-negative integer";
    public static final String VALIDATION_REGEX = "\\d+";
    public final Integer value;

    /**
     * Constructs a {@code Deaths}.
     *
     * @param deaths A valid deaths string.
     */
    public Deaths(String deaths) {
        requireNonNull(deaths);
        checkArgument(isValidDeaths(deaths), MESSAGE_CONSTRAINTS);
        value = Integer.parseInt(deaths);
    }

    private Deaths(int deaths) {
        value = deaths;
    }

    /**
     * Returns true if a given string is a valid deaths count.
     */
    public static boolean isValidDeaths(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns a new Deaths containing the sum of this Deaths and the other Deaths.
     * @param other the other Deaths
     * @return a new Deaths containing the sum
     */
    public Deaths add(Deaths other) {
        return new Deaths(this.value + other.value);
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
        if (!(other instanceof Deaths)) {
            return false;
        }

        Deaths otherDeaths = (Deaths) other;
        return value.equals(otherDeaths.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
