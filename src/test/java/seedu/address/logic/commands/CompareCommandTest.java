package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
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
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code CompareCommand}.
 */
public class CompareCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndicesUnfilteredList_success() {
        Person person1 = model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person person2 = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        CompareCommand compareCommand = new CompareCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);

        String expectedMessage = String.format(CompareCommand.MESSAGE_COMPARE_SUCCESS,
                Messages.format(person1), Messages.format(person2));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        CommandResult expectedCommandResult = new CommandResult(expectedMessage,
                false, false, true, person1, person2);

        assertCommandSuccess(compareCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndex1UnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getAddressBook().getPersonList().size() + 1);
        CompareCommand compareCommand = new CompareCommand(outOfBoundIndex, INDEX_FIRST_PERSON);

        assertCommandFailure(compareCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndex2UnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getAddressBook().getPersonList().size() + 1);
        CompareCommand compareCommand = new CompareCommand(INDEX_FIRST_PERSON, outOfBoundIndex);

        assertCommandFailure(compareCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndicesFilteredList_success() {
        // Filter to show only the first two people from the address book
        Person person1 = model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person person2 = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        model.updateFilteredPersonList(person ->
                person.equals(person1) || person.equals(person2));

        CompareCommand compareCommand = new CompareCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);

        String expectedMessage = String.format(CompareCommand.MESSAGE_COMPARE_SUCCESS,
                Messages.format(person1), Messages.format(person2));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(person ->
                person.equals(person1) || person.equals(person2));

        CommandResult expectedCommandResult = new CommandResult(expectedMessage,
                false, false, true, person1, person2);

        assertCommandSuccess(compareCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_validIndexOutOfFilteredView_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index indexOutOfFilteredView = INDEX_SECOND_PERSON;
        // ensures that indexOutOfFilteredView is still in bounds of address book list
        assertTrue(indexOutOfFilteredView.getZeroBased() < model.getAddressBook().getPersonList().size());

        Person person1 = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person person2 = model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        CompareCommand compareCommand = new CompareCommand(INDEX_SECOND_PERSON, INDEX_FIRST_PERSON);

        String expectedMessage = String.format(CompareCommand.MESSAGE_COMPARE_SUCCESS,
                Messages.format(person1), Messages.format(person2));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(person -> person.equals(person2));

        CommandResult expectedCommandResult = new CommandResult(expectedMessage,
                false, false, true, person1, person2);

        assertCommandSuccess(compareCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        CompareCommand compareFirstSecondCommand = new CompareCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);
        CompareCommand compareSecondFirstCommand = new CompareCommand(INDEX_SECOND_PERSON, INDEX_FIRST_PERSON);

        // same object -> returns true
        assertTrue(compareFirstSecondCommand.equals(compareFirstSecondCommand));

        // same values -> returns true
        CompareCommand compareFirstSecondCommandCopy = new CompareCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);
        assertTrue(compareFirstSecondCommand.equals(compareFirstSecondCommandCopy));

        // different types -> returns false
        assertFalse(compareFirstSecondCommand.equals(1));

        // null -> returns false
        assertFalse(compareFirstSecondCommand.equals(null));

        // different person order -> returns false
        assertFalse(compareFirstSecondCommand.equals(compareSecondFirstCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex1 = Index.fromOneBased(1);
        Index targetIndex2 = Index.fromOneBased(2);
        CompareCommand compareCommand = new CompareCommand(targetIndex1, targetIndex2);
        String expected = CompareCommand.class.getCanonicalName()
                + "{targetIndex1=" + targetIndex1 + ", targetIndex2=" + targetIndex2 + "}";
        assertEquals(expected, compareCommand.toString());
    }
}
