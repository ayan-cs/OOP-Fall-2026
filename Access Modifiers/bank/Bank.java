package bank;

public class Bank {
    public void audit(Account a) {
        System.out.println("Audit: " + a.owner + " | branch " + a.branch + " | rate " + a.interestRate);
    }
}
