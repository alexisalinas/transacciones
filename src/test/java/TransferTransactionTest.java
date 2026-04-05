import com.alexis.sprinfg.msvc.usuarios.TransferTransaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferTransactionTest {

    @Test
    public void TranfererenciaValida(){
        //Given
        TransferTransaction transaction = new TransferTransaction("TRF001",100.00,"ACC-999" );
        //When
        transaction.run(100);
        //Then
        assertTrue(transaction.isCommitted());
    }

    @Test
    public void SaldoInsuficiente(){
        //Given
        TransferTransaction transaction = new TransferTransaction("TRF002",50.00,"ACC-999" );
        //When
        transaction.run(100);
        //Then
        assertFalse(transaction.isCommitted());
    }
    @Test
    public void MontoInvalido(){
        //Given
        TransferTransaction transaction = new TransferTransaction("TRF003",500.00,"ACC-999" );
        //When
        transaction.run(-50);
        //Then
        assertFalse(transaction.isCommitted());
    }


}
