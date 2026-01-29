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

            if (input.equals("bye")) {
                System.out.println("Goodbye. Hope to see you again soon!");
                break;
            }

            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i].toString());
                }
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5));
                tasks[index - 1].mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + tasks[index - 1]);
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7));
                tasks[index - 1].unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" " + tasks[index - 1]);
            } else {
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println("added: " + input);
            }
        }
        scanner.close();
    }
}
