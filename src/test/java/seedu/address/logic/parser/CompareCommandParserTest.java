package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CompareCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the CompareCommand code. For example, inputs "1 2" and "1 2 abc" take the
 * same path through the CompareCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class CompareCommandParserTest {

    private CompareCommandParser parser = new CompareCommandParser();

    @Test
    public void parse_validArgs_returnsCompareCommand() {
        assertParseSuccess(parser, "1 2", new CompareCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON));
    }

    @Test
    public void parse_extraWhitespace_returnsCompareCommand() {
        assertParseSuccess(parser, "  1   2  ", new CompareCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON));
    }

    @Test
    public void parse_invalidArgCount_throwsParseException() {
        // Only one index provided
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompareCommand.PARAMETERS));

        // More than two indices provided
        assertParseFailure(parser, "1 2 3",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompareCommand.PARAMETERS));

        // No arguments provided
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompareCommand.PARAMETERS));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        // Non-numeric index
        assertParseFailure(parser, "a b",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompareCommand.PARAMETERS));

        // First index invalid, second valid
        assertParseFailure(parser, "a 2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompareCommand.PARAMETERS));

        // First index valid, second invalid
        assertParseFailure(parser, "1 b",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompareCommand.PARAMETERS));

        // Negative indices
        assertParseFailure(parser, "-1 2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompareCommand.PARAMETERS));

        assertParseFailure(parser, "1 -2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompareCommand.PARAMETERS));

        // Zero indices
        assertParseFailure(parser, "0 2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompareCommand.PARAMETERS));

        assertParseFailure(parser, "1 0",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompareCommand.PARAMETERS));
    }
}
