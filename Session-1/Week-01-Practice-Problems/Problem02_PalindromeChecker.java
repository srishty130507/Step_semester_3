public class Problem02_PalindromeChecker {

    
    public static boolean isPalindromeIterative(String text) {
        text = text.toLowerCase();
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindromeRecursive(String text) {
        text = text.toLowerCase();
        if (text.length() <= 1) {
            return true;
        }
        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    
    public static boolean isPalindromeArrayReversal(String text) {
        text = text.toLowerCase();
        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];

        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }

        return new String(original).equals(new String(reversed));
    }

    public static void testWord(String word) {
        String iterRes = isPalindromeIterative(word) ? "Palindrome" : "Not Palindrome";
        String recurRes = isPalindromeRecursive(word) ? "Palindrome" : "Not Palindrome";
        String arrayRes = isPalindromeArrayReversal(word) ? "Palindrome" : "Not Palindrome";

        System.out.println("Input: \"" + word + "\"");
        System.out.println("Iterative: " + iterRes + " | Recursive: " + recurRes + " | Array Reversal: " + arrayRes);
        System.out.println();
    }

    public static void main(String[] args) {
        testWord("madam");
        testWord("hello");
    }
}