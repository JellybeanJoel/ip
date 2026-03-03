package jenie;

import java.util.Scanner;
import java.io.IOException;
import jenie.ui.Ui;
import jenie.storage.Storage;
import jenie.parser.Parser;
import jenie.task.TaskList;
import jenie.task.Todo;
import jenie.task.Deadline;
import jenie.task.Event;
import jenie.task.Task;
import jenie.exception.JenieException;

public class Jenie {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Jenie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError("Error loading file. Starting afresh.");
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printGreetings();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = scanner.nextLine();
            String commandWord = Parser.getCommandWord(fullCommand);

            try {
                switch (commandWord) {
                case "bye":
                    isExit = true;
                    ui.printGoodbye();
                    break;
                case "list":
                    ui.printList(tasks);
                    break;
                case "todo":
                    tasks.addTask(new Todo(fullCommand.substring(5)));
                    ui.printTaskAdded(tasks.getTask(tasks.getSize() - 1), tasks.getSize());
                    storage.save(tasks);
                    break;
                case "deadline":
                    String[] parts = fullCommand.substring(9).split(" /by ");
                    tasks.addTask(new Deadline(parts[0], Parser.parseDate(parts[1])));
                    storage.save(tasks);
                    ui.printTaskAdded(tasks.getTask(tasks.getSize() - 1), tasks.getSize());
                case "event":
                    String[] eParts = Parser.parseEventDetails(fullCommand);
                    tasks.addTask(new Event(eParts[0], Parser.parseDate(eParts[1]), Parser.parseDate(eParts[2])));
                    ui.printTaskAdded(tasks.getTask(tasks.getSize() - 1), tasks.getSize());
                    storage.save(tasks);
                    break;
                case "delete":
                    int index = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
                    Task removed = tasks.deleteTask(index);
                    ui.printTaskDeleted(removed, tasks.getSize());
                    storage.save(tasks);
                    break;
                case "find":
                    if (fullCommand.length() <= 5) {
                        throw new JenieException("Oopsies! Please specify a keyword to find.");
                    }
                    String keyword = fullCommand.substring(5).trim();
                    TaskList results = tasks.findTasks(keyword);
                    ui.printSearchResults(results);
                    break;
                default:
                    throw new JenieException("Oopsies! My apologies, but I don't know what that means. womp womp :(");
                }
            } catch(Exception e){
                ui.showError(e.getMessage());
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Jenie("./data/jenie.txt").run();
    }
}
