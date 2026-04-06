package com.alexis.sprinfg.msvc.usuarios;

public class TransactionRecord {
    private String transactionId;
    private TransactionType type;
    private double amount;
    private boolean success;
    private String message;

    public TransactionRecord(String transactionId, TransactionType type, double amount, boolean success, String message) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.success = success;
        this.message = message;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }
    @Override
    public String toString() {
        return transactionId + " | " + type + " | $" + amount + " | " + (success ? "OK" : "KO");
    }
}
