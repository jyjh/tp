package seedu.address.model.match;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.match.exceptions.MatchNotFoundException;
import seedu.address.testutil.TypicalMatches;

public class MatchListTest {

    private final MatchList matchList = new MatchList();

    @Test
    public void contains_nullMatch_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> matchList.contains(null));
    }

    @Test
    public void contains_matchNotInList_returnsFalse() {
        Match match = TypicalMatches.WINNING_MATCH_4;
        assertFalse(matchList.contains(match));
    }

    @Test
    public void contains_matchInList_returnsTrue() {
        Match match = TypicalMatches.WINNING_MATCH_4;
        Match same = new Match(match.getDate(), match.getResult(), match.getPlayers());
        matchList.add(match);
        assertTrue(matchList.contains(match));
        assertTrue(matchList.contains(same));
    }

    @Test
    public void add_nullMatch_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> matchList.add(null));
    }

    @Test
    public void remove_nullMatch_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> matchList.remove(null));
    }

    @Test
    public void remove_matchNotInList_throwsMatchNotFoundException() {
        Match match = TypicalMatches.WINNING_MATCH_4;
        assertThrows(MatchNotFoundException.class, () -> matchList.remove(match));
    }

    @Test
    public void remove_existingMatch_removesMatch() {
        Match match = TypicalMatches.WINNING_MATCH_4;
        Match same = new Match(match.getDate(), match.getResult(), match.getPlayers());
        matchList.add(match);
        matchList.remove(match);
        assertFalse(matchList.contains(match));
        matchList.add(match);
        matchList.remove(same);
        assertFalse(matchList.contains(match));
    }

    @Test
    public void setMatches_nullMatchList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> matchList.setMatches(null));
    }

    @Test
    public void setMatches_validMatchList_replacesMatchList() {
        Match match1 = TypicalMatches.WINNING_MATCH_4;
        Match match2 = TypicalMatches.LOSING_MATCH_4;
        matchList.add(match1);
        matchList.setMatches(List.of(match2));
        MatchList espectedMatchList = new MatchList();
        espectedMatchList.add(match2);
        assertEquals(espectedMatchList, matchList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> matchList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        Match match1 = TypicalMatches.WINNING_MATCH_4;
        Match match2 = TypicalMatches.LOSING_MATCH_4;
        matchList.add(match1);
        matchList.add(match2);
        assertEquals(matchList.asUnmodifiableObservableList().toString(), matchList.toString());
    }
}
