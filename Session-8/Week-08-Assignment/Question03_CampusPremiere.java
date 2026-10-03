import java.util.*;

abstract class Seat {
    private String seatNumber;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() { return seatNumber; }
    public abstract double getPrice();
}

class RegularSeat extends Seat {
    public RegularSeat(String seatNumber) { super(seatNumber); }
    @Override public double getPrice() { return 150.0; }
}

class PremiumSeat extends Seat {
    public PremiumSeat(String seatNumber) { super(seatNumber); }
    @Override public double getPrice() { return 250.0; }
}

class ReclinerSeat extends Seat {
    public ReclinerSeat(String seatNumber) { super(seatNumber); }
    @Override public double getPrice() { return 400.0; }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Show {
    private String movieName;
    private String time;
    private boolean hasStarted;
    private Map<String, Seat> bookedSeats = new HashMap<>();

    public Show(String movieName, String time) {
        this.movieName = movieName;
        this.time = time;
        this.hasStarted = false;
    }

    public boolean isShowStarted() { return hasStarted; }

    public boolean isSeatAvailable(String seatNumber) {
        return !bookedSeats.containsKey(seatNumber);
    }

    public boolean reserveSeats(List<Seat> seats) {
        for (Seat seat : seats) {
            if (bookedSeats.containsKey(seat.getSeatNumber())) {
                System.out.printf("Seat %s is already booked for this show.%n", seat.getSeatNumber());
                return false;
            }
        }
        for (Seat seat : seats) {
            bookedSeats.put(seat.getSeatNumber(), seat);
        }
        return true;
    }

    public void releaseSeats(List<Seat> seats) {
        for (Seat seat : seats) {
            bookedSeats.remove(seat.getSeatNumber());
        }
    }
}

class Booking {
    private Customer customer;
    private Show show;
    private List<Seat> bookedSeats;
    private boolean isCancelled;

    public Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.bookedSeats = new ArrayList<>(seats);
        this.isCancelled = false;
    }

    public boolean processBooking() {
        if (bookedSeats.size() > 6) {
            System.out.println("Booking failed: Maximum 6 seats allowed per booking.");
            return false;
        }

        if (show.reserveSeats(bookedSeats)) {
            double total = calculateTotal();
            List<String> seatNums = new ArrayList<>();
            for (Seat s : bookedSeats) seatNums.add(s.getSeatNumber());
            System.out.printf("Booking confirmed for %s: %s. Total: ₹%.2f.%n",
                    customer.getName(), String.join(", ", seatNums), total);
            return true;
        }
        return false;
    }

    public double calculateTotal() {
        double sum = 0;
        for (Seat s : bookedSeats) sum += s.getPrice();
        return sum;
    }

    public void cancelBooking() {
        if (show.isShowStarted()) {
            System.out.println("Cannot cancel booking: Show has already started.");
            return;
        }
        if (isCancelled) {
            System.out.println("Booking is already cancelled.");
            return;
        }

        show.releaseSeats(bookedSeats);
        isCancelled = true;

        List<String> seatNums = new ArrayList<>();
        for (Seat s : bookedSeats) seatNums.add(s.getSeatNumber());
        System.out.printf("%s's booking cancelled. Seats %s released.%n",
                customer.getName(), String.join(", ", seatNums));
    }
}

public class Question03_CampusPremiere {
    public static void main(String[] args) {
        Show show7PM = new Show("Evening Movie", "7 PM");

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        // Asha books A1, A2 (Regular) and F5 (Premium)
        List<Seat> ashaSeats = Arrays.asList(
                new RegularSeat("A1"),
                new RegularSeat("A2"),
                new PremiumSeat("F5")
        );
        Booking ashaBooking = new Booking(asha, show7PM, ashaSeats);
        ashaBooking.processBooking();

        // Ravi attempts to book A2
        List<Seat> raviAttempt1 = Arrays.asList(new RegularSeat("A2"));
        Booking raviBooking1 = new Booking(ravi, show7PM, raviAttempt1);
        raviBooking1.processBooking();

        // Ravi books R1 (Recliner)
        List<Seat> raviAttempt2 = Arrays.asList(new ReclinerSeat("R1"));
        Booking raviBooking2 = new Booking(ravi, show7PM, raviAttempt2);
        raviBooking2.processBooking();

        // Asha cancels
        ashaBooking.cancelBooking();

        // Neha books A2
        List<Seat> nehaSeats = Arrays.asList(new RegularSeat("A2"));
        Booking nehaBooking = new Booking(neha, show7PM, nehaSeats);
        nehaBooking.processBooking();
    }
}