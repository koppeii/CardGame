import java.util.Scanner;

public class Input {
    private static Scanner scn = new Scanner(System.in);

    private Input() {}

    public static String getUserString(String prompt) {
        System.out.print(prompt);
        return scn.nextLine();
    }

    public static int getUserInt(String prompt) {
        String userStr;
        int userInt = 0;
        boolean validInt = false;
        while (!validInt) {
            userStr = getUserString(prompt);
            try {
                userInt = Integer.parseInt(userStr);
                validInt = true;
            } catch (NumberFormatException e) {
                System.out.println("'" + userStr + "' is not a valid integer. Try again.");
            }
        }
        return userInt;
    }

    public static double getUserDouble(String prompt) {
        String userStr;
        double userDouble = 0.0;
        boolean validDouble = false;
        while (!validDouble) {
            userStr = getUserString(prompt);
            try {
                userDouble = Double.parseDouble(userStr);
                validDouble = true;
            } catch (NumberFormatException e) {
                System.out.println("'" + userStr + "' is not a valid number. Try again.");
            }
        }
        return userDouble;
    }

    public static float getUserFloat(String prompt) {
        return (float)getUserDouble(prompt);
    }

    public static void waitForUserToPressEnter(String prompt) {
        System.out.print(prompt);
        scn.nextLine();
    }

    // helper for the Main menu selection
    public static int getMenuChoice(String prompt, int min, int max) {
        int choice;
        do {
            choice = getUserInt(prompt);
        } while (choice < min || choice > max);
        return choice;
    }
}
