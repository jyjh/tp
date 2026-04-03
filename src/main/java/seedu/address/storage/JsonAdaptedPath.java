package seedu.address.storage;

import java.nio.file.Path;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Jackson-friendly version of {Path}.
 */
class JsonAdaptedPath {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Entity's %s field is missing!";

    private final String iconPath;

    /**
     * Constructs a {@code JsonAdaptedEntity} with the given entity details.
     */
    @JsonCreator
    public JsonAdaptedPath(@JsonProperty("iconPath") String iconPath) {
        this.iconPath = iconPath;
    }

    /**
     * Converts a given {@code Path} into this class for Jackson use.
     */
    public JsonAdaptedPath(Path source) {
        iconPath = source.toString();
    }

    /**
     * Converts this Jackson-friendly adapted entity object into the model's {@code Entity} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted entity.
     */
    public Path toModelType() throws IllegalValueException {
        if (iconPath == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "name"));
        }
        if (iconPath.isEmpty()) {
            throw new IllegalValueException("Entity name cannot be empty.");
        }

        return Path.of(iconPath);
    }

}
