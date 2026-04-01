package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RankTest {

    @Test
    public void isValidRank() {
        // null rank
        assertFalse(Rank.isValidRank(null));

        // empty rank
        assertFalse(Rank.isValidRank(""));
        assertFalse(Rank.isValidRank("   "));

        // invalid format - multiple spaces
        assertFalse(Rank.isValidRank("GOLD  I"));

        // invalid tier
        assertFalse(Rank.isValidRank("LEGENDARY I"));

        // lower ranks without division
        assertFalse(Rank.isValidRank("GOLD"));
        assertFalse(Rank.isValidRank("SILVER"));
        assertFalse(Rank.isValidRank("PLATINUM"));

        // lower ranks with invalid division
        assertFalse(Rank.isValidRank("GOLD V"));
        assertFalse(Rank.isValidRank("SILVER VIII"));

        // top ranks with division
        assertFalse(Rank.isValidRank("MASTER I"));
        assertFalse(Rank.isValidRank("GRANDMASTER II"));
        assertFalse(Rank.isValidRank("CHALLENGER IV"));

        // valid lower ranks with divisions
        assertTrue(Rank.isValidRank("IRON I"));
        assertTrue(Rank.isValidRank("IRON II"));
        assertTrue(Rank.isValidRank("IRON III"));
        assertTrue(Rank.isValidRank("IRON IV"));

        assertTrue(Rank.isValidRank("BRONZE I"));
        assertTrue(Rank.isValidRank("SILVER II"));
        assertTrue(Rank.isValidRank("GOLD III"));
        assertTrue(Rank.isValidRank("PLATINUM IV"));
        assertTrue(Rank.isValidRank("DIAMOND I"));

        // valid top ranks without divisions
        assertTrue(Rank.isValidRank("MASTER"));
        assertTrue(Rank.isValidRank("GRANDMASTER"));
        assertTrue(Rank.isValidRank("CHALLENGER"));

        // case insensitive
        assertTrue(Rank.isValidRank("gold i"));
        assertTrue(Rank.isValidRank("Gold II"));
        assertTrue(Rank.isValidRank("master"));
    }

    @Test
    public void constructor_validRank_success() {
        // lower ranks with divisions
        Rank goldOne = new Rank("GOLD I");
        assertEquals(Rank.RankTier.GOLD, goldOne.tier);
        assertEquals(Rank.Division.I, goldOne.division);

        Rank silverTwo = new Rank("SILVER II");
        assertEquals(Rank.RankTier.SILVER, silverTwo.tier);
        assertEquals(Rank.Division.II, silverTwo.division);

        Rank diamondFour = new Rank("DIAMOND IV");
        assertEquals(Rank.RankTier.DIAMOND, diamondFour.tier);
        assertEquals(Rank.Division.IV, diamondFour.division);

        // top ranks without divisions
        Rank master = new Rank("MASTER");
        assertEquals(Rank.RankTier.MASTER, master.tier);
        assertEquals(null, master.division);

        Rank challenger = new Rank("CHALLENGER");
        assertEquals(Rank.RankTier.CHALLENGER, challenger.tier);
        assertEquals(null, challenger.division);
    }

    @Test
    public void constructor_invalidRank_throwsIllegalArgumentException() {
        // null rank
        assertThrows(NullPointerException.class, () -> new Rank(null));

        // invalid rank
        assertThrows(IllegalArgumentException.class, () -> new Rank(""));
        assertThrows(IllegalArgumentException.class, () -> new Rank("GOLD"));
        assertThrows(IllegalArgumentException.class, () -> new Rank("MASTER I"));
        assertThrows(IllegalArgumentException.class, () -> new Rank("GOLD V"));
    }

    @Test
    public void toString_success() {
        assertEquals("GOLD I", new Rank("GOLD I").toString());
        assertEquals("SILVER II", new Rank("SILVER II").toString());
        assertEquals("PLATINUM IV", new Rank("PLATINUM IV").toString());
        assertEquals("MASTER", new Rank("MASTER").toString());
        assertEquals("CHALLENGER", new Rank("CHALLENGER").toString());
    }

    @Test
    public void equals() {
        Rank goldOne = new Rank("GOLD I");
        Rank goldOneLower = new Rank("gold i");
        Rank goldTwo = new Rank("GOLD II");
        Rank master = new Rank("MASTER");
        Rank masterLower = new Rank("master");

        // same rank
        assertEquals(goldOne, goldOneLower);
        assertEquals(master, masterLower);

        // different ranks
        assertFalse(goldOne.equals(goldTwo));
        assertFalse(goldOne.equals(master));
        assertFalse(master.equals(goldOne));

        // null
        assertFalse(goldOne.equals(null));

        // different type
        assertFalse(goldOne.equals(5));
    }

    @Test
    public void hashCode_success() {
        Rank goldOne = new Rank("GOLD I");
        Rank goldOneLower = new Rank("gold i");
        Rank master = new Rank("MASTER");

        // same rank should have same hashcode
        assertEquals(goldOne.hashCode(), goldOneLower.hashCode());

        // different ranks should (likely) have different hashcode
        assertFalse(goldOne.hashCode() == master.hashCode());
    }

    @Test
    public void rankTier_getTierValue() {
        assertEquals(0, Rank.RankTier.IRON.getTierValue());
        assertEquals(1, Rank.RankTier.BRONZE.getTierValue());
        assertEquals(2, Rank.RankTier.SILVER.getTierValue());
        assertEquals(3, Rank.RankTier.GOLD.getTierValue());
        assertEquals(4, Rank.RankTier.PLATINUM.getTierValue());
        assertEquals(5, Rank.RankTier.DIAMOND.getTierValue());
        assertEquals(6, Rank.RankTier.MASTER.getTierValue());
        assertEquals(7, Rank.RankTier.GRANDMASTER.getTierValue());
        assertEquals(8, Rank.RankTier.CHALLENGER.getTierValue());
    }

    @Test
    public void division_getDivisionValue() {
        assertEquals(0, Rank.Division.I.getDivisionValue());
        assertEquals(1, Rank.Division.II.getDivisionValue());
        assertEquals(2, Rank.Division.III.getDivisionValue());
        assertEquals(3, Rank.Division.IV.getDivisionValue());
    }

    @Test
    public void equals_withDifferentDivisions() {
        Rank goldOne = new Rank("GOLD I");
        Rank goldTwo = new Rank("GOLD II");
        Rank goldThree = new Rank("GOLD III");
        Rank goldFour = new Rank("GOLD IV");

        // Different divisions should not be equal
        assertFalse(goldOne.equals(goldTwo));
        assertFalse(goldTwo.equals(goldThree));
        assertFalse(goldThree.equals(goldFour));
        assertFalse(goldOne.equals(goldFour));
    }

    @Test
    public void equals_topRanksWithAndWithoutDivisions() {
        Rank master = new Rank("MASTER");
        Rank goldOne = new Rank("GOLD I");

        // Top rank vs lower rank with division
        assertFalse(master.equals(goldOne));
        assertFalse(goldOne.equals(master));
    }
}
