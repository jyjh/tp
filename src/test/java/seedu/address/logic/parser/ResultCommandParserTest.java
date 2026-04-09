package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ASSISTS_DESC_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.ASSISTS_DESC_SET_2;
import static seedu.address.logic.commands.CommandTestUtil.ASSISTS_DESC_SET_3;
import static seedu.address.logic.commands.CommandTestUtil.ASSISTS_DESC_SET_4;
import static seedu.address.logic.commands.CommandTestUtil.ASSISTS_DESC_SET_5;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.DEATHS_DESC_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.DEATHS_DESC_SET_2;
import static seedu.address.logic.commands.CommandTestUtil.DEATHS_DESC_SET_3;
import static seedu.address.logic.commands.CommandTestUtil.DEATHS_DESC_SET_4;
import static seedu.address.logic.commands.CommandTestUtil.DEATHS_DESC_SET_5;
import static seedu.address.logic.commands.CommandTestUtil.ENTITY_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.ENTITY_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.ENTITY_DESC_3;
import static seedu.address.logic.commands.CommandTestUtil.ENTITY_DESC_4;
import static seedu.address.logic.commands.CommandTestUtil.ENTITY_DESC_5;
import static seedu.address.logic.commands.CommandTestUtil.IGN_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.IGN_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.IGN_DESC_CAT;
import static seedu.address.logic.commands.CommandTestUtil.IGN_DESC_DOG;
import static seedu.address.logic.commands.CommandTestUtil.IGN_DESC_EMA;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_IGN_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RESULT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.KILLS_DESC_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.KILLS_DESC_SET_2;
import static seedu.address.logic.commands.CommandTestUtil.KILLS_DESC_SET_3;
import static seedu.address.logic.commands.CommandTestUtil.KILLS_DESC_SET_4;
import static seedu.address.logic.commands.CommandTestUtil.KILLS_DESC_SET_5;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.RESULT_DESC_LOSE;
import static seedu.address.logic.commands.CommandTestUtil.RESULT_DESC_WIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENTITY_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENTITY_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENTITY_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENTITY_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENTITY_5;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IGN_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IGN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IGN_CAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IGN_DOG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IGN_EMA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESULT_WIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATS_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATS_SET_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATS_SET_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATS_SET_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATS_SET_5;
import static seedu.address.logic.commands.ResultCommand.MESSAGE_FIELD_QUANTITY_MISMATCH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESULT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ResultCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.match.Match;
import seedu.address.model.match.PlayerInMatch;
import seedu.address.model.match.PlayersInMatch;
import seedu.address.model.match.Result;
import seedu.address.model.person.InGameName;

public class ResultCommandParserTest {
    private final ResultCommandParser parser = new ResultCommandParser();
    private final LocalDate date = LocalDate.parse(VALID_DATE);

