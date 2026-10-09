public class Problem05_BankTransaction {

    public static String normalizeReference(String raw) {
        if (raw == null) return "";
        String trimmed = raw.trim();

        if (trimmed.length() < 3) {
            return trimmed;
        }

        // Uppercase only the first 3 characters and leave the rest untouched
        String bankCode = trimmed.substring(0, 3).toUpperCase();
        String rest = trimmed.substring(3);

        return bankCode + rest;
    }

    public static String validateAndFormat(String reference) {
        if (reference == null || reference.length() != 14) {
            return "Invalid: wrong length";
        }

        // Check first 3 characters are letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        // Check remaining 11 characters are digits
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: non-digit body";
            }
        }

        String bankCode = reference.substring(0, 3);
        String datePart = reference.substring(3, 9);
        String seqPart = reference.substring(9, 14);

        String dd = datePart.substring(0, 2);
        String mm = datePart.substring(2, 4);
        String yy = datePart.substring(4, 6);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(bankCode).append("] ");
        sb.append("DATE: ").append(dd).append("/").append(mm).append("/").append(yy);
        sb.append(" | SEQ: ").append(seqPart);

        return sb.toString();
    }

    public static void processReference(String raw) {
        String normalized = normalizeReference(raw);
        String result = validateAndFormat(normalized);
        System.out.println("Input: \"" + raw + "\" -> " + result);
    }

    public static void main(String[] args) {
        processReference(" hdf03022600042 ");
        processReference("12F03022600042");
    }
}