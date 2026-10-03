import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Assignment {
    private String title;
    private int maxMarks;
    private LocalDate dueDate;

    public Assignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() { return title; }
    public int getMaxMarks() { return maxMarks; }
    public LocalDate getDueDate() { return dueDate; }

    // Abstract method to apply late penalty polymorphically
    public abstract double calculateFinalMarks(double rawMarks, long daysLate);
    public abstract double getPenaltyPercentagePerDay();
}

class CodingAssignment extends Assignment {
    public CodingAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    @Override
    public double calculateFinalMarks(double rawMarks, long daysLate) {
        if (daysLate <= 0) return rawMarks;
        double penalty = rawMarks * (0.10 * daysLate);
        return Math.max(0, rawMarks - penalty);
    }

    @Override
    public double getPenaltyPercentagePerDay() { return 10.0; }
}

class WrittenAssignment extends Assignment {
    public WrittenAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    @Override
    public double calculateFinalMarks(double rawMarks, long daysLate) {
        if (daysLate <= 0) return rawMarks;
        double penalty = rawMarks * (0.20 * daysLate);
        return Math.max(0, rawMarks - penalty);
    }

    @Override
    public double getPenaltyPercentagePerDay() { return 20.0; }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

enum SubmissionStatus {
    SUBMITTED, GRADED
}

class Submission {
    private Student student;
    private Assignment assignment;
    private LocalDate submissionDate;
    private SubmissionStatus status;
    private double finalMarks;

    public Submission(Student student, Assignment assignment, LocalDate submissionDate) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
        this.status = SubmissionStatus.SUBMITTED;
        
        long daysLate = ChronoUnit.DAYS.between(assignment.getDueDate(), submissionDate);
        if (daysLate > 0) {
            System.out.printf("%s's submission for '%s' received (%d days late). Status: %s.%n",
                    student.getName(), assignment.getTitle(), daysLate, status);
        } else {
            System.out.printf("%s's submission for '%s' received (on time). Status: %s.%n",
                    student.getName(), assignment.getTitle(), status);
        }
    }

    public void grade(double awardedMarks) {
        if (status == SubmissionStatus.GRADED) {
            System.out.printf("Cannot resubmit: '%s' has already been graded.%n", assignment.getTitle());
            return;
        }

        long daysLate = ChronoUnit.DAYS.between(assignment.getDueDate(), submissionDate);
        if (daysLate < 0) daysLate = 0;

        this.finalMarks = assignment.calculateFinalMarks(awardedMarks, daysLate);
        this.status = SubmissionStatus.GRADED;

        if (daysLate > 0) {
            int totalPenaltyPercent = (int) (daysLate * assignment.getPenaltyPercentagePerDay());
            System.out.printf("%s graded: %d/%d after %d%% late penalty. Status: %s.%n",
                    student.getName(), (int) finalMarks, assignment.getMaxMarks(), totalPenaltyPercent, status);
        } else {
            System.out.printf("%s graded: %d/%d. Status: %s.%n",
                    student.getName(), (int) finalMarks, assignment.getMaxMarks(), status);
        }
    }

    public void resubmit(LocalDate newSubmissionDate) {
        if (status == SubmissionStatus.GRADED) {
            System.out.printf("Cannot resubmit: '%s' has already been graded.%n", assignment.getTitle());
        } else {
            this.submissionDate = newSubmissionDate;
            System.out.printf("%s resubmitted work for '%s'.%n", student.getName(), assignment.getTitle());
        }
    }
}

public class Question02_AssignmentPortal {
    public static void main(String[] args) {
        Assignment linkedListLab = new CodingAssignment("Linked List Lab", 50, LocalDate.of(2026, 3, 10));
        Assignment designEssay = new WrittenAssignment("Design Essay", 50, LocalDate.of(2026, 3, 12));

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Submission ashaSub = new Submission(asha, linkedListLab, LocalDate.of(2026, 3, 10));
        Submission raviSub = new Submission(ravi, designEssay, LocalDate.of(2026, 3, 14));

        ashaSub.grade(45);
        raviSub.grade(40);

        ashaSub.resubmit(LocalDate.of(2026, 3, 15));
    }
}