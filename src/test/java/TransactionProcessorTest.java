import com.alexis.sprinfg.msvc.usuarios.AbstractTransaction;
import com.alexis.sprinfg.msvc.usuarios.TransactionProcessor;
import com.alexis.sprinfg.msvc.usuarios.WithdrawalTransaction;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionProcessorTest {
    //Caso 1 — procesa varias transacciones y retorna el conteo correcto de exitosas.
    @Test
    public void testProcessAll_MultipleTransactions_ReturnsCorrectCount() {
        //given
        TransactionProcessor processor = new TransactionProcessor();
        Map<AbstractTransaction, Double> montos = new LinkedHashMap<>();
        WithdrawalTransaction wit001 = new WithdrawalTransaction("WIT001", 50.0);
        WithdrawalTransaction wit002 = new WithdrawalTransaction("WIT002", 500.0);
        WithdrawalTransaction wit003 = new WithdrawalTransaction("WIT003", 200.0);
        WithdrawalTransaction wit004 = new WithdrawalTransaction("WIT004", 5000000);
        //when
        montos.put(wit001, 100.0);
        montos.put(wit002, 100.0);
        montos.put(wit003, 100.0);
        montos.put(wit004, 15000.0);
        //then
        int exitosas = processor.processAll(montos);
        assertEquals(2, exitosas);

    }
    @Test
    public void testProcessAll_EmptyMap_ReturnsZero() {
        // Given
        TransactionProcessor processor = new TransactionProcessor();
        Map<AbstractTransaction, Double> montos = new LinkedHashMap<>();

        // transacciones que van a fallar todas
        WithdrawalTransaction wit001 = new WithdrawalTransaction("WIT001", 50.0);
        WithdrawalTransaction wit002 = new WithdrawalTransaction("WIT002", 30.0);

        montos.put(wit001, 100.0); // saldo insuficiente → KO
        montos.put(wit002, 100.0); // saldo insuficiente → KO

        // When
        int exitosas = processor.processAll(montos);

        // Then
        assertEquals(0, exitosas);
    }
}
