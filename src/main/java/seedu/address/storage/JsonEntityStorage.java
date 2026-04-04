package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.entity.EntityReference;

/**
 * A class to access EntityReference data stored as a json file on the hard disk.
 */
public class JsonEntityStorage implements EntityStorage {

    private final Path filePath;

    public JsonEntityStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getEntityFilePath() {
        return filePath;
    }

    @Override
    public Optional<EntityReference> readEntityReference() throws DataLoadingException {
        return readEntityReference(filePath);
    }

    /**
     * Similar to {@link #readEntityReference()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<EntityReference> readEntityReference(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableEntityReference> jsonEntityReference = JsonUtil.readJsonFile(
                filePath, JsonSerializableEntityReference.class);
        if (jsonEntityReference.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonEntityReference.get().toModelType());
        } catch (IllegalValueException ive) {
            throw new DataLoadingException(ive);
        }
    }

    /**
     * Saves the given {@link EntityReference} to the storage.
     */
    public void saveEntityReference(EntityReference entityReference) throws IOException {
        saveEntityReference(entityReference, getEntityFilePath());
    }

    /**
     * Saves the given {@link EntityReference} to the storage.
     */
    public void saveEntityReference(EntityReference entityReference, Path filePath) throws IOException {
        requireNonNull(entityReference);
        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableEntityReference(entityReference), filePath);
    }
}
