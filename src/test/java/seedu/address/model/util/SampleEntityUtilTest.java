package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityPathPair;

public class SampleEntityUtilTest {

    private static final int EXPECTED_CHAMPION_COUNT = 172;

    @Test
    public void getSampleEntities_notNull() {
        List<EntityPathPair> entities = SampleEntityUtil.getSampleEntities();
        assertNotNull(entities, "getSampleEntities should not return null");
    }

    @Test
    public void getSampleEntities_correctSize() {
        List<EntityPathPair> entities = SampleEntityUtil.getSampleEntities();
        assertEquals(EXPECTED_CHAMPION_COUNT, entities.size(),
            "getSampleEntities should return exactly "
            + EXPECTED_CHAMPION_COUNT + " champions, but returned " + entities.size());
    }

    @Test
    public void getSampleEntities_noDuplicates() {
        List<EntityPathPair> entities = SampleEntityUtil.getSampleEntities();
        Set<String> championNames = entities.stream()
            .map(pair -> pair.getEntity().getName())
            .collect(Collectors.toSet());

        assertEquals(entities.size(), championNames.size(),
            "getSampleEntities should not contain duplicate champions");
    }

    @Test
    public void getSampleEntities_validNames() {
        List<EntityPathPair> entities = SampleEntityUtil.getSampleEntities();
        for (EntityPathPair pair : entities) {
            Entity entity = pair.getEntity();
            String name = entity.getName();
            assertNotNull(name, "Champion name should not be null");
            assertTrue(!name.trim().isEmpty(), "Champion name should not be empty");
            assertTrue(name.length() > 0, "Champion name should have at least one character");
        }
    }

    @Test
    public void getSampleEntities_validImagePaths() {
        List<EntityPathPair> entities = SampleEntityUtil.getSampleEntities();
        for (EntityPathPair pair : entities) {
            assertNotNull(pair.getPath(), "Image path should not be null");
            String pathString = pair.getPath().toString();
            assertTrue(pathString.startsWith("/images/") || pathString.startsWith("\\images\\"),
                String.format("Image path should start with /images/ or \\images\\, but path is %s", pathString));
            assertTrue(pathString.endsWith(".jpg"),
                String.format("Image path should end with .jpg, but is %s", pathString));
        }
    }

    @Test
    public void getSampleEntities_imagePathFormat() {
        List<EntityPathPair> entities = SampleEntityUtil.getSampleEntities();
        for (EntityPathPair pair : entities) {
            String pathString = pair.getPath().toString();

            // Extract the filename (between /images/ and .jpg)
            String filename = pathString.substring(pathString.indexOf("/") + 1, pathString.indexOf(".jpg"));
            String lowercaseFilename = filename.toLowerCase();

            assertEquals(lowercaseFilename, filename,
                String.format("Image filename should be in lowercase for %s, but is %s",
                    pair.getEntity().getName(), pathString));
        }
    }
}
