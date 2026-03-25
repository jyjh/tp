package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.statistics.Deaths;
import seedu.address.model.person.statistics.Kills;
import seedu.address.model.person.statistics.Statistics;

/**
 * Jackson-friendly version of {@link Statistics}.
 */
class JsonAdaptedStatistics {

    private final String kills;
    private final String deaths;
    private final String assists;

    /**
     * Constructs a {@code JsonAdaptedStatistics} with the given details.
     */
    @JsonCreator
    public JsonAdaptedStatistics(@JsonProperty("kills") String kills,
            @JsonProperty("deaths") String deaths,
            @JsonProperty("assists") String assists) {
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
    }

    /**
     * Converts a given {@code Statistics} into this class for Jackson use.
     */
    public JsonAdaptedStatistics(Statistics source) {
        kills = source.getKills().value.toString();
        deaths = source.getDeaths().value.toString();
        assists = source.getAssists().value.toString();
    }

    /**
     * Converts this Jackson-friendly adapted statistics object into the model's
     * {@code Statistics} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted statistics.
     */
    public Statistics toModelType() throws IllegalValueException {
        if (kills == null) {
            throw new IllegalValueException(String.format("Statistics's kills field is missing!"));
        }
        if (!Kills.isValidKills(kills)) {
            throw new IllegalValueException(Kills.MESSAGE_CONSTRAINTS);
        }
        final Kills modelKills = new Kills(kills);

        if (deaths == null) {
            throw new IllegalValueException(String.format("Statistics's deaths field is missing!"));
        }
        if (!Deaths.isValidDeaths(deaths)) {
            throw new IllegalValueException(Deaths.MESSAGE_CONSTRAINTS);
        }
        final Deaths modelDeaths = new Deaths(deaths);

        if (assists == null) {
            throw new IllegalValueException(String.format("Statistics's assists field is missing!"));
        }
        if (!seedu.address.model.person.statistics.Assists.isValidAssists(assists)) {
            throw new IllegalValueException(seedu.address.model.person.statistics.Assists.MESSAGE_CONSTRAINTS);
        }
        final seedu.address.model.person.statistics.Assists modelAssists =
            new seedu.address.model.person.statistics.Assists(assists);

        return new Statistics.Builder()
                .withKills(modelKills)
                .withDeaths(modelDeaths)
                .withAssists(modelAssists)
                .build();
    }
}
