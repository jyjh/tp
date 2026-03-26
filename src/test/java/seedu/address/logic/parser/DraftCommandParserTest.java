package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.DraftCommand.MESSAGE_INVALID_IGN_EMPTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IGN;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DraftCommand;
import seedu.address.model.person.Person;

public class DraftCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DraftCommand.MESSAGE_USAGE);

    private DraftCommandParser parser = new DraftCommandParser();

    /**
     * Builds a command string with IGN arguments from the given persons.
     * Prefixes each IGN with PREFIX_IGN.
     */
    private String buildIgnCommand(Person... persons) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < persons.length; i++) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append(PREFIX_IGN.getPrefix()).append(persons[i].getIgn().value);
        }
        return sb.toString();
    }

    @Test
    public void parse_validIndexArgs_success() {
        DraftCommand expectedCommand = new DraftCommand(List.of(
                INDEX_FIRST_PERSON,
                INDEX_SECOND_PERSON,
                INDEX_THIRD_PERSON,
                Index.fromOneBased(4),
                Index.fromOneBased(5)), List.of());

        assertParseSuccess(parser, "1 2 3 4 5", expectedCommand);
        assertParseSuccess(parser, "  1   2  3  4   5  ", expectedCommand);
    }

    @Test
    public void parse_validIgnArgs_success() {
        DraftCommand expectedCommand = new DraftCommand(List.of(),
                List.of(ALICE.getIgn().value, BENSON.getIgn().value, CARL.getIgn().value,
                        DANIEL.getIgn().value, ELLE.getIgn().value));

        assertParseSuccess(parser, buildIgnCommand(ALICE, BENSON, CARL, DANIEL, ELLE), expectedCommand);
    }

    @Test
    public void parse_validHybridArgs_success() {
        DraftCommand expectedCommand = new DraftCommand(List.of(
                Index.fromOneBased(1),
                Index.fromOneBased(3)),
                List.of(BENSON.getIgn().value, DANIEL.getIgn().value, ELLE.getIgn().value));

        assertParseSuccess(parser, "1 " + buildIgnCommand(BENSON, DANIEL, ELLE) + " 3", expectedCommand);
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
