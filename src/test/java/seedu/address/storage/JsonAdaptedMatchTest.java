package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedMatch.INVALID_DATE_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.match.Match;
import seedu.address.model.match.Result;
import seedu.address.testutil.TypicalMatches;

public class JsonAdaptedMatchTest {
    private static final String INVALID_DATE = "2024-13-01T10:00:00";
    private static final String INVALID_RESULT = "INVALID";

    private static final Match modelMatch = TypicalMatches.WINNING_MATCH_4;

    private static final String VALID_DATE = modelMatch.getDate().toString();
    private static final String VALID_RESULT = modelMatch.getResult().toString();
    private static final List<JsonAdaptedPlayerInMatch> VALID_PLAYERS =
            modelMatch.getPlayers().asList().stream().map(JsonAdaptedPlayerInMatch::new).toList();

    @Test
    public void toModelType_validMatchDetails_returnsMatch() throws Exception {
        JsonAdaptedMatch match = new JsonAdaptedMatch(VALID_DATE, VALID_RESULT, VALID_PLAYERS);
        assertEquals(modelMatch, match.toModelType());
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedMatch match = new JsonAdaptedMatch(INVALID_DATE, VALID_RESULT, VALID_PLAYERS);
        assertThrows(IllegalValueException.class,
                String.format(INVALID_DATE_MESSAGE_FORMAT, INVALID_DATE), match::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedMatch match = new JsonAdaptedMatch(null, VALID_RESULT, VALID_PLAYERS);
        assertThrows(IllegalValueException.class,
                String.format(JsonAdaptedMatch.MISSING_FIELD_MESSAGE_FORMAT, "Date"), match::toModelType);
    }

    @Test
    public void toModelType_invalidResult_throwsIllegalValueException() {
        JsonAdaptedMatch match = new JsonAdaptedMatch(VALID_DATE, INVALID_RESULT, VALID_PLAYERS);
        String expectedMessage = Result.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, match::toModelType);
    }

    @Test
    public void toModelType_nullResult_throwsIllegalValueException() {
        JsonAdaptedMatch match = new JsonAdaptedMatch(VALID_DATE, null, VALID_PLAYERS);
        assertThrows(IllegalValueException.class,
                String.format(JsonAdaptedMatch.MISSING_FIELD_MESSAGE_FORMAT, "Result"), match::toModelType);
    }

    @Test
    public void toModelType_nullPlayers_throwsIllegalValueException() {
        JsonAdaptedMatch match = new JsonAdaptedMatch(VALID_DATE, VALID_RESULT, null);
        assertThrows(IllegalValueException.class,
                String.format(JsonAdaptedMatch.MISSING_FIELD_MESSAGE_FORMAT, "Players"), match::toModelType);
    }

}
