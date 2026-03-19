package seedu.address.model.match;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents details of a Match.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Match {

    private final LocalDateTime date;
    private final Result result;

    /**
     * Every field must be present and not null.
     */
    public Match(LocalDateTime date, Result result) {
        requireAllNonNull(date, result);
        this.result = result;
        this.date = date;
    }

    /**
     * Every field must be present and not null.
     * The date is set to the current time when the match is created.
     */
    public Match(Result result) {
        this(LocalDateTime.now(), result);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Result getResult() {
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Match otherMatch)) {
            return false;
        }

        return date.equals(otherMatch.date)
                && result.equals(otherMatch.result);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("creation date", date)
                .add("result", result)
                .toString();
    }

}
