public class Problem02_TypingAccuracyChecker {

    public static void checkTypingAccuracy(String original, String typed) {
        int matchedCount = 0;
        int firstMismatchPos = -1;
        char originalChar = ' ';
        char typedChar = ' ';

        int length = Math.min(original.length(), typed.length());

        for (int i = 0; i < length; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matchedCount++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1; // 1-based index position
                originalChar = original.charAt(i);
                typedChar = typed.charAt(i);
            }
        }

        double accuracy = ((double) matchedCount / original.length()) * 100;

        System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | ", matchedCount, original.length(), accuracy);

        if (firstMismatchPos != -1) {
            System.out.printf("First Mismatch at position %d ('%c' vs '%c')%n", firstMismatchPos, originalChar, typedChar);
        } else {
            System.out.println("No Mismatches");
        }
    }

    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");
    }
}