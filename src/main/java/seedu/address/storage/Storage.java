package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyMatchRecord;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.entity.EntityReference;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage, MatchRecordStorage, EntityStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    @Override
    Optional<ReadOnlyMatchRecord> readMatchRecord() throws DataLoadingException;

    @Override
    void saveMatchRecord(ReadOnlyMatchRecord matchRecord) throws IOException;

    @Override
    Path getEntityFilePath();

    @Override
    Optional<EntityReference> readEntityReference() throws DataLoadingException;

    @Override
    void saveEntityReference(EntityReference entityReference) throws IOException;

    @Override
    void saveEntityReference(EntityReference entityReference, Path filePath) throws IOException;

}
