package com.bank.transaction;
import java.time.LocalDateTime;

public class Transaction {
    public enum txn_type {
        DEPOSIT, WITHDRAWAL, TRANSFER
    }

    private LocalDateTime date_time;
    private double amount;
    private txn_type type;


    public Transaction(double amount, txn_type type) {
        this.date_time = LocalDateTime.now();
        this.amount = amount;
        this.type = type;
    }

    public String viewTransaction() {
        return date_time + " " + amount + " " + type;
    }
}
