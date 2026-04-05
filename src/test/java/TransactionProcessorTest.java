import com.alexis.sprinfg.msvc.usuarios.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionProcessorTest {

    @Mock
    AutorizacionService autorizacionService;

    TransactionProcessor processor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        processor = new TransactionProcessor(autorizacionService);
    }

    @Test
    void testSoloTransaccionesAutorizadasSeProcesan() {
        // Given
        DepositTransaction dep001 = new DepositTransaction("DEP001");
        DepositTransaction dep002 = new DepositTransaction("DEP002");

        Map<AbstractTransaction, Double> montos = new LinkedHashMap<>();
        montos.put(dep001, 100.0);
        montos.put(dep002, 100.0);

        // Le decimos al mock qué retornar
        when(autorizacionService.estaAutorizada("DEP001")).thenReturn(true);
        when(autorizacionService.estaAutorizada("DEP002")).thenReturn(false);

        // When
        int exitosas = processor.processAll(montos);

        // Then
        assertEquals(1, exitosas); // solo DEP001 fue autorizada y exitosa
        verify(autorizacionService).estaAutorizada("DEP001"); // verificar que se llamó
        verify(autorizacionService).estaAutorizada("DEP002"); // verificar que se llamó
    }
    @Test
    void testnopasaNingunaTransaccion(){
        // Given
        DepositTransaction dep001 = new DepositTransaction("DEP001");
        DepositTransaction dep002 = new DepositTransaction("DEP002");

        Map<AbstractTransaction, Double> montos = new LinkedHashMap<>();
        montos.put(dep001, 100.0);
        montos.put(dep002, 100.0);

        // Le decimos al mock qué retornar
        when(autorizacionService.estaAutorizada("DEP001")).thenReturn(false);
        when(autorizacionService.estaAutorizada("DEP002")).thenReturn(false);

        // When
        int exitosas = processor.processAll(montos);

        // Then
        assertEquals(0, exitosas); // solo DEP001 fue autorizada y exitosa
        verify(autorizacionService).estaAutorizada("DEP001"); // verificar que se llamó
        verify(autorizacionService).estaAutorizada("DEP002"); // verificar que se llamó

    }
}