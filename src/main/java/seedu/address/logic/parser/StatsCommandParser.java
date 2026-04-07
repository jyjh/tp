package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSISTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEATHS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_KILLS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.StatsCommand;
import seedu.address.logic.commands.StatsCommand.EditStatsDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entity.Entity;

/**
 * Parses input arguments and creates a new StatsCommand object
 */
public class StatsCommandParser implements Parser<StatsCommand> {

    public StatsCommandParser() {}

    /**
     * Parses the given {@code String} of arguments in the context of the StatsCommand
     * and returns a StatsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public StatsCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
            args, PREFIX_ENTITY, PREFIX_KILLS, PREFIX_DEATHS, PREFIX_ASSISTS);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.PARAMETERS), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ENTITY, PREFIX_KILLS, PREFIX_DEATHS, PREFIX_ASSISTS);

        EditStatsDescriptor editStatsDescriptor = new EditStatsDescriptor();

        Entity targetEntity = ParserUtil.parseEntity(argMultimap.getValue(PREFIX_ENTITY)
            .orElseThrow(() -> new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.PARAMETERS))));
        editStatsDescriptor.setEntity(targetEntity);


        if (argMultimap.getValue(PREFIX_KILLS).isPresent()) {
            editStatsDescriptor.setKills(ParserUtil.parseKills(argMultimap.getValue(PREFIX_KILLS).get()));
        }

        if (argMultimap.getValue(PREFIX_DEATHS).isPresent()) {
            editStatsDescriptor.setDeaths(ParserUtil.parseDeaths(argMultimap.getValue(PREFIX_DEATHS).get()));
        }

        if (argMultimap.getValue(PREFIX_ASSISTS).isPresent()) {
            editStatsDescriptor.setAssists(ParserUtil.parseAssists(argMultimap.getValue(PREFIX_ASSISTS).get()));
        }

        if (!editStatsDescriptor.isAnyFieldEdited()) {
            throw new ParseException(StatsCommand.MESSAGE_NOT_EDITED);
        }

        return new StatsCommand(index, editStatsDescriptor);
    }
}
