import java.util.*;

class BookInventory {
    private int copiesTotal;
    private int copiesAvailable;

    public BookInventory(int copiesTotal) {
        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    public void checkOut() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    public void checkIn() {
        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }
}

public class Problem03_BookCopyCirculationGuard {
    public static void main(String[] args) {
        BookInventory b = new BookInventory(3);
        b.checkOut(); 
        b.checkOut(); 
        b.checkOut();
        b.checkOut(); // 4th attempt - silently rejected
        System.out.println(b.getCopiesAvailable()); // 0

        b.checkIn(); 
        b.checkIn(); 
        b.checkIn();
        b.checkIn(); // 4th attempt - silently rejected
        System.out.println(b.getCopiesAvailable()); // 3
    }
}