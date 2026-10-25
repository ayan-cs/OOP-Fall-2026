package bank;

public class Account {
    public String owner;
    public double balance;
    // private double balance;
    String branch = "Jodhpur";          // default (no keyword)
    protected double interestRate = 4.0;

    public Account(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (isValid(amount)) {
            balance = balance + amount;
            System.out.println("Deposited Rs. " + amount);
        } else {
            System.out.println("Invalid amount: " + amount);
        }
    }

    public void withdraw(double amount) {
        if (isValid(amount) && amount <= balance) {
            balance = balance - amount;
            System.out.println("Withdrew Rs. " + amount);
        } else {
            System.out.println("Withdrawal of Rs. " + amount + " refused");
        }
    }

    private boolean isValid(double amount) {
        return amount > 0;
    }

    public void show() {
        System.out.println(owner + " has Rs. " + balance);
    }
}
