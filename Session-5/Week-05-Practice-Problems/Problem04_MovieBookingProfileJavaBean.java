import java.util.*;

class MovieBookingProfile {
    private String name;
    private boolean confirmed;
    private String otp;

    public MovieBookingProfile() {
        // Public no-argument constructor required by JavaBean standard
    }

    public MovieBookingProfile(String name) {
        this();
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setOtp(String otp) {
        // Store one-way transformed OTP value; no getter exists anywhere
        if (otp != null) {
            this.otp = String.valueOf(otp.hashCode());
        }
    }
}

public class Problem04_MovieBookingProfileJavaBean {
    public static void main(String[] args) {
        System.out.println(new MovieBookingProfile("Rahul Dev").getName());

        MovieBookingProfile p = new MovieBookingProfile("Rahul Dev");
        p.setConfirmed(true);
        System.out.println(p.isConfirmed());

        p.setOtp("4471"); // Write-only OTP
    }
}