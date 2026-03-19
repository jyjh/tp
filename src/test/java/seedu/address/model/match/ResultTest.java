package seedu.address.model.match;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;

public class ResultTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Result((String) null));
    }

    @Test
    public void constructor_invalidResult_throwsIllegalArgumentException() {
        String invalidResult = "INVALID_RESULT";
        assertThrows(IllegalArgumentException.class, () -> new Result(invalidResult));
    }

    @Test
    public void constructor_validResult_success() {
        String validResult1 = Result.WinType.WIN.toString();
        String validResult2 = Result.WinType.LOSE.toString();
        String validResult3 = Result.WinType.DRAW.toString();
        Result result1 = new Result(validResult1);
        Result result2 = new Result(validResult2);
        Result result3 = new Result(validResult3);
        assertEquals(Result.WinType.WIN, result1.value);
        assertEquals(Result.WinType.LOSE, result2.value);
        assertEquals(Result.WinType.DRAW, result3.value);
    }

    @Test
    public void isValidResult() {
        // null result
        assertThrows(NullPointerException.class, () -> Result.isValidResult(null));

        // invalid result
        assertFalse(Result.isValidResult("")); // empty string
        assertFalse(Result.isValidResult(" ")); // spaces only
        assertFalse(Result.isValidResult("fake")); // word is not a valid result
        assertFalse(Result.isValidResult("not a result")); // words are not a valid result

        // valid result
        assertTrue(Result.isValidResult("win")); // all lower case
        assertTrue(Result.isValidResult("lose"));
        assertTrue(Result.isValidResult("draw"));
        assertTrue(Result.isValidResult("Win")); // capitalized
        assertTrue(Result.isValidResult("Lose"));
        assertTrue(Result.isValidResult("Draw"));
        assertTrue(Result.isValidResult("WIN")); // all upper case
        assertTrue(Result.isValidResult("LOSE"));
        assertTrue(Result.isValidResult("DRAW"));

    }

    @Test
    public void equals() {
        Result result = new Result("WIN");

        // same values -> returns true
        assertEquals(new Result("WIN"), result);

        // same object -> returns true
        assertEquals(result, result);

        // null -> returns false
        assertNotEquals(null, result);

        // different types -> returns false
        assertFalse(result.equals(5.0f));

        // different values -> returns false
        assertNotEquals(new Name("LOSE"), result);
    }

}
