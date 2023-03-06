import java.util.Scanner;

public class Dialog {

    static Scanner input = new Scanner(System.in);

    public static String questionWord(String question) {
        System.out.print("\n\t" + question);
        return input.next();
    }

    public static int questionInt(String question) {
        System.out.print("\n\n\t" + question);
        return input.nextInt();
    }

    public static boolean questionYn(String questioString) {
        System.out.println("\n\n\t" + questioString + " (y/n)");
        return answerYn();
    }

    private static boolean answerYn() {
        String choice;
        System.out.print("\n\tEnter your choice: ");
        choice = input.next();

        if(! ((choice.toLowerCase().charAt(0) == 'y') ^ (choice.toLowerCase().charAt(0) == 'n'))){
            InvalidChoice();
            answerYn();
        }

        return (choice.toLowerCase().charAt(0) == 'y');
    }

    public static void InvalidChoice() {
        BoxMessage.printEmptyLine(2);
        BoxMessage.printSingleLine("Invalid choice. Please try again.", 3);
    }
}
