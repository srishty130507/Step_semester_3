import java.util.*;

class LibraryMember {
    private String membershipPin;
    String branchCode;         // default / package-private
    protected int finesOwed;
    public String displayName;

    public LibraryMember(String membershipPin, String branchCode, int finesOwed, String displayName) {
        this.membershipPin = membershipPin;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}

class AccessChecker {
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "public":
                return "ALLOWED";
            case "private":
                return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
            case "default":
                return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE")) ? "ALLOWED" : "DENIED";
            case "protected":
                return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE")) ? "ALLOWED" : "DENIED";
            default:
                return "DENIED";
        }
    }

    public static String summarizeByModifier(String[][] attempts) {
        String[] modifiers = {"private", "default", "protected", "public"};
        Map<String, Integer> allowedMap = new LinkedHashMap<>();
        Map<String, Integer> deniedMap = new LinkedHashMap<>();

        for (String mod : modifiers) {
            allowedMap.put(mod, 0);
            deniedMap.put(mod, 0);
        }

        for (String[] attempt : attempts) {
            String mod = attempt[0];
            String ctx = attempt[1];
            String result = classifyAccess(mod, ctx);

            if (result.equals("ALLOWED")) {
                allowedMap.put(mod, allowedMap.get(mod) + 1);
            } else {
                deniedMap.put(mod, deniedMap.get(mod) + 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < modifiers.length; i++) {
            String mod = modifiers[i];
            sb.append(mod).append(": ")
              .append(allowedMap.get(mod)).append(" allowed / ")
              .append(deniedMap.get(mod)).append(" denied");
            if (i < modifiers.length - 1) {
                sb.append(" | ");
            }
        }
        return sb.toString();
    }
}

public class Problem01_MembershipFieldReachChecker {
    public static void main(String[] args) {
        System.out.println(AccessChecker.classifyAccess("private", "SAME_CLASS"));
        System.out.println(AccessChecker.classifyAccess("protected", "DIFFERENT_PACKAGE"));

        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(AccessChecker.summarizeByModifier(attempts));
    }
}