import java.util.*;

interface NotificationChannel {
    void send(Student student, Notice notice);
}

class EmailChannel implements NotificationChannel {
    @Override
    public void send(Student student, Notice notice) {
        System.out.printf("[Email → %s] %s%n", student.getName(), notice.getTitle());
    }
}

class SmsChannel implements NotificationChannel {
    @Override
    public void send(Student student, Notice notice) {
        System.out.printf("[SMS → %s] %s%n", student.getName(), notice.getTitle());
    }
}

class AppChannel implements NotificationChannel {
    @Override
    public void send(Student student, Notice notice) {
        System.out.printf("[App → %s] %s%n", student.getName(), notice.getTitle());
    }
}

// Extensible design: WhatsApp channel added seamlessly
class WhatsAppChannel implements NotificationChannel {
    @Override
    public void send(Student student, Notice notice) {
        System.out.printf("[WhatsApp → %s] %s%n", student.getName(), notice.getTitle());
    }
}

class Student {
    private String name;
    private String department;
    private List<NotificationChannel> preferredChannels;

    public Student(String name, String department) {
        this.name = name;
        this.department = department;
        this.preferredChannels = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public List<NotificationChannel> getPreferredChannels() { return preferredChannels; }

    public void addPreferredChannel(NotificationChannel channel) {
        preferredChannels.add(channel);
    }
}

class Notice {
    private String title;
    private List<String> targetDepartments;

    public Notice(String title, List<String> targetDepartments) {
        this.title = title;
        this.targetDepartments = targetDepartments != null ? targetDepartments : new ArrayList<>();
    }

    public String getTitle() { return title; }
    public List<String> getTargetDepartments() { return targetDepartments; }

    public boolean isValid() {
        return title != null && !title.trim().isEmpty() && !targetDepartments.isEmpty();
    }
}

class NoticeBoard {
    private List<Student> registeredStudents = new ArrayList<>();

    public void registerStudent(Student student) {
        registeredStudents.add(student);
    }

    public void postNotice(Notice notice) {
        if (notice.getTitle() == null || notice.getTitle().trim().isEmpty()) {
            System.out.println("Cannot post notice: Title is required.");
            return;
        }

        if (notice.getTargetDepartments() == null || notice.getTargetDepartments().isEmpty()) {
            System.out.println("Cannot post notice: At least one target department is required.");
            return;
        }

        System.out.printf("Notice '%s' posted to %s.%n",
                notice.getTitle(), String.join(", ", notice.getTargetDepartments()));

        for (Student student : registeredStudents) {
            if (notice.getTargetDepartments().contains(student.getDepartment())) {
                for (NotificationChannel channel : student.getPreferredChannels()) {
                    channel.send(student, notice);
                }
            }
        }
    }
}

public class Question05_NoticeBroadcaster {
    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();

        Student asha = new Student("Asha", "CSE");
        asha.addPreferredChannel(new EmailChannel());
        asha.addPreferredChannel(new AppChannel());

        Student ravi = new Student("Ravi", "ECE");
        ravi.addPreferredChannel(new SmsChannel());

        board.registerStudent(asha);
        board.registerStudent(ravi);

        Notice notice1 = new Notice("Lab Closed Tomorrow", Arrays.asList("CSE"));
        board.postNotice(notice1);

        Notice notice2 = new Notice("Fee Deadline Extended", Arrays.asList("CSE", "ECE"));
        board.postNotice(notice2);

        Notice notice3 = new Notice("Sports Day", Collections.emptyList());
        board.postNotice(notice3);
    }
}