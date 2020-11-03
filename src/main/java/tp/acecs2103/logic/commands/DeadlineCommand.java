package tp.acecs2103.logic.commands;

import static java.util.Objects.requireNonNull;

import tp.acecs2103.logic.commands.exceptions.CommandException;
import tp.acecs2103.model.Model;
import tp.acecs2103.model.exceptions.InvalidTaskListOperationException;
import tp.acecs2103.model.task.CustomizedDeadline;
import tp.acecs2103.model.task.Index;


public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_SUCCESS = "Modified deadline for the task %1$s.\n Customized deadline set to:";

    public static final String MESSAGE_USAGE = COMMAND_WORD
                + ": Set customised deadline for the task identified by the index number.\n"
            + "Parameters: i/INDEX (in the form of 0 + two-digit week number + two-digit task number e.g. 01205)\n"
            + "c/CUSTOMISED_DEADLINE (in the form of YYYY-MM-DD, note the boundary for year, months, days)\n"
            + "Note that customised deadline set CANNOT be later than official deadline if the task if NOT overdue.\n"
            + "Example: " + COMMAND_WORD + " i/0101 c/2020-08-25";

    private final Index targetIndex;
    private final CustomizedDeadline newDeadline;

    /**
     * Creates a {@code DeadlineCommand} with given index and newDeadline
     */
    public DeadlineCommand(Index targetIndex, CustomizedDeadline newDeadline) {
        requireNonNull(targetIndex);
        requireNonNull(newDeadline);
        this.targetIndex = targetIndex;
        this.newDeadline = newDeadline;
    }

    public Index getTargetIndex() {
        return targetIndex;
    }

    public CustomizedDeadline getNewDeadline() {
        return newDeadline;
    }

    @Override
    public CommandResult execute(Model model) throws InvalidTaskListOperationException, CommandException {
        requireNonNull(model);
        model.deadlineTask(targetIndex, newDeadline);
        return new CommandResult(String.format(MESSAGE_SUCCESS, targetIndex.value) + newDeadline.value);
    }
}
