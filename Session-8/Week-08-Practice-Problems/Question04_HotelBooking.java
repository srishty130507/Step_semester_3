import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

abstract class Room {
    private String roomNumber;

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() { return roomNumber; }
    public abstract String getCategoryName();
    public abstract double calculatePrice(long nights);
}

class StandardRoom extends Room {
    private static final double NIGHTLY_RATE = 100.0;

    public StandardRoom(String roomNumber) { super(roomNumber); }
    @Override public String getCategoryName() { return "Standard Room"; }
    @Override public double calculatePrice(long nights) { return nights * NIGHTLY_RATE; }
}

class DeluxeRoom extends Room {
    private static final double NIGHTLY_RATE = 180.0;

    public DeluxeRoom(String roomNumber) { super(roomNumber); }
    @Override public String getCategoryName() { return "Deluxe Room"; }
    @Override public double calculatePrice(long nights) { return nights * NIGHTLY_RATE; }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Reservation {
    private Customer customer;
    private Room room;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private boolean isCancelled;

    public Reservation(Customer customer, Room room, LocalDate checkIn, LocalDate checkOut) {
        this.customer = customer;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.isCancelled = false;
    }

    public Room getRoom() { return room; }
    public LocalDate getCheckIn() { return checkIn; }
    public LocalDate getCheckOut() { return checkOut; }
    public boolean isCancelled() { return isCancelled; }

    public boolean overlapsWith(LocalDate start, LocalDate end) {
        if (isCancelled) return false;
        return checkIn.isBefore(end) && start.isBefore(checkOut);
    }

    public double calculatePrice() {
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        return room.calculatePrice(nights);
    }

    public void cancel(LocalDate currentDate) {
        if (currentDate.isAfter(checkIn.minusDays(1))) { // Deadline rule
            System.out.println("Cancellation failed: Past deadline.");
            return;
        }
        this.isCancelled = true;
        System.out.printf("Reservation for %s, %s %s (%s to %s) cancelled successfully.%n",
                customer.getName(), room.getCategoryName(), room.getRoomNumber(), checkIn, checkOut);
    }
}

class HotelManager {
    private List<Reservation> activeReservations = new ArrayList<>();

    public boolean isRoomAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
        for (Reservation res : activeReservations) {
            if (res.getRoom().getRoomNumber().equals(room.getRoomNumber()) && res.overlapsWith(checkIn, checkOut)) {
                return false;
            }
        }
        return true;
    }

    public Reservation reserveRoom(Customer customer, Room room, LocalDate checkIn, LocalDate checkOut) {
        if (!isRoomAvailable(room, checkIn, checkOut)) {
            System.out.printf("%s %s is not available from %s to %s.%n",
                    room.getCategoryName(), room.getRoomNumber(), checkIn, checkOut);
            return null;
        }

        Reservation res = new Reservation(customer, room, checkIn, checkOut);
        activeReservations.add(res);
        System.out.printf("Reservation confirmed for %s, %s %s (%s to %s). Price: $%.2f.%n",
                customer.getName(), room.getCategoryName(), room.getRoomNumber(), checkIn, checkOut, res.calculatePrice());
        return res;
    }
}

public class Question04_HotelBooking {
    public static void main(String[] args) {
        HotelManager manager = new HotelManager();

        Room room101 = new StandardRoom("101");
        Room room201 = new DeluxeRoom("201");

        Customer customerA = new Customer("Customer A");
        Customer customerB = new Customer("Customer B");
        Customer customerC = new Customer("Customer C");

        LocalDate jan1 = LocalDate.of(2026, 1, 1);
        LocalDate jan5 = LocalDate.of(2026, 1, 5);
        LocalDate jan3 = LocalDate.of(2026, 1, 3);
        LocalDate jan7 = LocalDate.of(2026, 1, 7);

        if (manager.isRoomAvailable(room101, jan1, jan5)) {
            System.out.printf("Standard Room 101 is available from %s to %s.%n", jan1, jan5);
        }

        Reservation resA = manager.reserveRoom(customerA, room101, jan1, jan5);
        manager.reserveRoom(customerB, room101, jan3, jan7);

        if (resA != null) {
            resA.cancel(LocalDate.of(2025, 12, 30));
        }

        LocalDate feb10 = LocalDate.of(2026, 2, 10);
        LocalDate feb12 = LocalDate.of(2026, 2, 12);
        manager.reserveRoom(customerC, room201, feb10, feb12);
    }
}