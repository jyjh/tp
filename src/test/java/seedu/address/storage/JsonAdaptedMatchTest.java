package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedMatch.INVALID_DATE_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.match.Match;
import seedu.address.model.match.Result;

public class JsonAdaptedMatchTest {
    private static final String INVALID_DATE = "2024-13-01T10:00:00";
    private static final String INVALID_RESULT = "INVALID";

    private static final String VALID_DATE = "2024-12-01T10:00:00";
    private static final String VALID_RESULT = Result.WinType.WIN.toString();

    private final JsonAdaptedMatch validMatch = new JsonAdaptedMatch(VALID_DATE, VALID_RESULT);

    @Test
    public void toModelType_validMatchDetails_returnsMatch() throws Exception {
        assertEquals(new Match(LocalDateTime.parse(VALID_DATE), new Result(VALID_RESULT)), validMatch.toModelType());
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedMatch match = new JsonAdaptedMatch(INVALID_DATE, VALID_RESULT);
        assertThrows(IllegalValueException.class,
                String.format(INVALID_DATE_MESSAGE_FORMAT, INVALID_DATE), match::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedMatch match = new JsonAdaptedMatch(null, VALID_RESULT);
        assertThrows(IllegalValueException.class,
                String.format(JsonAdaptedMatch.MISSING_FIELD_MESSAGE_FORMAT, "Date"), match::toModelType);
    }

    @Test
    public void toModelType_invalidResult_throwsIllegalValueException() {
        JsonAdaptedMatch match = new JsonAdaptedMatch(VALID_DATE, INVALID_RESULT);
        String expectedMessage = Result.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, match::toModelType);
    }

    @Test
    public void toModelType_nullResult_throwsIllegalValueException() {
        JsonAdaptedMatch match = new JsonAdaptedMatch(VALID_DATE, null);
        assertThrows(IllegalValueException.class,
                String.format(JsonAdaptedMatch.MISSING_FIELD_MESSAGE_FORMAT, "Result"), match::toModelType);
    }

}
