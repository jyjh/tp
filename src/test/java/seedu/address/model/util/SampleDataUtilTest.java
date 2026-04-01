package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class SampleDataUtilTest {

    @Test
    public void getSamplePerson() {
        assertDoesNotThrow(SampleDataUtil::getSamplePersons);
    }

    @Test
    public void getSampleMatches() {
        assertDoesNotThrow(SampleDataUtil::getSampleMatches);
    }

    @Test
    public void getSampleAddressBook() {
        assertDoesNotThrow(SampleDataUtil::getSampleAddressBook);
    }

    @Test
    public void getSampleMatchRecord() {
        assertDoesNotThrow(SampleDataUtil::getSampleMatchRecord);
    }

}
