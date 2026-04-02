package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPlayerInMatch.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.match.PlayerInMatch;
import seedu.address.model.person.InGameName;
import seedu.address.model.person.statistics.Deaths;
import seedu.address.model.person.statistics.Kills;
import seedu.address.model.person.statistics.Statistics;
import seedu.address.testutil.TypicalMatches;

public class JsonAdaptedPlayerInMatchTest {

    private static final String INVALID_IGN = " ";
    private static final String INVALID_KILLS = "-1";
    private static final String INVALID_DEATHS = "-1";

    private static final PlayerInMatch modelPlayer = TypicalMatches.WINNING_MATCH_1.getPlayers().get(0);

    private static final String VALID_NAME = modelPlayer.getInGameName().toString();
    private static final JsonAdaptedStatistics VALID_STATISTICS =
            new JsonAdaptedStatistics(modelPlayer.getStatistics());

    @Test
    public void toModelType_validPlayerInMatchDetails_returnsPlayer() throws IllegalValueException {
        JsonAdaptedPlayerInMatch player = new JsonAdaptedPlayerInMatch(modelPlayer);
        assertEquals(modelPlayer, player.toModelType());
    }

    @Test
    public void toModelType_invalidIgn_throwsIllegalValueException() {
        JsonAdaptedPlayerInMatch player = new JsonAdaptedPlayerInMatch(INVALID_IGN, VALID_STATISTICS);
        String expectedMessage = InGameName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, player::toModelType);
    }

    @Test
    public void toModelType_nullIgn_throwsIllegalValueException() {
        JsonAdaptedPlayerInMatch player = new JsonAdaptedPlayerInMatch(null, VALID_STATISTICS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, InGameName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, player::toModelType);
    }

    @Test
    public void toModelType_invalidKills_throwsIllegalValueException() {
        JsonAdaptedStatistics invalidStats = new JsonAdaptedStatistics(INVALID_KILLS, "0", "0");
        JsonAdaptedPlayerInMatch player = new JsonAdaptedPlayerInMatch(VALID_NAME, invalidStats);
        String expectedMessage = Kills.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, player::toModelType);
    }

    @Test
    public void toModelType_invalidDeaths_throwsIllegalValueException() {
        JsonAdaptedStatistics invalidStats = new JsonAdaptedStatistics("0", INVALID_DEATHS, "0");
        JsonAdaptedPlayerInMatch player = new JsonAdaptedPlayerInMatch(VALID_NAME, invalidStats);
        String expectedMessage = Deaths.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, player::toModelType);
    }

    @Test
    public void toModelType_nullStatistics_throwsIllegalValueException() {
        JsonAdaptedPlayerInMatch player = new JsonAdaptedPlayerInMatch(VALID_NAME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Statistics.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, player::toModelType);
    }

}
