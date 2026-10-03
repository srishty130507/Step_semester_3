import java.util.*;

class GymMember {
    private static int counter = 2000;
    private static int totalEnrolled = 0;

    private final String membershipNumber;
    private int monthlyFee;
    private int feesPaid = 0;

    public GymMember(int monthlyFee) {
        counter++;
        totalEnrolled++;
        this.membershipNumber = "GYM-" + counter;
        this.monthlyFee = monthlyFee;
    }

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public static int getMembersEnrolled() {
        return totalEnrolled;
    }

    public void payFee(int amount) {
        this.feesPaid += amount;
    }

    public void payFee(int amount, String mode) {
        // Reuses flat payFee(amount) logic internally
        payFee(amount);
    }

    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'G') {
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

    public static String processWeeklyCheckIn(GymMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        for (GymMember member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (member instanceof GroupClassMember) {
                groupCount++;
            } else {
                individualCount++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + groupCount + " group | " + individualCount + " individual";
    }
}

class GroupClassMember extends GymMember {
    private String className;

    public GroupClassMember(int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}

public class Problem05_MembershipNumbersAndSettlement {
    public static void main(String[] args) {
        GymMember m1 = new GymMember(1000);
        System.out.println(m1.getMembershipNumber());
        System.out.println(GymMember.getMembersEnrolled());

        System.out.println(GymMember.isValidReferralCode("G45B"));
        System.out.println(GymMember.isValidReferralCode("G4B"));
        System.out.println(GymMember.isValidReferralCode("X45B"));

        m1.payFee(500);
        m1.payFee(500, "UPI");
        System.out.println(m1.getFeesPaid());

        GymMember[] checkIns = {
            new GroupClassMember(1500, "Zumba"),
            null,
            new GymMember(1000)
        };
        System.out.println(GymMember.processWeeklyCheckIn(checkIns));
    }
}