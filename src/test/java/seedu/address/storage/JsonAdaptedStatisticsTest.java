package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.statistics.Assists;
import seedu.address.model.person.statistics.Deaths;
import seedu.address.model.person.statistics.Kills;
import seedu.address.model.person.statistics.Statistics;

public class JsonAdaptedStatisticsTest {

    private static final String INVALID_KILLS = "-1";
    private static final String VALID_KILLS = "10";
    private static final String INVALID_DEATHS = "-2";
    private static final String VALID_DEATHS = "5";
    private static final String VALID_ASSISTS = "2";

    @Test
    public void toModelType_validStatisticsDetails_returnsStatistics() throws Exception {
        JsonAdaptedStatistics statistics = new JsonAdaptedStatistics(VALID_KILLS, VALID_DEATHS, VALID_ASSISTS);
        Statistics expectedStatistics = new Statistics.Builder()
                .withKills(new Kills(VALID_KILLS))
                .withDeaths(new Deaths(VALID_DEATHS))
                .withAssists(new Assists(VALID_ASSISTS)) // Added for default assists parameter
                .build();
        assertEquals(expectedStatistics, statistics.toModelType());
    }

    @Test
    public void toModelType_nullKills_throwsIllegalValueException() {
        JsonAdaptedStatistics statistics = new JsonAdaptedStatistics(null, VALID_DEATHS, "0");
        String expectedMessage = String.format("Statistics's kills field is missing!",
                Kills.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, statistics::toModelType);
    }

    @Test
    public void toModelType_invalidKills_throwsIllegalValueException() {
        JsonAdaptedStatistics statistics = new JsonAdaptedStatistics(INVALID_KILLS, VALID_DEATHS, VALID_ASSISTS);
        String expectedMessage = Kills.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, statistics::toModelType);
    }

    @Test
    public void toModelType_nullDeaths_throwsIllegalValueException() {
        JsonAdaptedStatistics statistics = new JsonAdaptedStatistics(VALID_KILLS, null, VALID_ASSISTS);
        String expectedMessage = String.format("Statistics's deaths field is missing!",
                Deaths.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, statistics::toModelType);
    }

    @Test
    public void toModelType_invalidDeaths_throwsIllegalValueException() {
        JsonAdaptedStatistics statistics = new JsonAdaptedStatistics(VALID_KILLS, INVALID_DEATHS, VALID_ASSISTS);
        String expectedMessage = Deaths.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, statistics::toModelType);
    }

    @Test
    public void constructor_statisticsObject_success() throws Exception {
        Statistics stats = new Statistics.Builder()
                .withKills(new Kills(VALID_KILLS))
                .withDeaths(new Deaths(VALID_DEATHS))
                .withAssists(new Assists(VALID_ASSISTS))
                .build();
        JsonAdaptedStatistics jsonStats = new JsonAdaptedStatistics(stats);
        assertEquals(stats, jsonStats.toModelType());
    }

    @Test
    public void toModelType_nullAssists_throwsIllegalValueException() {
        JsonAdaptedStatistics statistics = new JsonAdaptedStatistics(VALID_KILLS, VALID_DEATHS, null);
        String expectedMessage = String.format("Statistics's assists field is missing!");
        assertThrows(IllegalValueException.class, expectedMessage, statistics::toModelType);
    }

    @Test
    public void toModelType_invalidAssists_throwsIllegalValueException() {
        JsonAdaptedStatistics statistics = new JsonAdaptedStatistics(VALID_KILLS, VALID_DEATHS, "-3");
        String expectedMessage = Assists.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, statistics::toModelType);
    }
}
