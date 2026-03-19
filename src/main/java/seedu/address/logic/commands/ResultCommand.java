package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESULT;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.match.Match;

/**
 * Adds a match to the Match Record.
 */
public class ResultCommand extends Command {

    public static final String COMMAND_WORD = "result";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a match to the Match Record. "
            + "Parameters: "
            + PREFIX_RESULT + "RESULT (must be one of: WIN, LOSE, DRAW)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_RESULT + "WIN";

    public static final String MESSAGE_SUCCESS = "New match added: %1$s";

    private final Match toAdd;

    /**
     * Creates a ResultCommand to add the specified {@code Match}
     */
    public ResultCommand(Match match) {
        requireNonNull(match);
        toAdd = match;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.addMatch(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ResultCommand otherResultCommand)) {
            return false;
        }

        return toAdd.equals(otherResultCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }

}
