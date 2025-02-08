package com.bank.account;
import java.util.ArrayList;

import com.bank.transaction.Transaction;

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
            transactions.add(new Transaction(balance, Transaction.txn_type.DEPOSIT));
        }
    }

    public double getBalance() {
        return balance;
    }

    public void getTransactions(int count) {
        // Last n transactions
        for (int i = transactions.size() - count; i < transactions.size(); i++) {
            System.out.println(transactions.get(i).viewTransaction());
        }
        // ALL
        // for (Transaction transaction : transactions) {
        //     System.out.println(transaction.viewTransaction());
        // }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction(amount, Transaction.txn_type.DEPOSIT));
            System.out.println("$" + amount + " credited successfully!");
        } else System.out.println("Invalid Input");
    }

    public void withdraw(double amount) {
        if (amount > 0) {
            if (amount < balance) {
                balance -= amount;
                transactions.add(new Transaction(amount, Transaction.txn_type.WITHDRAWAL));
                System.out.println("$" + amount + " debited successfully!");
            } else System.out.println("Insufficient Funds!");
        } else System.out.println("Enter +ve money!");
    }
}