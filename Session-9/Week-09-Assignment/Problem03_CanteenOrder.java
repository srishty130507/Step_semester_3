import java.util.HashMap;
import java.util.Map;

public class Problem03_CanteenOrder {

    // Hash Map Counting
    // Time Complexity: O(N) where N = number of orders
    // Space Complexity: O(U) where U = unique item names
    // Scan-again method would take O(N^2) time.
    public static Object[] mostPopular(String[] orders) {
        Map<String, Integer> counts = new HashMap<>();

        for (String item : orders) {
            counts.put(item, counts.getOrDefault(item, 0) + 1);
        }

        int maxCount = 0;
        String popularItem = "";

        // First pass iteration preserves original order for ties
        for (String item : orders) {
            if (counts.get(item) > maxCount) {
                maxCount = counts.get(item);
                popularItem = item;
            }
        }

        return new Object[]{popularItem, maxCount};
    }

    public static void main(String[] args) {
        String[] orders1 = {"dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"};
        Object[] res1 = mostPopular(orders1);
        System.out.println("(\"" + res1[0] + "\", " + res1[1] + ")");
        // Expected: ("dosa", 3)

        String[] orders2 = {"tea", "coffee", "coffee", "tea"};
        Object[] res2 = mostPopular(orders2);
        System.out.println("(\"" + res2[0] + "\", " + res2[1] + ")");
        // Expected: ("tea", 2)
    }
}