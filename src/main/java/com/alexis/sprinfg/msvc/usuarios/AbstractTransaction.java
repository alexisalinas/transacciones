package com.alexis.sprinfg.msvc.usuarios;

public abstract class AbstractTransaction {
    private String transactionId;
    private boolean committed = false;

    public AbstractTransaction(String transactionId) {
        this.transactionId = transactionId;
    }

    public final String run(double amount) {
        preAction();
        try {
            String result = execute(amount);
            committed = true;
            postAction("OK");
            return result;
        } catch (IllegalArgumentException e) {
            postAction("KO");
            return "ERROR: " + e.getMessage();
        }
    }

    protected abstract String execute(double amount);

    private void preAction() {
        System.out.println("[LOG] Iniciando transacción: " + transactionId);
    }

    private void postAction(String status) {
        System.out.println("[ROP] Transacción " + transactionId + " finalizada con: " + status);
    }

    public boolean isCommitted() { return committed; }
    public String getTransactionId() { return transactionId; }
}


