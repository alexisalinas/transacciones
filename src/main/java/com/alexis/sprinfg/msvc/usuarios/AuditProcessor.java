package com.alexis.sprinfg.msvc.usuarios;

import java.util.*;
import java.util.stream.Collectors;

public class AuditProcessor {

    private Map<AbstractTransaction, TransactionType> transactionMap
            = new LinkedHashMap<>();

    private List<TransactionRecord> records = new ArrayList<>();

//    1. addTransaction
//    Guarda la transacción junto a su tipo en el Map. Es como registrar una operación antes de ejecutarla.
//    recibe: una transacción y su tipo (DEPOSIT, WITHDRAWAL, TRANSFER)
//    hace: la agrega al Map

    public void addTransaction(AbstractTransaction transaction, TransactionType type) {
        transactionMap.put(transaction, type);
 }
//    2. processAll
//    Recorre el Map, ejecuta cada transacción y guarda el resultado en la lista de records.
//    recibe: el monto
//    hace: ejecuta run() en cada transacción y crea un TransactionRecord con el resultado
    public void processAll(double amount) {
        transactionMap.forEach((transaction, type) -> {
            String mensaje = transaction.run(amount);
            records.add(new TransactionRecord(
                    transaction.getTransactionId(),
                    type,
                    amount,
                    transaction.isCommitted(),
                    mensaje
            ));
        });
    }
//    3. getSuccessfulRecords
//    Usa Streams para filtrar solo los records exitosos y ordenarlos por ID.
//    recibe: nada
//    retorna: lista de records donde success = true, ordenados alfabéticamente

    public List<TransactionRecord> getSuccessfulRecords() {
        return records.stream()
                .filter(TransactionRecord::isSuccess)
                .sorted(Comparator.comparing(TransactionRecord::getTransactionId))
                .collect(Collectors.toList());
    }
//4. getTotalSuccessfulAmount
//    Usa Streams para sumar el monto de todas las transacciones exitosas.
//            recibe: nada
//    retorna: la suma total como double
    public Double getTotalSuccessfulAmount(){
            return records.stream()
                    .filter(TransactionRecord::isSuccess).mapToDouble(TransactionRecord :: getAmount).sum();
    }
//5. getSummaryByType
//    Usa Streams para agrupar los exitosos por tipo y contar cuántos hay de cada uno.
//            recibe: nada
//    retorna: String con formato "DEPOSIT: 1 exitosa\nTRANSFER: 1 exitosa..."

    public String getSummaryByType() {
        return records.stream()
                .filter(TransactionRecord::isSuccess) // solo exitosos
                .collect(Collectors.groupingBy(TransactionRecord::getType, Collectors.counting())) // agrupa y cuenta
                .entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue() +
                        (entry.getValue() == 1 ? " exitosa" : " exitosas"))
                .collect(Collectors.joining("\n"));
    }



}
