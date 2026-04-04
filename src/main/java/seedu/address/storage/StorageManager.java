package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyMatchRecord;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.entity.EntityReference;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private MatchRecordStorage matchRecordStorage;
    private EntityStorage entityStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage}, {@code MatchRecordStorage}.
     * {@code UserPrefStorage} and {@code EntityStorage}
     */
    public StorageManager(AddressBookStorage addressBookStorage, MatchRecordStorage matchRecordStorage,
                          UserPrefsStorage userPrefsStorage, EntityStorage entityStorage) {
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.matchRecordStorage = matchRecordStorage;
        this.entityStorage = entityStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    @Override
    public Path getMatchRecordFilePath() {
        return matchRecordStorage.getMatchRecordFilePath();
    }

    @Override
    public Optional<ReadOnlyMatchRecord> readMatchRecord() throws DataLoadingException {
        return matchRecordStorage.readMatchRecord();
    }

    @Override
    public Optional<ReadOnlyMatchRecord> readMatchRecord(Path filePath) throws DataLoadingException {
        return matchRecordStorage.readMatchRecord(filePath);
    }

    @Override
    public void saveMatchRecord(ReadOnlyMatchRecord matchRecord) throws IOException {
        matchRecordStorage.saveMatchRecord(matchRecord);
    }

    @Override
    public void saveMatchRecord(ReadOnlyMatchRecord matchRecord, Path filePath) throws IOException {
        logger.fine("Attempting to write to match record data file: " + filePath);
        matchRecordStorage.saveMatchRecord(matchRecord, filePath);
    }

    // ================ Entity methods ==============================

    @Override
    public Path getEntityFilePath() {
        return entityStorage.getEntityFilePath();
    }

    @Override
    public Optional<EntityReference> readEntityReference() throws DataLoadingException {
        return entityStorage.readEntityReference();
    }

    @Override
    public Optional<EntityReference> readEntityReference(Path filePath)
            throws DataLoadingException {
        logger.fine("Attempting to read entity data from file: " + filePath);
        return entityStorage.readEntityReference(filePath);
    }

    @Override
    public void saveEntityReference(EntityReference entityReference) throws IOException {
        entityStorage.saveEntityReference(entityReference, getEntityFilePath());
    }

    @Override
    public void saveEntityReference(EntityReference entityReference, Path filePath) throws IOException {
        entityStorage.saveEntityReference(entityReference, filePath);
    }

}
