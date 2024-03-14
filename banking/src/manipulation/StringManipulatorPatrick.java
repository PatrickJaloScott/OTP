package manipulation;

public class StringManipulatorPatrick {
    public static String reverse(String input) {
        StringBuilder reverser = new StringBuilder();
        for (int i = input.length()-1; i >= 0; i--) {
            reverser.append(input.charAt(i));
        }
        return reverser.toString();
    }

    public static String capitalize(String input) {
        return input.toUpperCase();
    }
}
