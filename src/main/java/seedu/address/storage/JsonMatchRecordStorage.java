package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyMatchRecord;

/**
 * A class to access MatchRecord data stored as a json file on the hard disk.
 */
public class JsonMatchRecordStorage implements MatchRecordStorage {

    private final Path filePath;

    public JsonMatchRecordStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getMatchRecordFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMatchRecord> readMatchRecord() throws DataLoadingException {
        return readMatchRecord(filePath);
    }

    /**
     * Similar to {@link #readMatchRecord()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyMatchRecord> readMatchRecord(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableMatchRecord> jsonMatchRecord = JsonUtil.readJsonFile(
                filePath, JsonSerializableMatchRecord.class);
        if (jsonMatchRecord.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonMatchRecord.get().toModelType());
        } catch (IllegalValueException ive) {
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveMatchRecord(ReadOnlyMatchRecord matchRecord) throws IOException {
        saveMatchRecord(matchRecord, filePath);
    }

    /**
     * Similar to {@link #saveMatchRecord(ReadOnlyMatchRecord)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveMatchRecord(ReadOnlyMatchRecord matchRecord, Path filePath) throws IOException {
        requireNonNull(matchRecord);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableMatchRecord(matchRecord), filePath);
    }
}
