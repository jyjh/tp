package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entity.EntityPathPair;
import seedu.address.model.entity.EntityReference;

/**
 * An Immutable AddressBook that is serializable to JSON format {@code JsonSerializableAddressBook}.
 */
@JsonRootName(value = "entityreference")
class JsonSerializableEntityReference {

    private final List<JsonAdaptedEntityPathPair> entities = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableEntityReference} with the given entities.
     */
    @JsonCreator
    public JsonSerializableEntityReference(@JsonProperty("entities") List<JsonAdaptedEntityPathPair> entities) {
        if (entities != null) {
            this.entities.addAll(entities);
        }
    }

    /**
     * Converts a given {@code EntityReference} into this class for Jackson use.
     */
    public JsonSerializableEntityReference(EntityReference source) {
        entities.addAll(source.getEntityPathPairs().stream()
                .map(JsonAdaptedEntityPathPair::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code EntityReference} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public EntityReference toModelType() throws IllegalValueException {
        List<JsonAdaptedEntityPathPair> entityList = entities;
        List<EntityPathPair> result = new ArrayList<>();
        for (JsonAdaptedEntityPathPair jsonAdaptedEntityPathPair : entityList) {
            result.add(jsonAdaptedEntityPathPair.toModelType());
        }
        return new EntityReference(result);
    }

}
