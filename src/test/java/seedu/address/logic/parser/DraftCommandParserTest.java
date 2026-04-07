package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.DraftCommand.MESSAGE_INVALID_IGN_EMPTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IGN;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DraftCommand;

public class DraftCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DraftCommand.PARAMETERS);

    private DraftCommandParser parser = new DraftCommandParser();

    /**
     * Builds a command string with IGN arguments from the given IGN strings.
     * Prefixes each IGN with PREFIX_IGN.
     */
    private String buildIgnCommand(String... igns) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < igns.length; i++) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(PREFIX_IGN.getPrefix()).append(igns[i]);
        }
        return sb.toString();
    }

    @Test
    public void parse_validIndexArgs_success() {
        DraftCommand expectedCommand = new DraftCommand(List.of(
                INDEX_FIRST_PERSON,
                INDEX_SECOND_PERSON,
                INDEX_THIRD_PERSON,
                INDEX_FOURTH_PERSON,
                INDEX_FIFTH_PERSON), List.of());

        assertParseSuccess(parser, "1 2 3 4 5", expectedCommand);
        assertParseSuccess(parser, "  1   2  3  4   5  ", expectedCommand);
    }

    @Test
    public void parse_validIgnArgs_success() {
        DraftCommand expectedCommand = new DraftCommand(List.of(),
                List.of("AliceP99", "BensonM88", "CarlK77", "DanielM66", "ElleM55"));

        assertParseSuccess(parser, buildIgnCommand("AliceP99", "BensonM88", "CarlK77", "DanielM66", "ElleM55"),
                expectedCommand);
    }

    @Test
    public void parse_validHybridArgs_success() {
        DraftCommand expectedCommand = new DraftCommand(List.of(
                INDEX_FIRST_PERSON,
                INDEX_THIRD_PERSON),
                List.of("BensonM88", "DanielM66", "ElleM55"));

        assertParseSuccess(parser,
                "1 " + buildIgnCommand("BensonM88", "DanielM66", "ElleM55") + " 3",
                expectedCommand);
    }

    @Test
    public void parse_missingArgs_failure() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "   ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, "0", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_emptyIgn_failure() {
        assertParseFailure(parser, PREFIX_IGN.getPrefix(), MESSAGE_INVALID_IGN_EMPTY);
    }
}
