package seedu.address.model.match;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.match.exceptions.MatchNotFoundException;

/**
 * A list of matches. Supports adding and removal of matches from the list.
 */
public class MatchList implements Iterable<Match> {

    private final ObservableList<Match> internalList = FXCollections.observableArrayList();
    private final ObservableList<Match> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent match as the given argument.
     */
    public boolean contains(Match toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a match to the list.
     */
    public void add(Match toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent match from the list.
     * The match must exist in the list.
     */
    public void remove(Match toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MatchNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code matches}.
     */
    public void setMatches(List<Match> matches) {
        requireAllNonNull(matches);
        internalList.setAll(matches);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Match> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Match> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MatchList otherMatchList)) {
            return false;
        }

        return internalList.equals(otherMatchList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }
}
