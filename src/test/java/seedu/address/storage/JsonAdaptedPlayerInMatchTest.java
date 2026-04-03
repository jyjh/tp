package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENTITY_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENTITY_REFERENCE;
import static seedu.address.storage.JsonAdaptedPlayerInMatch.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.match.PlayerInMatch;
import seedu.address.model.person.Name;
import seedu.address.model.person.statistics.Deaths;
import seedu.address.model.person.statistics.Kills;
import seedu.address.model.person.statistics.Statistics;
import seedu.address.testutil.TypicalMatches;

public class JsonAdaptedPlayerInMatchTest {

    private static final String INVALID_NAME = " ";
    private static final String INVALID_KILLS = "-1";
    private static final String INVALID_DEATHS = "-1";
    private static final String INVALID_ENTITY = "nonexistent_entity";

    private static final PlayerInMatch modelPlayer = TypicalMatches.WINNING_MATCH_1.getPlayers().get(0);

    private static final String VALID_NAME = modelPlayer.getName().toString();
    private static final JsonAdaptedStatistics VALID_STATISTICS =
            new JsonAdaptedStatistics(modelPlayer.getStatistics());

    @BeforeAll
    public static void setupAll() {
        VALID_ENTITY_REFERENCE.reload(); // Ensure the EntityReference is loaded
    }

    @Test
    public void toModelType_validPlayerInMatchDetails_returnsPlayer() throws IllegalValueException {
        JsonAdaptedPlayerInMatch player = new JsonAdaptedPlayerInMatch(modelPlayer);
        assertEquals(modelPlayer, player.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPlayerInMatch player = new JsonAdaptedPlayerInMatch(
            INVALID_NAME, VALID_ENTITY_1.getName(), VALID_STATISTICS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, player::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPlayerInMatch player = new JsonAdaptedPlayerInMatch(
            null, VALID_ENTITY_1.getName(), VALID_STATISTICS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, player::toModelType);
    }

    @Test
    public void toModelType_invalidKills_throwsIllegalValueException() {
        JsonAdaptedStatistics invalidStats = new JsonAdaptedStatistics(INVALID_KILLS, "0", "0");
        JsonAdaptedPlayerInMatch player = new JsonAdaptedPlayerInMatch(
            VALID_NAME, VALID_ENTITY_1.getName(), invalidStats);
        String expectedMessage = Kills.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, player::toModelType);
    }

    @Test
    public void toModelType_invalidDeaths_throwsIllegalValueException() {
        JsonAdaptedStatistics invalidStats = new JsonAdaptedStatistics(
            "0", INVALID_DEATHS, "0");
        JsonAdaptedPlayerInMatch player = new JsonAdaptedPlayerInMatch(
            VALID_NAME, VALID_ENTITY_1.getName(), invalidStats);
        String expectedMessage = Deaths.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, player::toModelType);
    }

    @Test
    public void toModelType_nullStatistics_throwsIllegalValueException() {
        JsonAdaptedPlayerInMatch player = new JsonAdaptedPlayerInMatch(VALID_NAME, VALID_ENTITY_1.getName(), null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Statistics.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, player::toModelType);
    }

    @Test
    public void toModelType_invalidEntity_throwsIllegalValueException() {
        JsonAdaptedPlayerInMatch player = new JsonAdaptedPlayerInMatch(VALID_NAME, INVALID_ENTITY, VALID_STATISTICS);
        assertThrows(IllegalValueException.class, player::toModelType);
    }

    @Test
    public void toModelType_nullEntity_throwsIllegalValueException() {
        JsonAdaptedPlayerInMatch player = new JsonAdaptedPlayerInMatch(VALID_NAME, null, VALID_STATISTICS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Entity");
        assertThrows(IllegalValueException.class, expectedMessage, player::toModelType);
    }

}
