package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.STATS_DESC_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSISTS_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSISTS_SET_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEATHS_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEATHS_SET_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_KILLS_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_KILLS_SET_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATS_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.StatsCommand.EditStatsDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditStatsDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

public class StatsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_statisticsSpecifiedUnfilteredList_success() {
        Person firstPerson = model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withStatistics(VALID_STATS_SET_1).build();
        EditStatsDescriptor descriptor = new EditStatsDescriptorBuilder()
                .withKills(VALID_KILLS_SET_1)
                .withDeaths(VALID_DEATHS_SET_1)
                .withAssists(VALID_ASSISTS_SET_1)
                .build();
        StatsCommand statsCommand = new StatsCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(StatsCommand.MESSAGE_STATS_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(statsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final StatsCommand statsFirstCommand = new StatsCommand(INDEX_FIRST_PERSON, STATS_DESC_SET_1);

        // same values -> returns true
        StatsCommand statsFirstCommandCopy = new StatsCommand(INDEX_FIRST_PERSON, STATS_DESC_SET_1);
        org.junit.jupiter.api.Assertions.assertTrue(statsFirstCommand.equals(statsFirstCommandCopy));

        // different index -> returns false
        org.junit.jupiter.api.Assertions.assertFalse(statsFirstCommand
            .equals(new StatsCommand(INDEX_SECOND_PERSON, STATS_DESC_SET_1)));

        // different descriptor -> returns false
        EditStatsDescriptor differentDescriptor = new EditStatsDescriptorBuilder().withKills("999").build();
        org.junit.jupiter.api.Assertions.assertFalse(statsFirstCommand
            .equals(new StatsCommand(INDEX_FIRST_PERSON, differentDescriptor)));
    }
    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getAddressBook().getPersonList().size() + 1);
        EditStatsDescriptor descriptor = new EditStatsDescriptorBuilder()
                .withKills(VALID_KILLS_SET_1)
                .withDeaths(VALID_DEATHS_SET_1)
                .withAssists(VALID_ASSISTS_SET_1)
                .build();
        StatsCommand statsCommand = new StatsCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(statsCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void editStatsDescriptor_equals() {
        final EditStatsDescriptor descriptor = new EditStatsDescriptorBuilder()
                .withKills(VALID_KILLS_SET_1)
                .withDeaths(VALID_DEATHS_SET_1)
                .withAssists(VALID_ASSISTS_SET_1)
                .build();

        // same values -> returns true
        EditStatsDescriptor descriptorWithSameValues =
            new EditStatsDescriptorBuilder()
                .withKills(VALID_KILLS_SET_1)
                .withDeaths(VALID_DEATHS_SET_1)
                .withAssists(VALID_ASSISTS_SET_1)
                .build();
        org.junit.jupiter.api.Assertions.assertTrue(descriptor.equals(descriptorWithSameValues));

        // same object -> returns true
        org.junit.jupiter.api.Assertions.assertTrue(descriptor.equals(descriptor));

        // null -> returns false
        org.junit.jupiter.api.Assertions.assertFalse(descriptor.equals(null));

        // different types -> returns false
        org.junit.jupiter.api.Assertions.assertFalse(descriptor.equals(5));

        // different values -> returns false
        EditStatsDescriptor descriptorWithDifferentValues =
            new EditStatsDescriptorBuilder()
                .withKills(VALID_KILLS_SET_2)
                .withDeaths(VALID_DEATHS_SET_1)
                .withAssists(VALID_ASSISTS_SET_1)
                .build();
        org.junit.jupiter.api.Assertions.assertFalse(descriptor.equals(descriptorWithDifferentValues));

        EditStatsDescriptor descriptorWithDifferentDeaths =
            new EditStatsDescriptorBuilder()
                .withKills(VALID_KILLS_SET_1)
                .withDeaths(VALID_DEATHS_SET_2)
                .withAssists(VALID_ASSISTS_SET_1)
                .build();
        org.junit.jupiter.api.Assertions.assertFalse(descriptor.equals(descriptorWithDifferentDeaths));

        EditStatsDescriptor descriptorWithDifferentAssists =
            new EditStatsDescriptorBuilder()
                .withKills(VALID_KILLS_SET_1)
                .withDeaths(VALID_DEATHS_SET_1)
                .withAssists(VALID_ASSISTS_SET_2)
                .build();
        org.junit.jupiter.api.Assertions.assertFalse(descriptor.equals(descriptorWithDifferentAssists));
    }

    @Test
    public void isAnyFieldEdited() {
        EditStatsDescriptor descriptor = new EditStatsDescriptor();
        org.junit.jupiter.api.Assertions.assertFalse(descriptor.isAnyFieldEdited());

        descriptor.setKills(new seedu.address.model.person.statistics.Kills(VALID_KILLS_SET_1));
        org.junit.jupiter.api.Assertions.assertTrue(descriptor.isAnyFieldEdited());

        descriptor = new EditStatsDescriptor();
        descriptor.setDeaths(new seedu.address.model.person.statistics.Deaths(VALID_DEATHS_SET_1));
        org.junit.jupiter.api.Assertions.assertTrue(descriptor.isAnyFieldEdited());

        descriptor = new EditStatsDescriptor();
        descriptor.setAssists(new seedu.address.model.person.statistics.Assists(VALID_ASSISTS_SET_1));
        org.junit.jupiter.api.Assertions.assertTrue(descriptor.isAnyFieldEdited());
    }
}
