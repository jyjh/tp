package seedu.address.model.util;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;
import seedu.address.model.entity.Entity;

/**
 * Contains utility methods for populating {@code EntityReference} with sample data.
 */
public class SampleEntityUtil {

    private SampleEntityUtil() {} // Prevent instantiation

    /**
     * Returns a list of sample entities.
     */
    public static Pair<List<Entity>, List<Path>> getSampleEntities() {
        List<Entity> entities = new ArrayList<>();
        List<Path> paths = new ArrayList<>();
        entities.add(new Entity("Ahri"));
        paths.add(Path.of("/images/ahri.png"));
        entities.add(new Entity("Garen"));
        paths.add(Path.of("/images/garen.png"));
        entities.add(new Entity("Lux"));
        paths.add(Path.of("/images/lux.png"));
        entities.add(new Entity("Ashe"));
        paths.add(Path.of("/images/ashe.png"));
        entities.add(new Entity("Jinx"));
        paths.add(Path.of("/images/jinx.png"));
        entities.add(new Entity("Yasuo"));
        paths.add(Path.of("/images/yasuo.png"));
        entities.add(new Entity("Lee Sin"));
        paths.add(Path.of("/images/leesin.png"));
        entities.add(new Entity("Thresh"));
        paths.add(Path.of("/images/thresh.png"));

        return new Pair<List<Entity>, List<Path>>(entities, paths);
    }
}
