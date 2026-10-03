public class Problem01_ATMPIN {

    public static void checkPinLength(String pin) {
        if (pin == null || pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        System.out.print("Input: \"482\" -> ");
        checkPinLength("482");

        System.out.print("Input: \"4820\" -> ");
        checkPinLength("4820");
    }
}