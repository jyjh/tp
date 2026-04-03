package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ASSISTS_DESC_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.ASSISTS_DESC_SET_2;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.DEATHS_DESC_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.DEATHS_DESC_SET_2;
import static seedu.address.logic.commands.CommandTestUtil.ENTITY_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.ENTITY_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.IGN_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.IGN_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_IGN_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RESULT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.KILLS_DESC_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.KILLS_DESC_SET_2;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.RESULT_DESC_LOSE;
import static seedu.address.logic.commands.CommandTestUtil.RESULT_DESC_WIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSISTS_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSISTS_SET_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEATHS_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEATHS_SET_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENTITY_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENTITY_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IGN_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IGN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_KILLS_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_KILLS_SET_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESULT_WIN;
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
import seedu.address.model.person.statistics.Assists;
import seedu.address.model.person.statistics.Deaths;
import seedu.address.model.person.statistics.Kills;
import seedu.address.model.person.statistics.Statistics;

public class ResultCommandParserTest {
    private final ResultCommandParser parser = new ResultCommandParser();
    private final LocalDate date = LocalDate.parse(VALID_DATE);

    @Test
    public void parse_allFieldsPresent_success() {
        Match expectedMatch = new Match(
                date, new Result(VALID_RESULT_WIN),
                new PlayersInMatch(
                        List.of(
                                new PlayerInMatch(new InGameName(VALID_IGN_AMY),
                                        new Statistics.Builder()
                                                .withAssists(new Assists(VALID_ASSISTS_SET_1))
                                                .withDeaths(new Deaths(VALID_DEATHS_SET_1))
                                                .withKills(new Kills(VALID_KILLS_SET_1))
                                        .build(),
                                VALID_ENTITY_1)
                        )
                ));

        Match expectedMatch2 = new Match(
                date, new Result(VALID_RESULT_WIN),
                new PlayersInMatch(
                        List.of(
                                new PlayerInMatch(new InGameName(VALID_IGN_AMY),
                                        new Statistics.Builder()
                                                .withAssists(new Assists(VALID_ASSISTS_SET_1))
                                                .withDeaths(new Deaths(VALID_DEATHS_SET_1))
                                                .withKills(new Kills(VALID_KILLS_SET_1)).build(),
                                VALID_ENTITY_1),
                                new PlayerInMatch(new InGameName(VALID_IGN_BOB),
                                        new Statistics.Builder()
                                                .withAssists(new Assists(VALID_ASSISTS_SET_2))
                                                .withDeaths(new Deaths(VALID_DEATHS_SET_2))
                                                .withKills(new Kills(VALID_KILLS_SET_2)).build(),
                                VALID_ENTITY_2)
                        )
                ));

        // Two players involved in match
        // Arguments in order: result, ign_1, entity_1, ign_2, entity_2,
        // kills_1, kills_1, deaths_1, deaths_1, assists_1, assists_2
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + RESULT_DESC_WIN + IGN_DESC_AMY + ENTITY_DESC_1
                + IGN_DESC_BOB + ENTITY_DESC_2
                + KILLS_DESC_SET_1 + KILLS_DESC_SET_2 + DEATHS_DESC_SET_1 + DEATHS_DESC_SET_2
                + ASSISTS_DESC_SET_1 + ASSISTS_DESC_SET_2 + DATE_DESC,
                new ResultCommand(expectedMatch2));

