import java.util.*;

class Employee {
    String empName;
    double salary;
    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    public Employee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    public static void printCompanyInfo() {
        System.out.println("Company: " + companyName + " | Total Employees: " + employeeCount);
    }
}

public class Problem05_EmployeeCompany {
    public static void main(String[] args) {
        Employee e1 = new Employee("Divya", 65000.0);
        Employee e2 = new Employee("Arjun", 50000.0);
        Employee e3 = new Employee("Rohan", 55000.0);

        Employee.printCompanyInfo();
    }
}