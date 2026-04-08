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
 * Compares two players identified by their displayed indices.
 */
public class CompareCommand extends Command {

    public static final String COMMAND_WORD = "compare";

    public static final String MESSAGE_USAGE =
            "Compares two players identified by their index numbers"
            + " or in-game names (IGNs) in the displayed person list.";

    public static final String PARAMETERS =
        "Parameters: INDEX1 INDEX2 or i/IGN1 i/IGN2 or combinations (must be two identifiers)\n";

    public static final String EXAMPLE = "Example: " + COMMAND_WORD + " 1 2\n" + COMMAND_WORD + " i/Player1 i/Player2\n"
            + COMMAND_WORD + " 1 i/Player2";

    public static final String MESSAGE_COMPARE_SUCCESS = "Comparing players:\n%1$s\n%2$s";
    public static final String MESSAGE_IGN_NOT_FOUND = "No person with IGN '%1$s' found.";

    private final String targetIdentifier1;
    private final String targetIdentifier2;

    /**
     * Creates a CompareCommand to compare two players.
     * @param targetIdentifier1 the index or IGN of the first player
     * @param targetIdentifier2 the index or IGN of the second player
     */
    public CompareCommand(String targetIdentifier1, String targetIdentifier2) {
        this.targetIdentifier1 = targetIdentifier1;
        this.targetIdentifier2 = targetIdentifier2;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> addressBookList = model.getAddressBook().getPersonList();

        Person person1 = CommandUtil.findPersonByIdentifier(addressBookList, targetIdentifier1);
        Person person2 = CommandUtil.findPersonByIdentifier(addressBookList, targetIdentifier2);

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
        return targetIdentifier1.equals(otherCompareCommand.targetIdentifier1)
                && targetIdentifier2.equals(otherCompareCommand.targetIdentifier2);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIdentifier1", targetIdentifier1)
                .add("targetIdentifier2", targetIdentifier2)
                .toString();
    }
}
