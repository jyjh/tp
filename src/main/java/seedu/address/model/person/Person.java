package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityStatisticMap;
import seedu.address.model.person.statistics.Statistics;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Role role;
    private final InGameName ign;
    private final Rank rank;
    private final Set<Tag> tags = new HashSet<>();
    private final EntityStatisticMap entityStats = new EntityStatisticMap();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Role role,
        InGameName ign, Rank rank, Set<Tag> tags, EntityStatisticMap entityStats) {
        requireAllNonNull(name, phone, email, role, ign, rank, tags, entityStats);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.ign = ign;
        this.rank = rank;
        this.tags.addAll(tags);
        this.entityStats.putAll(entityStats);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public InGameName getIgn() {
        return ign;
    }

    public Rank getRank() {
        return rank;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Gets the overall statistics across all different entities, merged into one.
     * @return overallStatistics
     */
    public Statistics getOverallStatistics() {
        return entityStats.getOverallStatistics();
    }

    /**
     * Gets the stats of a person on a particular entity.
     */
    public Statistics getEntityStatistics(Entity entity) {
        return entityStats.getStatistics(entity);
    }

    /**
     * Adds the given statistics to the person's existing statistics for the specified entity.
     * Returns a new Person with the updated statistics (immutable).
     * @param statsToAdd
     * @param entity
     */
    public Person addEntityStatistics(Statistics statsToAdd, Entity entity) {
        EntityStatisticMap newStats = new EntityStatisticMap();
        newStats.putAll(this.entityStats);
        newStats.addStatistics(entity, statsToAdd);
        return new Person(name, phone, email, role, ign, rank, tags, newStats);
    }

    /**
     * Gets overall entity statistics
     */
    public EntityStatisticMap getOverallEntityStatistics() {
        return entityStats;
    }

    /**
     * Returns true if both persons have the same IGN.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.ign.equals(this.ign);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && role.equals(otherPerson.role)
                && ign.equals(otherPerson.ign)
                && rank.equals(otherPerson.rank)
                && tags.equals(otherPerson.tags)
                && entityStats.equals(otherPerson.entityStats);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, role, ign, rank, tags, entityStats);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("role", role)
                .add("rank", rank)
                .add("tags", tags)
                .add("entityStats", entityStats)
                .toString();
    }

}
