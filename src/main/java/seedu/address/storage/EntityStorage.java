package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.entity.EntityReference;

/**
 * Represents a storage for {@link EntityReference}.
 */
public interface EntityStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getEntityFilePath();

    /**
     * Returns EntityReference data as a {@link EntityReference}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<EntityReference> readEntityReference() throws DataLoadingException;

    /**
     * @see #getEntityFilePath()
     */
    Optional<EntityReference> readEntityReference(Path filePath) throws DataLoadingException;

    void saveEntityReference(EntityReference entityReference) throws IOException;

    void saveEntityReference(EntityReference entityReference, Path filePath) throws IOException;

}
