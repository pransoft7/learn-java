import java.util.ArrayList;

public class BankAccount {
    private int acc_number;
    public String acc_holder;
    private ArrayList<Transaction> transactions;
    private double balance;

    public BankAccount(int acc_number, String acc_holder, double balance) {
        this.acc_number = acc_number;
        this.acc_holder = acc_holder;
        this.balance = balance;
        this.transactions = new ArrayList<>();
        if (balance > 0) {
            transactions.add(new Transaction(balance, Transaction.txn_type.CREDIT));
        }
    }

    public double getBalance() {
        return balance;
    }

    public void getTransactions(int count) {
        for (int i = transactions.size() - count; i < transactions.size(); i++) {
            System.out.println(transactions.get(i).viewTransaction());
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction(amount, Transaction.txn_type.CREDIT));
            System.out.println("$" + amount + " credited successfully!");
        } else System.out.println("Invalid Input");
    }

    public void withdraw(double amount) {
        if (amount > 0) {
            if (amount < balance) {
                balance -= amount;
                transactions.add(new Transaction(amount, Transaction.txn_type.DEBIT));
                System.out.println("$" + amount + " debited successfully!");
            } else System.out.println("Insufficient Funds!");
        } else System.out.println("Enter +ve money!");
    }

}
