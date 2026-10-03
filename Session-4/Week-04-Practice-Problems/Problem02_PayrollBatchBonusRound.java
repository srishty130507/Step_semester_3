import java.util.*;

class Employee {
    String empId;
    double salary;

    public Employee(String empId, double salary) {
        this.empId = empId;
        this.salary = salary;
    }

    public void raiseSalary(double salary) {
        this.salary += salary;
    }
}

public class Problem02_PayrollBatchBonusRound {
    public static void main(String[] args) {
        Employee[] employees = {
            new Employee("E-101", 40000.0),
            new Employee("E-102", 55000.0),
            new Employee("E-103", 62000.0),
            new Employee("E-104", 48000.0)
        };

        for (Employee emp : employees) {
            emp.raiseSalary(5000.0);
            System.out.println(emp.empId + " | Final Salary: Rs " + emp.salary);
        }
    }
}