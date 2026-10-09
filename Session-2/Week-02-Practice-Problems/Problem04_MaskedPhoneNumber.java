public class Problem04_MaskedPhoneNumber {

    public static String maskPhoneNumber(String phone) {
        if (phone == null || phone.length() != 10) {
            return "Invalid phone number";
        }

        // Validate that all characters are numeric digits
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }

        String lastFour = phone.substring(6);

        StringBuilder masked = new StringBuilder();
        masked.append("XXXXXX");
        masked.append("-");
        masked.append(lastFour);

        return masked.toString();
    }

    public static void main(String[] args) {
        System.out.println("9876543210 -> " + maskPhoneNumber("9876543210"));
        System.out.println("98765 -> " + maskPhoneNumber("98765"));
    }
}