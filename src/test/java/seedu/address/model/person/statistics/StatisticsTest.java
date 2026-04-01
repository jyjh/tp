package seedu.address.model.person.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class StatisticsTest {

    private final Kills kills1 = new Kills("50");
    private final Deaths deaths1 = new Deaths("10");
    private final Assists assists1 = new Assists("20");
    private final Kills kills2 = new Kills("30");
    private final Deaths deaths2 = new Deaths("60");
    private final Assists assists2 = new Assists("40");

    @Test
    public void createDefault_success() {
        Statistics defaultStats = Statistics.createDefault();
        assertEquals(new Kills("0"), defaultStats.getKills());
        assertEquals(new Deaths("0"), defaultStats.getDeaths());
        assertEquals(new Assists("0"), defaultStats.getAssists());
    }

    @Test
    public void createRandom_success() {
        Statistics.createRandom(10, 10, 10);
    }

    @Test
    public void builderWithKillsWithDeathsWithAssistsSuccess() {
        Statistics stats = new Statistics.Builder()
                .withKills(kills1)
                .withDeaths(deaths1)
                .withAssists(assists1)
                .build();
        assertEquals(kills1, stats.getKills());
        assertEquals(deaths1, stats.getDeaths());
        assertEquals(assists1, stats.getAssists());
    }

    @Test
    public void addStatistics() {
        Statistics stats1 = new Statistics.Builder()
                .withKills(kills1)
                .withDeaths(deaths1)
                .withAssists(assists1)
                .build();
        Statistics stats2 = new Statistics.Builder()
                .withKills(kills2)
                .withDeaths(deaths2)
                .withAssists(assists2)
                .build();
        Statistics stats = stats1.add(stats2);
        assertEquals(kills1.add(kills2), stats.getKills());
        assertEquals(deaths1.add(deaths2), stats.getDeaths());
        assertEquals(assists1.add(assists2), stats.getAssists());
    }

    @Test
    public void equals() {
        Statistics stats = new Statistics.Builder()
            .withKills(new Kills("10"))
            .withDeaths(new Deaths("5"))
            .withAssists(new Assists("2"))
            .build();

        // same values -> returns true
        assertEquals(stats, new Statistics.Builder()
                .withKills(new Kills("10"))
                .withDeaths(new Deaths("5"))
                .withAssists(new Assists("2"))
                .build());

        // same object -> returns true
        assertEquals(stats, stats);

        // null -> returns false
        assertNotEquals(null, stats);

        // different types -> returns false
        assertNotEquals("10", stats);

        // different values -> returns false
        assertNotEquals(stats, new Statistics.Builder()
                .withKills(new Kills("20"))
                .withDeaths(new Deaths("5"))
                .withAssists(new Assists("2"))
                .build());
        assertNotEquals(stats, new Statistics.Builder()
                .withKills(new Kills("10"))
                .withDeaths(new Deaths("15"))
                .withAssists(new Assists("2"))
                .build());
        assertNotEquals(stats, new Statistics.Builder()
                .withKills(new Kills("10"))
                .withDeaths(new Deaths("5"))
                .withAssists(new Assists("8"))
                .build());
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
