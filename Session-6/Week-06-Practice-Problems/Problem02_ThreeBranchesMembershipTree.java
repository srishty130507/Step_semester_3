import java.util.*;

class LibraryMember {
    private String memberId;
    private int borrowLimit;
    private int booksBorrowed = 0;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
            throw new IllegalArgumentException("Invalid memberId");
        }
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    public String getMemberId() {
        return memberId;
    }

    public void borrowBook() {
        this.booksBorrowed++;
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public String displayInfo() {
        return "General Member | Books Borrowed: " + booksBorrowed;
    }

    public static String classifyGeneration(LibraryMember member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof StudentMember) {
            return "Single-level descendant (2 generations deep)";
        } else {
            return "Base class generation";
        }
    }

    public static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;
        for (LibraryMember member : members) {
            total += member.getBooksBorrowed();
        }
        return total;
    }
}

class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String displayInfo() {
        return "Student Member | Course: " + course + " | Books Borrowed: " + getBooksBorrowed();
    }
}

class HonorsStudentMember extends StudentMember {
    private int bonusLimit;

    public HonorsStudentMember(String memberId, int borrowLimit, String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    public int getBonusLimit() {
        return bonusLimit;
    }

    @Override
    public String displayInfo() {
        return "Honors Student Member | Course: " + getCourse() + " | Bonus Limit: " + bonusLimit + " | Books Borrowed: " + getBooksBorrowed();
    }
}

class FacultyMember extends LibraryMember {
    private String department;

    public FacultyMember(String memberId, int borrowLimit, String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String displayInfo() {
        return "Faculty Member | Department: " + department + " | Books Borrowed: " + getBooksBorrowed();
    }
}

public class Problem02_ThreeBranchesMembershipTree {
    public static void main(String[] args) {
        LibraryMember g = new LibraryMember("STU1", 3);
        StudentMember s = new StudentMember("STU2", 3, "CSE");
        HonorsStudentMember h = new HonorsStudentMember("STU3", 3, "ECE", 2);
        FacultyMember f = new FacultyMember("STU4", 5, "Physics");

        System.out.println(g.displayInfo());
        System.out.println(s.displayInfo());
        System.out.println(h.displayInfo());
        System.out.println(f.displayInfo());

        System.out.println(LibraryMember.classifyGeneration(h));
        System.out.println(LibraryMember.classifyGeneration(f));

        for (int i = 0; i < 2; i++) s.borrowBook();
        for (int i = 0; i < 1; i++) h.borrowBook();
        for (int i = 0; i < 3; i++) f.borrowBook();

        LibraryMember[] list = { s, h, f };
        System.out.println(LibraryMember.getTotalBooksBorrowed(list));
    }
}