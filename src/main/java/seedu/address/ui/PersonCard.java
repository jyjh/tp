package seedu.address.ui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.entity.Entity;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

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
        String nameText = person.getIgn().toString() + "(" + person.getName().fullName + ")";
        name.setText(nameText);
        Tooltip nameTooltip = new Tooltip(nameText);
        name.setTooltip(nameTooltip);
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
        List<Entity> entities = new ArrayList<>(person.getOverallEntityStatistics().getMap().keySet());
        List<Button> entityButtons = EntityButtonFactory.createEntityButtons(entities);

        for (Button entityButton : entityButtons) {
            Entity entity = (Entity) entityButton.getUserData();
            entityButton.setOnAction(event -> selectEntity(entity));
            entityButtonMap.put(entity, entityButton);
            entityIconsGrid.getChildren().add(entityButton);
        }
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
