package seedu.address.logic.commands;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.util.Pair;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.parser.AddCommandParser;
import seedu.address.logic.parser.DeleteCommandParser;
import seedu.address.logic.parser.EditCommandParser;
import seedu.address.logic.parser.FilterCommandParser;
import seedu.address.logic.parser.FindCommandParser;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.StatsCommandParser;

/**
 * Registry for all available commands in the application.
 * Provides a single source of truth for command classes that both
 * Parser and HelpCommand can access.
 */
public class CommandRegistry {

    /**
     * Array of all available command classes.
     * This list should be updated whenever a new command is added.
     */
    public static final List<Pair<Class<? extends Command>, Optional<Class<? extends Parser<?>>>>>
        COMMAND_CLASSES = List.of(
        new Pair<Class<? extends Command>, Optional<Class<? extends Parser<?>>>>(
            AddCommand.class, Optional.of(AddCommandParser.class)),
        new Pair<Class<? extends Command>, Optional<Class<? extends Parser<?>>>>(
            ClearCommand.class, Optional.empty()),
        new Pair<Class<? extends Command>, Optional<Class<? extends Parser<?>>>>(
            DeleteCommand.class, Optional.of(DeleteCommandParser.class)),
        new Pair<Class<? extends Command>, Optional<Class<? extends Parser<?>>>>(
            EditCommand.class, Optional.of(EditCommandParser.class)),
        new Pair<Class<? extends Command>, Optional<Class<? extends Parser<?>>>>(
            ExitCommand.class, Optional.empty()),
        new Pair<Class<? extends Command>, Optional<Class<? extends Parser<?>>>>(
            FilterCommand.class, Optional.of(FilterCommandParser.class)),
        new Pair<Class<? extends Command>, Optional<Class<? extends Parser<?>>>>(
            FindCommand.class, Optional.of(FindCommandParser.class)),
        new Pair<Class<? extends Command>, Optional<Class<? extends Parser<?>>>>(
            HelpCommand.class, Optional.empty()),
        new Pair<Class<? extends Command>, Optional<Class<? extends Parser<?>>>>(
            ListCommand.class, Optional.empty()),
        new Pair<Class<? extends Command>, Optional<Class<? extends Parser<?>>>>(
            StatsCommand.class, Optional.of(StatsCommandParser.class))
    );

    private static final Logger logger = LogsCenter.getLogger(CommandRegistry.class);

    // Private constructor to prevent instantiation
    private CommandRegistry() {
        throw new AssertionError("CommandRegistry should not be instantiated");
    }

    public static String getHelp() {
        String output = "";
        for (Pair<Class<? extends Command>, Optional<Class<? extends Parser<?>>>>
            commandPair : CommandRegistry.COMMAND_CLASSES) {
            try {
                output += (String) commandPair.getKey()
                    .getDeclaredField("MESSAGE_USAGE").get(null);
                output += "\n";
            } catch (Exception e) {
                try {
                    output += (String) commandPair.getKey()
                        .getDeclaredField("COMMAND_WORD").get(null);
                    output += "\n";
                } catch (Exception ex) {
                    logger.severe("Error getting help string for command: " + ex.toString());
                }
            }
        }
        return output;
    }
}
