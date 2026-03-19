package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.match.Match;

/**
 * Unmodifiable view of a match record.
 */
public interface ReadOnlyMatchRecord {

    /**
     * Returns an unmodifiable view of the matches list.
     */
    ObservableList<Match> getMatchList();

}
