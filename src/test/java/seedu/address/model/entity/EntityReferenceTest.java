package seedu.address.model.entity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ENTITY_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENTITY_NAME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENTITY_REFERENCE;

import org.junit.jupiter.api.Test;

public class EntityReferenceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Entity(null));
    }

    @Test
    public void reloadTest() {
        EntityReference emptyEntityReference = new EntityReference(new ArrayList<>());
        emptyEntityReference.reload();
        assertTrue(emptyEntityReference.getEntities().equals(EntityReference.getLoadedEntities()));
        assertFalse(VALID_ENTITY_REFERENCE.equals(EntityReference.getLoadedEntities()));
        VALID_ENTITY_REFERENCE.reload();
        assertFalse(emptyEntityReference.getEntities().equals(EntityReference.getLoadedEntities()));
        assertTrue(VALID_ENTITY_REFERENCE.getEntities().equals(EntityReference.getLoadedEntities()));
    }

    @Test
    public void entityRetrievalTest() {
        VALID_ENTITY_REFERENCE.reload();
        assertTrue(EntityReference.hasEntity(VALID_ENTITY_NAME_1));
        assertFalse(EntityReference.hasEntity("INVALID_ENTITY"));
    
        assertTrue(EntityReference.findByName(VALID_ENTITY_NAME_1).get().equals(VALID_ENTITY_1));
        assertTrue(EntityReference.findByName("INVALID_ENTITY").isEmpty());

        EntityReference emptyEntityReference = new EntityReference(new ArrayList<>());
        emptyEntityReference.reload();
        assertFalse(EntityReference.hasEntity(VALID_ENTITY_NAME_1));
        assertFalse(EntityReference.hasEntity("INVALID_ENTITY"));
    
        assertTrue(EntityReference.findByName(VALID_ENTITY_NAME_1).isEmpty());
        assertTrue(EntityReference.findByName("INVALID_ENTITY").isEmpty());
    }

    @Test
    public void equals() {
        EntityReference entityReference = new EntityReference(
            VALID_ENTITY_REFERENCE.getEntityPathPairs());

        // same values -> returns true
        assertTrue(entityReference.equals(VALID_ENTITY_REFERENCE));

        // same object -> returns true
        assertTrue(entityReference.equals(entityReference));

        // null -> returns false
        assertFalse(entityReference.equals(null));

        // different types -> returns false
        assertFalse(entityReference.equals(5.0f));

        // different values -> returns false
        assertFalse(entityReference.equals(new EntityReference(new ArrayList<>())));
    }
}
