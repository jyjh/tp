package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ENTITY_1;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.model.MatchRecord;
import seedu.address.model.match.Match;
import seedu.address.model.match.PlayerInMatch;
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

    public static final List<Person> FOUR_PERSONS = PERSONS.subList(0, 4);
    public static final List<Person> THREE_PERSONS = PERSONS.subList(4, 7);
    public static final List<Person> TWO_PERSONS = PERSONS.subList(7, 9);
    public static final List<Person> ONE_PERSON = PERSONS.subList(0, 1);


    public static final List<PlayerInMatch> FOUR_PLAYERS = FOUR_PERSONS.stream()
            .map(person ->
                    new PlayerInMatch(person.getName(),
                            Statistics.createRandom(10, 10, 20),
                            VALID_ENTITY_1))
            .toList();
    public static final List<PlayerInMatch> THREE_PLAYERS = THREE_PERSONS.stream()
            .map(person ->
                    new PlayerInMatch(person.getName(),
                            Statistics.createRandom(10, 10, 20),
                            VALID_ENTITY_1))
            .toList();
    public static final List<PlayerInMatch> TWO_PLAYERS = TWO_PERSONS.stream()
            .map(person ->
                    new PlayerInMatch(person.getName(),
                            Statistics.createRandom(10, 10, 20),
                            VALID_ENTITY_1))
            .toList();
    public static final List<PlayerInMatch> ONE_PLAYER = ONE_PERSON.stream()
            .map(person ->
                    new PlayerInMatch(person.getName(),
                            Statistics.createRandom(10, 10, 20),
                            VALID_ENTITY_1))
            .toList();

    public static final LocalDateTime DATE_1 = LocalDateTime.of(2025, 12, 1, 12, 0);
    public static final LocalDateTime DATE_2 = LocalDateTime.of(2025, 11, 2, 14, 0);
    public static final LocalDateTime DATE_3 = LocalDateTime.of(2025, 10, 3, 16, 0);
    public static final LocalDateTime DATE_4 = LocalDateTime.of(2025, 9, 4, 18, 0);

    public static final Match WINNING_MATCH_4 = new Match(DATE_4, new Result(Result.WinType.WIN), FOUR_PLAYERS);
    public static final Match WINNING_MATCH_3 = new Match(DATE_3, new Result(Result.WinType.WIN), THREE_PLAYERS);
    public static final Match WINNING_MATCH_2 = new Match(DATE_2, new Result(Result.WinType.WIN), TWO_PLAYERS);
    public static final Match WINNING_MATCH_1 = new Match(DATE_1, new Result(Result.WinType.WIN), ONE_PLAYER);
    public static final Match LOSING_MATCH_4 = new Match(DATE_4, new Result(Result.WinType.LOSE), FOUR_PLAYERS);
    public static final Match LOSING_MATCH_3 = new Match(DATE_3, new Result(Result.WinType.LOSE), THREE_PLAYERS);
    public static final Match LOSING_MATCH_2 = new Match(DATE_2, new Result(Result.WinType.LOSE), TWO_PLAYERS);
    public static final Match LOSING_MATCH_1 = new Match(DATE_1, new Result(Result.WinType.LOSE), ONE_PLAYER);
    public static final Match DRAWING_MATCH_4 = new Match(DATE_4, new Result(Result.WinType.DRAW), FOUR_PLAYERS);
    public static final Match DRAWING_MATCH_3 = new Match(DATE_3, new Result(Result.WinType.DRAW), THREE_PLAYERS);
    public static final Match DRAWING_MATCH_2 = new Match(DATE_2, new Result(Result.WinType.DRAW), TWO_PLAYERS);
    public static final Match DRAWING_MATCH_1 = new Match(DATE_1, new Result(Result.WinType.DRAW), ONE_PLAYER);

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
