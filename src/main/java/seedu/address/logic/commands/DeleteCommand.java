package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.CommandUtil;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE =
        "Deletes the person identified by the index number or in-game name (IGN) used in the displayed person list.";

    public static final String PARAMETERS = "Parameters: INDEX (must be a positive integer) or i/IGN\n";

    public static final String EXAMPLE = "Example: " + COMMAND_WORD + " 1\n" + COMMAND_WORD + " i/PlayerName";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_IGN_NOT_FOUND = "No person with IGN '%1$s' found.";

    private final String targetIdentifier;

    /**
     * Creates a DeleteCommand to delete the person with the specified identifier.
     * The identifier can be either an index (as a string) or an IGN.
     */
    public DeleteCommand(String targetIdentifier) {
        this.targetIdentifier = targetIdentifier;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> addressBookList = model.getAddressBook().getPersonList();

        Person personToDelete = CommandUtil.findPersonByIdentifier(addressBookList, targetIdentifier);

        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetIdentifier.equals(otherDeleteCommand.targetIdentifier);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIdentifier", targetIdentifier)
                .toString();
    }
}
