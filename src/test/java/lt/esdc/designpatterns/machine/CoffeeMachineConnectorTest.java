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
    void shouldAcceptOrderWithToppings() {
        connector.send("200ml 15g 100ml cream caramel");
    }

    @Test
    void shouldAcceptOrderWithMultipleToppings() {
        connector.send("300ml 12g 250ml cream caramel liquor");
    }

    // --- State Pattern Tests ---

    @Test
    void shouldSwitchToClosedStateAfterTwoFailures() {
        // Arrange: Subclass to simulate failures
        CoffeeMachineConnector testConnector = new CoffeeMachineConnector() {
            @Override
            public void realSend(String order) {
                throw new RuntimeException("Simulated Failure");
            }
        };

        // Act & Assert
        // 1st failure
        assertThrows(RuntimeException.class, () -> testConnector.send("200ml 15g 100ml"));
        // 2nd failure -> should switch to Closed
        assertThrows(RuntimeException.class, () -> testConnector.send("200ml 15g 100ml"));

        // 3rd call -> Should be ignored (Closed State)
        // In ClosedState, handle() prints "Ignored" and does NOT call realSend (so no exception thrown)
        testConnector.send("200ml 15g 100ml"); 
        
        // No exception means we are in Closed State
    }

    @Test
    void shouldSwitchToSemiClosedAfterFiveIgnored() {
        CoffeeMachineConnector testConnector = new CoffeeMachineConnector() {
            @Override
            public void realSend(String order) {
                throw new RuntimeException("Simulated Failure");
            }
        };

        // 1. Trigger Closed State (2 failures)
        assertThrows(RuntimeException.class, () -> testConnector.send("valid"));
        assertThrows(RuntimeException.class, () -> testConnector.send("valid"));

        // 2. Consume 5 ignored calls
        for (int i = 0; i < 5; i++) {
            testConnector.send("valid"); // Should not throw
        }

        // 3. Next call should be Semi-Closed -> tries realSend -> throws Exception
        assertThrows(RuntimeException.class, () -> testConnector.send("valid"));
    }

    @Test
    void shouldRecoverToOpenStateFromSemiClosedOnSuccess() {
        // We need a connector that fails initially then succeeds
        CoffeeMachineConnector testConnector = new CoffeeMachineConnector() {
            private int failCount = 0;
            @Override
            public void realSend(String order) {
                if (failCount < 2) {
                    failCount++;
                    throw new RuntimeException("Simulated Failure");
                }
                // Success afterwards
            }
        };

        // 1. Trigger Closed State (2 failures)
        assertThrows(RuntimeException.class, () -> testConnector.send("valid"));
        assertThrows(RuntimeException.class, () -> testConnector.send("valid"));

        // 2. Consume 5 ignored calls
        for (int i = 0; i < 5; i++) {
            testConnector.send("valid");
        }

        // 3. Semi-Closed try -> Success
        testConnector.send("valid");

        // 4. Next call -> Open State -> Success (if we were still closed, we'd ignore)
        testConnector.send("valid");
    }
}
