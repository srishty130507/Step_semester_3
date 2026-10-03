import java.util.*;

class LibraryMember {
    private static int counter = 100;
    private static int totalEnrolled = 0;

    public final String memberNumber;
    private int borrowLimit;
    private int booksBorrowed = 0;

    public LibraryMember(int borrowLimit) {
        counter++;
        totalEnrolled++;
        this.memberNumber = "LIB-" + counter;
        this.borrowLimit = borrowLimit;
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public static int getMembersEnrolled() {
        return totalEnrolled;
    }

    public void borrowBook() {
        this.booksBorrowed++;
    }

    public void borrowBook(String genre) {
        // Reuses no-argument borrowBook() logic internally
        borrowBook();
    }

    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'R') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        if (!Character.isUpperCase(code.charAt(3))) {
            return false;
        }
        return true;
    }

    public static String processNightlyAudit(LibraryMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int facultyCount = 0;
        int regularCount = 0;

        for (LibraryMember member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (member instanceof FacultyMember) {
                facultyCount++;
            } else {
                regularCount++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + facultyCount + " faculty | " + regularCount + " regular";
    }
}

class FacultyMember extends LibraryMember {
    private String department;

    public FacultyMember(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}

public class Problem05_MembershipNumbersAndAudit {
    public static void main(String[] args) {
        LibraryMember m1 = new LibraryMember(3);
        System.out.println(m1.memberNumber);
        System.out.println(LibraryMember.getMembersEnrolled());

        System.out.println(LibraryMember.isValidRenewalCode("R12A"));
        System.out.println(LibraryMember.isValidRenewalCode("R1A"));
        System.out.println(LibraryMember.isValidRenewalCode("X12A"));

        m1.borrowBook();
        m1.borrowBook("Fiction");
        System.out.println(m1.getBooksBorrowed());

        LibraryMember[] batch = {
            new FacultyMember(5, "Physics"),
            null,
            new LibraryMember(3)
        };
        System.out.println(LibraryMember.processNightlyAudit(batch));
    }
}