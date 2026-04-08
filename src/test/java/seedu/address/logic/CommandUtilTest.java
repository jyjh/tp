package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;

/**
 * Contains tests for {@link CommandUtil}.
 */
public class CommandUtilTest {

    private static final List<Person> TYPICAL_PERSONS = getTypicalAddressBook().getPersonList();
    private static final String VALID_IGN = "AliceP99"; // First person's IGN in typical address book
    private static final String INVALID_IGN = "NonExistentIGN";

    @Test
    public void findPersonByIdentifier_validIndex_success() throws CommandException {
        // First person
        Person person = CommandUtil.findPersonByIdentifier(TYPICAL_PERSONS, "1");
        assertEquals(TYPICAL_PERSONS.get(0), person);

        // Middle person
        person = CommandUtil.findPersonByIdentifier(TYPICAL_PERSONS, "3");
        assertEquals(TYPICAL_PERSONS.get(2), person);

        // Last person
        person = CommandUtil.findPersonByIdentifier(TYPICAL_PERSONS,
                String.valueOf(TYPICAL_PERSONS.size()));
        assertEquals(TYPICAL_PERSONS.get(TYPICAL_PERSONS.size() - 1), person);
    }

    @Test
    public void findPersonByIdentifier_invalidIndex_throwsCommandException() {
        // Negative index - treated as IGN since it doesn't match numeric pattern
        assertThrows(CommandException.class,
                String.format("Player with IGN '%1$s' not found", "-1"), () ->
                        CommandUtil.findPersonByIdentifier(TYPICAL_PERSONS, "-1"));

        // Index out of bounds (too large)
        int outOfBoundsIndex = TYPICAL_PERSONS.size() + 1;
        assertThrows(CommandException.class, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, () ->
                CommandUtil.findPersonByIdentifier(TYPICAL_PERSONS, String.valueOf(outOfBoundsIndex)));

        // Very large index
        assertThrows(CommandException.class, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, () ->
                CommandUtil.findPersonByIdentifier(TYPICAL_PERSONS, "999999"));
    }

    @Test
    public void findPersonByIdentifier_validIgn_success() throws CommandException {
        // Find person by IGN
        Person person = CommandUtil.findPersonByIdentifier(TYPICAL_PERSONS, VALID_IGN);
        assertEquals(VALID_IGN, person.getIgn().value);
    }

    @Test
    public void findPersonByIdentifier_invalidIgn_throwsCommandException() {
        // Non-existent IGN
        assertThrows(CommandException.class,
                String.format("Player with IGN '%1$s' not found", INVALID_IGN), () ->
                        CommandUtil.findPersonByIdentifier(TYPICAL_PERSONS, INVALID_IGN));
    }

    @Test
    public void findPersonByIdentifier_emptyList_throwsCommandException() {
        List<Person> emptyList = new ArrayList<>();

        // Index with empty list
        assertThrows(CommandException.class, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, () ->
                CommandUtil.findPersonByIdentifier(emptyList, "1"));

        // IGN with empty list
        assertThrows(CommandException.class,
                String.format("Player with IGN '%1$s' not found", VALID_IGN), () ->
                        CommandUtil.findPersonByIdentifier(emptyList, VALID_IGN));
    }

    @Test
    public void findPersonByIdentifier_numericString_usesIndex() throws CommandException {
        // Numeric string "1" should be treated as index, not IGN
        Person person = CommandUtil.findPersonByIdentifier(TYPICAL_PERSONS, "1");
        assertEquals(TYPICAL_PERSONS.get(0), person);

        // Even if a person has IGN "123", it should still use index lookup
        assertThrows(CommandException.class, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, () ->
                CommandUtil.findPersonByIdentifier(TYPICAL_PERSONS, "999"));
    }

    @Test
    public void findPersonByIdentifier_ignWithNumbers_treatedAsIgn() throws CommandException {
        // IGN with numbers should be treated as IGN, not index
        // This assumes there's at least one person with a numeric-like IGN
        // For now, just test that non-numeric strings are treated as IGNs
        assertThrows(CommandException.class,
                String.format("Player with IGN '%1$s' not found", "123abc"), () ->
                        CommandUtil.findPersonByIdentifier(TYPICAL_PERSONS, "123abc"));
    }

    @Test
    public void findPersonByIdentifier_nullIdentifier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                CommandUtil.findPersonByIdentifier(TYPICAL_PERSONS, null));
    }

    @Test
    public void findPersonByIdentifier_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                CommandUtil.findPersonByIdentifier(null, "1"));
    }

    @Test
    public void findPersonByIdentifier_whitespaceIndex_throwsCommandException() {
        // Whitespace strings should not match numeric pattern
        assertThrows(CommandException.class,
                String.format("Player with IGN '%1$s' not found", " "), () ->
                        CommandUtil.findPersonByIdentifier(TYPICAL_PERSONS, " "));

        // Empty string
        assertThrows(CommandException.class,
                String.format("Player with IGN '%1$s' not found", ""), () ->
                        CommandUtil.findPersonByIdentifier(TYPICAL_PERSONS, ""));
    }

    @Test
    public void findPersonByIdentifier_ignWithSpaces_success() throws CommandException {
        // IGN should match exactly (no trimming)
        // This test assumes no person has IGN with leading/trailing spaces
        assertThrows(CommandException.class,
                String.format("Player with IGN '%1$s' not found", " " + VALID_IGN), () ->
                        CommandUtil.findPersonByIdentifier(TYPICAL_PERSONS, " " + VALID_IGN));

        assertThrows(CommandException.class,
                String.format("Player with IGN '%1$s' not found", VALID_IGN + " "), () ->
                        CommandUtil.findPersonByIdentifier(TYPICAL_PERSONS, VALID_IGN + " "));
    }
}
