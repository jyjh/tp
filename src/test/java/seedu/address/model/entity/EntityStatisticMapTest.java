package seedu.address.model.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.statistics.Assists;
import seedu.address.model.person.statistics.Deaths;
import seedu.address.model.person.statistics.Kills;
import seedu.address.model.person.statistics.Statistics;

public class EntityStatisticMapTest {

    private static final Entity ENTITY_A = new Entity("ChampionA");
    private static final Entity ENTITY_B = new Entity("ChampionB");
    private static final Entity ENTITY_C = new Entity("ChampionC");

    private static final Statistics STATS_1 = new Statistics.Builder()
            .withKills(new Kills("1"))
            .withDeaths(new Deaths("0"))
            .withAssists(new Assists("0"))
            .build();

    private static final Statistics STATS_2 = new Statistics.Builder()
            .withKills(new Kills("2"))
            .withDeaths(new Deaths("0"))
            .withAssists(new Assists("0"))
            .build();

    @Test
    public void equals_sameReference_returnsTrue() {
        EntityStatisticMap map = new EntityStatisticMap();
        assertTrue(map.equals(map));
    }

    @Test
    public void equals_null_returnsFalse() {
        EntityStatisticMap map = new EntityStatisticMap();
        assertFalse(map.equals(null));
    }

    @Test
    public void equals_differentType_returnsFalse() {
        EntityStatisticMap map = new EntityStatisticMap();
        assertFalse(map.equals("not a map"));
    }

    @Test
    public void equals_bothEmpty_returnsTrue() {
        EntityStatisticMap map1 = new EntityStatisticMap();
        EntityStatisticMap map2 = new EntityStatisticMap();
        assertTrue(map1.equals(map2));
        assertTrue(map2.equals(map1));
    }

    @Test
    public void equals_sameContents_returnsTrue() {
        EntityStatisticMap map1 = new EntityStatisticMap.Builder()
                .withEntity(ENTITY_A, STATS_1)
                .withEntity(ENTITY_B, STATS_2)
                .build();

        EntityStatisticMap map2 = new EntityStatisticMap.Builder()
                .withEntity(ENTITY_A, STATS_1)
                .withEntity(ENTITY_B, STATS_2)
                .build();

        assertTrue(map1.equals(map2));
        assertTrue(map2.equals(map1));
    }

    @Test
    public void equals_differentStatistics_returnsFalse() {
        EntityStatisticMap map1 = new EntityStatisticMap.Builder()
                .withEntity(ENTITY_A, STATS_1)
                .build();

        EntityStatisticMap map2 = new EntityStatisticMap.Builder()
                .withEntity(ENTITY_A, STATS_2)
                .build();

        assertFalse(map1.equals(map2));
        assertFalse(map2.equals(map1));
    }

    @Test
    public void equals_differentEntities_returnsFalse() {
        EntityStatisticMap map1 = new EntityStatisticMap.Builder()
                .withEntity(ENTITY_A, STATS_1)
                .build();

        EntityStatisticMap map2 = new EntityStatisticMap.Builder()
                .withEntity(ENTITY_B, STATS_1)
                .build();

        assertFalse(map1.equals(map2));
        assertFalse(map2.equals(map1));
    }

    @Test
    public void equals_supersetMap_returnsFalse() {
        // This is the exact bug scenario: map1 is a subset of map2
        EntityStatisticMap map1 = new EntityStatisticMap.Builder()
                .withEntity(ENTITY_A, STATS_1)
                .build();

        EntityStatisticMap map2 = new EntityStatisticMap.Builder()
                .withEntity(ENTITY_A, STATS_1)
                .withEntity(ENTITY_B, STATS_2)
                .build();

        assertFalse(map1.equals(map2));
        assertFalse(map2.equals(map1));
    }

    @Test
    public void equals_symmetryDifferentSizes_bothDirectionsMatch() {
        // map1 has 1 entry, map2 has 3 entries — neither is a subset equal
        EntityStatisticMap map1 = new EntityStatisticMap.Builder()
                .withEntity(ENTITY_A, STATS_1)
                .build();

        EntityStatisticMap map2 = new EntityStatisticMap.Builder()
                .withEntity(ENTITY_A, STATS_1)
                .withEntity(ENTITY_B, STATS_2)
                .withEntity(ENTITY_C, STATS_1)
                .build();

        boolean forward = map1.equals(map2);
        boolean reverse = map2.equals(map1);

        // Symmetry: both must agree
        assertEquals(forward, reverse);
        // And both must be false since the maps differ
        assertFalse(forward);
        assertFalse(reverse);
    }

    @Test
    public void equals_transitivity() {
        EntityStatisticMap map1 = new EntityStatisticMap.Builder()
                .withEntity(ENTITY_A, STATS_1)
                .build();

        EntityStatisticMap map2 = new EntityStatisticMap.Builder()
                .withEntity(ENTITY_A, STATS_1)
                .build();

        EntityStatisticMap map3 = new EntityStatisticMap.Builder()
                .withEntity(ENTITY_A, STATS_1)
                .build();

        assertTrue(map1.equals(map2));
        assertTrue(map2.equals(map3));
        assertTrue(map1.equals(map3));
    }

    @Test
    public void equals_consistentAcrossMultipleCalls() {
        EntityStatisticMap map1 = new EntityStatisticMap.Builder()
                .withEntity(ENTITY_A, STATS_1)
                .build();

        EntityStatisticMap map2 = new EntityStatisticMap.Builder()
                .withEntity(ENTITY_A, STATS_1)
                .build();

        // Calling equals multiple times should return the same result
        for (int i = 0; i < 10; i++) {
            assertTrue(map1.equals(map2));
            assertTrue(map2.equals(map1));
        }
    }

    @Test
    public void hashCode_equalObjects_sameHashCode() {
        EntityStatisticMap map1 = new EntityStatisticMap.Builder()
                .withEntity(ENTITY_A, STATS_1)
                .withEntity(ENTITY_B, STATS_2)
                .build();

        EntityStatisticMap map2 = new EntityStatisticMap.Builder()
                .withEntity(ENTITY_A, STATS_1)
                .withEntity(ENTITY_B, STATS_2)
                .build();

        assertTrue(map1.equals(map2));
        assertEquals(map1.hashCode(), map2.hashCode());
    }
}
