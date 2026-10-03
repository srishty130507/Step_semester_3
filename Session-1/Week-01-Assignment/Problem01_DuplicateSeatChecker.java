public class Problem01_DuplicateSeatChecker {

    public static void checkDuplicateSeats(int[] seatNumbers) {
        boolean duplicateFound = false;

        // Nested loops using arrays and basic logic only (no Collections)
        for (int i = 0; i < seatNumbers.length; i++) {
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                    duplicateFound = true;
                    break; // Move to the next element once duplicate is flagged
                }
            }
        }

        if (!duplicateFound) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    public static void main(String[] args) {
        int[] input1 = {101, 102, 103, 102, 105};
        System.out.print("Input 1: ");
        checkDuplicateSeats(input1);

        int[] input2 = {101, 102, 103, 104, 105};
        System.out.print("Input 2: ");
        checkDuplicateSeats(input2);
    }
}