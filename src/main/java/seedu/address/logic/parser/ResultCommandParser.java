package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESULT;

import java.util.stream.Stream;

import seedu.address.logic.commands.ResultCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.match.Match;
import seedu.address.model.match.Result;

/**
 * Parses input arguments and creates a new ResultCommand object
 */
public class ResultCommandParser implements Parser<ResultCommand> {

    public ResultCommandParser() {}

    /**
     * Parses the given {@code String} of arguments in the context of the ResultCommand
     * and returns a ResultCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ResultCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RESULT);
        if (!arePrefixesPresent(argMultimap, PREFIX_RESULT) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResultCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_RESULT);
        Result result = ParserUtil.parseResult(argMultimap.getValue(PREFIX_RESULT).get());
        Match match = new Match(result);

        return new ResultCommand(match);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
