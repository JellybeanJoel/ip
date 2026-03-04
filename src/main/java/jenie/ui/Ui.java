package jenie.ui;

import jenie.task.Task;
import jenie.task.TaskList;

public class Ui {
    public void printGreetings() {
        String greetings = "  ______                  O      \n"
                + " |__   _|  ___    _____   _    ___      \n"
                + " _  | |   / __\\  |  _  | | |  / __\\    \n"
                + "| |_| |  |  __/  | | | | | | |  __/     \n"
                + "|_____/   \\___|  |_| |_| |_|  \\___|    \n";

        System.out.println("Hello I'm\n" + greetings);
        System.out.println("What can I do for you?");
    }

    public void printGoodbye() {
        System.out.println("Goodbye. Hope to see you again soon!");
    }

    public void printList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i).toString());
        }
    }

    public void printTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void printTaskDeleted(Task task, int size) {
        System.out.println("Removed: " + task);
        System.out.println("Now you have " + size + " tasks.");
    }

    public void printSearchResults(TaskList results) {
        if (results.getSize() == 0) {
            System.out.println("No matching tasks found in your list.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < results.getSize(); i++) {
                System.out.println((i + 1) + "." + results.getTask(i));
            }
        }
    }

    public void printMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + task);
    }

    public void printUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + task);
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
