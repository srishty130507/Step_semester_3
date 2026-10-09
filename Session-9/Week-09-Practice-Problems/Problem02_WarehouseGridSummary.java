public class Problem02_WarehouseGridSummary {

    public static class SummaryResult {
        int totalItems;
        int maxRow;
        int maxCol;

        public SummaryResult(int totalItems, int maxRow, int maxCol) {
            this.totalItems = totalItems;
            this.maxRow = maxRow;
            this.maxCol = maxCol;
        }

        @Override
        public String toString() {
            return "(" + totalItems + ", (" + maxRow + ", " + maxCol + "))";
        }
    }

    // Time Complexity: O(m * n), Space Complexity: O(1)
    public static SummaryResult warehouseSummary(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return new SummaryResult(0, -1, -1);
        }

        int totalItems = 0;
        int maxItems = -1;
        int maxRow = 0;
        int maxCol = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                int currentCount = grid[r][c];
                totalItems += currentCount;

                if (currentCount > maxItems) {
                    maxItems = currentCount;
                    maxRow = r;
                    maxCol = c;
                }
            }
        }

        return new SummaryResult(totalItems, maxRow, maxCol);
    }

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };

        System.out.println(warehouseSummary(grid)); // Output: (49, (2, 1))
    }
}