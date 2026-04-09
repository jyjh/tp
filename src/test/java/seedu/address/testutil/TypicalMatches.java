package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ENTITY_1;

import java.time.LocalDate;
import java.util.List;

import seedu.address.model.MatchRecord;
import seedu.address.model.match.Match;
import seedu.address.model.match.PlayerInMatch;
import seedu.address.model.match.PlayersInMatch;
import seedu.address.model.match.Result;
import seedu.address.model.person.Person;
import seedu.address.model.person.statistics.Statistics;

/**
 * A utility class containing a list of {@code Match} objects to be used in tests.
 */
public class TypicalMatches {

    public static final List<Person> PERSONS = List.of(TypicalPersons.ALICE, TypicalPersons.BENSON,
            TypicalPersons.CARL, TypicalPersons.DANIEL, TypicalPersons.ELLE, TypicalPersons.FIONA,
            TypicalPersons.GEORGE, TypicalPersons.HOON, TypicalPersons.IDA
    );

    public static final List<Person> PERSONS_4 = List.of(TypicalPersons.ALICE, TypicalPersons.BENSON,
            TypicalPersons.CARL, TypicalPersons.DANIEL, TypicalPersons.ELLE);
    public static final List<Person> PERSONS_3 = List.of(TypicalPersons.BENSON,
            TypicalPersons.CARL, TypicalPersons.DANIEL, TypicalPersons.ELLE, TypicalPersons.FIONA);
    public static final List<Person> PERSONS_2 = List.of(TypicalPersons.CARL, TypicalPersons.DANIEL,
            TypicalPersons.ELLE, TypicalPersons.FIONA, TypicalPersons.GEORGE);
    public static final List<Person> PERSONS_1 = List.of(TypicalPersons.DANIEL, TypicalPersons.ELLE,
            TypicalPersons.FIONA, TypicalPersons.GEORGE, TypicalPersons.HOON);


    public static final PlayersInMatch PLAYERS_4 = new PlayersInMatch(
            PERSONS_4.stream()
                    .map(person ->
                            new PlayerInMatch(person.getIgn(),
                                    Statistics.createRandom(10, 10, 20),
                            VALID_ENTITY_1))
                    .toList());
    public static final PlayersInMatch PLAYERS_3 = new PlayersInMatch(
            PERSONS_3.stream()
                    .map(person ->
                            new PlayerInMatch(person.getIgn(),
                                    Statistics.createRandom(10, 10, 20),
                            VALID_ENTITY_1))
                    .toList()
    );
    public static final PlayersInMatch PLAYERS_2 = new PlayersInMatch(
            PERSONS_2.stream()
                    .map(person ->
                            new PlayerInMatch(person.getIgn(),
                                    Statistics.createRandom(10, 10, 20),
                            VALID_ENTITY_1))
                    .toList()
    );
    public static final PlayersInMatch PLAYERS_1 = new PlayersInMatch(
            PERSONS_1.stream()
                    .map(person ->
                            new PlayerInMatch(person.getIgn(),
                                    Statistics.createRandom(10, 10, 20),
                            VALID_ENTITY_1))
                    .toList()
    );

    public static final LocalDate DATE_1 = LocalDate.of(2025, 12, 1);
    public static final LocalDate DATE_2 = LocalDate.of(2025, 11, 2);
    public static final LocalDate DATE_3 = LocalDate.of(2025, 10, 3);
    public static final LocalDate DATE_4 = LocalDate.of(2025, 9, 4);

    public static final Match WINNING_MATCH_4 = new Match(DATE_4, new Result(Result.WinType.WIN), PLAYERS_4);
    public static final Match WINNING_MATCH_3 = new Match(DATE_3, new Result(Result.WinType.WIN), PLAYERS_3);
    public static final Match WINNING_MATCH_2 = new Match(DATE_2, new Result(Result.WinType.WIN), PLAYERS_2);
    public static final Match WINNING_MATCH_1 = new Match(DATE_1, new Result(Result.WinType.WIN), PLAYERS_1);
    public static final Match LOSING_MATCH_4 = new Match(DATE_4, new Result(Result.WinType.LOSE), PLAYERS_4);
    public static final Match LOSING_MATCH_3 = new Match(DATE_3, new Result(Result.WinType.LOSE), PLAYERS_3);
    public static final Match LOSING_MATCH_2 = new Match(DATE_2, new Result(Result.WinType.LOSE), PLAYERS_2);
    public static final Match LOSING_MATCH_1 = new Match(DATE_1, new Result(Result.WinType.LOSE), PLAYERS_1);
    public static final Match DRAWING_MATCH_4 = new Match(DATE_4, new Result(Result.WinType.DRAW), PLAYERS_4);
    public static final Match DRAWING_MATCH_3 = new Match(DATE_3, new Result(Result.WinType.DRAW), PLAYERS_3);
    public static final Match DRAWING_MATCH_2 = new Match(DATE_2, new Result(Result.WinType.DRAW), PLAYERS_2);
    public static final Match DRAWING_MATCH_1 = new Match(DATE_1, new Result(Result.WinType.DRAW), PLAYERS_1);

    public static List<Match> getTypicalMatches() {
        return List.of(WINNING_MATCH_1, WINNING_MATCH_2, WINNING_MATCH_3, WINNING_MATCH_4,
                LOSING_MATCH_1, LOSING_MATCH_2, LOSING_MATCH_3, LOSING_MATCH_4,
                DRAWING_MATCH_1, DRAWING_MATCH_2, DRAWING_MATCH_3, DRAWING_MATCH_4);
    }

    public static MatchRecord getTypicalMatchRecord() {
        MatchRecord matchRecord = new MatchRecord();
        getTypicalMatches().forEach(matchRecord::addMatch);
        return matchRecord;
    }

}
