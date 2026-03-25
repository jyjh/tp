package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ASSISTS_DESC_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.DEATHS_DESC_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ASSISTS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEATHS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_KILLS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.KILLS_DESC_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.STATS_DESC_SET_1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.StatsCommand;
import seedu.address.model.person.statistics.Assists;
import seedu.address.model.person.statistics.Deaths;
import seedu.address.model.person.statistics.Kills;

public class StatsCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE);

    private StatsCommandParser parser = new StatsCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, KILLS_DESC_SET_1, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", StatsCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + KILLS_DESC_SET_1, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + KILLS_DESC_SET_1, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_KILLS_DESC, Kills.MESSAGE_CONSTRAINTS); // invalid kills
        assertParseFailure(parser, "1" + INVALID_DEATHS_DESC, Deaths.MESSAGE_CONSTRAINTS); // invalid deaths
        assertParseFailure(parser, "1" + INVALID_ASSISTS_DESC, Assists.MESSAGE_CONSTRAINTS); // invalid assists
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + KILLS_DESC_SET_1 + DEATHS_DESC_SET_1 + ASSISTS_DESC_SET_1;

        StatsCommand expectedCommand = new StatsCommand(targetIndex, STATS_DESC_SET_1);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
