package jenie.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import jenie.exception.JenieException;

/**
 * Parses user input strings into meaningful commands and data.
 */
public class Parser {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Extracts the first word (command) from the user's input.
     *
     * @param input The full line of input from the user.
     * @return The first word in lowercase to ensure consistent matching.
     */
    public static String getCommandWord(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "";
        }
        // Use trim() to remove leading spaces and split to get the first word
        return input.trim().split(" ")[0].toLowerCase();
    }

    public static LocalDateTime parseDateTime(String dateTimeStr) throws JenieException {
        try {
            return LocalDateTime.parse(dateTimeStr.trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new JenieException("Oopsies! Please use yyyy-mm-dd HHmm format for dates (e.g., 2026-03-03 1330).");
        }
    }

    public static String[] parseEventDetails(String input) throws JenieException {
        String content = input.substring(6);
        String[] parts = content.split(" /from | /to ");
        if (parts.length < 3) {
            throw new JenieException("Oopsies! Use: event [desc] /from [yyyy-mm-dd HHmm] /to [yyyy-mm-dd HHmm]");
        }
        return parts;
    }
}
