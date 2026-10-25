package app;

import bank.Account;
import bank.Bank;

public class Main {
    public static void main(String[] args) {
        Account a = new Account("Ravi", 1000);
        a.show();

        a.deposit(500);
        a.deposit(-200);
        a.withdraw(5000);
        a.withdraw(300);
        a.show();
        System.out.println("Balance via getter: " + a.getBalance());

        Bank b = new Bank();
        b.audit(a);

        SavingsAccount s = new SavingsAccount("Meera", 2000);
        s.addInterest();
        s.show();
    }
}
