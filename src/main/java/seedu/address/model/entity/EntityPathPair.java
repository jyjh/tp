package seedu.address.model.entity;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.Objects;

/**
 * Represents a pair of an {@code Entity} and its corresponding file path.
 */
public class EntityPathPair {
    private final Entity entity;
    private final Path path;

    /**
     * Constructs an EntityPathPair with the given entity and file path.
     * @param entity
     * @param path
     */
    public EntityPathPair(Entity entity, Path path) {
        requireNonNull(entity);
        requireNonNull(path);
        this.entity = entity;
        this.path = path;
    }

    /**
     * Returns the entity in this pair.
     */
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * Returns the file path in this pair.
     */
    public Path getPath() {
        return this.path;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EntityPathPair)) {
            return false;
        }

        EntityPathPair otherEntity = (EntityPathPair) other;
        return otherEntity.getEntity().equals(this.getEntity())
            && otherEntity.getPath().equals(this.getPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(entity, path);
    }
}
