package seedu.address.model.entity;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Manages a list of Entity objects.
 * Entities are loaded from a JSON file and are not modifiable by users.
 * However, the list of reference entities can be reloaded (future feature)
 */
public class EntityReference {

    private static List<Entity> loadedEntities;
    private static Map<Entity, Path> loadedEntityIconMap; // Map to store entity icons, if needed in the future
    private final List<Entity> entities;
    private final Map<Entity, Path> entityIconMap;

    /**
     * Constructs an EntityReference with the given list of entities.
     * @param entities List of Entity objects to store
     */
    public EntityReference(List<EntityPathPair> entities) {
        requireNonNull(entities);
        this.entities = entities.stream()
                .map(x -> x.getEntity())
                .collect(Collectors.toList());
        this.entityIconMap = new HashMap<>();
        entities.stream()
                .forEach(x -> entityIconMap.put(x.getEntity(), x.getPath()));
        this.reload();
    }

    /**
     * Returns an unmodifiable list of all entities.
     */
    public List<Entity> getEntities() {
        return Collections.unmodifiableList(entities);
    }

    public List<Path> getPaths() {
        return Collections.unmodifiableList(new ArrayList<>(entityIconMap.values()));
    }

    public List<EntityPathPair> getEntityPathPairs() {
        return entities.stream().map(x -> new EntityPathPair(x, entityIconMap.get(x)))
            .collect(Collectors.toList());
    }

    /**
     * Returns the currently set reference entity list.
     */
    public static List<Entity> getLoadedEntities() {
        return loadedEntities;
    }

    /**
     * Reloads this objects entities into the static reference.
     * @return
     */

    public void reload() {
        EntityReference.loadedEntities = new ArrayList<>(this.entities);
        EntityReference.loadedEntityIconMap = new HashMap<>(this.entityIconMap);
    }

    /**
     * Finds an entity by name. Uses the loaded entity list.
     * @param name The name of the entity to find
     * @return Optional containing the entity if found, empty otherwise
     */
    public static Optional<Entity> findByName(String name) {
        return loadedEntities.stream()
                .filter(entity -> entity.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    /**
     * Checks if an entity with the given name exists. Uses the loaded entity list.
     * @param name The name to check
     * @return true if an entity with the name exists, false otherwise
     */
    public static boolean hasEntity(String name) {
        return findByName(name).isPresent();
    }

    public static Path getIconPath(Entity entity) {
        return loadedEntityIconMap.get(entity);
    }

    @Override
    public int hashCode() {
        return entities.hashCode();
    }

    @Override
    public String toString() {
        return entities.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EntityReference)) {
            return false;
        }
        EntityReference that = (EntityReference) obj;
        return entities.equals(that.entities) &&
            entityIconMap.equals(that.entityIconMap);
    }
}
