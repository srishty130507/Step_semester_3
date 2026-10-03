import java.util.*;

class GymMember {
    private String memberId;
    private int monthlyFee;
    private int sessionsAttended = 0;

    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
            throw new IllegalArgumentException("Invalid memberId");
        }
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    public String getMemberId() {
        return memberId;
    }

    public void attendSession() {
        this.sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public String displayInfo() {
        return "Standard Member | Sessions: " + sessionsAttended;
    }

    public static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof PremiumMember) {
            return "Single-level descendant (2 generations deep)";
        } else {
            return "Base class generation";
        }
    }

    public static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;
        for (GymMember member : members) {
            total += member.getSessionsAttended();
        }
        return total;
    }
}

class PremiumMember extends GymMember {
    private String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium Member | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended();
    }
}

class EliteMember extends PremiumMember {
    private String lockerNumber;

    public EliteMember(String memberId, int monthlyFee, String trainerName, String lockerNumber) {
        super(memberId, monthlyFee, trainerName);
        this.lockerNumber = lockerNumber;
    }

    public String getLockerNumber() {
        return lockerNumber;
    }

    @Override
    public String displayInfo() {
        return "Elite Member | Trainer: " + getTrainerName() + " | Locker: " + lockerNumber + " | Sessions: " + getSessionsAttended();
    }
}

class GroupClassMember extends GymMember {
    private String className;

    public GroupClassMember(String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String displayInfo() {
        return "Group Class Member | Class: " + className + " | Sessions: " + getSessionsAttended();
    }
}

public class Problem02_ThreeTiersGymMembership {
    public static void main(String[] args) {
        GymMember g = new GymMember("MEM1", 1000);
        PremiumMember p = new PremiumMember("MEM2", 2000, "Coach Riya");
        EliteMember e = new EliteMember("MEM3", 3000, "Coach Arjun", "L12");
        GroupClassMember gc = new GroupClassMember("MEM4", 1500, "Zumba");

        System.out.println(g.displayInfo());
        System.out.println(p.displayInfo());
        System.out.println(e.displayInfo());
        System.out.println(gc.displayInfo());

        System.out.println(GymMember.classifyGeneration(e));
        System.out.println(GymMember.classifyGeneration(gc));

        for (int i = 0; i < 3; i++) p.attendSession();
        for (int i = 0; i < 2; i++) e.attendSession();
        for (int i = 0; i < 4; i++) gc.attendSession();

        GymMember[] list = { p, e, gc };
        System.out.println(GymMember.getTotalSessionsAttended(list));
    }
}