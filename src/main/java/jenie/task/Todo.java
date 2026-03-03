package jenie.task;

/**
 * Represents a task that has no specific deadline or time frame.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the given description.
     *
     * @param description The text describing the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the task for file storage.
     *
     * @return A formatted string suitable for saving to the hard disk.
     */
    @Override
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
