package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityPathPair;
import seedu.address.model.entity.EntityReference;

/**
 * Contains tests for EntityButtonFactory, testing the createEntityButton() functionality.
 * These tests are disabled on Linux because they require JavaFX which needs a display server.
 */
@DisabledOnOs(OS.LINUX)
public class EntityButtonFactoryTest {

    private static final String TEST_IMAGE_NAME = "Ahri.png";
    private static final String INVALID_IMAGE_NAME = "NonExistentImage.png";
    private static final String INVALID_EMPTY_NAME_IMAGE = "InvalidForEmpty.png";

    private Entity entityWithValidImage;
    private Entity entityWithInvalidPath;
    private Entity entityWithEmptyName;
    private EntityReference testEntityReference;

    /**
     * Initializes the JavaFX toolkit.
     * This is required for JavaFX components to work in tests.
     */
    @BeforeAll
    public static void initJavaFX() throws Exception {
        try {
            System.setProperty("java.awt.headless", "true");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.verbose", "true");
            System.setProperty("prism.order", "sw");
            Platform.startup(() -> {});
        } catch (IllegalStateException | UnsupportedOperationException e) {
            // JavaFX already initialized or not supported in this environment
        }
    }

    @BeforeEach
    public void setUp() {
        // Create entities for testing
        entityWithValidImage = new Entity("Ahri");
        entityWithInvalidPath = new Entity("TestEntity");
        entityWithEmptyName = new Entity("");

        // Set up EntityReference with test entities
        // Get the path to the test image in src/test/resources/images/
        Path validImagePath = Paths.get("src", "test", "resources", "images", TEST_IMAGE_NAME);
        Path invalidImagePath = Paths.get("src", "test", "resources", "images", INVALID_IMAGE_NAME);
        Path invalidEmptyNamePath = Paths.get("src", "test", "resources", "images", INVALID_EMPTY_NAME_IMAGE);

        testEntityReference = new EntityReference(List.of(
            new EntityPathPair(entityWithValidImage, validImagePath),
            new EntityPathPair(entityWithInvalidPath, invalidImagePath),
            new EntityPathPair(entityWithEmptyName, invalidEmptyNamePath)
        ));
        testEntityReference.reload();
    }

    @Test
    public void createEntityButton_withValidImage_displaysImageView() {
        Button button = EntityButtonFactory.createEntityButton(entityWithValidImage);

        // Verify button has an ImageView as its graphic
        assertNotNull(button.getGraphic(), "Button should have a graphic");
        assertTrue(button.getGraphic() instanceof ImageView,
                "Button graphic should be an ImageView");

        ImageView imageView = (ImageView) button.getGraphic();
        Image image = imageView.getImage();

        assertNotNull(image, "ImageView should contain an Image");
        assertTrue(image.getWidth() > 0, "Image should have positive width");
        assertTrue(image.getHeight() > 0, "Image should have positive height");

        // Verify button has transparent background style
        String style = button.getStyle();
        assertTrue(style.contains("-fx-background-color: transparent"),
                "Button style should contain transparent background");
    }

    @Test
    public void createEntityButton_withInvalidPath_displaysFallbackCharacter() {
        Button button = EntityButtonFactory.createEntityButton(entityWithInvalidPath);

        // Verify button displays the first character of entity name (uppercase)
        assertEquals("T", button.getText(),
                "Button should display first character 'T' of entity name 'TestEntity'");

        // Verify button has no graphic (fallback case)
        assertNull(button.getGraphic(), "Button should not have a graphic in fallback case");

        // Verify button has the fallback style (blue background, white text)
        String style = button.getStyle();
        assertTrue(style.contains("-fx-background-color: #4a90e2"),
                "Button style should contain blue background color");
        assertTrue(style.contains("-fx-text-fill: white"),
                "Button style should contain white text color");
        assertTrue(style.contains("-fx-font-weight: bold"),
                "Button style should contain bold font weight");
    }

    @Test
    public void createEntityButton_withEmptyName_displaysQuestionMark() {
        Button button = EntityButtonFactory.createEntityButton(entityWithEmptyName);

        // Verify button displays "?" as fallback for empty name
        assertEquals("?", button.getText(),
                "Button should display '?' for empty entity name");

        // Verify button has no graphic (fallback case)
        assertNull(button.getGraphic(), "Button should not have a graphic in fallback case");

        // Verify button has the fallback style
        String style = button.getStyle();
        assertTrue(style.contains("-fx-background-color: #4a90e2"),
                "Button style should contain blue background color");
        assertTrue(style.contains("-fx-text-fill: white"),
                "Button style should contain white text color");
    }

    @Test
    public void createEntityButton_multipleEntities_createsDistinctButtons() {
        Button buttonWithImage = EntityButtonFactory.createEntityButton(entityWithValidImage);
        Button buttonWithFallback = EntityButtonFactory.createEntityButton(entityWithInvalidPath);

        // Verify one button has image and one has fallback
        assertNotNull(buttonWithImage.getGraphic(), "Button with valid image should have a graphic");
        assertTrue(buttonWithImage.getGraphic() instanceof ImageView,
                "Button with valid image should have ImageView as graphic");

        assertNull(buttonWithFallback.getGraphic(), "Button with invalid path should not have a graphic");
        assertEquals("T", buttonWithFallback.getText(),
                "Button with invalid path should display fallback character 'T'");
    }
}
