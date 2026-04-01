package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's League of Legends rank with divisions in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRank(String)}
 * <p>
 * Rank format: "TIER [DIVISION]" (e.g., "GOLD I", "GOLD II", "CHALLENGER")
 * - Divisions I-IV apply to IRON through DIAMOND
 * - MASTER, GRANDMASTER, and CHALLENGER have no divisions
 */
public class Rank {

    public static final String MESSAGE_CONSTRAINTS = "Rank should be a valid League of Legends rank. "
            + "Valid ranks are: IRON I-IV, BRONZE I-IV, SILVER I-IV, GOLD I-IV, PLATINUM I-IV, "
            + "DIAMOND I-IV, MASTER, GRANDMASTER, CHALLENGER.";

    /**
     * Represents the set of supported League of Legends rank tiers.
     * Ordered from lowest to highest rank.
     */
    public enum RankTier {
        IRON(0), BRONZE(1), SILVER(2), GOLD(3), PLATINUM(4),
        DIAMOND(5), MASTER(6), GRANDMASTER(7), CHALLENGER(8);

        private final int tierValue;

        RankTier(int tierValue) {
            this.tierValue = tierValue;
        }

        public int getTierValue() {
            return tierValue;
        }
    }

    /**
     * Represents the divisions within a rank tier (I through IV).
     * Only applicable to IRON through DIAMOND ranks.
     */
    public enum Division {
        I(0), II(1), III(2), IV(3);

        private final int divisionValue;

        Division(int divisionValue) {
            this.divisionValue = divisionValue;
        }

        public int getDivisionValue() {
            return divisionValue;
        }
    }

    public final RankTier tier;
    public final Division division;

    /**
     * Constructs a {@code Rank} with the specified tier and optional division.
     *
     * @param rank A valid League of Legends rank (e.g., "GOLD I" or "CHALLENGER").
     */
    public Rank(String rank) {
        requireNonNull(rank);
        String trimmedRank = rank.trim().toUpperCase();
        checkArgument(isValidRank(trimmedRank), MESSAGE_CONSTRAINTS);

        String[] parts = trimmedRank.split(" ");
        this.tier = RankTier.valueOf(parts[0]);

        if (parts.length == 2) {
            this.division = Division.valueOf(parts[1]);
        } else {
            this.division = null;
        }
    }

    /**
     * Returns true if a given string is a valid rank.
     * Valid formats:
     * - "TIER DIVISION" for IRON through DIAMOND (e.g., "GOLD I")
     * - Just "TIER" for MASTER, GRANDMASTER, CHALLENGER (e.g., "CHALLENGER")
     */
    public static boolean isValidRank(String test) {
        if (test == null) {
            return false;
        }

        String trimmed = test.trim().toUpperCase();

        if (trimmed.isEmpty()) {
            return false;
        }

        // Check for multiple spaces between tier and division
        if (trimmed.contains("  ")) {
            return false;
        }

        String[] parts = trimmed.split(" ");

        if (parts.length < 1 || parts.length > 2) {
            return false;
        }

        try {
            RankTier tier = RankTier.valueOf(parts[0]);

            // Top ranks (MASTER, GRANDMASTER, CHALLENGER) should not have divisions
            if (tier.getTierValue() >= 6) {
                return parts.length == 1;
            }

            // Lower ranks (IRON through DIAMOND) must have divisions
            if (parts.length != 2) {
                return false;
            }

            Division.valueOf(parts[1]);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        if (division == null) {
            return tier.toString();
        }
        return tier.toString() + " " + division.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Rank)) {
            return false;
        }

        Rank otherRank = (Rank) other;
        return tier.equals(otherRank.tier)
                && ((division == null && otherRank.division == null)
                || (division != null && division.equals(otherRank.division)));
    }

    @Override
    public int hashCode() {
        return tier.hashCode() * 31 + (division != null ? division.hashCode() : 0);
    }

}
