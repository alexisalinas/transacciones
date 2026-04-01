package com.alexis.sprinfg.msvc.usuarios;

public class WithdrawalTransaction extends AbstractTransaction {

    private double balance;

    public WithdrawalTransaction(String transactionId, double balance) {
        super(transactionId);
        this.balance = balance;
    }

    @Override
    protected String execute(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Monto invalido");
        }
        if(amount > 10000)        {
            throw new IllegalArgumentException("Monto excede el limite permitido");
        }
        if (balance < amount) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        return "Retiro exitoso: $" + amount;
    }
}