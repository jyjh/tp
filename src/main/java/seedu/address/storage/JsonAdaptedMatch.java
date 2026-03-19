package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.match.Match;
import seedu.address.model.match.Result;

/**
 * Jackson-friendly version of {@link Match}
 */
public class JsonAdaptedMatch {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Match's %s field is missing!";
    public static final String INVALID_DATE_MESSAGE_FORMAT = "Invalid date format: %s";

    private final String date;
    private final String result;

    /**
     * Constructs a {@code JsonAdaptedMatch} with the given match details.
     */
    public JsonAdaptedMatch(@JsonProperty("date") String date, @JsonProperty("result") String result) {
        this.result = result;
        this.date = date;
    }

    /**
     * Converts a give {@code Match} into this class for Jackson use.
     */
    public JsonAdaptedMatch(Match source) {
        date = source.getDate().toString();
        result = source.getResult().toString();
    }

    /**
     * Converts this Jackson-friendly adapted match object into the model's {@code Match} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted match.
     */
    public Match toModelType() throws IllegalValueException {
        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Date"));
        }
        final LocalDateTime modelDate;
        try {
            modelDate = LocalDateTime.parse(date);
        } catch (Exception e) {
            throw new IllegalValueException(String.format(INVALID_DATE_MESSAGE_FORMAT, date));
        }

        if (result == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Result"));
        }
        if (!Result.isValidResult(result)) {
            throw new IllegalValueException(Result.MESSAGE_CONSTRAINTS);
        }
        final Result modelResult = new Result(result);

        return new Match(modelDate, modelResult);
    }

}
