package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.ResultCommand.MESSAGE_FIELD_QUANTITY_MISMATCH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSISTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEATHS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_KILLS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESULT;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.ResultCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityReference;
import seedu.address.model.match.Match;
import seedu.address.model.match.PlayerInMatch;
import seedu.address.model.match.Result;
import seedu.address.model.person.Name;
import seedu.address.model.person.statistics.Assists;
import seedu.address.model.person.statistics.Deaths;
import seedu.address.model.person.statistics.Kills;
import seedu.address.model.person.statistics.Statistics;

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
                ArgumentTokenizer.tokenize(args, PREFIX_RESULT, PREFIX_DATE, PREFIX_NAME, PREFIX_ENTITY,
                    PREFIX_KILLS, PREFIX_DEATHS, PREFIX_ASSISTS);
        if (!arePrefixesPresent(argMultimap, PREFIX_RESULT, PREFIX_NAME, PREFIX_ENTITY, PREFIX_KILLS,
                PREFIX_DEATHS, PREFIX_ASSISTS) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResultCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_RESULT);
        Result result = ParserUtil.parseResult(argMultimap.getValue(PREFIX_RESULT).get());
        List<String> argNames = argMultimap.getAllValues(PREFIX_NAME);
        List<String> argEntities = argMultimap.getAllValues(PREFIX_ENTITY);
        List<String> argKills = argMultimap.getAllValues(PREFIX_KILLS);
        List<String> argDeaths = argMultimap.getAllValues(PREFIX_DEATHS);
        List<String> argAssists = argMultimap.getAllValues(PREFIX_ASSISTS);
        int noPlayers = argNames.size();

        if (noPlayers != argEntities.size() || noPlayers != argKills.size()
                || noPlayers != argDeaths.size() || noPlayers != argAssists.size()) {
            throw new ParseException(String.format(MESSAGE_FIELD_QUANTITY_MISMATCH));
        }
        List<PlayerInMatch> players = new ArrayList<>(noPlayers);
        for (int i = 0; i < noPlayers; i++) {
            Name name = ParserUtil.parseName(argNames.get(i));
            String entityName = argEntities.get(i);
            // Validate that entity exists
            Entity entity = EntityReference.findByName(entityName)
                    .orElseThrow(() -> new ParseException(
                            String.format("Entity '%s' does not exist in the entity list.", entityName)));

            Kills kills = ParserUtil.parseKills(argKills.get(i));
            Deaths deaths = ParserUtil.parseDeaths(argDeaths.get(i));
            Assists assists = ParserUtil.parseAssists(argAssists.get(i));

            Statistics statistics = new Statistics.Builder()
                    .withKills(kills)
                    .withDeaths(deaths)
                    .withAssists(assists)
                    .build();
            players.add(new PlayerInMatch(name, statistics, entity));
        }

        Match match;

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            try {
                LocalDateTime date = LocalDateTime.parse(argMultimap.getValue(PREFIX_DATE).get());
                match = new Match(date, result, players);
            } catch (DateTimeParseException e) {
                throw new ParseException(e.getMessage());
            }
        } else {
            match = new Match(result, players);
        }

        return new ResultCommand(match);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
