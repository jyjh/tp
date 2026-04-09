package seedu.address.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.MatchRecord;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyMatchRecord;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityStatisticMap;
import seedu.address.model.match.Match;
import seedu.address.model.match.PlayerInMatch;
import seedu.address.model.match.PlayersInMatch;
import seedu.address.model.match.Result;
import seedu.address.model.person.Email;
import seedu.address.model.person.InGameName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Rank;
import seedu.address.model.person.Role;
import seedu.address.model.person.statistics.Statistics;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Role("TOP"),
                new InGameName("AlexY42"), new Rank("GOLD I"),
                getTagSet("friends"),
                new EntityStatisticMap()),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Role("JUNGLE"),
                new InGameName("Bern_Storm"), new Rank("PLATINUM II"),
                getTagSet("colleagues", "friends"),
                new EntityStatisticMap()),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Role("MID"),
                new InGameName("Charlie99"), new Rank("SILVER III"),
                getTagSet("neighbours"),
                new EntityStatisticMap()),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Role("BOT"),
                new InGameName("DavidLi91"), new Rank("DIAMOND IV"),
                getTagSet("family"),
                new EntityStatisticMap()),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Role("SUPPORT"),
                new InGameName("IrfanZ"), new Rank("GOLD III"),
                getTagSet("classmates"),
                new EntityStatisticMap()),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Role("TOP"),
                new InGameName("Roy_Vortex"), new Rank("CHALLENGER"),
                getTagSet("colleagues"),
                new EntityStatisticMap())
        };
    }

    public static Match[] getSampleMatches() {
        PlayerInMatch player1 = new PlayerInMatch(
                new InGameName("AlexY42"), Statistics.createRandom(10, 10, 20),
                new Entity("Ahri"));
        PlayerInMatch player2 = new PlayerInMatch(
                new InGameName("Bern_Storm"), Statistics.createRandom(10, 10, 20),
                new Entity("Lee Sin"));
        PlayerInMatch player3 = new PlayerInMatch(
                new InGameName("Charlie99"), Statistics.createRandom(10, 10, 20),
                new Entity("Jinx"));
        PlayerInMatch player4 = new PlayerInMatch(
                new InGameName("DavidLi91"), Statistics.createRandom(10, 10, 20),
                new Entity("Yasuo"));
        PlayerInMatch player5 = new PlayerInMatch(
                new InGameName("IrfanZ"), Statistics.createRandom(10, 10, 20),
                new Entity("Zed"));
        PlayerInMatch player6 = new PlayerInMatch(
                new InGameName("Roy_Vortex"), Statistics.createRandom(10, 10, 20),
                new Entity("Annie"));

        PlayersInMatch players1 = new PlayersInMatch(List.of(player1, player2, player3, player4, player5));
        PlayersInMatch players2 = new PlayersInMatch(List.of(player2, player3, player4, player5, player6));
        PlayersInMatch players3 = new PlayersInMatch(List.of(player1, player3, player4, player5, player6));

        return new Match[] {
            new Match(
                    LocalDate.of(2025, 1, 1),
                    new Result("WIN"), players1),
            new Match(
                    LocalDate.of(2025, 2, 2),
                    new Result("LOSE"), players2),
            new Match(LocalDate.of(2025, 3, 3),
                    new Result("DRAW"), players3)
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyMatchRecord getSampleMatchRecord() {
        MatchRecord sample = new MatchRecord();
        for (Match sampleMatch : getSampleMatches()) {
            sample.addMatch(sampleMatch);
        }
        return sample;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
