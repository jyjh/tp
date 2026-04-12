package seedu.address.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityStatisticMap;
import seedu.address.model.person.Person;
import seedu.address.model.person.statistics.Statistics;

/**
 * A UI component that displays a side-by-side comparison of two players.
 */
public class ComparePanel extends UiPart<VBox> {

    private static final String FXML = "ComparePanel.fxml";
    private static final String STYLE_BETTER = "indicator-better";
    private static final String STYLE_WORSE = "indicator-worse";
    private static final String STYLE_EQUAL = "indicator-equal";
    private static final String STYLE_SELECTED = "entity-button-selected";
    private static final String ARROW_UP = " ▲";
    private static final String ARROW_DOWN = " ▼";
    private static final String EMPTY_STATS = "N/A";

    @FXML
    private Label player1Name;
    @FXML
    private Label player1Role;
    @FXML
    private Label player1Rank;
    @FXML
    private Label player1StatsLabel;
    @FXML
    private Label player1Kills;
    @FXML
    private Label player1KillsIndicator;
    @FXML
    private Label player1Deaths;
    @FXML
    private Label player1DeathsIndicator;
    @FXML
    private Label player1Assists;
    @FXML
    private Label player1AssistsIndicator;
    @FXML
    private Label player1KdRatio;
    @FXML
    private Label player1KdIndicator;

    @FXML
    private Label player2Name;
    @FXML
    private Label player2Role;
    @FXML
    private Label player2Rank;
    @FXML
    private Label player2StatsLabel;
    @FXML
    private Label player2Kills;
    @FXML
    private Label player2KillsIndicator;
    @FXML
    private Label player2Deaths;
    @FXML
    private Label player2DeathsIndicator;
    @FXML
    private Label player2Assists;
    @FXML
    private Label player2AssistsIndicator;
    @FXML
    private Label player2KdRatio;
    @FXML
    private Label player2KdIndicator;

    @FXML
    private FlowPane entityButtonsFlowPanePlayer1;

    @FXML
    private FlowPane entityButtonsFlowPaneCommon;

    @FXML
    private FlowPane entityButtonsFlowPanePlayer2;

    @FXML
    private Label selectedEntityLabel;

    private final Person player1;
    private final Person player2;
    private Entity selectedEntity;

    /**
     * Creates a {@code ComparePanel} with given two players to compare.
     */
    public ComparePanel(Person player1, Person player2) {
        super(FXML);
        this.player1 = player1;
        this.player2 = player2;

        initializeEntityButtons();
        displayOverallStats();
    }

    /**
     * Initializes the entity buttons panel.
     */
    private void initializeEntityButtons() {
        EntityStatisticMap player1Stats = player1.getOverallEntityStatistics();
        EntityStatisticMap player2Stats = player2.getOverallEntityStatistics();

        // Get all unique entities from both players
        Set<Entity> uniqueEntities = new HashSet<>();
        uniqueEntities.addAll(player1Stats.getUnmodifiableMap().keySet());
        uniqueEntities.addAll(player2Stats.getUnmodifiableMap().keySet());

        // Convert to list and create sorted buttons
        List<Entity> entities = new ArrayList<>(uniqueEntities);
        List<Button> entityButtons = EntityButtonFactory.createEntityButtons(entities);

        for (Button entityButton : entityButtons) {
            Entity entity = (Entity) entityButton.getUserData();

            boolean player1HasStats = player1Stats.containsKey(entity);
            boolean player2HasStats = player2Stats.containsKey(entity);

            // Add click handler
            entityButton.setOnAction(event -> handleEntityButtonClick(entity, entityButton));

            // Categorize and add to appropriate FlowPane
            if (player1HasStats && player2HasStats) {
                // Both players have stats - add to common section
                entityButtonsFlowPaneCommon.getChildren().add(entityButton);
            } else if (player1HasStats) {
                // Only player 1 has stats - add to player 1 section
                entityButtonsFlowPanePlayer1.getChildren().add(entityButton);
            } else if (player2HasStats) {
                // Only player 2 has stats - add to player 2 section
                entityButtonsFlowPanePlayer2.getChildren().add(entityButton);
            }
        }

        selectedEntityLabel.setText("Click an entity to view detailed stats");
    }

    /**
     * Handles the click event on an entity button.
     */
    private void handleEntityButtonClick(Entity entity, Button clickedButton) {
        // Toggle selection
        if (selectedEntity != null && selectedEntity.equals(entity)) {
            // Deselect
            selectedEntity = null;
            displayOverallStats();
            updateButtonSelection(clickedButton, false);
            selectedEntityLabel.setText("Showing overall stats");
        } else {
            // Select new entity
            selectedEntity = entity;
            displayEntityStats(entity);
            updateButtonSelection(clickedButton, true);
            selectedEntityLabel.setText("Showing stats for: " + entity.getName());
        }
    }

