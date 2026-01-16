package lt.esdc.designpatterns.machine;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @Test
    void shouldAcceptOrderWithToppings() {
        connector.send("200ml 15g 100ml cream caramel");
    }

    @Test
    void shouldAcceptOrderWithMultipleToppings() {
        connector.send("300ml 12g 250ml cream caramel liquor");
    }

}