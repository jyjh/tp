package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.util.Pair;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandRegistry;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.PARAMETERS));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        for (Pair<Class<? extends Command>, Optional<Class<? extends Parser<?>>>>
            commandPair : CommandRegistry.COMMAND_CLASSES) {
            try {
                if (commandWord.equals((String) commandPair.getKey()
                    .getDeclaredField("COMMAND_WORD").get(null))) {
                    return commandPair.getValue()
                    .map(x -> {
                        try {
                            return (Command) (x.getConstructor().newInstance().parse(arguments));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        } catch (Exception e) {
                            logger.severe(e.toString());
                            return null;
                        }
                    })
                    .orElseGet(() -> {
                        try {
                            return commandPair.getKey().getConstructor().newInstance();
                        } catch (Exception e) {
                            return null;
                        }
                    });
                }
            } catch (RuntimeException e) {
                if (e.getCause() instanceof ParseException) {
                    logger.finer("This user input caused a ParseException: " + userInput);
                    throw (ParseException) e.getCause();
                }
                logger.severe("Exception encountered in AddressBookParser." + e.toString());
            } catch (Exception e) {
                logger.severe("Exception encountered in AddressBookParser." + e.toString());
            }
        }
        logger.finer("This user input caused a ParseException: " + userInput);
        throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
    }
}
