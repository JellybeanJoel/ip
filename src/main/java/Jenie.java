import java.util.Scanner;

public class Jenie {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        String greetings = "  ______                  O      \n"
                + " |__   _|  ___    _____   _    ___      \n"
                + " _  | |   / __\\  |  _  | | |  / __\\    \n"
                + "| |_| |  |  __/  | | | | | | |  __/     \n"
                + "|_____/   \\___|  |_| |_| |_|  \\___|    \n";

        System.out.println("Hello I'm\n" + greetings);
        System.out.println("What can I do for you?");

        while (true) {
            String input = scanner.nextLine();

            try {
                if (input.equals("bye")) {
                    System.out.println("Goodbye. Hope to see you again soon!");
                    break;
                }

                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i].toString());
                    }
                } else if (input.startsWith("mark")) {
                    if (input.length() <= 5 || input.charAt(4) != ' ') {
                        throw new JenieException("Oopsies! Please input mark [integer]");
                    }
                    try {
                        int index = Integer.parseInt(input.substring(5));
                        if (index <= 0 || index > taskCount) {
                            throw new JenieException("Task number " + index + " does not exist in your list.");
                        }
                        tasks[index - 1].mark();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(" " + tasks[index - 1]);
                    } catch (NumberFormatException e) {
                        throw new JenieException("Oopsies! Please input a valid integer.");
                    }
                } else if (input.startsWith("unmark")) {
                    if (input.length() <= 7 || input.charAt(6) != ' ') {
                        throw new JenieException("Oopsies! Please input unmark [integer]");
                    }
                    try {
                        int index = Integer.parseInt(input.substring(7));
                        if (index <= 0 || index > taskCount) {
                            throw new JenieException("Task number " + index + " does not exist in your list.");
                        }
                        tasks[index - 1].unmark();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(" " + tasks[index - 1]);
                    } catch (NumberFormatException e) {
                        throw new JenieException("Oopsies! Please input a valid integer.");
                    }
                } else if (input.startsWith("todo")) {
                    if (input.charAt(4) != ' ') {
                        throw new JenieException("Oopsies! Please input todo [description]");
                    }
                    if (input.length() <= 5) {
                        throw new JenieException("Oopsies! The description of a todo cannot be empty!");
                    }
                    tasks[taskCount] = new Todo(input.substring(5));
                    taskCount++;
                    printStatement(tasks[taskCount - 1], taskCount);
                } else if (input.startsWith("deadline")) {
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
                    tasks[taskCount] = new Deadline(parts[0], parts[1]);
                    taskCount++;
                    printStatement(tasks[taskCount - 1], taskCount);
                } else if (input.startsWith("event")) {
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
                    tasks[taskCount] = new Event(parts[0], parts[1], parts[2]);
                    taskCount++;
                    printStatement(tasks[taskCount - 1], taskCount);
                } else {
                    throw new JenieException("Oopsies! My apologies, but I don't know what " + input + " means. womp womp :(");
                }
            } catch (JenieException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    private static void printStatement(Task task, int count) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + count + " tasks in the list.");
    }
}
