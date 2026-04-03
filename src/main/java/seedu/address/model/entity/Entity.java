package seedu.address.model.entity;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Entity (champion/character) in the system.
 * Guarantees: details are present and not null, immutable.
 */
public class Entity {

    // Identity fields
    private final String name;

    /**
     * Every field must be present and not null.
     */
    public Entity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns true if both entities have the same name.
     * This defines a weaker notion of equality between two entities.
     */
    public boolean isSameEntity(Entity otherEntity) {
        if (otherEntity == this) {
            return true;
        }

        return otherEntity != null
                && otherEntity.getName().equals(getName());
    }

    /**
     * Returns true if both entities have the same identity and data fields.
     * This defines a stronger notion of equality between two entities.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Entity)) {
            return false;
        }

        Entity otherEntity = (Entity) other;
        return name.equals(otherEntity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .toString();
    }

    public static Entity createDefault() {
        return new Entity("Default Entity");
    }

}
