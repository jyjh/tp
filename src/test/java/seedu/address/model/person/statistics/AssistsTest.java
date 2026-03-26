package seedu.address.model.person.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AssistsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Assists(null));
    }

    @Test
    public void constructor_invalidAssists_throwsIllegalArgumentException() {
        String invalidAssists = "";
        assertThrows(IllegalArgumentException.class, () -> new Assists(invalidAssists));
    }

    @Test
    public void isValidAssists() {
        // null assists count
        assertThrows(NullPointerException.class, () -> Assists.isValidAssists(null));

        // invalid assists count
        assertFalse(Assists.isValidAssists("")); // empty string
        assertFalse(Assists.isValidAssists(" ")); // spaces only
        assertFalse(Assists.isValidAssists("911a")); // contains letters
        assertFalse(Assists.isValidAssists("-1")); // negative number

        // valid assists count
        assertTrue(Assists.isValidAssists("911")); // normal number
        assertTrue(Assists.isValidAssists("0")); // zero
        assertTrue(Assists.isValidAssists("124293842033123")); // long number
    }

    @Test
    public void equals() {
        Assists assists = new Assists("10");

        // same values -> returns true
        assertTrue(assists.equals(new Assists("10")));

        // same object -> returns true
        assertTrue(assists.equals(assists));

        // null -> returns false
        assertFalse(assists.equals(null));

        // different types -> returns false
        assertFalse(assists.equals(5.0f));

        // different values -> returns false
        assertFalse(assists.equals(new Assists("20")));
    }

    @Test
    public void add() {
        Assists assists1 = new Assists("5");
        Assists assists2 = new Assists("10");
        assertEquals(new Assists("15"), assists1.add(assists2));
    }

    @Test
    public void toStringMethod() {
        Assists assists = new Assists("15");
        assertEquals("15", assists.toString());
    }

    @Test
    public void hashCodeMethod() {
        Assists assists1 = new Assists("10");
        Assists assists2 = new Assists("10");
        assertEquals(assists1.hashCode(), assists2.hashCode());
    }
}
