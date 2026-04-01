import com.alexis.sprinfg.msvc.usuarios.DepositTransaction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DepositTransactionTest {

    @Test
    void depositoValido() {
        // Given
        DepositTransaction dep = new DepositTransaction("DEP001");

        // When
        dep.run(100.0);

        // Then
        assertTrue(dep.isCommitted());
    }

    @Test
    void depositoMontoInvalido() {
        // Given
        DepositTransaction dep = new DepositTransaction("DEP002");

        // When
        dep.run(-50.0);

        // Then
        assertFalse(dep.isCommitted());
    }

}