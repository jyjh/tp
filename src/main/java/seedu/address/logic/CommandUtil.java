package seedu.address.logic;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;

/**
 * Utility class containing common command-related operations.
 */
public class CommandUtil {

    /**
     * Finds a person in the address book by either index or IGN.
     *
     * @param personList the list of persons to search
     * @param identifier the index (as string) or IGN of the person to find
     * @return the person matching the identifier
     * @throws CommandException if the identifier is invalid (out of bounds index or IGN not found)
     */
    public static Person findPersonByIdentifier(List<Person> personList, String identifier) throws CommandException {
        // Check if identifier is an index (numeric)
        if (identifier.matches("\\d+")) {
            int index = Integer.parseInt(identifier) - 1; // Convert to zero-based
            if (index < 0 || index >= personList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            return personList.get(index);
        } else {
            // It's an IGN
            for (Person person : personList) {
                if (person.getIgn().value.equals(identifier)) {
                    return person;
                }
            }
            throw new CommandException(String.format("Player with IGN '%1$s' not found", identifier));
        }
    }
}
