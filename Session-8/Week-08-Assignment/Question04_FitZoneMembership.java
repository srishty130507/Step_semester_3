abstract class MembershipPlan {
    private String planName;
    private int durationMonths;
    private double discountPercentage;

    public MembershipPlan(String planName, int durationMonths, double discountPercentage) {
        this.planName = planName;
        this.durationMonths = durationMonths;
        this.discountPercentage = discountPercentage;
    }

    public String getPlanName() { return planName; }

    public double calculateFee(double baseMonthlyRate) {
        double totalBase = baseMonthlyRate * durationMonths;
        return totalBase * (1.0 - discountPercentage);
    }
}

class MonthlyPlan extends MembershipPlan {
    public MonthlyPlan() { super("Monthly", 1, 0.0); }
}

class QuarterlyPlan extends MembershipPlan {
    public QuarterlyPlan() { super("Quarterly", 3, 0.10); }
}

class AnnualPlan extends MembershipPlan {
    public AnnualPlan() { super("Annual", 12, 0.25); }
}

// Extensible design: New Half-Yearly plan added without modifying existing logic
class HalfYearlyPlan extends MembershipPlan {
    public HalfYearlyPlan() { super("Half-Yearly", 6, 0.15); }
}

class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

enum MembershipStatus {
    ACTIVE, FROZEN, EXPIRED
}

class Membership {
    private Member member;
    private MembershipPlan plan;
    private double fee;
    private MembershipStatus status;
    private static final double BASE_RATE = 1000.0;

    public Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.fee = plan.calculateFee(BASE_RATE);
        this.status = MembershipStatus.ACTIVE;

        System.out.printf("%s membership created for %s. Fee: ₹%.2f. Status: %s.%n",
                plan.getPlanName(), member.getName(), fee, capitalize(status.name()));
    }

    public void checkIn() {
        if (status == MembershipStatus.ACTIVE) {
            System.out.printf("%s checked in successfully.%n", member.getName());
        } else {
            System.out.printf("Check-in denied: %s's membership is %s.%n",
                    member.getName(), capitalize(status.name()));
        }
    }

    public void freeze() {
        if (status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot freeze an Expired membership.");
        } else if (status == MembershipStatus.ACTIVE) {
            status = MembershipStatus.FROZEN;
            System.out.printf("%s's membership frozen. Status: %s.%n",
                    member.getName(), capitalize(status.name()));
        }
    }

    public void unfreeze() {
        if (status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot unfreeze an Expired membership.");
        } else if (status == MembershipStatus.FROZEN) {
            status = MembershipStatus.ACTIVE;
            System.out.printf("%s's membership unfrozen. Status: %s.%n",
                    member.getName(), capitalize(status.name()));
        }
    }

    public void expire() {
        status = MembershipStatus.EXPIRED;
        System.out.printf("%s's membership expired. Status: %s.%n",
                member.getName(), capitalize(status.name()));
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}

public class Question04_FitZoneMembership {
    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership ashaMem = new Membership(asha, new QuarterlyPlan());
        Membership raviMem = new Membership(ravi, new MonthlyPlan());

        ashaMem.checkIn();
        ashaMem.freeze();
        ashaMem.checkIn();

        raviMem.expire();
        raviMem.freeze();
    }
}