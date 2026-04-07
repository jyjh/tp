package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.DraftCommand.MESSAGE_INVALID_IGN_EMPTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IGN;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DraftCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DraftCommand object.
 */
public class DraftCommandParser implements Parser<DraftCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DraftCommand
     * and returns a DraftCommand object for execution.
     *
     * @param args the arguments string containing space-separated indices and/or IGN arguments
     * @return a DraftCommand object
     * @throws ParseException if the user input does not conform the expected format
     */
    public DraftCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DraftCommand.PARAMETERS));
        }

        String[] argStrings = trimmedArgs.split("\\s+");
        List<Index> indices = new ArrayList<>();
        List<String> igns = new ArrayList<>();
        String prefix = PREFIX_IGN.getPrefix();

        for (String argString : argStrings) {
            if (argString.startsWith(prefix)) {
                String ign = argString.substring(prefix.length());
                if (ign.isEmpty()) {
                    throw new ParseException(MESSAGE_INVALID_IGN_EMPTY);
                }
                igns.add(ign);
            } else if (!argString.matches("\\d+")) {
                igns.add(argString);
            } else {
                Index index = ParserUtil.parseIndex(argString);
                indices.add(index);
            }
        }

        return new DraftCommand(indices, igns);
    }
}
