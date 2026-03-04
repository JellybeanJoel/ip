package jenie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that must be completed by a specific date.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with a description and a due date.
     *
     * @param description The text describing the deadline.
     * @param by The LocalDate representing when the task is due.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation for file saving, including the date.
     *
     * @return A formatted string with the 'D' type identifier and ISO date.
     */
    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a")) + ")";
    }
}
