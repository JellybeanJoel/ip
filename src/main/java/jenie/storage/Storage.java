package jenie.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
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
            Task t = null;
            switch (p[0]) {
            case "T":
                t = new Todo(p[2]);
                break;
            case "D":
                t = new Deadline(p[2], LocalDate.parse(p[3]));
                break;
            case "E":
                t = new Event(p[2], LocalDate.parse(p[3]), LocalDate.parse(p[4])); // Expand to LocalDate similarly if needed
                break;
            }
            if (t != null && p[1].equals("1")) {
                t.markAsDone();
            }
            if (t != null) {
                loadedTasks.add(t);
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
        for (Task t : taskList.getAllTasks()) {
            fw.write(t.toFileFormat() + System.lineSeparator());
        }
        fw.close();
    }
}
