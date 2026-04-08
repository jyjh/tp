package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for CompareCommand.
 */
public class CompareCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndicesUnfilteredList_success() {
        Person person1 = model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person person2 = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        CompareCommand compareCommand = new CompareCommand("1", "2");

        String expectedMessage = String.format(CompareCommand.MESSAGE_COMPARE_SUCCESS,
                Messages.format(person1), Messages.format(person2));

        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false, true, person1, person2);
        assertCommandSuccess(compareCommand, model, expectedCommandResult, model);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getAddressBook().getPersonList().size() + 1);
        CompareCommand compareCommand = new CompareCommand(outOfBoundIndex.getOneBased() + "", "1");

        assertCommandFailure(compareCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidSecondIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getAddressBook().getPersonList().size() + 1);
        CompareCommand compareCommand = new CompareCommand("1", outOfBoundIndex.getOneBased() + "");

        assertCommandFailure(compareCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        CompareCommand compareFirstSecondCommand = new CompareCommand("1", "2");
        CompareCommand compareSecondFirstCommand = new CompareCommand("2", "1");

        // same object -> returns true
        assertTrue(compareFirstSecondCommand.equals(compareFirstSecondCommand));

        // same values -> returns true
        CompareCommand compareFirstSecondCommandCopy = new CompareCommand("1", "2");
        assertTrue(compareFirstSecondCommand.equals(compareFirstSecondCommandCopy));

        // different types -> returns false
        assertFalse(compareFirstSecondCommand.equals(1));

        // null -> returns false
        assertFalse(compareFirstSecondCommand.equals(null));

        // different order -> returns false
        assertFalse(compareFirstSecondCommand.equals(compareSecondFirstCommand));
    }

    @Test
    public void toStringMethod() {
        String targetIndex1 = "1";
        String targetIndex2 = "2";
        CompareCommand compareCommand = new CompareCommand(targetIndex1, targetIndex2);
        String expected = CompareCommand.class.getCanonicalName()
                + "{targetIdentifier1=" + targetIndex1 + ", targetIdentifier2=" + targetIndex2 + "}";
        assertEquals(expected, compareCommand.toString());
    }

}
