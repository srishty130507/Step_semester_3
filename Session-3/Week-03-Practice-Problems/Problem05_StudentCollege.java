import java.util.*;

class Student {
    String name;
    double attendance;
    static String collegeName = "SRM Institute of Science and Technology";
    static int studentCount = 0;

    public Student(String name, double attendance) {
        this.name = name;
        this.attendance = attendance;
        studentCount++;
    }

    public static void printCollegeInfo() {
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }
}

public class Problem05_StudentCollege {
    public static void main(String[] args) {
        Student s1 = new Student("Ravi", 85.0);
        Student s2 = new Student("Anitha", 90.0);

        Student.printCollegeInfo();
    }
}