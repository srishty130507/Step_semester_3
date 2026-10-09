
public class Problem01_ClassTopperFinder {

    // Returns [rowIndex, totalMarks]
    // Time Complexity: O(m * n) where m = students, n = subjects
    // Space Complexity: O(1) additional space
    public static int[] findTopper(int[][] marks) {
        int maxTotal = -1;
        int topperIndex = -1;

        for (int i = 0; i < marks.length; i++) {
            int currentTotal = 0;
            for (int j = 0; j < marks[i].length; j++) {
                currentTotal += marks[i][j];
            }

            // Strictly greater ensures tie-breaking goes to the smallest row index
            if (currentTotal > maxTotal) {
                maxTotal = currentTotal;
                topperIndex = i;
            }
        }

        return new int[]{topperIndex, maxTotal};
    }

    public static void main(String[] args) {
        int[][] marks = {
            {78, 85, 90},
            {88, 92, 79},
            {65, 70, 95}
        };

        int[] result = findTopper(marks);
        System.out.println("Topper (rowIndex, total): (" + result[0] + ", " + result[1] + ")");
        // Expected Output: (1, 259)
    }
}