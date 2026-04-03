package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.entity.EntityStatisticMap;
import seedu.address.model.person.Email;
import seedu.address.model.person.InGameName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Rank;
import seedu.address.model.person.Role;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_ROLE = "MID";
    public static final String DEFAULT_IGN = "AmyBee88";
    public static final String DEFAULT_RANK = "GOLD I";

    private Name name;
    private Phone phone;
    private Email email;
    private Role role;
    private InGameName ign;
    private Rank rank;
    private Set<Tag> tags;
    private EntityStatisticMap entityStatisticMap;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        role = new Role(DEFAULT_ROLE);
        ign = new InGameName(DEFAULT_IGN);
        rank = new Rank(DEFAULT_RANK);
        tags = new HashSet<>();
        entityStatisticMap = new EntityStatisticMap();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        role = personToCopy.getRole();
        ign = personToCopy.getIgn();
        rank = personToCopy.getRank();
        tags = new HashSet<>(personToCopy.getTags());
        entityStatisticMap = personToCopy.getOverallEntityStatistics();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Statistics} of the {@code Person} that we are building.
     */
    public PersonBuilder withEntityStatistics(EntityStatisticMap map) {
        this.entityStatisticMap = new EntityStatisticMap();
        this.entityStatisticMap.putAll(map);
        return this;
    }


    /**
     * Sets the {@code Role} of the {@code Person} that we are building.
     */
    public PersonBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    /**
     * Sets the {@code InGameName} of the {@code Person} that we are building.
     */
    public PersonBuilder withIgn(String ign) {
        this.ign = new InGameName(ign);
        return this;
    }

    /**
     * Sets the {@code Rank} of the {@code Person} that we are building.
     */
    public PersonBuilder withRank(String rank) {
        this.rank = new Rank(rank);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, role, ign, rank, tags, entityStatisticMap);
    }

}
