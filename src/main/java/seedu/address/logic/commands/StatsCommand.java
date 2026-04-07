package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSISTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEATHS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_KILLS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityStatisticMap;
import seedu.address.model.person.Person;
import seedu.address.model.person.statistics.Assists;
import seedu.address.model.person.statistics.Deaths;
import seedu.address.model.person.statistics.Kills;
import seedu.address.model.person.statistics.Statistics;

/**
 * Updates the statistics of an existing person in the address book.
 */
public class StatsCommand extends Command {
    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = "Updates the statistics of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.";

    public static final String PARAMETERS = "Parameters: INDEX (must be a positive integer) "
            + PREFIX_ENTITY + "ENTITY "
            + "[" + PREFIX_KILLS + "KILLS] "
            + "[" + PREFIX_DEATHS + "DEATHS] "
            + "[" + PREFIX_ASSISTS + "ASSISTS]\n";

    public static final String EXAMPLE = "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_KILLS + "50 " + PREFIX_DEATHS + "10 "
            + PREFIX_ASSISTS + "20";

    public static final String MESSAGE_STATS_SUCCESS = "Updated Statistics for Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one statistics field to update must be provided.";

    private final Index index;
    private final EditStatsDescriptor editStatsDescriptor;

    /**
     * @param index               of the person in the filtered person list to
     *                            update stats
     * @param editStatsDescriptor details to update the statistics with
     */
    public StatsCommand(Index index, EditStatsDescriptor editStatsDescriptor) {
        requireNonNull(index);
        requireNonNull(editStatsDescriptor);

        this.index = index;
        this.editStatsDescriptor = new EditStatsDescriptor(editStatsDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> addressBookList = model.getAddressBook().getPersonList();

        if (index.getZeroBased() >= addressBookList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = addressBookList.get(index.getZeroBased());
        Person editedPerson = createStatsEditedPerson(personToEdit, editStatsDescriptor);

        model.setPerson(personToEdit, editedPerson);
        return new CommandResult(String.format(MESSAGE_STATS_SUCCESS, Messages.format(editedPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * but with updated statistics from {@code descriptor}.
     */
    private static Person createStatsEditedPerson(Person personToEdit, EditStatsDescriptor descriptor) {
        assert personToEdit != null;
        assert descriptor.getEntity() != null; // Entity is required for stats update

        EntityStatisticMap stats = personToEdit.getOverallEntityStatistics();

        // Build new statistics object using provided values or current ones
        if (stats.containsKey(descriptor.getEntity())) {
            stats.addStatistics(descriptor.getEntity(),
                stats.getStatistics(descriptor.getEntity())
                .add(new Statistics.Builder()
                .withKills(descriptor.getKills().orElse(new Kills("0")))
                .withDeaths(descriptor.getDeaths().orElse(new Deaths("0")))
                .withAssists(descriptor.getAssists().orElse(new Assists("0")))
                .build()));
        } else {
            stats.addStatistics(descriptor.getEntity(),
                new Statistics.Builder()
                .withKills(descriptor.getKills().orElse(new Kills("0")))
                .withDeaths(descriptor.getDeaths().orElse(new Deaths("0")))
                .withAssists(descriptor.getAssists().orElse(new Assists("0")))
                .build());
        }
        return new Person(
                personToEdit.getName(),
                personToEdit.getPhone(),
                personToEdit.getEmail(),
                personToEdit.getRole(),
                personToEdit.getIgn(),
                personToEdit.getRank(),
                personToEdit.getTags(),
                stats);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StatsCommand)) {
            return false;
        }

        StatsCommand otherStatsCommand = (StatsCommand) other;
        return index.equals(otherStatsCommand.index)
                && editStatsDescriptor.equals(otherStatsCommand.editStatsDescriptor);
    }

    /**
     * Stores the details to update the person's statistics with.
     * Each non-empty field value will replace the corresponding field value of the
     * person.
     * Entity cannot be null.
     */
    public static class EditStatsDescriptor {
        private Kills kills;
        private Deaths deaths;
        private Assists assists;
        private Entity entity;

        public EditStatsDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditStatsDescriptor(EditStatsDescriptor toCopy) {
            setKills(toCopy.kills);
            setDeaths(toCopy.deaths);
            setAssists(toCopy.assists);
            setEntity(toCopy.entity);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(kills, deaths, assists);
        }

        public void setKills(Kills kills) {
            this.kills = kills;
        }

        public Optional<Kills> getKills() {
            return Optional.ofNullable(kills);
        }

        public void setDeaths(Deaths deaths) {
            this.deaths = deaths;
        }

        public Optional<Deaths> getDeaths() {
            return Optional.ofNullable(deaths);
        }

        public void setAssists(Assists assists) {
            this.assists = assists;
        }

        public Optional<Assists> getAssists() {
            return Optional.ofNullable(assists);
        }

        public Entity getEntity() {
            return entity;
        }

        public void setEntity(Entity entity) {
            this.entity = entity;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditStatsDescriptor)) {
                return false;
            }

            EditStatsDescriptor otherDescriptor = (EditStatsDescriptor) other;
            return java.util.Objects.equals(kills, otherDescriptor.kills)
                    && java.util.Objects.equals(deaths, otherDescriptor.deaths)
                    && java.util.Objects.equals(assists, otherDescriptor.assists)
                    && java.util.Objects.equals(entity, otherDescriptor.entity);
        }
    }
}
