import java.util.Scanner;

public class Jenie {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
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
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else {
                tasks[taskCount] = input;
                taskCount++;
                System.out.println("added: " + input);
            }
        }
        scanner.close();
    }
}
