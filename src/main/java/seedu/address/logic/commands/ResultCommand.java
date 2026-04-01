package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSISTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEATHS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_KILLS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESULT;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.match.Match;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Adds a match to the Match Record.
 */
public class ResultCommand extends Command {

    public static final String COMMAND_WORD = "result";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a match to the Match Record. "
            + "Parameters: "
            + PREFIX_RESULT + "RESULT (must be one of: WIN, LOSE, DRAW) "
            + "[" + PREFIX_DATE + "yyyy-MM-dd'T'HH:mm:ss] "
            + PREFIX_NAME + "NAME (must exist in the list) "
            + PREFIX_KILLS + "KILLS "
            + PREFIX_DEATHS + "DEATHS "
            + PREFIX_ASSISTS + "ASSISTS\n"
            + "The number of names and number of statistics must match.\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_RESULT + "WIN "
            + PREFIX_NAME + "Harry " + PREFIX_KILLS + "20 " + PREFIX_DEATHS + "10 " + PREFIX_ASSISTS + "30 "
            + PREFIX_NAME + "Harried " + PREFIX_KILLS + "0 " + PREFIX_DEATHS + "500 " + PREFIX_ASSISTS + "0 ";

    public static final String MESSAGE_SUCCESS = "New match added: %1$s";
    public static final String MESSAGE_FIELD_QUANTITY_MISMATCH =
            "The number of values provided for each field do not match!";
    public static final String MESSAGE_PLAYER_DOES_NOT_EXIST =
            "One or more players does not exist in the address book: %1$s";

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

        try {
            model.addMatch(toAdd);
        } catch (PersonNotFoundException e) {
            return new CommandResult(String.format(MESSAGE_PLAYER_DOES_NOT_EXIST, Messages.format(toAdd)));
        }
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
