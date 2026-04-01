package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** Comparison panel should be shown to the user. */
    private final boolean showCompare;

    /** First person to compare. */
    private final Person person1;

    /** Second person to compare. */
    private final Person person2;

    /** Draft panel should be shown to the user. */
    private final boolean showDraft;

    /** Players to be displayed in the draft panel. */
    private final List<Person> draftPlayers;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         boolean showCompare, Person person1, Person person2,
                         boolean showDraft, List<Person> draftPlayers) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showCompare = showCompare;
        this.person1 = person1;
        this.person2 = person2;
        this.showDraft = showDraft;
        this.draftPlayers = draftPlayers;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         boolean showCompare, Person person1, Person person2) {
        this(feedbackToUser, showHelp, exit, showCompare, person1, person2, false, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, exit, false, null, null, false, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, null, null, false, null);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isShowCompare() {
        return showCompare;
    }

    public Person getPerson1() {
        return person1;
    }

    public Person getPerson2() {
        return person2;
    }

    public boolean isShowDraft() {
        return showDraft;
    }

    public List<Person> getDraftPlayers() {
        return draftPlayers;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit
                && showCompare == otherCommandResult.showCompare
                && Objects.equals(person1, otherCommandResult.person1)
                && Objects.equals(person2, otherCommandResult.person2)
                && showDraft == otherCommandResult.showDraft
                && Objects.equals(draftPlayers, otherCommandResult.draftPlayers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, showCompare, person1, person2, showDraft, draftPlayers);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("showHelp", showHelp)
                .add("exit", exit)
                .add("showCompare", showCompare)
                .add("showDraft", showDraft)
                .toString();
    }

}
