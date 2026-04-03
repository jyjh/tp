package seedu.address.model.match;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.entity.Entity;
import seedu.address.testutil.TypicalMatches;

public class PlayerInMatchTest {

    @Test
    public void equals() {

        // same values -> returns true
        PlayerInMatch player1 = TypicalMatches.FOUR_PLAYERS.get(0);
        PlayerInMatch player2 = TypicalMatches.FOUR_PLAYERS.get(1);
        PlayerInMatch same = new PlayerInMatch(
            player1.getName(), player1.getStatistics(), Entity.createDefault());
        PlayerInMatch differentStatistics = new PlayerInMatch(
            player1.getName(), player2.getStatistics(), Entity.createDefault());
        PlayerInMatch differentName = new PlayerInMatch(
            player2.getName(), player1.getStatistics(), Entity.createDefault());
        assertEquals(player1, same);

        // same object -> returns true
        assertEquals(player1, player1);

        // null -> returns false
        assertNotEquals(null, player1);

        // different type -> returns false
        assertNotEquals("player", player1);

        // different player -> returns false
        assertNotEquals(player2, player1);

        // different name -> returns false
        assertNotEquals(differentName, player1);

        // different statistics -> returns false
        assertNotEquals(differentStatistics, player1);

    }

    @Test
    public void toStringMethod() {
        PlayerInMatch player = TypicalMatches.FOUR_PLAYERS.get(2);
        String expected = PlayerInMatch.class.getCanonicalName() + "{name=" + player.getName() + ", statistics="
                + player.getStatistics() + "}";
        assertEquals(expected, player.toString());
    }

}
