import java.util.*;

abstract class Question {
    private String id;
    private String text;
    private int maxPoints;

    public Question(String id, String text, int maxPoints) {
        this.id = id;
        this.text = text;
        this.maxPoints = maxPoints;
    }

    public String getId() { return id; }
    public String getText() { return text; }
    public int getMaxPoints() { return maxPoints; }

    public abstract boolean evaluate(String answer);
}

class MultipleChoiceQuestion extends Question {
    private String correctAnswer;

    public MultipleChoiceQuestion(String id, String text, int maxPoints, String correctAnswer) {
        super(id, text, maxPoints);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer != null ? answer.trim() : "");
    }
}

class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    public TrueFalseQuestion(String id, String text, int maxPoints, boolean correctAnswer) {
        super(id, text, maxPoints);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return Boolean.parseBoolean(answer) == correctAnswer;
    }
}

class Examination {
    private String title;
    private List<Question> questions = new ArrayList<>();

    public Examination(String title) {
        this.title = title;
    }

    public String getTitle() { return title; }
    public void addQuestion(Question q) { questions.add(q); }
    public List<Question> getQuestions() { return questions; }

    public int getTotalPossibleScore() {
        int sum = 0;
        for (Question q : questions) sum += q.getMaxPoints();
        return sum;
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

enum AttemptStatus {
    IN_PROGRESS, SUBMITTED
}

class Attempt {
    private Student student;
    private Examination exam;
    private Map<String, String> answers = new HashMap<>();
    private AttemptStatus status;

    public Attempt(Student student, Examination exam) {
        this.student = student;
        this.exam = exam;
        this.status = AttemptStatus.IN_PROGRESS;
        System.out.printf("%s started by %s.%n", exam.getTitle(), student.getName());
    }

    public void recordAnswer(Question question, String answer) {
        if (status == AttemptStatus.SUBMITTED) {
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }
        answers.put(question.getId(), answer);
        System.out.printf("Answer recorded for Question %s.%n", question.getId());
    }

    public void submit() {
        if (status == AttemptStatus.SUBMITTED) {
            System.out.println("Examination already submitted.");
            return;
        }

        this.status = AttemptStatus.SUBMITTED;
        System.out.printf("%s submitted by %s.%n", exam.getTitle(), student.getName());

        int totalScore = 0;
        List<String> results = new ArrayList<>();

        for (Question q : exam.getQuestions()) {
            String studentAns = answers.get(q.getId());
            boolean isCorrect = q.evaluate(studentAns);
            int score = isCorrect ? q.getMaxPoints() : 0;
            totalScore += score;

            results.add(String.format("Question %s: %s (%d points)",
                    q.getId(), isCorrect ? "Correct" : "Incorrect", score));
        }

        System.out.printf("Result: %s. Total score: %d/%d.%n",
                String.join(", ", results), totalScore, exam.getTotalPossibleScore());
    }
}

public class Question03_OnlineExam {
    public static void main(String[] args) {
        Examination examA = new Examination("Exam A");
        Question q1 = new MultipleChoiceQuestion("1", "Select Option", 5, "C");
        Question q2 = new TrueFalseQuestion("2", "Is Sky Blue?", 5, false); // Correct answer False for test case

        examA.addQuestion(q1);
        examA.addQuestion(q2);

        Student student1 = new Student("Student 1");

        Attempt attempt = new Attempt(student1, examA);
        attempt.recordAnswer(q1, "C");
        attempt.recordAnswer(q2, "True");

        attempt.submit();

        attempt.recordAnswer(q1, "A");
    }
}