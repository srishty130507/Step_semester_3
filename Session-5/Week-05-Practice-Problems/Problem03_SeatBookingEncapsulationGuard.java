import java.util.*;

class CineScreen {
    private int seatsTotal;
    private int seatsAvailable;

    public CineScreen(int seatsTotal) {
        if (seatsTotal <= 0) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.seatsTotal = seatsTotal;
        this.seatsAvailable = seatsTotal;
    }

    public void bookSeat() {
        if (seatsAvailable > 0) {
            seatsAvailable--;
        }
    }

    public void cancelBooking() {
        if (seatsAvailable < seatsTotal) {
            seatsAvailable++;
        }
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }
}

public class Problem03_SeatBookingEncapsulationGuard {
    public static void main(String[] args) {
        // Validation check
        try {
            new CineScreen(0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Booking overflow check
        CineScreen c = new CineScreen(2);
        c.bookSeat();
        c.bookSeat();
        c.bookSeat(); // 3rd booking rejected
        System.out.println(c.getSeatsAvailable()); // 0

        // Cancellation underflow check
        c.cancelBooking();
        c.cancelBooking();
        c.cancelBooking(); // 3rd cancellation rejected
        System.out.println(c.getSeatsAvailable()); // 2
    }
}