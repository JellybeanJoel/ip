package jenie;

import java.util.Scanner;
import java.util.ArrayList;
import jenie.exception.JenieException;
import jenie.task.Deadline;
import jenie.task.Event;
import jenie.task.Task;
import jenie.task.Todo;

public class Jenie {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printGreetings();

        while (true) {
            String input = scanner.nextLine();

            try {
                if (input.equals("bye")) {
                    System.out.println("Goodbye. Hope to see you again soon!");
                    break;
                }
                processCommand(input);
            } catch (JenieException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    private static void processCommand(String input) throws JenieException {
        if (input.equals("list")) {
            showList();
        } else if(input.startsWith("mark")) {
            handleMark(input);
        } else if (input.startsWith("unmark")) {
            handleUnmark(input);
        } else if (input.startsWith("todo")) {
            handleTodo(input);
        } else if (input.startsWith("deadline")) {
            handleDeadline(input);
        } else if (input.startsWith("event")) {
            handleEvent(input);
        } else if (input.startsWith("delete")) {
            handleDelete(input);
        } else {
            throw new JenieException("Oopsies! My apologies, but I don't know what " + input + " means. womp womp :(");
        }
    }

    private static void printGreetings() {
        String greetings = "  ______                  O      \n"
                + " |__   _|  ___    _____   _    ___      \n"
                + " _  | |   / __\\  |  _  | | |  / __\\    \n"
                + "| |_| |  |  __/  | | | | | | |  __/     \n"
                + "|_____/   \\___|  |_| |_| |_|  \\___|    \n";

        System.out.println("Hello I'm\n" + greetings);
        System.out.println("What can I do for you?");
    }

    private static void showList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    private static void handleMark(String input) throws JenieException {
        if (input.length() <= 5 || input.charAt(4) != ' ') {
            throw new JenieException("Oopsies! Please input mark [integer]");
        }
        try {
            int index = Integer.parseInt(input.substring(5)) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new JenieException("Task number " + (index + 1) + " does not exist in your list.");
            }
            tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" " + tasks.get(index));
        } catch (NumberFormatException e) {
            throw new JenieException("Oopsies! Please input a valid integer.");
        }
    }

    private static void handleUnmark(String input) throws JenieException {
        if (input.length() <= 7 || input.charAt(6) != ' ') {
            throw new JenieException("Oopsies! Please input unmark [integer]");
        }
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new JenieException("Task number " + (index + 1) + " does not exist in your list.");
            }
            tasks.get(index).unmarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(" " + tasks.get(index));
        } catch (NumberFormatException e) {
            throw new JenieException("Oopsies! Please input a valid integer.");
        }
    }

    private static void handleTodo(String input) throws JenieException {
        if (input.charAt(4) != ' ') {
            throw new JenieException("Oopsies! Please input todo [description]");
        }
        if (input.length() <= 5) {
            throw new JenieException("Oopsies! The description of a todo cannot be empty!");
        }
        tasks.add(new Todo(input.substring(5)));
        printTaskAdded(tasks.get(tasks.size() - 1));
    }

    private static void handleDeadline(String input) throws JenieException {
        if (input.charAt(8) != ' ') {
            throw new JenieException("Oopsies! Please input deadline [description] /by [date/time]");
        }
        if (input.length() <= 9) {
            throw new JenieException("Oopsies! The description of a deadline cannot be empty!");
        }
        String content = input.substring(9);
        if (!content.contains(" /by ")) {
            throw new JenieException("Oopsies! Please input deadline [description] /by [date/time]");
        }
        String[] parts = content.split(" /by ");
        tasks.add(new Deadline(parts[0], parts[1]));
        printTaskAdded(tasks.get(tasks.size() - 1));
    }

    private static void handleEvent(String input) throws JenieException {
        if (input.charAt(5) != ' ') {
            throw new JenieException("Oopsies! Please input event [description] /from [date/time] /to [date/time]");
        }
        if (input.length() <= 6) {
            throw new JenieException("Oopsies! The description of an event cannot be empty!");
        }
        String content = input.substring(6);
        if (!content.contains(" /from ") || !content.contains(" /to ")) {
            throw new JenieException("Oopsies! Please input event [description] /from [date/time] /to [date/time]");
        }
        String[] parts = content.split(" /from | /to ");
        tasks.add(new Event(parts[0], parts[1], parts[2]));
        printTaskAdded(tasks.get(tasks.size() - 1));
    }

    private static void handleDelete(String input) throws JenieException {
        if (input.length() <= 7 || input.charAt(6) != ' ') {
            throw new JenieException("Oopsies! Please input delete [integer]");
        }
        try {
            int index = Integer.parseInt(input.substring(7)) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new JenieException("Task number " + (index + 1) + " does not exist in your list.");
            }
            Task removedTask = tasks.remove(index);
            System.out.println("OK, I've removed this task:");
            System.out.println(" " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            throw new JenieException("Oopsies! Please input a valid integer.");
        }
    }

    private static void printTaskAdded(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}