        // Two players involved in match
        // Arguments in order: result, ign_1, entity_1, kills_1, deaths_1,
        // assists_1, ign_2, entity_2, kills_2, deaths_2, assists_2
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + RESULT_DESC_WIN + IGN_DESC_AMY + ENTITY_DESC_1
                + KILLS_DESC_SET_1 + DEATHS_DESC_SET_1 + ASSISTS_DESC_SET_1 + IGN_DESC_BOB + ENTITY_DESC_2
                + KILLS_DESC_SET_2 + DEATHS_DESC_SET_2 + ASSISTS_DESC_SET_2 + DATE_DESC,
                new ResultCommand(expectedMatch2));

        // One player involved in the match
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + RESULT_DESC_WIN + IGN_DESC_AMY + ENTITY_DESC_1
                + KILLS_DESC_SET_1 + DEATHS_DESC_SET_1 + ASSISTS_DESC_SET_1 + DATE_DESC,
                new ResultCommand(expectedMatch));
    }

    @Test
    public void parse_noIgnAndStatsDoNotMatch_failure() {

        // Two players but only one statistic
        String twoPlayersOneStatistic = PREAMBLE_WHITESPACE + RESULT_DESC_WIN + IGN_DESC_AMY + ENTITY_DESC_1
                + IGN_DESC_BOB + ENTITY_DESC_2
                + KILLS_DESC_SET_1 + DEATHS_DESC_SET_1 + ASSISTS_DESC_SET_1 + DATE_DESC;
        assertParseFailure(parser, twoPlayersOneStatistic, MESSAGE_FIELD_QUANTITY_MISMATCH);

        // One player but two statistic
        String onePlayerTwoStatistics = PREAMBLE_WHITESPACE + RESULT_DESC_WIN + IGN_DESC_BOB + ENTITY_DESC_1
                + KILLS_DESC_SET_1 + KILLS_DESC_SET_2 + DEATHS_DESC_SET_1 + DEATHS_DESC_SET_2
                + ASSISTS_DESC_SET_1 + ASSISTS_DESC_SET_2 + DATE_DESC;
        assertParseFailure(parser, onePlayerTwoStatistics, MESSAGE_FIELD_QUANTITY_MISMATCH);

        // Two players but only one statistic and one partial statistic
        String twoPlayerPartialStatistics = PREAMBLE_WHITESPACE + RESULT_DESC_WIN + IGN_DESC_AMY + ENTITY_DESC_1
                + IGN_DESC_BOB + ENTITY_DESC_2
                + KILLS_DESC_SET_1 + KILLS_DESC_SET_2 + DEATHS_DESC_SET_1 + DEATHS_DESC_SET_2
                + ASSISTS_DESC_SET_1 + DATE_DESC;
        assertParseFailure(parser, twoPlayerPartialStatistics, MESSAGE_FIELD_QUANTITY_MISMATCH);
    }

    @Test
    public void parse_repeatedResult_failure() {
        String validExpectedMatchString = PREAMBLE_WHITESPACE + RESULT_DESC_WIN + IGN_DESC_AMY + ENTITY_DESC_1
                + IGN_DESC_BOB + ENTITY_DESC_2
                + KILLS_DESC_SET_1 + KILLS_DESC_SET_2 + DEATHS_DESC_SET_1 + DEATHS_DESC_SET_2
                + ASSISTS_DESC_SET_1 + ASSISTS_DESC_SET_2 + DATE_DESC;

        assertParseFailure(parser, RESULT_DESC_LOSE + validExpectedMatchString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_RESULT));

    }

    @Test
    public void parse_duplicateIgn_failure() {
        String duplicateIgnString = PREAMBLE_WHITESPACE + RESULT_DESC_WIN + IGN_DESC_AMY + IGN_DESC_AMY
                + ENTITY_DESC_1 + ENTITY_DESC_2
                + KILLS_DESC_SET_1 + KILLS_DESC_SET_2 + DEATHS_DESC_SET_1 + DEATHS_DESC_SET_2
                + ASSISTS_DESC_SET_1 + ASSISTS_DESC_SET_2 + DATE_DESC;

        assertParseFailure(parser, duplicateIgnString, PlayersInMatch.MESSAGE_CONSTRAINTS);

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResultCommand.MESSAGE_USAGE);

        // missing result
        assertParseFailure(parser, PREAMBLE_WHITESPACE + IGN_DESC_AMY + ENTITY_DESC_1 + KILLS_DESC_SET_1
                + DEATHS_DESC_SET_1 + ASSISTS_DESC_SET_1 + DATE_DESC,
                expectedMessage);

        // missing name
        assertParseFailure(parser, PREAMBLE_WHITESPACE + RESULT_DESC_WIN + KILLS_DESC_SET_1
                + DEATHS_DESC_SET_1 + ASSISTS_DESC_SET_1 + DATE_DESC,
                expectedMessage);

        // missing entity
        assertParseFailure(parser, PREAMBLE_WHITESPACE + RESULT_DESC_WIN + IGN_DESC_AMY + KILLS_DESC_SET_1
                + DEATHS_DESC_SET_1 + ASSISTS_DESC_SET_1 + DATE_DESC,
                expectedMessage);

        // missing statistics
        assertParseFailure(parser, PREAMBLE_WHITESPACE + RESULT_DESC_WIN
                + IGN_DESC_AMY + ENTITY_DESC_1 + DATE_DESC,
                expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid result
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INVALID_RESULT_DESC + IGN_DESC_AMY + ENTITY_DESC_1
                 + KILLS_DESC_SET_1 + DEATHS_DESC_SET_1 + ASSISTS_DESC_SET_1 + DATE_DESC,
                Result.MESSAGE_CONSTRAINTS);

        // invalid name
        assertParseFailure(parser, PREAMBLE_WHITESPACE + RESULT_DESC_WIN + INVALID_IGN_DESC + ENTITY_DESC_1
                + KILLS_DESC_SET_1 + DEATHS_DESC_SET_1 + ASSISTS_DESC_SET_1 + DATE_DESC,
                InGameName.MESSAGE_CONSTRAINTS);

        // invalid date
        assertThrows(ParseException.class, () ->
                parser.parse(PREAMBLE_WHITESPACE + RESULT_DESC_WIN + INVALID_IGN_DESC + ENTITY_DESC_1
                    + KILLS_DESC_SET_1 + DEATHS_DESC_SET_1 + ASSISTS_DESC_SET_1 + DATE_DESC));

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, PREAMBLE_WHITESPACE + INVALID_RESULT_DESC + INVALID_IGN_DESC + ENTITY_DESC_1
                        + KILLS_DESC_SET_1 + DEATHS_DESC_SET_1 + ASSISTS_DESC_SET_1 + DATE_DESC,
                Result.MESSAGE_CONSTRAINTS);

    }

}
