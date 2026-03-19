package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.MatchRecord;
import seedu.address.model.ReadOnlyMatchRecord;
import seedu.address.model.match.Match;
import seedu.address.model.match.Result;

public class JsonMatchRecordStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonMatchRecordStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readMatchRecord_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readMatchRecord(null));
    }

    private Optional<ReadOnlyMatchRecord> readMatchRecord(String filePath) throws Exception {
        return new JsonMatchRecordStorage(Paths.get(filePath)).readMatchRecord(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assert(readMatchRecord("NonExistentFile.json").isEmpty());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readMatchRecord("notJsonFormatMatchRecord.json"));
    }

    @Test
    public void readMatchRecord_invalidMatchRecord_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readMatchRecord("invalidMatchRecord.json"));
    }

    @Test
    public void readMatchRecord_invalidAndValidMatchRecord_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readMatchRecord("invalidAndValidMatchRecord.json"));
    }

    @Test
    public void readAndSaveMatchRecord_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempMatchRecord.json");
        JsonMatchRecordStorage jsonMatchRecordStorage = new JsonMatchRecordStorage(filePath);

        MatchRecord matchRecord = new MatchRecord();
        Match match = new Match(new Result(Result.WinType.DRAW));
        matchRecord.addMatch(new Match(new Result(Result.WinType.WIN)));
        matchRecord.addMatch(new Match(new Result(Result.WinType.LOSE)));
        matchRecord.addMatch(match);

        // Save in new file and read back
        jsonMatchRecordStorage.saveMatchRecord(matchRecord);
        ReadOnlyMatchRecord readBack = jsonMatchRecordStorage.readMatchRecord().get();
        assertEquals(matchRecord, readBack);

        // Modify data, overwrite exiting file, and read back
        matchRecord.addMatch(new Match(new Result(Result.WinType.WIN)));
        matchRecord.removeMatch(match);
        jsonMatchRecordStorage.saveMatchRecord(matchRecord);
        readBack = jsonMatchRecordStorage.readMatchRecord().get();
        assertEquals(matchRecord, readBack);

        // Save and read without specifying file path
        matchRecord.addMatch(new Match(new Result(Result.WinType.LOSE)));
        jsonMatchRecordStorage.saveMatchRecord(matchRecord); // file path not specified
        readBack = jsonMatchRecordStorage.readMatchRecord().get(); // file path not specified
        assertEquals(matchRecord, readBack);
    }

    @Test
    public void saveMatchRecord_nullMatchRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMatchRecord(null, "SomeFile.json"));
    }

    private void saveMatchRecord(ReadOnlyMatchRecord matchRecord, String filePath) throws Exception {
        try {
            new JsonMatchRecordStorage(Paths.get(filePath))
                    .saveMatchRecord(matchRecord, addToTestDataPathIfNotNull(filePath));
        } catch (IOException e) {
            throw new AssertionError("There should not be an error writing to the file.", e);
        }
    }

    @Test
    public void saveMatchRecord_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMatchRecord(new MatchRecord(), null));
    }

}
