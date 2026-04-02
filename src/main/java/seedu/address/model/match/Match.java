package seedu.address.model.match;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents details of a Match.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Match {

    private final LocalDateTime date;
    private final Result result;
    private final PlayersInMatch players;

    /**
     * Every field must be present and not null.
     */
    public Match(LocalDateTime date, Result result, PlayersInMatch players) {
        requireAllNonNull(date, result, players);

        this.result = result;
        this.date = date;
        this.players = players;
    }

    /**
     * Every field must be present and not null.
     * The date is set to the current time when the match is created.
     */
    public Match(Result result, PlayersInMatch players) {
        this(LocalDateTime.now(), result, players);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Result getResult() {
        return result;
    }

    public PlayersInMatch getPlayers() {
        return players;
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
                && result.equals(otherMatch.result)
                && players.equals(otherMatch.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, result, players);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("creation date", date)
                .add("result", result)
                .add("players", players)
                .toString();
    }

}
