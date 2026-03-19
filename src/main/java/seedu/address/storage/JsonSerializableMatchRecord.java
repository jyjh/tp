package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.MatchRecord;
import seedu.address.model.ReadOnlyMatchRecord;
import seedu.address.model.match.Match;

/**
 * An Immutable MatchRecord that is serializable to JSON format.
 */
public class JsonSerializableMatchRecord {

    private final List<JsonAdaptedMatch> matches = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMatchRecord} with the given matches.
     */
    @JsonCreator
    public JsonSerializableMatchRecord(@JsonProperty("matches") List<JsonAdaptedMatch> matches) {
        this.matches.addAll(matches);
    }

    /**
     * Converts a given {@code ReadOnlyMatchRecord} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMatchRecord}.
     */
    public JsonSerializableMatchRecord(ReadOnlyMatchRecord source) {
        matches.addAll(source.getMatchList().stream().map(JsonAdaptedMatch::new).toList());
    }

    /**
     * Converts this match record into the model's {@code MatchRecord} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ReadOnlyMatchRecord toModelType() throws IllegalValueException {
        MatchRecord matchRecord = new MatchRecord();
        for (JsonAdaptedMatch jsonAdaptedMatch : matches) {
            Match m = jsonAdaptedMatch.toModelType();
            matchRecord.addMatch(m);
        }
        return matchRecord;
    }
}
