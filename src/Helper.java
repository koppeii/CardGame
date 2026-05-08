public class Helper {
    public static String pluralSuffix(int count) {
        // tried to return a char, weird
        if (count == 1)
            return "";
        return "s";
    }
}