    /**
     * Updates the visual selection state of entity buttons.
     */
    private void updateButtonSelection(Button clickedButton, boolean isSelected) {
        // Remove selection from all buttons in all three FlowPanes
        for (FlowPane flowPane : List.of(
                entityButtonsFlowPanePlayer1,
                entityButtonsFlowPaneCommon,
                entityButtonsFlowPanePlayer2)) {
            for (Object child : flowPane.getChildren()) {
                if (child instanceof Button) {
                    Button button = (Button) child;
                    button.getStyleClass().remove(STYLE_SELECTED);
                }
            }
        }

        // Add selection to clicked button if selected
        if (isSelected) {
            clickedButton.getStyleClass().add(STYLE_SELECTED);
        }
    }

    /**
     * Displays overall stats for both players.
     */
    private void displayOverallStats() {
        setPlayerStats(player1, player1Name, player1Role, player1Rank, player1StatsLabel,
                player1Kills, player1KillsIndicator, player1Deaths, player1DeathsIndicator,
                player1Assists, player1AssistsIndicator, player1KdRatio, player1KdIndicator,
                player1.getOverallStatistics(), "Overall Stats:");

        setPlayerStats(player2, player2Name, player2Role, player2Rank, player2StatsLabel,
                player2Kills, player2KillsIndicator, player2Deaths, player2DeathsIndicator,
                player2Assists, player2AssistsIndicator, player2KdRatio, player2KdIndicator,
                player2.getOverallStatistics(), "Overall Stats:");

        setComparisons(player1.getOverallStatistics(), player2.getOverallStatistics());
    }

    /**
     * Displays stats for a specific entity for both players.
     */
    private void displayEntityStats(Entity entity) {
        EntityStatisticMap player1Stats = player1.getOverallEntityStatistics();
        EntityStatisticMap player2Stats = player2.getOverallEntityStatistics();

        boolean player1HasStats = player1Stats.containsKey(entity);
        boolean player2HasStats = player2Stats.containsKey(entity);

        Statistics stats1 = player1HasStats
                ? player1Stats.getStatistics(entity) : Statistics.createDefault();
        Statistics stats2 = player2HasStats
                ? player2Stats.getStatistics(entity) : Statistics.createDefault();

        setPlayerStats(player1, player1Name, player1Role, player1Rank, player1StatsLabel,
                player1Kills, player1KillsIndicator, player1Deaths, player1DeathsIndicator,
                player1Assists, player1AssistsIndicator, player1KdRatio, player1KdIndicator,
                stats1, player1HasStats ? "Stats for " + entity.getName() : EMPTY_STATS);

        setPlayerStats(player2, player2Name, player2Role, player2Rank, player2StatsLabel,
                player2Kills, player2KillsIndicator, player2Deaths, player2DeathsIndicator,
                player2Assists, player2AssistsIndicator, player2KdRatio, player2KdIndicator,
                stats2, player2HasStats ? "Stats for " + entity.getName() : EMPTY_STATS);

        // Only set comparisons if both players have stats
        if (player1HasStats && player2HasStats) {
            setComparisons(stats1, stats2);
        } else {
            // Clear comparison indicators if one side has no stats
            clearComparisonIndicators();
        }
    }

    /**
     * Clears all comparison indicators.
     */
    private void clearComparisonIndicators() {
        player1KillsIndicator.setText("");
        player1KillsIndicator.getStyleClass().removeAll(STYLE_BETTER, STYLE_WORSE, STYLE_EQUAL);
        player2KillsIndicator.setText("");
        player2KillsIndicator.getStyleClass().removeAll(STYLE_BETTER, STYLE_WORSE, STYLE_EQUAL);

        player1DeathsIndicator.setText("");
        player1DeathsIndicator.getStyleClass().removeAll(STYLE_BETTER, STYLE_WORSE, STYLE_EQUAL);
        player2DeathsIndicator.setText("");
        player2DeathsIndicator.getStyleClass().removeAll(STYLE_BETTER, STYLE_WORSE, STYLE_EQUAL);

        player1AssistsIndicator.setText("");
        player1AssistsIndicator.getStyleClass().removeAll(STYLE_BETTER, STYLE_WORSE, STYLE_EQUAL);
        player2AssistsIndicator.setText("");
        player2AssistsIndicator.getStyleClass().removeAll(STYLE_BETTER, STYLE_WORSE, STYLE_EQUAL);

        player1KdIndicator.setText("");
        player1KdIndicator.getStyleClass().removeAll(STYLE_BETTER, STYLE_WORSE, STYLE_EQUAL);
        player2KdIndicator.setText("");
        player2KdIndicator.getStyleClass().removeAll(STYLE_BETTER, STYLE_WORSE, STYLE_EQUAL);
    }

