import java.util.Scanner;

public class Jenie {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String greetings = "  ______                  O      \n"
                + " |__   _|  ___    _____   _    ___      \n"
                + " _  | |   / __\\  |  _  | | |  / __\\    \n"
                + "| |_| |  |  __/  | | | | | | |  __/     \n"
                + "|_____/   \\___|  |_| |_| |_|  \\___|    \n";

        System.out.println("Hello I'm\n" + greetings);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Goodbye. Hope to see you again soon!");
                break;
            }
            System.out.println(input);
        }
        scanner.close();
    }
}
