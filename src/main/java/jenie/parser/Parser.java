package jenie.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import jenie.exception.JenieException;

/**
 * Parses user input strings into meaningful commands and data.
 */
public class Parser {
    /**
     * Extracts the command word from a raw input string.
     *
     * @param input The full line of user input.
     * @return The first word of the input string.
     */
    public static String getCommandWord(String input) {
        return input.split(" ")[0];
    }
    /**
     * Converts a date string into a LocalDate object.
     *
     * @param dateStr The date in yyyy-mm-dd format.
     * @return The parsed LocalDate.
     * @throws JenieException If the date format is invalid.
     */
    public static LocalDate parseDate(String dateStr) throws JenieException {
        try {
            return LocalDate.parse(dateStr.trim());
        } catch (DateTimeParseException e) {
            throw new JenieException("Oopsies! Please use yyyy-mm-dd format for dates (e.g., 2026-03-03).");
        }
    }

    public static String[] parseEventDetails(String input) throws JenieException {
        String content = input.substring(6);
        String[] parts = content.split(" /from | /to ");
        if (parts.length < 3) {
            throw new JenieException("Use: event [desc] /from [date] /to [date]");
        }
        return parts;
    }
}
