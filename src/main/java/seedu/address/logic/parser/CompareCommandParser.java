package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CompareCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CompareCommand object
 */
public class CompareCommandParser implements Parser<CompareCommand> {

    public CompareCommandParser() {}

    /**
     * Parses the given {@code String} of arguments in the context of the CompareCommand
     * and returns a CompareCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CompareCommand parse(String args) throws ParseException {
        try {
            List<String> indexStrings = Arrays.asList(args.trim().split("\\s+"));
            if (indexStrings.size() != 2) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompareCommand.PARAMETERS));
            }

            Index index1 = ParserUtil.parseIndex(indexStrings.get(0));
            Index index2 = ParserUtil.parseIndex(indexStrings.get(1));
            return new CompareCommand(index1, index2);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompareCommand.PARAMETERS), pe);
        }
    }

}
