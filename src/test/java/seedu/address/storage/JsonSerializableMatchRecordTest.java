package seedu.address.storage;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ENTITY_REFERENCE;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;


public class JsonSerializableMatchRecordTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableMatchRecordTest");
    private static final Path INVALID_MATCH_FILE = TEST_DATA_FOLDER.resolve("invalidMatchRecord.json");
    private static final Path VALID_MATCH_FILE = TEST_DATA_FOLDER.resolve("validMatchRecord.json");

    @BeforeAll
    public static void setupAll() {
        VALID_ENTITY_REFERENCE.reload(); // Ensure the EntityReference is loaded before tests run
    }

    @Test
    public void toModelType_validMatchFile_success() throws Exception {
        JsonSerializableMatchRecord dataFromFile = JsonUtil.readJsonFile(VALID_MATCH_FILE,
                JsonSerializableMatchRecord.class).get();
        dataFromFile.toModelType();
    }

    @Test
    public void toModelType_invalidMatchFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMatchRecord dataFromFile = JsonUtil.readJsonFile(INVALID_MATCH_FILE,
                JsonSerializableMatchRecord.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

}