    @Test
    public void parse_allFieldsPresent_success() {
        Match expectedMatch = new Match(
                date,
                new Result(VALID_RESULT_WIN),
                new PlayersInMatch(List.of(
                        new PlayerInMatch(new InGameName(VALID_IGN_AMY), VALID_STATS_SET_1, VALID_ENTITY_1),
                        new PlayerInMatch(new InGameName(VALID_IGN_BOB), VALID_STATS_SET_2, VALID_ENTITY_2),
                        new PlayerInMatch(new InGameName(VALID_IGN_CAT), VALID_STATS_SET_3, VALID_ENTITY_3),
                        new PlayerInMatch(new InGameName(VALID_IGN_DOG), VALID_STATS_SET_4, VALID_ENTITY_4),
                        new PlayerInMatch(new InGameName(VALID_IGN_EMA), VALID_STATS_SET_5, VALID_ENTITY_5))
                )
        );

        // Arguments in order: result, ign_1, ign_2, ..., ign_5, entity_1, entity_2, ..., entity_5,
        // kills_1, kills_2, ..., kills_5, deaths_1, deaths_2, ..., deaths_5, assists_1, assists_2, ..., assists_5
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + RESULT_DESC_WIN
                + IGN_DESC_AMY + IGN_DESC_BOB + IGN_DESC_CAT + IGN_DESC_DOG + IGN_DESC_EMA
                + ENTITY_DESC_1 + ENTITY_DESC_2 + ENTITY_DESC_3 + ENTITY_DESC_4 + ENTITY_DESC_5
                + KILLS_DESC_SET_1 + KILLS_DESC_SET_2 + KILLS_DESC_SET_3 + KILLS_DESC_SET_4 + KILLS_DESC_SET_5
                + DEATHS_DESC_SET_1 + DEATHS_DESC_SET_2 + DEATHS_DESC_SET_3 + DEATHS_DESC_SET_4 + DEATHS_DESC_SET_5
                + ASSISTS_DESC_SET_1 + ASSISTS_DESC_SET_2 + ASSISTS_DESC_SET_3 + ASSISTS_DESC_SET_4 + ASSISTS_DESC_SET_5
                + DATE_DESC,
                new ResultCommand(expectedMatch));

