package seedu.address.model.entity;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.statistics.Statistics;

/**
 * Manages a mapping of Entity objects to their Statistics.
 */
public class EntityStatisticMap {

    private final Map<Entity, Statistics> entityStats;

    /**
     * Constructs an empty EntityStatisticMap.
     */
    public EntityStatisticMap() {
        this.entityStats = new HashMap<>();
    }

    /**
     * Constructs an EntityStatisticMap with the given map.
     * @param entityStats Map of Entity to Statistics to store
     */
    public EntityStatisticMap(Map<Entity, Statistics> entityStats) {
        requireNonNull(entityStats);
        this.entityStats = new HashMap<>(entityStats);
    }

    /**
     * Returns the statistics for a given entity.
     * @param entity The entity to get statistics for
     * @return Statistics for the entity, or null if not found
     */
    public Statistics getStatistics(Entity entity) {
        return entityStats.get(entity);
    }

    /**
     * Returns the sum total of statistics for all entities.
     * @return
     */
    public Statistics getOverallStatistics() {
        return entityStats.values().stream().reduce((x, y) -> x.add(y)).orElse(Statistics.createDefault());
    }

    /**
     * Adds or updates statistics for a given entity.
     * @param entity The entity to add/update
     * @param statistics The statistics to associate with the entity
     */
    public void addStatistics(Entity entity, Statistics statistics) {
        requireNonNull(entity);
        requireNonNull(statistics);
        entityStats.put(entity, statistics);
    }

    /**
     * Checks if the map contains statistics for a given entity.
     * @param entity The entity to check
     * @return true if the entity exists in the map, false otherwise
     */
    public boolean containsKey(Entity entity) {
        return entityStats.containsKey(entity);
    }

    /**
     * Returns a collection of all statistics values.
     * @return Collection of all Statistics objects
     */
    public Collection<Statistics> values() {
        return entityStats.values();
    }

    /**
     * Returns the internal map for operations that need direct access.
     */
    public Map<Entity, Statistics> getMap() {
        return entityStats;
    }

    /**
     * Returns an unmodifiable view of the internal map.
     */
    public Map<Entity, Statistics> getUnmodifiableMap() {
        return Collections.unmodifiableMap(entityStats);
    }

    /**
     * Puts all entries from another map into this map.
     * @param other The other EntityStatisticMap to copy from
     */
    public void putAll(EntityStatisticMap other) {
        requireNonNull(other);
        entityStats.putAll(other.entityStats);
    }

    /**
     * Puts all entries from a Map into this map.
     * @param other The map to copy from
     */
    public void putAll(Map<Entity, Statistics> other) {
        requireNonNull(other);
        entityStats.putAll(other);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EntityStatisticMap)) {
            return false;
        }

        EntityStatisticMap otherEntityStatisticMap = (EntityStatisticMap) other;
        for (Entity entity : entityStats.keySet()) {
            if (!otherEntityStatisticMap.containsKey(entity)
                    || !this.getStatistics(entity).equals(otherEntityStatisticMap.getStatistics(entity))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return entityStats.hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("entityStats", entityStats)
                .toString();
    }

    /**
     * Builder class for constructing EntityStatisticMap instances with a fluent interface.
     */
    public static class Builder {

        private EntityStatisticMap map;

        /**
         * Initializes the builder with an empty EntityStatisticMap.
         */
        public Builder() {
            map = new EntityStatisticMap();
        }

        /**
         * Adds an entity and its statistics to the map being built.
         * @param entity
         * @param stat
         * @return
         */
        public Builder withEntity(Entity entity, Statistics stat) {
            map.addStatistics(entity, stat);
            return this;
        }

        public EntityStatisticMap build() {
            return map;
        }
    }
}
