package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.DraftCommand.MESSAGE_INVALID_IGN_NOT_FOUND;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SIXTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class DraftCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndices_throwsNullPointerException() {
        seedu.address.testutil.Assert.assertThrows(NullPointerException.class, () ->
                new DraftCommand(null, List.of()));
    }

    @Test
    public void constructor_nullIgns_throwsNullPointerException() {
        seedu.address.testutil.Assert.assertThrows(NullPointerException.class, () ->
                new DraftCommand(List.of(), null));
    }

    @Test
    public void execute_validDraft_success() {
        DraftCommand draftCommand = new DraftCommand(List.of(
                INDEX_FIRST_PERSON,
                INDEX_SECOND_PERSON,
                INDEX_THIRD_PERSON,
                INDEX_FOURTH_PERSON,
                INDEX_FIFTH_PERSON), List.of());

        List<Person> draftPlayers = List.of(
                model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased()),
                model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased()),
                model.getAddressBook().getPersonList().get(INDEX_THIRD_PERSON.getZeroBased()),
                model.getAddressBook().getPersonList().get(INDEX_FOURTH_PERSON.getZeroBased()),
                model.getAddressBook().getPersonList().get(INDEX_FIFTH_PERSON.getZeroBased()));

        String expectedValidation = "\u2713 Draft Valid!\n"
                + "Composition: TOP (1) | JUNGLE (1) | MID (1) | BOT (1) | SUPPORT (1)";
        String expectedMessage = String.format(DraftCommand.MESSAGE_SUCCESS, expectedValidation);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false, false, null, null,
                true, draftPlayers);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        assertCommandSuccess(draftCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidDraftComposition_successWithIssues() {
        DraftCommand draftCommand = new DraftCommand(List.of(
                INDEX_FIRST_PERSON,
                INDEX_SIXTH_PERSON,
                INDEX_THIRD_PERSON,
                INDEX_FOURTH_PERSON,
                INDEX_FIFTH_PERSON), List.of());

        List<Person> draftPlayers = List.of(
                model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased()),
                model.getAddressBook().getPersonList().get(INDEX_SIXTH_PERSON.getZeroBased()),
                model.getAddressBook().getPersonList().get(INDEX_THIRD_PERSON.getZeroBased()),
                model.getAddressBook().getPersonList().get(INDEX_FOURTH_PERSON.getZeroBased()),
                model.getAddressBook().getPersonList().get(INDEX_FIFTH_PERSON.getZeroBased()));

        String expectedValidation = "\u2717 Invalid Draft Composition\n"
                + "Composition: TOP (2) | JUNGLE (0) | MID (1) | BOT (1) | SUPPORT (1)\n"
                + "Issues: Too many TOP players, Missing JUNGLE player";
        String expectedMessage = String.format(DraftCommand.MESSAGE_SUCCESS, expectedValidation);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false, false, null, null,
                true, draftPlayers);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        assertCommandSuccess(draftCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndex_failure() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getAddressBook().getPersonList().size() + 1);
        DraftCommand draftCommand = new DraftCommand(List.of(INDEX_FIRST_PERSON, outOfBoundsIndex), List.of());

        assertCommandFailure(draftCommand, model, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIgn_failure() {
        String invalidIgn = "InvalidName";
        DraftCommand draftCommand = new DraftCommand(List.of(), List.of(invalidIgn));

        assertCommandFailure(draftCommand, model, String.format(MESSAGE_INVALID_IGN_NOT_FOUND, invalidIgn));
    }

    @Test
    public void execute_validDraftWithIgn_success() {
        List<Person> draftPlayers = List.of(
                model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased()),
                model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased()),
                model.getAddressBook().getPersonList().get(INDEX_THIRD_PERSON.getZeroBased()),
                model.getAddressBook().getPersonList().get(INDEX_FOURTH_PERSON.getZeroBased()),
                model.getAddressBook().getPersonList().get(INDEX_FIFTH_PERSON.getZeroBased()));

        List<String> ignList = draftPlayers.stream()
                .map(person -> person.getIgn().value)
                .toList();

        DraftCommand draftCommand = new DraftCommand(List.of(), ignList);

        String expectedValidation = "\u2713 Draft Valid!\n"
                + "Composition: TOP (1) | JUNGLE (1) | MID (1) | BOT (1) | SUPPORT (1)";
        String expectedMessage = String.format(DraftCommand.MESSAGE_SUCCESS, expectedValidation);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false, false, null, null,
                true, draftPlayers);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        assertCommandSuccess(draftCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_hybridArguments_success() {
        // Mix of indices and IGNs
        List<Person> expectedDraftPlayers = List.of(
                model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased()),
                model.getAddressBook().getPersonList().get(INDEX_THIRD_PERSON.getZeroBased()),
                model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased()),
                model.getAddressBook().getPersonList().get(INDEX_FOURTH_PERSON.getZeroBased()),
                model.getAddressBook().getPersonList().get(INDEX_FIFTH_PERSON.getZeroBased()));

        List<String> ignList = List.of(
                expectedDraftPlayers.get(2).getIgn().value,
                expectedDraftPlayers.get(3).getIgn().value,
                expectedDraftPlayers.get(4).getIgn().value);

        DraftCommand draftCommand = new DraftCommand(List.of(INDEX_FIRST_PERSON, INDEX_THIRD_PERSON), ignList);

        String expectedValidation = "\u2713 Draft Valid!\n"
                + "Composition: TOP (1) | JUNGLE (1) | MID (1) | BOT (1) | SUPPORT (1)";
        String expectedMessage = String.format(DraftCommand.MESSAGE_SUCCESS, expectedValidation);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false, false, null, null,
                true, expectedDraftPlayers);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        assertCommandSuccess(draftCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        DraftCommand draftFirstCommand = new DraftCommand(List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON), List.of());
        DraftCommand draftSecondCommand = new DraftCommand(List.of(INDEX_SECOND_PERSON, INDEX_THIRD_PERSON), List.of());

        // same object -> returns true
        assertTrue(draftFirstCommand.equals(draftFirstCommand));

        // same values -> returns true
        DraftCommand draftFirstCommandCopy = new DraftCommand(
                List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON),
                List.of());
        assertTrue(draftFirstCommand.equals(draftFirstCommandCopy));

        // different types -> returns false
        assertFalse(draftFirstCommand.equals(1));

        // null -> returns false
        assertFalse(draftFirstCommand.equals(null));

        // different arguments -> returns false
        assertFalse(draftFirstCommand.equals(draftSecondCommand));

        // hybrid commands with same values -> returns true
        DraftCommand draftHybrid1 = new DraftCommand(List.of(INDEX_FIRST_PERSON), List.of("BensonM88"));
        DraftCommand draftHybrid2 = new DraftCommand(List.of(INDEX_FIRST_PERSON), List.of("BensonM88"));
        assertTrue(draftHybrid1.equals(draftHybrid2));

        // indices-only vs IGNs-only -> returns false
        DraftCommand draftWithIndicesOnly = new DraftCommand(
                List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON),
                List.of());
        DraftCommand draftWithIgnsOnly = new DraftCommand(
                List.of(),
                List.of("AliceP99", "BensonM88"));
        assertFalse(draftWithIndicesOnly.equals(draftWithIgnsOnly));

        // indices-only vs hybrid -> returns false
        assertFalse(draftWithIndicesOnly.equals(draftHybrid1));
    }

    @Test
    public void toStringMethod() {
        // test with indices only
        List<Index> indices = List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);
        DraftCommand draftCommand = new DraftCommand(indices, List.of());
        String expected = DraftCommand.class.getCanonicalName() + "{indices=" + indices + ", igns=[]}";
        assertEquals(expected, draftCommand.toString());

        // test with hybrid (indices and IGNs)
        indices = List.of(INDEX_FIRST_PERSON);
        List<String> igns = List.of("BensonM88", "CarlK77");
        draftCommand = new DraftCommand(indices, igns);
        expected = DraftCommand.class.getCanonicalName() + "{indices=" + indices + ", igns=" + igns + "}";
        assertEquals(expected, draftCommand.toString());
    }
}
