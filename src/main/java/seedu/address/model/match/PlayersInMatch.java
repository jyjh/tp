package seedu.address.model.match;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.InGameName;

/**
 * Represents a valid list of players in a match.
 */
public class PlayersInMatch implements Iterable<PlayerInMatch> {

    public static final String MESSAGE_CONSTRAINTS = "Player list must contain no duplicates and be of size 5.";

    private final List<PlayerInMatch> players;

    /**
     * Constructs a new PlayersInMatch with a valid list of players.
     * @param players The list of players
     */
    public PlayersInMatch(List<PlayerInMatch> players) {
        requireNonNull(players);
        checkArgument(isValidPlayerList(players), MESSAGE_CONSTRAINTS);

        this.players = players;
    }

    /**
     * Check if the list of players input is valid
     * @param players The list of players
     * @return True if this list of players is of size 5 and does not contain duplicates.
     */
    public static boolean isValidPlayerList(List<PlayerInMatch> players) {
        Set<InGameName> uniquePlayers = new HashSet<>(players.stream().map(PlayerInMatch::getInGameName).toList());
        return uniquePlayers.size() == players.size() && uniquePlayers.size() == 5;
    }

    public PlayerInMatch get(int i) {
        return players.get(i);
    }

    public List<PlayerInMatch> asList() {
        return new ArrayList<>(this.players);
    }

    @Override
    public Iterator<PlayerInMatch> iterator() {
        return players.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PlayersInMatch otherPlayers)) {
            return false;
        }

        return this.players.equals(otherPlayers.players);

    }

    @Override
    public int hashCode() {
        return players.hashCode();
    }

    @Override
    public String toString() {
        return players.toString();
    }

}
