package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.MatchRecord;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyMatchRecord;
import seedu.address.model.match.Match;
import seedu.address.model.match.Result;
import seedu.address.model.person.Address;
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
                new Address("Blk 30 Geylang Street 29, #06-40"), new Role("TOP"),
                new InGameName("AlexY42"), new Rank("GOLD"),
                getTagSet("friends"),
            Statistics.createDefault()),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Role("JUNGLE"),
                new InGameName("Bern_Storm"), new Rank("PLATINUM"),
                getTagSet("colleagues", "friends"),
            Statistics.createDefault()),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Role("MID"),
                new InGameName("Charlie99"), new Rank("SILVER"),
                getTagSet("neighbours"),
            Statistics.createDefault()),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Role("BOT"),
                new InGameName("DavidLi91"), new Rank("DIAMOND"),
                getTagSet("family"),
            Statistics.createDefault()),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Role("SUPPORT"),
                new InGameName("IrfanZ"), new Rank("GOLD"),
                getTagSet("classmates"),
            Statistics.createDefault()),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Role("TOP"),
                new InGameName("Roy_Vortex"), new Rank("CHALLENGER"),
                getTagSet("colleagues"),
            Statistics.createDefault())
        };
    }

    public static Match[] getSampleMatches() {
        return new Match[] {
            new Match(LocalDateTime.of(2025, 1, 1, 13, 0), new Result("WIN")),
            new Match(LocalDateTime.of(2025, 2, 2, 14, 0), new Result("LOSE")),
            new Match(LocalDateTime.of(2025, 3, 3, 15, 0), new Result("DRAW"))
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
