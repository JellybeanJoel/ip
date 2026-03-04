package jenie.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import jenie.task.Todo;
import jenie.task.Deadline;
import jenie.task.Event;
import jenie.task.Task;
import jenie.task.TaskList;

/**
 * Handles the loading and saving of task data to the local file system.
 */
public class Storage {
    /**
     * Saves the current task list to the hard disk.
     *
     * @param taskList The list of tasks to be persisted.
     * @throws IOException If there is an error writing to the file.
     */
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        File file = new File(filePath);
        ArrayList<Task> loadedTasks = new ArrayList<>();
        if (!file.exists()) {
            return loadedTasks;
        }

        Scanner s = new Scanner(file);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] p = line.split(" \\| ");
            Task task = null;
            switch (p[0]) {
            case "T":
                task = new Todo(p[2]);
                break;
            case "D":
//                task = new Deadline(p[2], LocalDateTime.parse(p[3]));
                LocalDateTime deadlineTime;
                try {
                    deadlineTime = LocalDateTime.parse(p[3]);
                } catch (DateTimeParseException e) {
                    // Fallback: If it's an old file with only a date, add a default time (00:00)
                    deadlineTime = LocalDate.parse(p[3]).atStartOfDay();
                }
                task = new Deadline(p[2], deadlineTime);
                break;
            case "E":
//                task = new Event(p[2], LocalDateTime.parse(p[3]), LocalDateTime.parse(p[4]));
                LocalDateTime fromTime;
                LocalDateTime toTime;
                try {
                    // Attempt to parse as new format (LocalDateTime)
                    fromTime = LocalDateTime.parse(p[3]);
                    toTime = LocalDateTime.parse(p[4]);
                } catch (DateTimeParseException e) {
                    // Fallback for old format (LocalDate only)
                    // .atStartOfDay() converts 2026-03-03 to 2026-03-03T00:00
                    fromTime = LocalDate.parse(p[3]).atStartOfDay();
                    toTime = LocalDate.parse(p[4]).atStartOfDay();
                }
                task = new Event(p[2], fromTime, toTime);
                break;
            }
            if (task != null && p[1].equals("1")) {
                task.markAsDone();
            }
            if (task != null) {
                loadedTasks.add(task);
            }
        }
        s.close();
        return loadedTasks;
    }

    public void save(TaskList taskList) throws IOException {
        File f = new File(filePath);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        FileWriter fw = new FileWriter(filePath);
        for (Task task : taskList.getAllTasks()) {
            fw.write(task.toFileFormat() + System.lineSeparator());
        }
        fw.close();
    }
}
