public class Problem04_HotWeatherAlert {

    // Sliding Window Approach
    // Time Complexity: O(N)
    // Space Complexity: O(1)
    // Recalculating from scratch would take O(N * k) time.
    public static int countAlerts(int[] readings, int k, int threshold) {
        if (readings.length < k) return 0;

        int currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += readings[i];
        }

        long targetSum = (long) k * threshold; // Avoid floating point division
        int alertCount = 0;

        if (currentSum >= targetSum) {
            alertCount++;
        }

        for (int i = k; i < readings.length; i++) {
            currentSum += readings[i] - readings[i - k];
            if (currentSum >= targetSum) {
                alertCount++;
            }
        }

        return alertCount;
    }

    public static void main(String[] args) {
        int[] readings = {2, 2, 2, 2, 5, 5, 5, 8};
        int k = 3;
        int threshold = 4;

        System.out.println("Alert Count: " + countAlerts(readings, k, threshold));
        // Expected Output: 3
    }
}