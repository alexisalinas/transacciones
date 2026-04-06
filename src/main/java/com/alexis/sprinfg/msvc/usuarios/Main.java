package com.alexis.sprinfg.msvc.usuarios;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TransactionProcessor processor = new TransactionProcessor(new AutorizacionServiceImpl());
        AuditProcessor op = new AuditProcessor();

        // 1. Crear con referencia
        DepositTransaction dep001 = new DepositTransaction("DEP001");
        DepositTransaction dep002 = new DepositTransaction("DEP002") {
            @Override
            protected String execute(double amount) {
                throw new IllegalArgumentException("Monto invalido");
            }
        };
        WithdrawalTransaction wit001 = new WithdrawalTransaction("WIT001", 50.0);
        WithdrawalTransaction wit002 = new WithdrawalTransaction("WIT002", 500.0);
        WithdrawalTransaction wit003 = new WithdrawalTransaction("WIT003", 200.0);
        WithdrawalTransaction wit004 = new WithdrawalTransaction("WIT004", 5000000);
        TransferTransaction wit005 = new TransferTransaction("WIT005",900.0,"WIT004");

// 3. Map con montos
        Map<AbstractTransaction, Double> montos = new LinkedHashMap<>();
        montos.put(dep001, 100.0);
        montos.put(dep002, 100.0);
        montos.put(wit001, 100.0);
        montos.put(wit002, 100.0);
        montos.put(wit003, 100.0);
        montos.put(wit004, 15000.0);
        montos.put(wit005,400.0);

        op.addTransaction(dep001,TransactionType.DEPOSIT);
        op.addTransaction(wit005,TransactionType.TRANSFER);
        op.addTransaction(wit001,TransactionType.WITHDRAWAL);

// 4. Ejecutar y mostrar resultado
        int exitosas = processor.processAll(montos);
        System.out.println("Transacciones exitosas: " + exitosas + " de 6");
        System.out.println();


        System.out.println("//////////////////////");
        op.processAll(600);
        System.out.println("///////////////////////");
        String exitosas2 = op.getSuccessfulRecords().stream()
                .map(TransactionRecord::getTransactionId)
                .collect(Collectors.joining(", "));
        System.out.println("Exitosas: " + exitosas2);
        System.out.println("///////////////////");
        System.out.println(op.getSuccessfulRecords());
        System.out.println("///////////////////");
        System.out.println(op.getTotalSuccessfulAmount());
        System.out.println("///////////////////");
        System.out.println(op.getSummaryByType());

    }
}
