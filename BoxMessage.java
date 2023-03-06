public class BoxMessage {

    // public static void main(String[] args) {
    //     printSingleLine("Login!");
    //     printSingleLine("Login!", 1);
    // }
    public static void printEmptyLine(int noOfLines) {
        System.out.print(repeatString("\n", noOfLines));;
    }

    public static void printSingleLine(String message) {
        String seperatoString = seperatorString(message.length());
        String messageString = "| " + message + " |";
        printSingleLine(messageString, seperatoString);
    }

    public static void printSingleLine(String message, int tabStart) {
        String tabSpace = repeatString("\t", tabStart);
        String seperatoString = tabSpace + seperatorString(message.length());
        String messageString = tabSpace + "| " + message + " |";

        printSingleLine(messageString, seperatoString);
    }

    private static void printSingleLine(String message, String seperator) {
        System.out.println(seperator);
        System.out.println(message);
        System.out.println(seperator);
    }

    // returns 
    private static String seperatorString(int messageLength) {
        return "+" + repeatString("-", messageLength + 2) + "+";
    }

    // Returns a repeated Sequence of String
    private static String repeatString(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++){
            sb.append(str);
        }
        return sb.toString();
    }

    
}
