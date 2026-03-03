package jenie.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import jenie.exception.JenieException;

public class Parser {
    public static String getCommandWord(String input) {
        return input.split(" ")[0];
    }

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
