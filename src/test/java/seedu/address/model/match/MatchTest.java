package seedu.address.model.match;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class MatchTest {

    @Test
    public void equals() {
        Match match1 = new Match(new Result("WIN"));
        Match match2 = new Match(match1.getDate(), new Result("WIN"));
        Match match3 = new Match(match1.getDate(), new Result("LOSE"));
        Match match4 = new Match(LocalDateTime.MIN, new Result("WIN"));

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
        Match match = new Match(new Result("WIN"));
        String expectedString = Match.class.getCanonicalName() + "{creation date=" + match.getDate() + ", result=WIN}";
        assertEquals(expectedString, match.toString());
    }

}
