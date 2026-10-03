import java.util.*;

// Abstract strategy for WashType following the Open/Closed Principle
abstract class WashType {
    private String name;
    private int durationMinutes;
    private double cost;

    public WashType(String name, int durationMinutes, double cost) {
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.cost = cost;
    }

    public String getName() { return name; }
    public int getDurationMinutes() { return durationMinutes; }
    public double getCost() { return cost; }
}

class QuickWash extends WashType {
    public QuickWash() { super("Quick", 30, 20.00); }
}

class NormalWash extends WashType {
    public NormalWash() { super("Normal", 45, 30.00); }
}

class HeavyWash extends WashType {
    public HeavyWash() { super("Heavy", 60, 45.00); }
}

// Extensible design: New wash type added without modifying common logic
class DelicateWash extends WashType {
    public DelicateWash() { super("Delicate", 40, 35.00); }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class WashingMachine {
    private String machineId;
    private boolean isBusy; // Encapsulated status

    public WashingMachine(String machineId) {
        this.machineId = machineId;
        this.isBusy = false;
    }

    public String getMachineId() { return machineId; }
    public boolean isBusy() { return isBusy; }

    // Controlled state changes (Encapsulation)
    public boolean startWash() {
        if (isBusy) return false;
        isBusy = true;
        return true;
    }

    public void completeWash() {
        isBusy = false;
    }
}

class WashCycle {
    private Student student;
    private WashingMachine machine;
    private WashType washType;

    public WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public boolean start() {
        if (machine.startWash()) {
            System.out.printf("%s wash started on %s for %s (%d min). Charge: ₹%.2f.%n",
                    washType.getName(), machine.getMachineId(), student.getName(),
                    washType.getDurationMinutes(), washType.getCost());
            return true;
        } else {
            System.out.printf("Machine %s is currently busy.%n", machine.getMachineId());
            return false;
        }
    }

    public void complete() {
        machine.completeWash();
        System.out.printf("%s cycle completed. %s is now free.%n",
                machine.getMachineId(), machine.getMachineId());
    }
}

public class Question01_HostelLaundry {
    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        WashCycle cycle1 = new WashCycle(asha, m1, new QuickWash());
        cycle1.start();

        WashCycle cycle2 = new WashCycle(ravi, m1, new HeavyWash());
        cycle2.start();

        WashCycle cycle3 = new WashCycle(ravi, m2, new HeavyWash());
        cycle3.start();

        cycle1.complete();

        WashCycle cycle4 = new WashCycle(neha, m1, new NormalWash());
        cycle4.start();
    }
}