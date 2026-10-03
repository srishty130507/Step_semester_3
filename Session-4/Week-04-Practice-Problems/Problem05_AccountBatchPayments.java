
class FeeAccount {
    public void makePayment(double amount) {
        System.out.println("Paid in one go (day-scholar account)");
    }
}

class HostelFeeAccount extends FeeAccount {
    @Override
    public void makePayment(double amount) {
        System.out.println("Paid in two installments (hostel account)");
    }
}

public class Problem05_AccountBatchPayments {
    private static int hostelCount = 0;
    private static int dayScholarCount = 0;

    public static void processPayment(FeeAccount account, double amount) {
        if (account instanceof HostelFeeAccount) {
            hostelCount++;
        } else {
            dayScholarCount++;
        }
        account.makePayment(amount);
    }

    public static void main(String[] args) {
        FeeAccount[] accounts = {
            new HostelFeeAccount(),
            new HostelFeeAccount(),
            new FeeAccount(),
            new FeeAccount()
        };

        double amount = 60000.0;

        for (FeeAccount account : accounts) {
            processPayment(account, amount);
        }

        System.out.println("Hostel accounts processed: " + hostelCount + " | Day-scholar accounts processed: " + dayScholarCount);
    }
}