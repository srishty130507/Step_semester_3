public class Problem05_TicketPriceSlotFinder {

    // Binary Search Approach
    // Time Complexity: O(log N)
    // Space Complexity: O(1)
    // Linear scan would take O(N) time.
    public static int findSlot(int[] prices, int newPrice) {
        int low = 0;
        int high = prices.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (prices[mid] == newPrice) {
                return mid;
            } else if (prices[mid] < newPrice) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low; // Position where newPrice should be inserted
    }

    public static void main(String[] args) {
        int[] prices = {120, 150, 200, 260};

        System.out.println(findSlot(prices, 150)); // Expected: 1
        System.out.println(findSlot(prices, 210)); // Expected: 3
        System.out.println(findSlot(prices, 300)); // Expected: 4
    }
}