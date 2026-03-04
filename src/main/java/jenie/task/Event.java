package jenie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs within a specific date range.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with a description and start/end dateTimes.
     *
     * @param description The text describing the event.
     * @param from The starting LocalDateTime of the event.
     * @param to The ending LocalDateTime of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation for file saving, including date ranges.
     *
     * @return A formatted string with the 'E' type identifier and start/end dateTimes.
     */
    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a")) + ")";
    }
}
