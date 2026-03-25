package seedu.address.model.person.statistics;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Player's statistics in the address book.
 * Guarantees: immutable; details are present and not null.
 */
public class Statistics {
    private final Kills kills;
    private final Deaths deaths;
    private final Assists assists;

    /**
     * Constructs a {@code Statistics}.
     *
     * @param kills A valid kills count.
     * @param deaths A valid deaths count.
     * @param assists A valid assists count.
     */
    private Statistics(Kills kills, Deaths deaths, Assists assists) {
        requireAllNonNull(kills, deaths, assists);
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
    }

    public Kills getKills() {
        return kills;
    }

    public Deaths getDeaths() {
        return deaths;
    }

    public Assists getAssists() {
        return assists;
    }

    /**
     * Returns the KDA of the player as a double.
     * Calculated as (Kills + Assists) / Deaths.
     * If Deaths is 0, returns Kills + Assists.
     */
    public double getKda() {
        int d = deaths.value;
        int sumKA = kills.value + assists.value;
        return d == 0 ? sumKA : (double) sumKA / d;
    }

    /**
     * A Builder for {@code Statistics}.
     */
    public static class Builder {
        // Set statistics to default values
        private Kills kills = new Kills("0");
        private Deaths deaths = new Deaths("0");
        private Assists assists = new Assists("0");

        /**
         * Sets the {@code Kills} of the {@code Statistics} that we are building.
         */
        public Builder withKills(Kills kills) {
            if (kills != null) {
                this.kills = kills;
            }
            return this;
        }

        /**
         * Sets the {@code Deaths} of the {@code Statistics} that we are building.
         */
        public Builder withDeaths(Deaths deaths) {
            if (deaths != null) {
                this.deaths = deaths;
            }
            return this;
        }

        /**
         * Sets the {@code Assists} of the {@code Statistics} that we are building.
         */
        public Builder withAssists(Assists assists) {
            if (assists != null) {
                this.assists = assists;
            }
            return this;
        }

        /**
         * Builds the {@code Statistics} object.
         */
        public Statistics build() {
            return new Statistics(kills, deaths, assists);
        }
    }

    /**
     * Creates a {@code Statistics} object with default values (e.g., zero kills).
     *
     * @return A Statistics object with initial default values.
     */
    public static Statistics createDefault() {
        return new Builder().build();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Statistics)) {
            return false;
        }

        Statistics otherStats = (Statistics) other;
        return kills.equals(otherStats.kills) && deaths.equals(otherStats.deaths) && assists.equals(otherStats.assists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kills, deaths, assists);
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return "Kills: " + kills.toString() + ", Deaths: " + deaths.toString() + ", Assists: " + assists.toString();
    }
}
