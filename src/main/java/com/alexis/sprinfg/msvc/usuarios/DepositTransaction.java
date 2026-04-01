package com.alexis.sprinfg.msvc.usuarios;

public class DepositTransaction extends AbstractTransaction {

    public DepositTransaction(String transactionId) {
        super(transactionId);
    }

    @Override
    protected String execute(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Monto invalido");
        }
        return "Deposito exitoso: $" + amount;
    }
}