package seedu.address.ui;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityReference;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static final int ICON_SIZE = 40;

    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);
    private final Person person;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label inGameName;
    @FXML
    private Label role;
    @FXML
    private Label rank;
    @FXML
    private FlowPane tags;
    @FXML
    private Label stats;
    @FXML
    private FlowPane entityIconsGrid;
    @FXML
    private Label selectedEntityLabel;
    @FXML
    private Label entityStatsDisplay;

    private Entity selectedEntity;
    private Map<Entity, Button> entityButtonMap;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        this.entityButtonMap = new HashMap<>();

        id.setText(displayedIndex + ". ");
        name.setText(person.getIgn().toString() + "(" + person.getName().fullName + ")");
        phone.setText(person.getPhone().value);
        email.setText(person.getEmail().value);
        role.setText("Role: " + person.getRole().toString());
        rank.setText("Rank: " + person.getRank().toString());
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        stats.setText(person.getOverallStatistics().toString());

        populateEntityIcons();
        showDefaultStats();
    }

    /**
     * Populates the entity icons grid with buttons for each entity the person has stats for.
     */
    private void populateEntityIcons() {
        person.getOverallEntityStatistics().getMap().keySet().forEach(entity -> {
            Button entityButton = createEntityButton(entity);
            entityButtonMap.put(entity, entityButton);
            entityIconsGrid.getChildren().add(entityButton);
        });
    }

    /**
     * Creates a button for an entity with either an image or a fallback character.
     */
    private Button createEntityButton(Entity entity) {
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

        button.setOnAction(event -> selectEntity(entity));

        return button;
    }

    /**
     * Selects an entity and updates the stats display.
     */
    private void selectEntity(Entity entity) {
        selectedEntity = entity;
        selectedEntityLabel.setText(entity.getName() + ":");
        entityStatsDisplay.setText(person.getEntityStatistics(entity).toString());

        // Update visual feedback
        entityButtonMap.forEach((e, button) -> {
            if (e.equals(entity)) {
                button.setStyle(button.getStyle() + " -fx-border-color: #000000; -fx-border-width: 2;");
            } else {
                // Remove border from other buttons
                String currentStyle = button.getStyle();
                button.setStyle(currentStyle.replace(
                    "-fx-border-color: #000000; -fx-border-width: 2;", ""));
            }
        });
    }

    /**
     * Shows default stats when no entity is selected.
     */
    private void showDefaultStats() {
        selectedEntityLabel.setText("Select an entity to view stats");
        entityStatsDisplay.setText("");
    }
}
