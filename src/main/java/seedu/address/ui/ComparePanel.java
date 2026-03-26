package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.address.model.person.Person;

/**
 * A UI component that displays a side-by-side comparison of two players.
 */
public class ComparePanel extends UiPart<HBox> {

    private static final String FXML = "ComparePanel.fxml";
    private static final String STYLE_BETTER = "indicator-better";
    private static final String STYLE_WORSE = "indicator-worse";
    private static final String STYLE_EQUAL = "indicator-equal";
    private static final String ARROW_UP = " ▲";
    private static final String ARROW_DOWN = " ▼";

    @FXML
    private Label player1Name;
    @FXML
    private Label player1Role;
    @FXML
    private Label player1Rank;
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

    /**
     * Creates a {@code ComparePanel} with the given two players to compare.
     */
    public ComparePanel(Person player1, Person player2) {
        super(FXML);
        setPlayer1Details(player1);
        setPlayer2Details(player2);
        setComparisons(player1, player2);
    }

    /**
     * Sets the details for Player 1.
     */
    private void setPlayer1Details(Person player) {
        String displayName = player.getIgn().toString() + " (" + player.getName().fullName + ")";
        player1Name.setText(displayName);
        player1Role.setText(player.getRole().toString());
        player1Rank.setText(player.getRank().toString());
        player1Kills.setText(player.getStatistics().getKills().toString());
        player1Deaths.setText(player.getStatistics().getDeaths().toString());
        player1Assists.setText(player.getStatistics().getAssists().toString());

        double kdRatio = player.getStatistics().getKda();
        player1KdRatio.setText(String.format("%.2f", kdRatio));
    }

    /**
     * Sets the details for Player 2.
     */
    private void setPlayer2Details(Person player) {
        String displayName = player.getIgn().toString() + " (" + player.getName().fullName + ")";
        player2Name.setText(displayName);
        player2Role.setText(player.getRole().toString());
        player2Rank.setText(player.getRank().toString());
        player2Kills.setText(player.getStatistics().getKills().toString());
        player2Deaths.setText(player.getStatistics().getDeaths().toString());
        player2Assists.setText(player.getStatistics().getAssists().toString());

        double kdRatio = player.getStatistics().getKda();
        player2KdRatio.setText(String.format("%.2f", kdRatio));
    }

    /**
     * Sets comparison indicators between the two players.
     */
    private void setComparisons(Person player1, Person player2) {
        // Compare Kills
        int kills1 = Integer.parseInt(player1.getStatistics().getKills().toString());
        int kills2 = Integer.parseInt(player2.getStatistics().getKills().toString());
        setIndicators(player1KillsIndicator, player2KillsIndicator, kills1, kills2);

        // Compare Deaths (lower is better)
        int deaths1 = Integer.parseInt(player1.getStatistics().getDeaths().toString());
        int deaths2 = Integer.parseInt(player2.getStatistics().getDeaths().toString());
        setIndicatorsReverse(player1DeathsIndicator, player2DeathsIndicator, deaths1, deaths2);

        // Compare Assists
        int assists1 = Integer.parseInt(player1.getStatistics().getAssists().toString());
        int assists2 = Integer.parseInt(player2.getStatistics().getAssists().toString());
        setIndicators(player1AssistsIndicator, player2AssistsIndicator, assists1, assists2);

        // Compare KDA Ratio
        double kd1 = player1.getStatistics().getKda();
        double kd2 = player2.getStatistics().getKda();
        setIndicators(player1KdIndicator, player2KdIndicator, kd1, kd2);
    }

    /**
     * Sets indicators for a field where higher is better.
     */
    private void setIndicators(Label indicator1, Label indicator2, double value1, double value2) {
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
