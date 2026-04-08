package seedu.address.model.person.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class KillsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Kills(null));
    }

    @Test
    public void constructor_invalidKills_throwsIllegalArgumentException() {
        String invalidKills = "";
        assertThrows(IllegalArgumentException.class, () -> new Kills(invalidKills));
    }

    @Test
    public void isValidKills() {
        // null kills count
        assertThrows(NullPointerException.class, () -> Kills.isValidKills(null));

        // invalid kills count
        assertFalse(Kills.isValidKills("")); // empty string
        assertFalse(Kills.isValidKills(" ")); // spaces only
        assertFalse(Kills.isValidKills("911a")); // contains letters
        assertFalse(Kills.isValidKills("-1")); // negative number
        assertFalse(Kills.isValidKills("124293842033123")); // number longer than Integer.MAX_VALUE

        // valid kills count
        assertTrue(Kills.isValidKills("911")); // normal number
        assertTrue(Kills.isValidKills("0")); // zero
    }

    @Test
    public void equals() {
        Kills kills = new Kills("10");

        // same values -> returns true
        assertTrue(kills.equals(new Kills("10")));

        // same object -> returns true
        assertTrue(kills.equals(kills));

        // null -> returns false
        assertFalse(kills.equals(null));

        // different types -> returns false
        assertFalse(kills.equals(5.0f));

        // different values -> returns false
        assertFalse(kills.equals(new Kills("20")));
    }

    @Test
    public void hashCodeMethod() {
        Kills kills1 = new Kills("10");
        Kills kills2 = new Kills("10");
        assertEquals(kills1.hashCode(), kills2.hashCode());
    }
}
