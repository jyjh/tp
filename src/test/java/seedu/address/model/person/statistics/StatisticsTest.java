package seedu.address.model.person.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StatisticsTest {

    @Test
    public void createDefault_success() {
        Statistics defaultStats = Statistics.createDefault();
        assertEquals(new Kills("0"), defaultStats.getKills());
        assertEquals(new Deaths("0"), defaultStats.getDeaths());
        assertEquals(new Assists("0"), defaultStats.getAssists());
    }

    @Test
    public void builderWithKillsWithDeathsWithAssistsSuccess() {
        Kills kills = new Kills("50");
        Deaths deaths = new Deaths("10");
        Assists assists = new Assists("20");
        Statistics stats = new Statistics.Builder().withKills(kills).withDeaths(deaths).withAssists(assists).build();
        assertEquals(kills, stats.getKills());
        assertEquals(deaths, stats.getDeaths());
        assertEquals(assists, stats.getAssists());
    }

    @Test
    public void equals() {
        Statistics stats = new Statistics.Builder()
            .withKills(new Kills("10"))
            .withDeaths(new Deaths("5"))
            .withAssists(new Assists("2"))
            .build();

        // same values -> returns true
        assertTrue(stats.equals(new Statistics.Builder()
            .withKills(new Kills("10"))
            .withDeaths(new Deaths("5"))
            .withAssists(new Assists("2"))
            .build()));

        // same object -> returns true
        assertTrue(stats.equals(stats));

        // null -> returns false
        assertFalse(stats.equals(null));

        // different types -> returns false
        assertFalse(stats.equals("10"));

        // different values -> returns false
        assertFalse(stats.equals(new Statistics.Builder()
            .withKills(new Kills("20"))
            .withDeaths(new Deaths("5"))
            .withAssists(new Assists("2"))
            .build()));
        assertFalse(stats.equals(new Statistics.Builder()
            .withKills(new Kills("10"))
            .withDeaths(new Deaths("15"))
            .withAssists(new Assists("2"))
            .build()));
        assertFalse(stats.equals(new Statistics.Builder()
            .withKills(new Kills("10"))
            .withDeaths(new Deaths("5"))
            .withAssists(new Assists("8"))
            .build()));
    }

    @Test
    public void getKda() {
        // Deaths > 0
        Statistics stats1 = new Statistics.Builder()
                .withKills(new Kills("5"))
                .withAssists(new Assists("10"))
                .withDeaths(new Deaths("2"))
                .build();
        assertEquals(7.5, stats1.getKda());

        // Deaths = 0
        Statistics stats2 = new Statistics.Builder()
                .withKills(new Kills("5"))
                .withAssists(new Assists("10"))
                .withDeaths(new Deaths("0"))
                .build();
        assertEquals(15.0, stats2.getKda());
    }

    @Test
    public void toStringMethod() {
        Statistics stats = new Statistics.Builder()
            .withKills(new Kills("10"))
            .withDeaths(new Deaths("5"))
            .withAssists(new Assists("2"))
            .build();
        assertEquals("Kills: 10, Deaths: 5, Assists: 2", stats.toString());
    }

    @Test
    public void hashCodeMethod() {
        Statistics stats1 = new Statistics.Builder()
            .withKills(new Kills("10"))
            .withDeaths(new Deaths("5"))
            .withAssists(new Assists("2"))
            .build();
        Statistics stats2 = new Statistics.Builder()
            .withKills(new Kills("10"))
            .withDeaths(new Deaths("5"))
            .withAssists(new Assists("2"))
            .build();
        assertEquals(stats1.hashCode(), stats2.hashCode());
    }
}
