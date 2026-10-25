package app;

import bank.Account;

public class SavingsAccount extends Account {

    public SavingsAccount(String owner, double balance) {
        super(owner, balance);
    }

    public void addInterest() {
        double interest = getBalance() * interestRate / 100;
        System.out.println("Adding interest at " + interestRate + "%");
        deposit(interest);
    }
}
