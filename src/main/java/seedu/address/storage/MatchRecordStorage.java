package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyMatchRecord;

/**
 * Represents a storage for {@link seedu.address.model.AddressBook}.
 */
public interface MatchRecordStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getMatchRecordFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyMatchRecord}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyMatchRecord> readMatchRecord() throws DataLoadingException;

    /**
     * @see #getMatchRecordFilePath()
     */
    Optional<ReadOnlyMatchRecord> readMatchRecord(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyMatchRecord} to the storage.
     * @param matchRecord cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMatchRecord(ReadOnlyMatchRecord matchRecord) throws IOException;

    /**
     * @see #saveMatchRecord(ReadOnlyMatchRecord)
     */
    void saveMatchRecord(ReadOnlyMatchRecord matchRecord, Path filePath) throws IOException;

}
