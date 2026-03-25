package seedu.address.model.match;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalMatches;

public class MatchTest {

    @Test
    public void equals() {
        Match match1 = TypicalMatches.WINNING_MATCH_3;
        Match match2 = new Match(match1.getDate(), match1.getResult(), match1.getPlayers());
        Match match3 = TypicalMatches.LOSING_MATCH_3;
        Match match4 = TypicalMatches.WINNING_MATCH_4;

        // same object -> returns true
        assertEquals(match1, match1);

        // null -> returns false
        assertNotEquals(null, match1);

        // different type -> returns false
        assertNotEquals("Not a Match", match1);

        // different date -> returns false
        assertNotEquals(match4, match1);

        // different result -> returns false
        assertNotEquals(match3, match1);

        // same date and result -> returns true
        assertEquals(match2, match1);
    }

    @Test
    public void toStringMethod() {
        Match match = TypicalMatches.WINNING_MATCH_4;
        String expectedString = Match.class.getCanonicalName() + "{creation date=" + match.getDate()
                + ", result=" + match.getResult() + ", players=" + match.getPlayers() + "}";
        assertEquals(expectedString, match.toString());
    }

}
