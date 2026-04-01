import com.alexis.sprinfg.msvc.usuarios.WithdrawalTransaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WithdrawalTransactionTest {

    //balance: 500, monto: 100 → isCommitted() true
    @Test
    public void testDepositoValido() {
        // Given

        WithdrawalTransaction transaction = new WithdrawalTransaction("tx123", 500);
        //when
        transaction.run(100);
        //ten
        assertTrue(transaction.isCommitted());
    }
    @Test
    public void testSaldoInsuficiente() {
        // Given
        WithdrawalTransaction transaction = new WithdrawalTransaction
                ("tx124", 50);
        //when
        transaction.run(100);
        //ten
        assertFalse(transaction.isCommitted());
    }
    //balance: 5000000, monto: 15000 → isCommitted() false
    @Test
    public void testMontoExcedeLimite() {
        // Given
        WithdrawalTransaction transaction = new WithdrawalTransaction
                ("tx125", 5000000);
        //when
        transaction.run(15000);
        //ten
        assertFalse(transaction.isCommitted());
    }

}





