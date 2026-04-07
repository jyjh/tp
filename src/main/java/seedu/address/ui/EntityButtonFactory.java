package seedu.address.ui;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityReference;

/**
 * Factory class for creating buttons that represent entities.
 * Provides reusable functionality to create entity buttons with either images or fallback characters.
 */
public class EntityButtonFactory {

    private static final int ICON_SIZE = 40;
    private static final Logger logger = LogsCenter.getLogger(EntityButtonFactory.class);

    /**
     * Creates a button for an entity with either an image or a fallback character.
     *
     * @param entity the entity to create a button for
     * @return a Button component representing the entity
     */
    public static Button createEntityButton(Entity entity) {
        Button button = new Button();
        button.setMinSize(ICON_SIZE, ICON_SIZE);
        button.setMaxSize(ICON_SIZE, ICON_SIZE);
        button.setStyle("-fx-background-radius: 0%; -fx-cursor: hand; -fx-padding: 0;");

        Path iconPath = EntityReference.getIconPath(entity);

        if (iconPath != null && iconPath.toFile().canRead()) {
            // Use image if available
            Image image = new Image(iconPath.toUri().toString(), ICON_SIZE, ICON_SIZE, true, true);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(ICON_SIZE);
            imageView.setFitHeight(ICON_SIZE);
            button.setGraphic(imageView);
            button.setStyle(button.getStyle() + " -fx-background-color: transparent;");
        } else {
            // Fallback to first character
            logger.info(iconPath.toUri() + " was not found or inaccessible. Reverting to fallback.");
            String firstChar = entity.getName().isEmpty() ? "?"
                : Character.toString(entity.getName().charAt(0)).toUpperCase();
            button.setText(firstChar);
            button.setStyle(button.getStyle()
                + " -fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;"
                + " -fx-background-color: #4a90e2;");
        }

        // Add tooltip to display full entity name on hover
        Tooltip tooltip = new Tooltip(entity.getName());
        Tooltip.install(button, tooltip);

        return button;
    }

    /**
     * Creates a list of buttons for multiple entities, sorted alphabetically by entity name.
     *
     * @param entities the list of entities to create buttons for (unsorted)
     * @return a sorted list of Buttons representing the entities
     */
    public static List<Button> createEntityButtons(List<Entity> entities) {
        List<Entity> sortedEntities = new ArrayList<>(entities);
        sortedEntities.sort(Comparator.comparing(Entity::getName));

        List<Button> buttons = new ArrayList<>();
        for (Entity entity : sortedEntities) {
            buttons.add(createEntityButton(entity));
        }
        return buttons;
    }
}
