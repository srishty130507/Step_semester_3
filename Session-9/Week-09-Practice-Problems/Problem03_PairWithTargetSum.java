import java.util.HashSet;
import java.util.Set;

public class Problem03_PairWithTargetSum {

    // Approach: Using HashSet for O(n) Time Complexity
    public static boolean hasPairWithSum(int[] nums, int target) {
        Set<Integer> seen = new HashSet<>();

        for (int num : nums) {
            int complement = target - num;
            if (seen.contains(complement)) {
                return true;
            }
            seen.add(num);
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Has Pair Sum (" + target1 + "): " + hasPairWithSum(nums1, target1)); // true

        int[] nums2 = {3, 4, 6};
        int target2 = 20;
        System.out.println("Has Pair Sum (" + target2 + "): " + hasPairWithSum(nums2, target2)); // false
    }
}