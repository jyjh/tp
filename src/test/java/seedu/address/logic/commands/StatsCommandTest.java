package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.STATS_DESC_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSISTS_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSISTS_SET_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEATHS_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEATHS_SET_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENTITY_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENTITY_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENTITY_STATISTIC_MAP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_KILLS_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_KILLS_SET_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATS_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.StatsCommand.EditStatsDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.entity.EntityStatisticMap;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditStatsDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

public class StatsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_statisticsSpecifiedUnfilteredList_success() {
        Person firstPerson = model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withEntityStatistics(VALID_ENTITY_STATISTIC_MAP).build();
        EditStatsDescriptor descriptor = new EditStatsDescriptorBuilder()
                .withEntity(VALID_ENTITY_1)
                .withKills(VALID_KILLS_SET_1)
                .withDeaths(VALID_DEATHS_SET_1)
                .withAssists(VALID_ASSISTS_SET_1)
                .build();
        StatsCommand statsCommand = new StatsCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(StatsCommand.MESSAGE_STATS_SUCCESS, Messages.format(editedPerson));
        expectedMessage += "\n" + Messages.MESSAGE_GLOBAL_INDEX_COMMAND_CUE;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(statsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_statistics_personWithoutExistingEntityEntry() {
        Person firstPerson = model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person emptyEntityMapPerson = new PersonBuilder(firstPerson)
            .withEntityStatistics(new EntityStatisticMap()).build();
        model.setPerson(firstPerson, emptyEntityMapPerson); //reset's first person's entity statistic map to be empty
        Person editedPerson = new PersonBuilder(emptyEntityMapPerson)
            .withEntityStatistics(VALID_ENTITY_STATISTIC_MAP).build();
        EditStatsDescriptor descriptor = new EditStatsDescriptorBuilder()
                .withEntity(VALID_ENTITY_1)
                .withKills(VALID_KILLS_SET_1)
                .withDeaths(VALID_DEATHS_SET_1)
                .withAssists(VALID_ASSISTS_SET_1)
                .build();
        StatsCommand statsCommand = new StatsCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(StatsCommand.MESSAGE_STATS_SUCCESS, Messages.format(editedPerson));
        expectedMessage += "\n" + Messages.MESSAGE_GLOBAL_INDEX_COMMAND_CUE;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(statsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_statistics_personWithExistingEntityEntry() {
        Person firstPerson = model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person filledEntityMapPerson = new PersonBuilder(firstPerson)
            .withEntityStatistics(VALID_ENTITY_STATISTIC_MAP).build();
        model.setPerson(firstPerson, filledEntityMapPerson);
        EntityStatisticMap updatedEntityStatisticMap = new EntityStatisticMap.Builder()
            .withEntity(VALID_ENTITY_1,
                VALID_STATS_SET_1.add(VALID_STATS_SET_1))
            .build();
        Person editedPerson = new PersonBuilder(filledEntityMapPerson)
            .withEntityStatistics(updatedEntityStatisticMap).build();
        EditStatsDescriptor descriptor = new EditStatsDescriptorBuilder()
                .withEntity(VALID_ENTITY_1)
                .withKills(VALID_KILLS_SET_1)
                .withDeaths(VALID_DEATHS_SET_1)
                .withAssists(VALID_ASSISTS_SET_1)
                .build();
        StatsCommand statsCommand = new StatsCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(StatsCommand.MESSAGE_STATS_SUCCESS, Messages.format(editedPerson));
        expectedMessage += "\n" + Messages.MESSAGE_GLOBAL_INDEX_COMMAND_CUE;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(filledEntityMapPerson, editedPerson);

        assertCommandSuccess(statsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final StatsCommand statsFirstCommand = new StatsCommand(INDEX_FIRST_PERSON, STATS_DESC_SET_1);

        // same values -> returns true
        StatsCommand statsFirstCommandCopy = new StatsCommand(INDEX_FIRST_PERSON, STATS_DESC_SET_1);
        Assertions.assertTrue(statsFirstCommand.equals(statsFirstCommandCopy));

        // different index -> returns false
        Assertions.assertFalse(statsFirstCommand
            .equals(new StatsCommand(INDEX_SECOND_PERSON, STATS_DESC_SET_1)));

        // different descriptor -> returns false
        EditStatsDescriptor differentDescriptor = new EditStatsDescriptorBuilder()
            .withEntity(VALID_ENTITY_1).withKills("999").build();
        Assertions.assertFalse(statsFirstCommand
            .equals(new StatsCommand(INDEX_FIRST_PERSON, differentDescriptor)));
    }
    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getAddressBook().getPersonList().size() + 1);
        EditStatsDescriptor descriptor = new EditStatsDescriptorBuilder()
                .withEntity(VALID_ENTITY_1)
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
                .withEntity(VALID_ENTITY_1)
                .withKills(VALID_KILLS_SET_1)
                .withDeaths(VALID_DEATHS_SET_1)
                .withAssists(VALID_ASSISTS_SET_1)
                .build();

        // same values -> returns true
        EditStatsDescriptor descriptorWithSameValues =
            new EditStatsDescriptorBuilder()
                .withEntity(VALID_ENTITY_1)
                .withKills(VALID_KILLS_SET_1)
                .withDeaths(VALID_DEATHS_SET_1)
                .withAssists(VALID_ASSISTS_SET_1)
                .build();
        Assertions.assertTrue(descriptor.equals(descriptorWithSameValues));

        // same object -> returns true
        Assertions.assertTrue(descriptor.equals(descriptor));

        // null -> returns false
        Assertions.assertFalse(descriptor.equals(null));

        // different types -> returns false
        Assertions.assertFalse(descriptor.equals(5));

        // different values -> returns false
        EditStatsDescriptor descriptorWithDifferentValues =
            new EditStatsDescriptorBuilder()
                .withEntity(VALID_ENTITY_1)
                .withKills(VALID_KILLS_SET_2)
                .withDeaths(VALID_DEATHS_SET_1)
                .withAssists(VALID_ASSISTS_SET_1)
                .build();
        Assertions.assertFalse(descriptor.equals(descriptorWithDifferentValues));

        EditStatsDescriptor descriptorWithDifferentDeaths =
            new EditStatsDescriptorBuilder()
                .withEntity(VALID_ENTITY_1)
                .withKills(VALID_KILLS_SET_1)
                .withDeaths(VALID_DEATHS_SET_2)
                .withAssists(VALID_ASSISTS_SET_1)
                .build();
        Assertions.assertFalse(descriptor.equals(descriptorWithDifferentDeaths));

        EditStatsDescriptor descriptorWithDifferentAssists =
            new EditStatsDescriptorBuilder()
                .withEntity(VALID_ENTITY_1)
                .withKills(VALID_KILLS_SET_1)
                .withDeaths(VALID_DEATHS_SET_1)
                .withAssists(VALID_ASSISTS_SET_2)
                .build();
        Assertions.assertFalse(descriptor.equals(descriptorWithDifferentAssists));

        EditStatsDescriptor descriptorWithDifferentEntity =
            new EditStatsDescriptorBuilder()
                .withEntity(VALID_ENTITY_2)
                .withKills(VALID_KILLS_SET_1)
                .withDeaths(VALID_DEATHS_SET_1)
                .withAssists(VALID_ASSISTS_SET_1)
                .build();
        Assertions.assertFalse(descriptor.equals(descriptorWithDifferentEntity));
    }

    @Test
    public void isAnyFieldEdited() {
        EditStatsDescriptor descriptor = new EditStatsDescriptor();
        Assertions.assertFalse(descriptor.isAnyFieldEdited());

        descriptor.setKills(new seedu.address.model.person.statistics.Kills(VALID_KILLS_SET_1));
        Assertions.assertTrue(descriptor.isAnyFieldEdited());

        descriptor = new EditStatsDescriptor();
        descriptor.setDeaths(new seedu.address.model.person.statistics.Deaths(VALID_DEATHS_SET_1));
        Assertions.assertTrue(descriptor.isAnyFieldEdited());

        descriptor = new EditStatsDescriptor();
        descriptor.setAssists(new seedu.address.model.person.statistics.Assists(VALID_ASSISTS_SET_1));
        Assertions.assertTrue(descriptor.isAnyFieldEdited());
    }
}
