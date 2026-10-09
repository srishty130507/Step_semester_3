import java.util.Arrays;

public class Problem04_PairWithTarget {

    // Approach: Returning 1-based indices of the pair if found, else returning empty array
    public static int[] findPairIndices(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i + 1, j + 1}; // Returning 1-based positions
                }
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] result = findPairIndices(nums, target);
        if (result.length == 2) {
            System.out.println("Pair found at positions: " + Arrays.toString(result));
        } else {
            System.out.println("No pair found.");
        }
    }
}