import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IceCreamParlour {
    
    static Scanner input = new Scanner(System.in);
    static User user = new User();

    static boolean isLogin = false;
    static boolean isExit = false;

    static List<IceCream> avIceCreams = new ArrayList<IceCream>();

    static List<Topping> avToppings = new ArrayList<Topping>();

    static Cart cart = new Cart();

    private static void DefineData(){
        // IceCreams
        IceCream iCream1 = new IceCream();
        iCream1.name = "Vanilla Ice Cream";
        iCream1.price = 15f;
        avIceCreams.add(iCream1);

        IceCream iCream2 = new IceCream();
        iCream2.name = "Chocolate Ice Cream";
        iCream2.price = 20f;
        avIceCreams.add(iCream2);

        IceCream iCream3 = new IceCream();
        iCream3.name = "Strawberry Ice Cream";
        iCream3.price = 25f;
        avIceCreams.add(iCream3);

        // Toppings
        Topping topping1 = new Topping();
        topping1.name = "Rainbow sprinkles";
        topping1.price = 5f;
        avToppings.add(topping1);

        Topping topping2 = new Topping();
        topping2.name = "Chocolate sprinkles";
        topping2.price = 7f;
        avToppings.add(topping2);
    }

    private static void printIceCreamLogo() {
        System.out.println("      @\n    ('')\n   ( ' .)\n  ( *.' .)\n (*.~ . *.)\n {\\######/}\n   \\####/\n    \\##/\n     \\/");
    }
    public static void main(String[] args) {

        DefineData();

        while(!isExit){
            AppRun();
        }

    }

    private static void AppRun() {
        if (isLogin) {
            showShopMenu();
            handleShop();
        } else {
            ShowMainMenu();
            handleMainMenu();
        }
    }

    private static void ShowMainMenu() {
        // Display menu
        BoxMessage.printEmptyLine(2);
        printIceCreamLogo();
        BoxMessage.printSingleLine("Welcome to the Ice Cream Parlour!", 3);

        System.out.println("\n\n============Menu==========");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }

    public static void handleMainMenu(){
        // Get user input
        int choice = Dialog.questionInt("Enter your choice: ");
        // Process user input
        switch (choice) {
            case 1:
                register();
                break;
            case 2:
                login();
                break;
            case 3:
                isLogin = false;
                isExit = true;
                break;
            default:
                Dialog.InvalidChoice();
                break;
        }
    }

    public static void handleShop(){
        // Get user input
        int choice = Dialog.questionInt("Enter your choice: ");

        // Process user input
        switch (choice) {
            case 1:
                Buy();
                break;
            case 2:
                logout();
                break;
            default:
                Dialog.InvalidChoice();
                break;
        }
    }

    public static void register() {
        // Get user input
        user.username = Dialog.questionWord("Enter your username: ");
        user.password = Dialog.questionWord("Enter your password: ");

        try {
            // Write user information to a file
            java.io.File file = new java.io.File("users.txt");
            java.io.PrintWriter output = new java.io.PrintWriter(file);
            output.println(user.username);
            output.println(user.password);
            output.close();

            BoxMessage.printEmptyLine(2);
            BoxMessage.printSingleLine("Registration successful!", 3);
        } catch (java.io.IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void login() {
        // Get user input
        user.username = Dialog.questionWord("Enter your username: ");
        user.password = Dialog.questionWord("Enter your password: ");

        try {
            // Read user information from file
            java.io.File file = new java.io.File("users.txt");
            Scanner input = new Scanner(file);
            String fileUsername = input.nextLine();
            String filePassword = input.nextLine();
            input.close();

            // Check if login information is valid
            if (user.username.equals(fileUsername) && user.password.equals(filePassword)) {
                BoxMessage.printEmptyLine(2);
                BoxMessage.printSingleLine("Login successful!", 3);
                isLogin = true;
            } else {
                System.out.println("Incorrect username or password.");
            }
        } catch (java.io.IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void logout() {
        BoxMessage.printEmptyLine(2);
        BoxMessage.printSingleLine("Thank You! Please Come Again!", 3);
        isLogin = false;
    }

    public static void showShopMenu(){
        System.out.println("\n\n============SHOP MENU============");
        System.out.println("1. BUY");
        System.out.println("2. Logout");
    }

    public static void showIceCreams() {
        //show All Available IceCreams

        System.out.println("\n\n=============Available IceCreams===========");
        int i = 1;
        for ( IceCream icecream: avIceCreams) {
            System.out.println((i++) + ". " + icecream.name + " [" + icecream.price + "]");
        }
    }
    
    // choose one icecream from Available Choice
    public static IceCream handleIceCreamChoice() {
        // Get user input
        int choice = Dialog.questionInt("Enter your choice: ");

        if(avIceCreams.size() < choice || choice <= 0){
            Dialog.InvalidChoice();
            handleIceCreamChoice();
        }

        return IceCream.copy(avIceCreams.get(--choice));
    }

    public static void showToppings() {
        // show All Available Toppings
        System.out.println("\n\n=============Available Toppings===========");
        int i = 1;
        for (Topping topping: avToppings) {
            System.out.println((i++) + ". " + topping.name + " [" + topping.price + "]");
        }
    }

    // choose one topping from available choice
    public static Topping handleToppingChoice() {
        // Get user input
        int choice = Dialog.questionInt("Enter your choice: ");

        if(avToppings.size() < choice || choice <= 0){
            Dialog.InvalidChoice();
            handleToppingChoice();
        }
        
        return avToppings.get(--choice);
        
    }

    public static void buyAnother() {
        //handle buy another y/n
        boolean answer = Dialog.questionYn("Buy Another Ice Cream?");
        if(answer){
            Buy();
        }
    }

    public static void Buy() {

        IceCream currentIceCream =  new IceCream();

        showIceCreams();
        currentIceCream = handleIceCreamChoice();

        boolean answer = Dialog.questionYn("Add Topping?");
        if(answer){
            showToppings();
            currentIceCream.topping = handleToppingChoice();
        }

        cart.iceCream.add(currentIceCream);

        buyAnother();

        showCart();

        if(Dialog.questionYn("Stop ?")){
            isLogin = false;
        }
    }

    public static void showCart() {
        //show All Available IceCreams
        System.out.println("\n\n============ Bill ===========");
        int i = 1;
        float total = 0;
        for ( IceCream icecream: cart.iceCream) {
            boolean haveTopping = icecream.topping.name != null;
            System.out.println((i++) + ". " + icecream.name + (haveTopping ? ("(" + icecream.topping.name + ")") : "") + " [" + icecream.price + (haveTopping ? (" + " + icecream.topping.price + "") : "") + "]");
            total = total + icecream.price + (haveTopping ? icecream.topping.price : 0);
        }
        System.out.println("-----------------------------------------");
        System.out.println("Total: " + total);
    }


}

