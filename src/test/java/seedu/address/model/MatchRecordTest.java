package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.match.Match;
import seedu.address.testutil.TypicalMatches;

public class MatchRecordTest {

    private final MatchRecord matchRecord = new MatchRecord();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), matchRecord.getMatchList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> matchRecord.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyMatchRecord_replacesData() {
        MatchRecord newData = new MatchRecord();
        newData.addMatch(TypicalMatches.WINNING_MATCH_4);
        newData.addMatch(TypicalMatches.LOSING_MATCH_3);
        matchRecord.addMatch(TypicalMatches.DRAWING_MATCH_2);
        matchRecord.resetData(newData);
        assertEquals(newData, matchRecord);
    }

    @Test
    public void hasMatch_nullMatch_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> matchRecord.hasMatch(null));
    }

    @Test
    public void hasMatch_matchNotInMatchRecord_returnsFalse() {
        Match match = TypicalMatches.WINNING_MATCH_4;
        assertFalse(matchRecord.hasMatch(match));
    }

    @Test
    public void hasMatch_matchInMatchRecord_returnsTrue() {
        Match match = TypicalMatches.WINNING_MATCH_4;
        Match same = new Match(match.getDate(), match.getResult(), match.getPlayers());
        matchRecord.addMatch(match);
        assertTrue(matchRecord.hasMatch(match));
        assertTrue(matchRecord.hasMatch(same));
    }

    @Test
    public void getMatchList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> matchRecord.getMatchList().remove(0));
    }

    @Test
    public void toStringMethod() {
        matchRecord.addMatch(TypicalMatches.WINNING_MATCH_4);
        matchRecord.addMatch(TypicalMatches.LOSING_MATCH_3);
        matchRecord.addMatch(TypicalMatches.DRAWING_MATCH_2);
        String expectedString = MatchRecord.class.getCanonicalName()
                + "{matches=" + matchRecord.getMatchList() + "}";
        assertEquals(expectedString, matchRecord.toString());
    }

}
