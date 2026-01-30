package lt.esdc.designpatterns.observer;

import lt.esdc.designpatterns.controller.CoffeeMachineController;
import lt.esdc.designpatterns.controller.CoffeeMachineControllerImpl;
import lt.esdc.designpatterns.domain.OrderHistory;
import lt.esdc.designpatterns.domain.OrderRecord;
import lt.esdc.designpatterns.factory.CoffeeFactory;
import lt.esdc.designpatterns.factory.USACoffeeFactory;
import lt.esdc.designpatterns.machine.CoffeeMachineV69;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class OrderHistoryListenerTest {

    @Test
    void shouldStoreSuccessfulOrder() {
        CoffeeFactory factory = new USACoffeeFactory();
        CoffeeMachineV69 machine = mock(CoffeeMachineV69.class);
        CoffeeMachineController controller = new CoffeeMachineControllerImpl(factory, machine);
        OrderHistoryListener listener = new OrderHistoryListener();

        controller.addListener(listener);
        controller.processOrder(new String[]{"latte"});

        OrderHistory history = listener.getHistory();
        assertEquals(1, history.getOrders().size());
        OrderRecord record = history.getOrders().get(0);
        assertTrue(record.isSuccess());
        assertEquals("latte", record.getCoffeeType());
        verify(machine).send("300ml 12g 250ml");
    }

    @Test
    void shouldStoreFailedOrder() {
        CoffeeFactory factory = new USACoffeeFactory();
        CoffeeMachineV69 machine = mock(CoffeeMachineV69.class);
        CoffeeMachineController controller = new CoffeeMachineControllerImpl(factory, machine);
        OrderHistoryListener listener = new OrderHistoryListener();

        controller.addListener(listener);

        assertThrows(IllegalArgumentException.class, () -> controller.processOrder(new String[]{"unknown"}));

        OrderHistory history = listener.getHistory();
        assertEquals(1, history.getOrders().size());
        OrderRecord record = history.getOrders().get(0);
        assertFalse(record.isSuccess());
    }
}
