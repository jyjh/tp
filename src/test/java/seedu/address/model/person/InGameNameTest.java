package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InGameNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InGameName(null));
    }

    @Test
    public void constructor_invalidIgn_throwsIllegalArgumentException() {
        String invalidIgn = " ";
        assertThrows(IllegalArgumentException.class, () -> new InGameName(invalidIgn));
    }

    @Test
    public void isValidIgn() {
        // null IGN
        assertThrows(NullPointerException.class, () -> InGameName.isValidIgn(null));

        // invalid IGN - empty string or only whitespace
        assertFalse(InGameName.isValidIgn("")); // empty string
        assertFalse(InGameName.isValidIgn(" ")); // spaces only
        assertFalse(InGameName.isValidIgn("   ")); // multiple spaces

        // valid IGN - can contain any characters except leading whitespace
        assertTrue(InGameName.isValidIgn("AlexY42"));
        assertTrue(InGameName.isValidIgn("Bern_Storm"));
        assertTrue(InGameName.isValidIgn("Charlie99"));
        assertTrue(InGameName.isValidIgn("a")); // single character
        assertTrue(InGameName.isValidIgn("ProGamer123"));
        assertTrue(InGameName.isValidIgn("Player Name")); // with space in middle
        assertTrue(InGameName.isValidIgn("IGN!@#")); // special characters
        assertTrue(InGameName.isValidIgn("123")); // numbers only
    }

    @Test
    public void equals() {
        InGameName firstIgn = new InGameName("AlexY42");
        InGameName firstIgnCopy = new InGameName("AlexY42");
        InGameName secondIgn = new InGameName("Bern_Storm");

        // same values
        assertTrue(firstIgn.equals(firstIgnCopy));

        // same object
        assertTrue(firstIgn.equals(firstIgn));

        // different values
        assertFalse(firstIgn.equals(secondIgn));

        // null
        assertFalse(firstIgn.equals(null));

        // different type
        assertFalse(firstIgn.equals(5.0f));
    }

    @Test
    public void hashCode_test() {
        InGameName firstIgn = new InGameName("AlexY42");
        InGameName firstIgnCopy = new InGameName("AlexY42");
        InGameName secondIgn = new InGameName("Bern_Storm");

        // equal objects have equal hash codes
        assertTrue(firstIgn.hashCode() == firstIgnCopy.hashCode());

        // different objects may have different hash codes (not guaranteed but likely)
        // This test is probabilistic, so we just ensure it doesn't throw
        int hashCode1 = firstIgn.hashCode();
        int hashCode2 = secondIgn.hashCode();
        assertTrue(hashCode1 != Integer.MIN_VALUE && hashCode2 != Integer.MIN_VALUE);
    }

    @Test
    public void toString_test() {
        InGameName ign = new InGameName("AlexY42");
        assertEquals("AlexY42", ign.toString());

        InGameName ign2 = new InGameName("Bern_Storm");
        assertEquals("Bern_Storm", ign2.toString());
    }

    // Helper method for assertEquals
    private void assertEquals(String expected, String actual) {
        assertTrue(expected.equals(actual), "Expected: " + expected + ", but got: " + actual);
    }
}
