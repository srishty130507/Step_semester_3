import java.util.*;

abstract class Vehicle {
    private String name;
    private String licensePlate;
    private boolean isAvailable;

    public Vehicle(String name, String licensePlate) {
        this.name = name;
        this.licensePlate = licensePlate;
        this.isAvailable = true;
    }

    public String getName() { return name; }
    public String getLicensePlate() { return licensePlate; }
    public boolean isAvailable() { return isAvailable; }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public abstract double calculateRentalCharge(int days);
}

class Sedan extends Vehicle {
    private static final double DAILY_RATE = 50.0;

    public Sedan(String name, String licensePlate) {
        super(name, licensePlate);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * DAILY_RATE;
    }
}

class SUV extends Vehicle {
    private static final double DAILY_RATE = 80.0;

    public SUV(String name, String licensePlate) {
        super(name, licensePlate);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * DAILY_RATE;
    }
}

class Truck extends Vehicle {
    private static final double DAILY_RATE = 110.0;

    public Truck(String name, String licensePlate) {
        super(name, licensePlate);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * DAILY_RATE;
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Rental {
    private Customer customer;
    private Vehicle vehicle;
    private int days;
    private double charge;

    public Rental(Customer customer, Vehicle vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
    }

    public boolean processRental() {
        if (!vehicle.isAvailable()) {
            System.out.printf("%s is currently unavailable.%n", vehicle.getName());
            return false;
        }

        vehicle.setAvailable(false);
        this.charge = vehicle.calculateRentalCharge(days);
        System.out.printf("%s rented successfully by %s. Rental charge: $%.2f.%n",
                vehicle.getName(), customer.getName(), charge);
        return true;
    }

    public void returnVehicle() {
        vehicle.setAvailable(true);
        System.out.printf("%s returned by %s.%n", vehicle.getName(), customer.getName());
    }
}

public class Question01_VehicleRental {
    public static void main(String[] args) {
        Customer customer1 = new Customer("Customer 1");
        Customer customer2 = new Customer("Customer 2");
        Customer customer3 = new Customer("Customer 3");

        Vehicle sedanA = new Sedan("Sedan A", "SED-101");
        Vehicle suvB = new SUV("SUV B", "SUV-202");

        Rental rental1 = new Rental(customer1, sedanA, 3);
        rental1.processRental();

        Rental rental2 = new Rental(customer2, sedanA, 2);
        rental2.processRental();

        rental1.returnVehicle();

        Rental rental3 = new Rental(customer3, suvB, 5);
        rental3.processRental();
    }
}