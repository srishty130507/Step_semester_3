import java.util.*;

class LibraryMember {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    public LibraryMember() {
        // Public no-argument constructor
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {
        // Write-once property: only takes effect on the first call
        if (this.membershipId == null) {
            this.membershipId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {
        // Simple deterministic one-way transformation (e.g., hash code string)
        if (answer != null) {
            this.securityAnswer = String.valueOf(answer.hashCode());
        }
    }
    // No getter exists for securityAnswer anywhere in this class
}

public class Problem04_LibraryMemberJavaBean {
    public static void main(String[] args) {
        LibraryMember m = new LibraryMember();
        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);
        System.out.println(m.getMembershipId()); // LIB-8841

        m.setMembershipId("FAKE-0000"); // Ignored
        System.out.println(m.getMembershipId()); // LIB-8841

        System.out.println(m.isPremiumMember()); // true

        m.setSecurityAnswer("BlueMountain"); // Write-only
    }
}