package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.match.Match;
import seedu.address.model.match.MatchList;

/**
 * Wraps all data at the match-record level
 */
public class MatchRecord implements ReadOnlyMatchRecord {

    private final MatchList matches;

    public MatchRecord() {
        matches = new MatchList();
    }

    /**
     * Creates an MatchRecord using the Matches in the {@code toBeCopied}
     */
    public MatchRecord(ReadOnlyMatchRecord toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the matches list with {@code matches}.
     */
    public void setMatches(List<Match> matches) {
        this.matches.setMatches(matches);
    }

    /**
     * Resets the existing data of this {@code MatchRecord} with {@code newData}.
     */
    public void resetData(ReadOnlyMatchRecord newData) {
        requireNonNull(newData);

        setMatches(newData.getMatchList());
    }

    /**
     * Returns true if a match exists in the match record.
     */
    public boolean hasMatch(Match match) {
        requireNonNull(match);
        return matches.contains(match);
    }

    /**
     * Adds a match to the match record.
     */
    public void addMatch(Match m) {
        matches.add(m);
    }

    public void removeMatch(Match m) {
        matches.remove(m);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("matches", matches)
                .toString();
    }

    @Override
    public ObservableList<Match> getMatchList() {
        return matches.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MatchRecord otherMatchRecord)) {
            return false;

        }
        return matches.equals(otherMatchRecord.matches);
    }

    @Override
    public int hashCode() {
        return matches.hashCode();
    }

}
