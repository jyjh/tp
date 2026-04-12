package seedu.address.ui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import seedu.address.model.entity.Entity;
import seedu.address.model.person.Person;
import seedu.address.model.person.Role.RoleType;

/**
 * A UI component that displays 5 drafted players in a row, ordered by their roles.
 */
public class DraftPanel extends UiPart<HBox> {

    private static final String FXML = "DraftPanel.fxml";

    @FXML
    private Label topName;
    @FXML
    private Label topRole;
    @FXML
    private Label topRank;
    @FXML
    private Label topKills;
    @FXML
    private Label topDeaths;
    @FXML
    private Label topAssists;
    @FXML
    private Label topKdRatio;

    @FXML
    private Label jungleName;
    @FXML
    private Label jungleRole;
    @FXML
    private Label jungleRank;
    @FXML
    private Label jungleKills;
    @FXML
    private Label jungleDeaths;
    @FXML
    private Label jungleAssists;
    @FXML
    private Label jungleKdRatio;

    @FXML
    private Label midName;
    @FXML
    private Label midRole;
    @FXML
    private Label midRank;
    @FXML
    private Label midKills;
    @FXML
    private Label midDeaths;
    @FXML
    private Label midAssists;
    @FXML
    private Label midKdRatio;

    @FXML
    private Label botName;
    @FXML
    private Label botRole;
    @FXML
    private Label botRank;
    @FXML
    private Label botKills;
    @FXML
    private Label botDeaths;
    @FXML
    private Label botAssists;
    @FXML
    private Label botKdRatio;

    @FXML
    private Label supportName;
    @FXML
    private Label supportRole;
    @FXML
    private Label supportRank;
    @FXML
    private Label supportKills;
    @FXML
    private Label supportDeaths;
    @FXML
    private Label supportAssists;
    @FXML
    private Label supportKdRatio;

    @FXML
    private FlowPane topEntityFlowPane;
    @FXML
    private FlowPane jungleEntityFlowPane;
    @FXML
    private FlowPane midEntityFlowPane;
    @FXML
    private FlowPane botEntityFlowPane;
    @FXML
    private FlowPane supportEntityFlowPane;

    /**
     * Creates a {@code DraftPanel} with the given list of drafted players.
     * Players are automatically sorted by role: TOP, JUNGLE, MID, BOT, SUPPORT.
     */
    public DraftPanel(List<Person> draftPlayers) {
        super(FXML);

        // Sort players by role order: TOP, JUNGLE, MID, BOT, SUPPORT
        List<Person> sortedPlayers = draftPlayers.stream()
                .sorted(Comparator.comparingInt(this::getRoleOrder))
                .toList();

        // Populate the 5 panels with players in role order
        if (sortedPlayers.size() >= 1) {
            setPlayerDetails(sortedPlayers.get(0), RoleType.TOP, topName, topRole, topRank,
                    topKills, topDeaths, topAssists, topKdRatio, topEntityFlowPane);
        }
        if (sortedPlayers.size() >= 2) {
            setPlayerDetails(sortedPlayers.get(1), RoleType.JUNGLE, jungleName, jungleRole, jungleRank,
                    jungleKills, jungleDeaths, jungleAssists, jungleKdRatio, jungleEntityFlowPane);
        }
        if (sortedPlayers.size() >= 3) {
            setPlayerDetails(sortedPlayers.get(2), RoleType.MID, midName, midRole, midRank,
                    midKills, midDeaths, midAssists, midKdRatio, midEntityFlowPane);
        }
        if (sortedPlayers.size() >= 4) {
            setPlayerDetails(sortedPlayers.get(3), RoleType.BOT, botName, botRole, botRank,
                    botKills, botDeaths, botAssists, botKdRatio, botEntityFlowPane);
        }
        if (sortedPlayers.size() >= 5) {
            setPlayerDetails(sortedPlayers.get(4), RoleType.SUPPORT, supportName, supportRole, supportRank,
                    supportKills, supportDeaths, supportAssists, supportKdRatio, supportEntityFlowPane);
        }
    }

    /**
     * Gets the role order for sorting (0 = TOP, 1 = JUNGLE, 2 = MID, 3 = BOT, 4 = SUPPORT).
     */
    private int getRoleOrder(Person player) {
        RoleType role = player.getRole().value;
        return switch (role) {
        case TOP -> 0;
        case JUNGLE -> 1;
        case MID -> 2;
        case BOT -> 3;
        case SUPPORT -> 4;
        };
    }

    /**
     * Sets the player details for a role panel.
     */
    private void setPlayerDetails(Person player, RoleType role, Label nameLabel, Label roleLabel, Label rankLabel,
                                   Label killsLabel, Label deathsLabel, Label assistsLabel,
                                   Label kdLabel, FlowPane entityFlowPane) {
        nameLabel.setText(player.getIgn().toString());
        String fullNameAndIgn = player.getIgn().toString() + " (" + player.getName().fullName + ")";
        Tooltip nameTooltip = new Tooltip(fullNameAndIgn);
        nameLabel.setTooltip(nameTooltip);
        roleLabel.setText(player.getRole().value.toString());
        rankLabel.setText(player.getRank().toString());
        killsLabel.setText(player.getOverallStatistics().getKills().toString());
        deathsLabel.setText(player.getOverallStatistics().getDeaths().toString());
        assistsLabel.setText(player.getOverallStatistics().getAssists().toString());

        double kdRatio = player.getOverallStatistics().getKda();
        kdLabel.setText(String.format("%.2f", kdRatio));

        populateEntityIcons(player, entityFlowPane);
    }

    /**
     * Populates the entity icons grid with buttons for each entity the person has stats for.
     */
    private void populateEntityIcons(Person person, FlowPane entityFlowPane) {
        List<Entity> entities = new ArrayList<>(person.getOverallEntityStatistics().getMap().keySet());
        List<Button> entityButtons = EntityButtonFactory.createEntityButtons(entities);

        for (int i = 0; i < entityButtons.size(); i++) {
            Button entityButton = entityButtons.get(i);
            entityFlowPane.getChildren().add(entityButton);
        }
    }
}
