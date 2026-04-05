package com.alexis.sprinfg.msvc.usuarios;

import java.util.Map;

public class TransactionProcessor {

    private AutorizacionService autorizacionService;

    public TransactionProcessor(AutorizacionService autorizacionService) {
        this.autorizacionService = autorizacionService;
    }

    public int processAll(Map<AbstractTransaction, Double> montos) {
        int successful = 0;
        for (Map.Entry<AbstractTransaction, Double> entry : montos.entrySet()) {

            // primero verifica si está autorizada
            boolean autorizada = autorizacionService
                    .estaAutorizada(entry.getKey().getTransactionId());

            if (!autorizada) {
                continue; // salta esta transacción
            }

            entry.getKey().run(entry.getValue());
            if (entry.getKey().isCommitted()) {
                successful++;
            }
        }
        return successful;
    }
}