package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entity.Entity;

/**
 * Jackson-friendly version of {@link Entity}.
 */
class JsonAdaptedEntity {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Entity's %s field is missing!";

    private final String name;

    /**
     * Constructs a {@code JsonAdaptedEntity} with the given entity details.
     */
    @JsonCreator
    public JsonAdaptedEntity(@JsonProperty("name") String name,
            @JsonProperty("iconPath") String iconPath) {
        this.name = name;
    }

    /**
     * Converts a given {@code Entity} into this class for Jackson use.
     */
    public JsonAdaptedEntity(Entity source) {
        name = source.getName();
    }

    /**
     * Converts this Jackson-friendly adapted entity object into the model's {@code Entity} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted entity.
     */
    public Entity toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "name"));
        }
        if (name.isEmpty()) {
            throw new IllegalValueException("Entity name cannot be empty.");
        }

        return new Entity(name);
    }

}
