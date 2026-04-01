package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Compares two players identified by their displayed indices.
 */
public class CompareCommand extends Command {

    public static final String COMMAND_WORD = "compare";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Compares two players identified by their index numbers in the displayed person list.\n"
            + "Parameters: INDEX1 INDEX2 (must be two positive integers)\n"
            + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_COMPARE_SUCCESS = "Comparing players:\n%1$s\n%2$s";

    private final Index targetIndex1;
    private final Index targetIndex2;

    /**
     * Creates a CompareCommand to compare two players.
     * @param targetIndex1 the index of the first player
     * @param targetIndex2 the index of the second player
     */
    public CompareCommand(Index targetIndex1, Index targetIndex2) {
        this.targetIndex1 = targetIndex1;
        this.targetIndex2 = targetIndex2;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> addressBookList = model.getAddressBook().getPersonList();

        if (targetIndex1.getZeroBased() >= addressBookList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (targetIndex2.getZeroBased() >= addressBookList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person1 = addressBookList.get(targetIndex1.getZeroBased());
        Person person2 = addressBookList.get(targetIndex2.getZeroBased());

        return new CommandResult(
                String.format(MESSAGE_COMPARE_SUCCESS, Messages.format(person1), Messages.format(person2)),
                false, false, true, person1, person2
        );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CompareCommand)) {
            return false;
        }

        CompareCommand otherCompareCommand = (CompareCommand) other;
        return targetIndex1.equals(otherCompareCommand.targetIndex1)
                && targetIndex2.equals(otherCompareCommand.targetIndex2);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex1", targetIndex1)
                .add("targetIndex2", targetIndex2)
                .toString();
    }
}