        // Arguments in order: result, ign_i, entity_i, kills_i, deaths_i, assists_i
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + RESULT_DESC_WIN
                + IGN_DESC_AMY + ENTITY_DESC_1 + KILLS_DESC_SET_1 + DEATHS_DESC_SET_1 + ASSISTS_DESC_SET_1
                + IGN_DESC_BOB + ENTITY_DESC_2 + KILLS_DESC_SET_2 + DEATHS_DESC_SET_2 + ASSISTS_DESC_SET_2
                + IGN_DESC_CAT + ENTITY_DESC_3 + KILLS_DESC_SET_3 + DEATHS_DESC_SET_3 + ASSISTS_DESC_SET_3
                + IGN_DESC_DOG + ENTITY_DESC_4 + KILLS_DESC_SET_4 + DEATHS_DESC_SET_4 + ASSISTS_DESC_SET_4
                + IGN_DESC_EMA + ENTITY_DESC_5 + KILLS_DESC_SET_5 + DEATHS_DESC_SET_5 + ASSISTS_DESC_SET_5
                + DATE_DESC,
                new ResultCommand(expectedMatch));


    }

    @Test
    public void parse_noIgnAndStatsDoNotMatch_failure() {

        // Kills missing
        String oneKillMissing = PREAMBLE_WHITESPACE + RESULT_DESC_WIN
                + IGN_DESC_AMY + IGN_DESC_BOB + IGN_DESC_CAT + IGN_DESC_DOG + IGN_DESC_EMA
                + ENTITY_DESC_1 + ENTITY_DESC_2 + ENTITY_DESC_3 + ENTITY_DESC_4 + ENTITY_DESC_5
                + KILLS_DESC_SET_1 + KILLS_DESC_SET_2 + KILLS_DESC_SET_3 + KILLS_DESC_SET_4
                + DEATHS_DESC_SET_1 + DEATHS_DESC_SET_2 + DEATHS_DESC_SET_3 + DEATHS_DESC_SET_4 + DEATHS_DESC_SET_5
                + ASSISTS_DESC_SET_1 + ASSISTS_DESC_SET_2 + ASSISTS_DESC_SET_3 + ASSISTS_DESC_SET_4 + ASSISTS_DESC_SET_5
                + DATE_DESC;
        assertParseFailure(parser, oneKillMissing, MESSAGE_FIELD_QUANTITY_MISMATCH);

        String killsMissing = PREAMBLE_WHITESPACE + RESULT_DESC_WIN
                + IGN_DESC_AMY + IGN_DESC_BOB + IGN_DESC_CAT + IGN_DESC_DOG + IGN_DESC_EMA
                + ENTITY_DESC_1 + ENTITY_DESC_2 + ENTITY_DESC_3 + ENTITY_DESC_4 + ENTITY_DESC_5 + KILLS_DESC_SET_1
                + DEATHS_DESC_SET_1 + DEATHS_DESC_SET_2 + DEATHS_DESC_SET_3 + DEATHS_DESC_SET_4 + DEATHS_DESC_SET_5
                + ASSISTS_DESC_SET_1 + ASSISTS_DESC_SET_2 + ASSISTS_DESC_SET_3 + ASSISTS_DESC_SET_4 + ASSISTS_DESC_SET_5
                + DATE_DESC;
        assertParseFailure(parser, killsMissing, MESSAGE_FIELD_QUANTITY_MISMATCH);

        // Deaths missing
        String oneDeathMissing = PREAMBLE_WHITESPACE + RESULT_DESC_WIN
                + IGN_DESC_AMY + IGN_DESC_BOB + IGN_DESC_CAT + IGN_DESC_DOG + IGN_DESC_EMA
                + ENTITY_DESC_1 + ENTITY_DESC_2 + ENTITY_DESC_3 + ENTITY_DESC_4 + ENTITY_DESC_5
                + KILLS_DESC_SET_1 + KILLS_DESC_SET_2 + KILLS_DESC_SET_3 + KILLS_DESC_SET_4 + KILLS_DESC_SET_5
                + DEATHS_DESC_SET_1 + DEATHS_DESC_SET_2 + DEATHS_DESC_SET_3 + DEATHS_DESC_SET_5
                + ASSISTS_DESC_SET_1 + ASSISTS_DESC_SET_2 + ASSISTS_DESC_SET_3 + ASSISTS_DESC_SET_4 + ASSISTS_DESC_SET_5
                + DATE_DESC;
        assertParseFailure(parser, oneDeathMissing, MESSAGE_FIELD_QUANTITY_MISMATCH);

        String deathsMissing = PREAMBLE_WHITESPACE + RESULT_DESC_WIN
                + IGN_DESC_AMY + IGN_DESC_BOB + IGN_DESC_CAT + IGN_DESC_DOG + IGN_DESC_EMA
                + ENTITY_DESC_1 + ENTITY_DESC_2 + ENTITY_DESC_3 + ENTITY_DESC_4 + ENTITY_DESC_5 + DEATHS_DESC_SET_1
                + KILLS_DESC_SET_1 + KILLS_DESC_SET_2 + KILLS_DESC_SET_3 + KILLS_DESC_SET_4 + KILLS_DESC_SET_5
                + ASSISTS_DESC_SET_1 + ASSISTS_DESC_SET_2 + ASSISTS_DESC_SET_3 + ASSISTS_DESC_SET_4 + ASSISTS_DESC_SET_5
                + DATE_DESC;
        assertParseFailure(parser, deathsMissing, MESSAGE_FIELD_QUANTITY_MISMATCH);

        // Assists missing
        String oneAssistMissing = PREAMBLE_WHITESPACE + RESULT_DESC_WIN
                + IGN_DESC_AMY + IGN_DESC_BOB + IGN_DESC_CAT + IGN_DESC_DOG + IGN_DESC_EMA
                + ENTITY_DESC_1 + ENTITY_DESC_2 + ENTITY_DESC_3 + ENTITY_DESC_4 + ENTITY_DESC_5
                + KILLS_DESC_SET_1 + KILLS_DESC_SET_2 + KILLS_DESC_SET_3 + KILLS_DESC_SET_4 + KILLS_DESC_SET_5
                + DEATHS_DESC_SET_1 + DEATHS_DESC_SET_2 + DEATHS_DESC_SET_3 + DEATHS_DESC_SET_4 + DEATHS_DESC_SET_5
                + ASSISTS_DESC_SET_1 + ASSISTS_DESC_SET_2 + ASSISTS_DESC_SET_4 + ASSISTS_DESC_SET_5
                + DATE_DESC;
        assertParseFailure(parser, oneAssistMissing, MESSAGE_FIELD_QUANTITY_MISMATCH);

        String assistsMissing = PREAMBLE_WHITESPACE + RESULT_DESC_WIN
                + IGN_DESC_AMY + IGN_DESC_BOB + IGN_DESC_CAT + IGN_DESC_DOG + IGN_DESC_EMA
                + ENTITY_DESC_1 + ENTITY_DESC_2 + ENTITY_DESC_3 + ENTITY_DESC_4 + ENTITY_DESC_5 + ASSISTS_DESC_SET_1
                + KILLS_DESC_SET_1 + KILLS_DESC_SET_2 + KILLS_DESC_SET_3 + KILLS_DESC_SET_4 + KILLS_DESC_SET_5
                + DEATHS_DESC_SET_1 + DEATHS_DESC_SET_2 + DEATHS_DESC_SET_3 + DEATHS_DESC_SET_4 + DEATHS_DESC_SET_5
                + DATE_DESC;
        assertParseFailure(parser, assistsMissing, MESSAGE_FIELD_QUANTITY_MISMATCH);
    }

    @Test
    public void parse_repeatedResult_failure() {
        String validExpectedMatchString = PREAMBLE_WHITESPACE + RESULT_DESC_WIN
                + IGN_DESC_AMY + ENTITY_DESC_1 + KILLS_DESC_SET_1 + DEATHS_DESC_SET_1 + ASSISTS_DESC_SET_1
                + IGN_DESC_BOB + ENTITY_DESC_2 + KILLS_DESC_SET_2 + DEATHS_DESC_SET_2 + ASSISTS_DESC_SET_2
                + IGN_DESC_CAT + ENTITY_DESC_3 + KILLS_DESC_SET_3 + DEATHS_DESC_SET_3 + ASSISTS_DESC_SET_3
                + IGN_DESC_DOG + ENTITY_DESC_4 + KILLS_DESC_SET_4 + DEATHS_DESC_SET_4 + ASSISTS_DESC_SET_4
                + IGN_DESC_EMA + ENTITY_DESC_5 + KILLS_DESC_SET_5 + DEATHS_DESC_SET_5 + ASSISTS_DESC_SET_5
                + DATE_DESC;

        assertParseFailure(parser, RESULT_DESC_LOSE + validExpectedMatchString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_RESULT));

    }

    @Test
    public void parse_duplicateIgn_failure() {
        String duplicateIgnString = PREAMBLE_WHITESPACE + RESULT_DESC_WIN
                + IGN_DESC_AMY + ENTITY_DESC_1 + KILLS_DESC_SET_1 + DEATHS_DESC_SET_1 + ASSISTS_DESC_SET_1
                + IGN_DESC_BOB + ENTITY_DESC_2 + KILLS_DESC_SET_2 + DEATHS_DESC_SET_2 + ASSISTS_DESC_SET_2
                + IGN_DESC_CAT + ENTITY_DESC_3 + KILLS_DESC_SET_3 + DEATHS_DESC_SET_3 + ASSISTS_DESC_SET_3
                + IGN_DESC_DOG + ENTITY_DESC_4 + KILLS_DESC_SET_4 + DEATHS_DESC_SET_4 + ASSISTS_DESC_SET_4
                + IGN_DESC_AMY + ENTITY_DESC_5 + KILLS_DESC_SET_5 + DEATHS_DESC_SET_5 + ASSISTS_DESC_SET_5
                + DATE_DESC;

        assertParseFailure(parser, duplicateIgnString, PlayersInMatch.MESSAGE_CONSTRAINTS);

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResultCommand.PARAMETERS);

        // missing result
        assertParseFailure(parser, PREAMBLE_WHITESPACE
                        + IGN_DESC_AMY + ENTITY_DESC_1 + KILLS_DESC_SET_1 + DEATHS_DESC_SET_1 + ASSISTS_DESC_SET_1
                        + IGN_DESC_BOB + ENTITY_DESC_2 + KILLS_DESC_SET_2 + DEATHS_DESC_SET_2 + ASSISTS_DESC_SET_2
                        + IGN_DESC_CAT + ENTITY_DESC_3 + KILLS_DESC_SET_3 + DEATHS_DESC_SET_3 + ASSISTS_DESC_SET_3
                        + IGN_DESC_DOG + ENTITY_DESC_4 + KILLS_DESC_SET_4 + DEATHS_DESC_SET_4 + ASSISTS_DESC_SET_4
                        + IGN_DESC_EMA + ENTITY_DESC_5 + KILLS_DESC_SET_5 + DEATHS_DESC_SET_5 + ASSISTS_DESC_SET_5
                        + DATE_DESC,
                expectedMessage);

        // missing name
        assertParseFailure(parser, PREAMBLE_WHITESPACE + RESULT_DESC_WIN
                        + ENTITY_DESC_1 + KILLS_DESC_SET_1 + DEATHS_DESC_SET_1 + ASSISTS_DESC_SET_1
                        + ENTITY_DESC_2 + KILLS_DESC_SET_2 + DEATHS_DESC_SET_2 + ASSISTS_DESC_SET_2
                        + ENTITY_DESC_3 + KILLS_DESC_SET_3 + DEATHS_DESC_SET_3 + ASSISTS_DESC_SET_3
                        + ENTITY_DESC_4 + KILLS_DESC_SET_4 + DEATHS_DESC_SET_4 + ASSISTS_DESC_SET_4
                        + ENTITY_DESC_5 + KILLS_DESC_SET_5 + DEATHS_DESC_SET_5 + ASSISTS_DESC_SET_5
                        + DATE_DESC,
                expectedMessage);

        // missing entity
        assertParseFailure(parser, PREAMBLE_WHITESPACE + RESULT_DESC_WIN
                        + IGN_DESC_AMY + KILLS_DESC_SET_1 + DEATHS_DESC_SET_1 + ASSISTS_DESC_SET_1
                        + IGN_DESC_BOB + KILLS_DESC_SET_2 + DEATHS_DESC_SET_2 + ASSISTS_DESC_SET_2
                        + IGN_DESC_CAT + KILLS_DESC_SET_3 + DEATHS_DESC_SET_3 + ASSISTS_DESC_SET_3
                        + IGN_DESC_DOG + KILLS_DESC_SET_4 + DEATHS_DESC_SET_4 + ASSISTS_DESC_SET_4
                        + IGN_DESC_EMA + KILLS_DESC_SET_5 + DEATHS_DESC_SET_5 + ASSISTS_DESC_SET_5
                        + DATE_DESC,
                expectedMessage);

        // missing statistics
        assertParseFailure(parser, PREAMBLE_WHITESPACE + RESULT_DESC_WIN
                        + IGN_DESC_AMY + ENTITY_DESC_1
                        + IGN_DESC_BOB + ENTITY_DESC_2
                        + IGN_DESC_CAT + ENTITY_DESC_3
                        + IGN_DESC_DOG + ENTITY_DESC_4
                        + IGN_DESC_EMA + ENTITY_DESC_5,
                expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid result
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INVALID_RESULT_DESC
                        + IGN_DESC_AMY + ENTITY_DESC_1 + KILLS_DESC_SET_1 + DEATHS_DESC_SET_1 + ASSISTS_DESC_SET_1
                        + IGN_DESC_BOB + ENTITY_DESC_2 + KILLS_DESC_SET_2 + DEATHS_DESC_SET_2 + ASSISTS_DESC_SET_2
                        + IGN_DESC_CAT + ENTITY_DESC_3 + KILLS_DESC_SET_3 + DEATHS_DESC_SET_3 + ASSISTS_DESC_SET_3
                        + IGN_DESC_DOG + ENTITY_DESC_4 + KILLS_DESC_SET_4 + DEATHS_DESC_SET_4 + ASSISTS_DESC_SET_4
                        + IGN_DESC_EMA + ENTITY_DESC_5 + KILLS_DESC_SET_5 + DEATHS_DESC_SET_5 + ASSISTS_DESC_SET_5
                        + DATE_DESC,
                Result.MESSAGE_CONSTRAINTS);

        // invalid name
        assertParseFailure(parser, PREAMBLE_WHITESPACE + RESULT_DESC_WIN
                        + INVALID_IGN_DESC + ENTITY_DESC_1 + KILLS_DESC_SET_1 + DEATHS_DESC_SET_1 + ASSISTS_DESC_SET_1
                        + IGN_DESC_BOB + ENTITY_DESC_2 + KILLS_DESC_SET_2 + DEATHS_DESC_SET_2 + ASSISTS_DESC_SET_2
                        + IGN_DESC_CAT + ENTITY_DESC_3 + KILLS_DESC_SET_3 + DEATHS_DESC_SET_3 + ASSISTS_DESC_SET_3
                        + IGN_DESC_DOG + ENTITY_DESC_4 + KILLS_DESC_SET_4 + DEATHS_DESC_SET_4 + ASSISTS_DESC_SET_4
                        + IGN_DESC_EMA + ENTITY_DESC_5 + KILLS_DESC_SET_5 + DEATHS_DESC_SET_5 + ASSISTS_DESC_SET_5
                        + DATE_DESC,
                InGameName.MESSAGE_CONSTRAINTS);

        // invalid date
        assertThrows(ParseException.class, () ->
                parser.parse(PREAMBLE_WHITESPACE + RESULT_DESC_WIN
                        + IGN_DESC_AMY + ENTITY_DESC_1 + KILLS_DESC_SET_1 + DEATHS_DESC_SET_1 + ASSISTS_DESC_SET_1
                        + IGN_DESC_BOB + ENTITY_DESC_2 + KILLS_DESC_SET_2 + DEATHS_DESC_SET_2 + ASSISTS_DESC_SET_2
                        + IGN_DESC_CAT + ENTITY_DESC_3 + KILLS_DESC_SET_3 + DEATHS_DESC_SET_3 + ASSISTS_DESC_SET_3
                        + IGN_DESC_DOG + ENTITY_DESC_4 + KILLS_DESC_SET_4 + DEATHS_DESC_SET_4 + ASSISTS_DESC_SET_4
                        + IGN_DESC_EMA + ENTITY_DESC_5 + KILLS_DESC_SET_5 + DEATHS_DESC_SET_5 + ASSISTS_DESC_SET_5
                        + INVALID_DATE_DESC));

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INVALID_RESULT_DESC
                        + IGN_DESC_AMY + ENTITY_DESC_1 + KILLS_DESC_SET_1 + DEATHS_DESC_SET_1 + ASSISTS_DESC_SET_1
                        + IGN_DESC_BOB + ENTITY_DESC_2 + KILLS_DESC_SET_2 + DEATHS_DESC_SET_2 + ASSISTS_DESC_SET_2
                        + IGN_DESC_CAT + ENTITY_DESC_3 + KILLS_DESC_SET_3 + DEATHS_DESC_SET_3 + ASSISTS_DESC_SET_3
                        + IGN_DESC_DOG + ENTITY_DESC_4 + KILLS_DESC_SET_4 + DEATHS_DESC_SET_4 + ASSISTS_DESC_SET_4
                        + IGN_DESC_EMA + ENTITY_DESC_5 + KILLS_DESC_SET_5 + DEATHS_DESC_SET_5 + ASSISTS_DESC_SET_5
                        + DATE_DESC,
                Result.MESSAGE_CONSTRAINTS);

    }

}
