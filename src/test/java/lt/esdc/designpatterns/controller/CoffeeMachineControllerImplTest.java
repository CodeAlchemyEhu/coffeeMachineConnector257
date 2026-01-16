package lt.esdc.designpatterns.controller;

import lt.esdc.designpatterns.factory.CoffeeFactory;
import lt.esdc.designpatterns.factory.USACoffeeFactory;
import lt.esdc.designpatterns.machine.CoffeeMachineV69;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CoffeeMachineControllerImplTest {

    @Test
    void shouldProcessOrderWithoutToppings() {
        CoffeeFactory factory = new USACoffeeFactory();
        CoffeeMachineV69 machine = mock(CoffeeMachineV69.class);
        
        CoffeeMachineController controller = new CoffeeMachineControllerImpl(factory, machine);
        controller.processOrder(new String[]{"latte"});
        
        verify(machine).send("300ml 12g 250ml");
    }

    @Test
    void shouldProcessOrderWithToppings() {
        CoffeeFactory factory = new USACoffeeFactory();
        CoffeeMachineV69 machine = mock(CoffeeMachineV69.class);
        
        CoffeeMachineController controller = new CoffeeMachineControllerImpl(factory, machine);
        controller.processOrder(new String[]{"latte cream caramel"});
        
        verify(machine).send("300ml 12g 250ml cream caramel");
    }

    @Test
    void shouldThrowExceptionForUnknownDrink() {
        CoffeeFactory factory = new USACoffeeFactory();
        CoffeeMachineV69 machine = mock(CoffeeMachineV69.class);
        
        CoffeeMachineController controller = new CoffeeMachineControllerImpl(factory, machine);
        
        assertThrows(IllegalArgumentException.class, () -> {
            controller.processOrder(new String[]{"unknown"});
        });
    }

    @Test
    void shouldThrowExceptionForUnknownTopping() {
        CoffeeFactory factory = new USACoffeeFactory();
        CoffeeMachineV69 machine = mock(CoffeeMachineV69.class);
        
        CoffeeMachineController controller = new CoffeeMachineControllerImpl(factory, machine);
        
        assertThrows(IllegalArgumentException.class, () -> {
            controller.processOrder(new String[]{"latte chocolate"});
        });
    }
}


