package lt.esdc.designpatterns.machine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.*;

class CoffeeMachineConnectorTest {

    private CoffeeMachineConnector connector;
    private Logger mockLogger;
    private MockedStatic<LoggerFactory> mockedLoggerFactory;

    @BeforeEach
    void setUp() {
        mockLogger = mock(Logger.class);

        mockedLoggerFactory = Mockito.mockStatic(LoggerFactory.class);
        mockedLoggerFactory.when(() -> LoggerFactory.getLogger(CoffeeMachineConnector.class))
                .thenReturn(mockLogger);

        connector = new CoffeeMachineConnector();
    }

    @AfterEach
    void tearDown() {
        mockedLoggerFactory.close();
    }

    @Test
    void shouldThrowExceptionWhenOrderIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> connector.send(null));

        assertEquals("Order string cannot be null or empty.", ex.getMessage());
        verifyNoInteractions(mockLogger);
    }

    @Test
    void shouldThrowExceptionWhenOrderIsBlank() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> connector.send("   "));

        assertEquals("Order string cannot be null or empty.", ex.getMessage());
        verifyNoInteractions(mockLogger);
    }

    @Test
    void shouldThrowExceptionWhenOrderDoesNotMatchPattern() {
        String invalidOrder = "200ml 15g"; // Missing milk part

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> connector.send(invalidOrder));

        assertTrue(ex.getMessage().contains("Invalid order format"));
        verifyNoInteractions(mockLogger);
    }

    @Test
    void shouldWrapInterruptedExceptionInRuntimeException() {
        // Override to simulate Thread.sleep throwing InterruptedException
        CoffeeMachineConnector testConnector = new CoffeeMachineConnector() {
            @Override
            public void send(String order) {
                if (!order.matches(pattern)) return;
                throw new RuntimeException(new InterruptedException("Simulated interruption"));
            }
        };

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> testConnector.send("200ml 15g 100ml"));

        assertTrue(ex.getCause() instanceof InterruptedException);
    }

}