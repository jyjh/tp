package seedu.address.model.match;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENTITY_1;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalMatches;

public class PlayerInMatchTest {

    @Test
    public void equals() {

        // same values -> returns true
        PlayerInMatch player1 = TypicalMatches.PLAYERS_4.get(0);
        PlayerInMatch player2 = TypicalMatches.PLAYERS_4.get(1);
        PlayerInMatch same = new PlayerInMatch(
            player1.getInGameName(), player1.getStatistics(), VALID_ENTITY_1);
        PlayerInMatch differentStatistics = new PlayerInMatch(
            player1.getInGameName(), player2.getStatistics(), VALID_ENTITY_1);
        PlayerInMatch differentName = new PlayerInMatch(
            player2.getInGameName(), player1.getStatistics(), VALID_ENTITY_1);
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
        PlayerInMatch player = TypicalMatches.PLAYERS_4.get(2);
        String expected = player.getInGameName() + "{statistics=" + player.getStatistics() + "}";
        assertEquals(expected, player.toString());
    }

}
