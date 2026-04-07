package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandRegistry;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2526s2-cs2103t-w09-2.github.io/tp/UserGuide.html";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label urlLabel;

    @FXML
    private TableView<Pair<String, String>> commandTable;

    @FXML
    private TableColumn<Pair<String, String>, String> commandColumn;

    @FXML
    private TableColumn<Pair<String, String>, String> usageColumn;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        urlLabel.setText("Refer to the user guide: " + USERGUIDE_URL);
        setupCommandTable();
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }

    /**
     * Sets up the command table with all available commands and their usage.
     */
    private void setupCommandTable() {
        ObservableList<Pair<String, String>> commandData = FXCollections.observableArrayList();

        for (Pair<Class<? extends seedu.address.logic.commands.Command>,
                java.util.Optional<Class<? extends seedu.address.logic.parser.Parser<?>>>>
            commandPair : CommandRegistry.COMMAND_CLASSES) {
            try {
                String commandWord = (String) commandPair.getKey()
                    .getDeclaredField("COMMAND_WORD").get(null);
                String messageUsage = (String) commandPair.getKey()
                    .getDeclaredField("MESSAGE_USAGE").get(null);
                String parametersString = (String) commandPair.getKey()
                    .getDeclaredField("PARAMETERS").get(null);
                String exampleString = (String) commandPair.getKey()
                    .getDeclaredField("EXAMPLE").get(null);
                messageUsage = messageUsage + "\n" + parametersString + exampleString;
                commandData.add(new Pair<>(commandWord, messageUsage));
            } catch (Exception e) {
                logger.severe("Error getting help string for command: " + e.toString());
            }
        }

        commandTable.setItems(commandData);
        commandTable.setFixedCellSize(-1); // Enable variable row heights

        commandColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
        usageColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        // Enable text wrapping for usage column using Text node
        usageColumn.setCellFactory(column -> new WrappedTableCell());

        // Apply cell factory to command column for consistent styling
        commandColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                }
            }
        });
    }

    /**
     * A custom TableCell that uses a Text node for proper text wrapping and dynamic height calculation.
     */
    private class WrappedTableCell extends TableCell<Pair<String, String>, String> {
        private final Text textNode;

        WrappedTableCell() {
            textNode = new Text();
            textNode.setFill(javafx.scene.paint.Color.WHITE);
            textNode.wrappingWidthProperty().bind(usageColumn.widthProperty().subtract(10));
            itemProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    textNode.setText(newVal);
                } else {
                    textNode.setText("");
                }
                setGraphic(textNode);
            });
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
                setPrefHeight(0);
            } else {
                textNode.setText(item);
                setGraphic(textNode);
                // Calculate height based on text layout
                double height = textNode.getLayoutBounds().getHeight() + 10; // +10 for padding
                setPrefHeight(height);
            }
            if (getTableRow() != null) {
                getTableRow().requestLayout();
            }
        }
    }
}
