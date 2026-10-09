import java.util.Arrays;

public class Problem02_MergingTwoToken {

    // Two-Pointer Merge
    // Time Complexity: O(m + n)
    // Space Complexity: O(m + n) to hold the combined result
    // Comparison: Better than concat + sort O((m+n) log(m+n))
    public static int[] mergeTokens(int[] counterA, int[] counterB) {
        int m = counterA.length;
        int n = counterB.length;
        int[] merged = new int[m + n];

        int i = 0, j = 0, k = 0;

        while (i < m && j < n) {
            if (counterA[i] <= counterB[j]) {
                merged[k++] = counterA[i++];
            } else {
                merged[k++] = counterB[j++];
            }
        }

        while (i < m) {
            merged[k++] = counterA[i++];
        }

        while (j < n) {
            merged[k++] = counterB[j++];
        }

        return merged;
    }

    public static void main(String[] args) {
        int[] counterA = {3, 8, 15, 20};
        int[] counterB = {5, 8, 12};
        System.out.println(Arrays.toString(mergeTokens(counterA, counterB))); 
        // Expected: [3, 5, 8, 8, 12, 15, 20]

        int[] emptyA = {};
        int[] counterB2 = {4, 9};
        System.out.println(Arrays.toString(mergeTokens(emptyA, counterB2))); 
        // Expected: [4, 9]
    }
}