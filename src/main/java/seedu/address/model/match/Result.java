package seedu.address.model.match;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the result of a Match.
 * Guarantees: immutable; is valid as declared in {@link #isValidResult(String)}
 */
public class Result {

    public static final String MESSAGE_CONSTRAINTS = "Result should be one of: WIN, LOSE, DRAW";

    /**
     * Represents the possible results of a match.
     */
    public enum WinType {
        WIN, LOSE, DRAW
    }

    public final WinType value;

    /**
     * Constructs a {@code Result}.
     *
     * @param result A value result.
     */
    public Result(String result) {
        requireNonNull(result);
        String trimmedResult = result.trim().toUpperCase();
        checkArgument(isValidResult(trimmedResult), MESSAGE_CONSTRAINTS);
        value = WinType.valueOf(trimmedResult);
    }

    /**
     * Constructs a {@code Result}.
     *
     * @param result A value result.
     */
    public Result(WinType result) {
        requireNonNull(result);
        value = result;
    }

    /**
     * Returns true if a given String is a valid result.
     */
    public static boolean isValidResult(String test) {
        try {
            WinType.valueOf(test.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
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

        if (!(other instanceof Result otherResult)) {
            return false;
        }

        return value.equals(otherResult.value);

    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