    /**
     * Sets the details for a player.
     */
    private void setPlayerStats(Person player, Label nameLabel, Label roleLabel, Label rankLabel,
            Label statsLabel, Label killsLabel, Label killsIndicator, Label deathsLabel,
            Label deathsIndicator, Label assistsLabel, Label assistsIndicator, Label kdLabel,
            Label kdIndicator, Statistics stats, String statsLabelText) {
        String displayName = player.getIgn().toString() + " (" + player.getName().fullName + ")";
        nameLabel.setText(displayName);
        Tooltip nameTooltip = new Tooltip(displayName);
        nameLabel.setTooltip(nameTooltip);
        roleLabel.setText(player.getRole().toString());
        rankLabel.setText(player.getRank().toString());

        statsLabel.setText(statsLabelText);

        if (statsLabelText.equals(EMPTY_STATS)) {
            killsLabel.setText(EMPTY_STATS);
            deathsLabel.setText(EMPTY_STATS);
            assistsLabel.setText(EMPTY_STATS);
            kdLabel.setText(EMPTY_STATS);
            killsIndicator.setText("");
            deathsIndicator.setText("");
            assistsIndicator.setText("");
            kdIndicator.setText("");
        } else {
            killsLabel.setText(stats.getKills().toString());
            deathsLabel.setText(stats.getDeaths().toString());
            assistsLabel.setText(stats.getAssists().toString());
            double kdRatio = stats.getKda();
            kdLabel.setText(String.format("%.2f", kdRatio));
        }
    }

    /**
     * Sets comparison indicators between the two players' stats.
     */
    private void setComparisons(Statistics stats1, Statistics stats2) {
        // Compare Kills
        int kills1 = Integer.parseInt(stats1.getKills().toString());
        int kills2 = Integer.parseInt(stats2.getKills().toString());
        setIndicators(player1KillsIndicator, player2KillsIndicator, kills1, kills2);

        // Compare Deaths (lower is better)
        int deaths1 = Integer.parseInt(stats1.getDeaths().toString());
        int deaths2 = Integer.parseInt(stats2.getDeaths().toString());
        setIndicatorsReverse(player1DeathsIndicator, player2DeathsIndicator, deaths1, deaths2);

        // Compare Assists
        int assists1 = Integer.parseInt(stats1.getAssists().toString());
        int assists2 = Integer.parseInt(stats2.getAssists().toString());
        setIndicators(player1AssistsIndicator, player2AssistsIndicator, assists1, assists2);

        // Compare KDA Ratio
        double kd1 = stats1.getKda();
        double kd2 = stats2.getKda();
        setIndicators(player1KdIndicator, player2KdIndicator, kd1, kd2);
    }

    /**
     * Sets indicators for a field where higher is better.
     */
    private void setIndicators(Label indicator1, Label indicator2, double value1, double value2) {
        indicator1.getStyleClass().removeAll(STYLE_BETTER, STYLE_WORSE, STYLE_EQUAL);
        indicator2.getStyleClass().removeAll(STYLE_BETTER, STYLE_WORSE, STYLE_EQUAL);

        if (value1 > value2) {
            indicator1.setText(ARROW_UP);
            indicator1.getStyleClass().add(STYLE_BETTER);
            indicator2.setText(ARROW_DOWN);
            indicator2.getStyleClass().add(STYLE_WORSE);
        } else if (value1 < value2) {
            indicator1.setText(ARROW_DOWN);
            indicator1.getStyleClass().add(STYLE_WORSE);
            indicator2.setText(ARROW_UP);
            indicator2.getStyleClass().add(STYLE_BETTER);
        } else {
            indicator1.setText(" =");
            indicator1.getStyleClass().add(STYLE_EQUAL);
            indicator2.setText(" =");
            indicator2.getStyleClass().add(STYLE_EQUAL);
        }
    }

    /**
     * Sets indicators for a field where lower is better (e.g., deaths).
     */
    private void setIndicatorsReverse(Label indicator1, Label indicator2, double value1, double value2) {
        indicator1.getStyleClass().removeAll(STYLE_BETTER, STYLE_WORSE, STYLE_EQUAL);
        indicator2.getStyleClass().removeAll(STYLE_BETTER, STYLE_WORSE, STYLE_EQUAL);

        if (value1 < value2) {
            indicator1.setText(ARROW_UP);
            indicator1.getStyleClass().add(STYLE_BETTER);
            indicator2.setText(ARROW_DOWN);
            indicator2.getStyleClass().add(STYLE_WORSE);
        } else if (value1 > value2) {
            indicator1.setText(ARROW_DOWN);
            indicator1.getStyleClass().add(STYLE_WORSE);
            indicator2.setText(ARROW_UP);
            indicator2.getStyleClass().add(STYLE_BETTER);
        } else {
            indicator1.setText(" =");
            indicator1.getStyleClass().add(STYLE_EQUAL);
            indicator2.setText(" =");
            indicator2.getStyleClass().add(STYLE_EQUAL);
        }
    }
}
