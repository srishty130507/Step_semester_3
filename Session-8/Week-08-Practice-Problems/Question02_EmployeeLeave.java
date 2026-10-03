import java.util.*;

enum LeaveStatus {
    PENDING, APPROVED, REJECTED
}

abstract class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public abstract String getRole();
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name) { super(name); }
    @Override public String getRole() { return "FullTime"; }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name) { super(name); }
    @Override public String getRole() { return "PartTime"; }
}

class LeaveRequest {
    private Employee employee;
    private String startDate;
    private String endDate;
    private LeaveStatus status;

    public LeaveRequest(Employee employee, String startDate, String endDate) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = LeaveStatus.PENDING;

        System.out.printf("Leave request submitted for %s (%s to %s). Status: %s.%n",
                employee.getName(), startDate, endDate, capitalize(status.name()));
    }

    public void approve(String reviewerName) {
        if (status != LeaveStatus.PENDING) {
            System.out.printf("Cannot approve leave request: Current status is %s.%n", capitalize(status.name()));
            return;
        }
        this.status = LeaveStatus.APPROVED;
        System.out.printf("%s's leave request (%s to %s) approved. Status: %s.%n",
                employee.getName(), startDate, endDate, capitalize(status.name()));
    }

    public void reject(String reviewerName) {
        if (status != LeaveStatus.PENDING) {
            System.out.printf("Cannot reject leave request: Current status is %s.%n", capitalize(status.name()));
            return;
        }
        this.status = LeaveStatus.REJECTED;
        System.out.printf("%s's leave request (%s to %s) rejected. Status: %s.%n",
                employee.getName(), startDate, endDate, capitalize(status.name()));
    }

    public void changeStatusToPending() {
        if (status == LeaveStatus.APPROVED || status == LeaveStatus.REJECTED) {
            System.out.printf("Cannot change leave request status from %s to Pending.%n", capitalize(status.name()));
        } else {
            this.status = LeaveStatus.PENDING;
        }
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}

public class Question02_EmployeeLeave {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        LeaveRequest req1 = new LeaveRequest(john, "Jan 1", "Jan 5");
        req1.approve("Alice");

        LeaveRequest req2 = new LeaveRequest(jane, "Feb 10", "Feb 11");
        req2.reject("Bob");

        req1.changeStatusToPending();
    }
}