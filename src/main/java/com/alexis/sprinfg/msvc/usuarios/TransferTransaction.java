package com.alexis.sprinfg.msvc.usuarios;

public class TransferTransaction  extends AbstractTransaction{

    private Double sourceBalance;
    private String destinationId;

    public TransferTransaction(String transactionId, Double sourceBalance, String destinationId) {
        super(transactionId);
        this.sourceBalance = sourceBalance;
        this.destinationId = destinationId;
    }

    @Override
    protected String execute(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Monto invalido");
        }
        if(sourceBalance < amount)        {
            throw new IllegalArgumentException("saldo insuficiente");
        }
        return "Transferencia exitosa: $" + amount + " hacia " + destinationId;
    }
}

