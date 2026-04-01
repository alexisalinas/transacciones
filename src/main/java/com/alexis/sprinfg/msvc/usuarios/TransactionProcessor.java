package com.alexis.sprinfg.msvc.usuarios;

import java.util.Map;

public class TransactionProcessor {


    public int processAll(Map<AbstractTransaction, Double> montos) {
        int successful = 0;
        for (Map.Entry<AbstractTransaction, Double> entry : montos.entrySet()) {
            entry.getKey().run(entry.getValue());
            if (entry.getKey().isCommitted()) {
                successful++;
            }
        }
        return successful;
    }
}
