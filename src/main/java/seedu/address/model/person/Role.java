package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's role in the address book (League of Legends role).
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class Role {

    public static final String MESSAGE_CONSTRAINTS = "Role should be one of: TOP, BOT, MID, SUPPORT, JUNGLE";

    /**
     * Represents the set of supported League of Legends roles.
     */
    public enum RoleType {
        TOP, BOT, MID, SUPPORT, JUNGLE
    }

    public final RoleType value;

    /**
     * Constructs a {@code Role}.
     *
     * @param role A valid role.
     */
    public Role(String role) {
        requireNonNull(role);
        String trimmedRole = role.trim().toUpperCase();
        checkArgument(isValidRole(trimmedRole), MESSAGE_CONSTRAINTS);
        value = RoleType.valueOf(trimmedRole);
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidRole(String test) {
        try {
            RoleType.valueOf(test);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Role)) {
            return false;
        }

        Role otherRole = (Role) other;
        return value.equals(otherRole.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
