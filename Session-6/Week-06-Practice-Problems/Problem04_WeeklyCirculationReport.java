
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

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public String displayInfo() {
        return "General | Books: " + booksBorrowed;
    }

    public static String batchPrint(LibraryMember[] members) {
        StringBuilder sb = new StringBuilder();

        for (LibraryMember member : members) {
            sb.append(member.displayInfo());

            if (member instanceof StudentMember) {
                StudentMember sm = (StudentMember) member;
                sb.append(" [Course via downcast: ").append(sm.getCourse()).append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
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
        return "Student | Course: " + course + " | Books: " + getBooksBorrowed();
    }
}

public class Problem04_WeeklyCirculationReport {
    public static void main(String[] args) {
        LibraryMember[] members = {
            new LibraryMember("LB5", 3),
            new StudentMember("STU6", 3, "ECE")
        };

        System.out.println(LibraryMember.batchPrint(members));
    }
}