package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Role.RoleType;

/**
 * Drafts a team by validating the composition of selected players.
 * Players are selected by their indices in the person list or by their in-game name (IGN).
 * A valid draft requires exactly 5 players with one player per role.
 */
public class DraftCommand extends Command {

    public static final String COMMAND_WORD = "draft";

    public static final String MESSAGE_USAGE = "Validates team composition using selected players.";

    public static final String PARAMETERS = "Parameters: (INDEX | i/IGNNAME) [(INDEX | i/IGNNAME)]...\n";

    public static final String EXAMPLE = "Example: " + COMMAND_WORD + " 1 2 i/CarlK77 4 i/ElleM55";

    public static final String MESSAGE_SUCCESS = "Draft validation complete: %1$s";
    public static final String MESSAGE_INVALID_IGN_NOT_FOUND = "Player with IGN '%1$s' not found";
    public static final String MESSAGE_INVALID_IGN_EMPTY = "IGN cannot be empty. Use format: i/playername";

    private static final int REQUIRED_TEAM_SIZE = 5;
    private static final int REQUIRED_PLAYERS_PER_ROLE = 1;

    private final List<Index> indices;
    private final List<String> igns;

    /**
     * Creates a DraftCommand with the specified player indices and IGNs.
     *
     * @param indices list of indices of players to draft (may be empty)
     * @param igns list of in-game names of players to draft (may be empty)
     */
    public DraftCommand(List<Index> indices, List<String> igns) {
        requireNonNull(indices);
        requireNonNull(igns);
        this.indices = new ArrayList<>(indices);
        this.igns = new ArrayList<>(igns);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> addressBookList = model.getAddressBook().getPersonList();

        // Resolve all players from both indices and IGNs
        List<Person> selectedPlayers = new ArrayList<>();

        // Resolve indices
        for (Index index : indices) {
            if (index.getZeroBased() >= addressBookList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            selectedPlayers.add(addressBookList.get(index.getZeroBased()));
        }

        // Resolve IGNs
        for (String ign : igns) {
            boolean found = false;
            for (Person person : addressBookList) {
                if (person.getIgn().value.equals(ign)) {
                    selectedPlayers.add(person);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new CommandException(String.format(MESSAGE_INVALID_IGN_NOT_FOUND, ign));
            }
        }

        // Validate composition and generate result message
        String validationMessage = generateValidationMessage(selectedPlayers);
        return new CommandResult(String.format(MESSAGE_SUCCESS, validationMessage),
                false, false, false, null, null, true, selectedPlayers);
    }

    /**
     * Generates a user-friendly validation message for the selected players.
     * Checks team size and role composition. Displays roles in order: TOP, JUNGLE, MID, BOT, SUPPORT.
     *
     * @param players the selected players
     * @return formatted validation message
     */
    private String generateValidationMessage(List<Person> players) {
        String result = "";
        Map<RoleType, Integer> roleCounts = buildRoleCountMap(players);
        List<String> errors = validateComposition(players, roleCounts);

        // Validity indicator
        if (errors.isEmpty()) {
            result += "✓ Draft Valid!\n";
        } else {
            result += "✗ Invalid Draft Composition\n";
        }

        // Composition summary on one line (in role order: TOP, JUNGLE, MID, BOT, SUPPORT)
        result += "Composition: ";
        RoleType[] roleOrder = {RoleType.TOP, RoleType.JUNGLE, RoleType.MID, RoleType.BOT, RoleType.SUPPORT};
        boolean isFirst = true;
        for (RoleType role : roleOrder) {
            if (!isFirst) {
                result += " | ";
            }
            result += role + " (" + roleCounts.get(role) + ")";
            isFirst = false;
        }

        // Issues on one line (if any)
        if (!errors.isEmpty()) {
            result += "\nIssues: " + String.join(", ", errors);
        }

        return result;
    }

    /**
     * Validates the composition of selected players.
     * Checks for exact team size and one player per role.
     *
     * @param players the selected players
     * @param roleCount map of roles to player counts
     * @return list of validation errors (empty if valid)
     */
    private List<String> validateComposition(List<Person> players, Map<RoleType, Integer> roleCount) {
        List<String> errors = new ArrayList<>();

        // Check team size
        if (players.size() != REQUIRED_TEAM_SIZE) {
            errors.add("Invalid team size (" + players.size() + ")");
        }

        // Check role requirements
        for (RoleType role : RoleType.values()) {
            int count = roleCount.get(role);
            if (count == 0) {
                errors.add("Missing " + role + " player");
            } else if (count > REQUIRED_PLAYERS_PER_ROLE) {
                errors.add("Too many " + role + " players");
            }
        }

        return errors;
    }

    /**
     * Builds a map of player counts by their roles.
     *
     * @param players the list of players
     * @return map of role to player count
     */
    private Map<RoleType, Integer> buildRoleCountMap(List<Person> players) {
        Map<RoleType, Integer> roleCounts = new HashMap<>();

        // Initialize all roles to 0
        for (RoleType role : RoleType.values()) {
            roleCounts.put(role, 0);
        }

        // Count players by role
        for (Person player : players) {
            RoleType role = player.getRole().value;
            roleCounts.put(role, roleCounts.get(role) + 1);
        }

        return roleCounts;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DraftCommand)) {
            return false;
        }

        DraftCommand otherDraftCommand = (DraftCommand) other;
        return indices.equals(otherDraftCommand.indices) && igns.equals(otherDraftCommand.igns);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("indices", indices)
                .add("igns", igns)
                .toString();
    }
}
