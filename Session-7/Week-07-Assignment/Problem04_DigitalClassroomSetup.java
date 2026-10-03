
interface Chargeable {
    String charge();
    String charge(int minutes);
}

abstract class ClassroomDevice {
    public abstract String operate();
}

class Tablet extends ClassroomDevice implements Chargeable {
    private String assetTag;

    public Tablet(String assetTag) {
        this.assetTag = assetTag;
    }

    @Override
    public String operate() {
        return "Tablet " + assetTag + " displaying lesson";
    }

    @Override
    public String charge() {
        return assetTag + " charging";
    }

    @Override
    public String charge(int minutes) {
        return assetTag + " charging for " + minutes + " minutes";
    }
}

public class Problem04_DigitalClassroomSetup {
    public static void main(String[] args) {
        Tablet t = new Tablet("TAB-5");
        System.out.println(t.operate());
        System.out.println(t.charge());
        System.out.println(t.charge(30));
    }
}