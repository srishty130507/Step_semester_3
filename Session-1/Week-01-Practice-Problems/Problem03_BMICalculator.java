public class Problem03_BMICalculator {

    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            return "Normal";
        } else if (bmi >= 25.0 && bmi <= 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("=================================================================");
        System.out.printf("%-8s | %-12s | %-12s | %-8s | %-12s%n", "Person", "Height (m)", "Weight (kg)", "BMI", "Status");
        System.out.println("=================================================================");

        for (int i = 0; i < heights.length; i++) {
            double height = heights[i];
            double weight = weights[i];
            double bmi = weight / (height * height);
            String status = getBmiStatus(bmi);

            System.out.printf("Person %-2d | %-12.2f | %-12.2f | %-8.2f | %-12s%n", 
                    (i + 1), height, weight, bmi, status);
        }
        System.out.println("=================================================================");
    }

    public static void main(String[] args) {
        // Sample data for a team of employees
        double[] heights = {1.75, 1.60, 1.80, 1.65, 1.70, 1.55, 1.85, 1.72, 1.68, 1.78};
        double[] weights = {70.0, 90.0, 65.0, 50.0, 85.0, 45.0, 95.0, 72.0, 68.0, 82.0};

        printWellnessReport(heights, weights);
    }
}