package lt.esdc.designpatterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lt.esdc.designpatterns.controller.CoffeeMachineController;
import lt.esdc.designpatterns.controller.CoffeeMachineControllerImpl;
import lt.esdc.designpatterns.factory.CoffeeFactory;
import lt.esdc.designpatterns.factory.JapanCoffeeFactory;
import lt.esdc.designpatterns.factory.USACoffeeFactory;
import lt.esdc.designpatterns.machine.CoffeeMachineConnector;
import lt.esdc.designpatterns.machine.NewCoffeeMachineConnector;
import lt.esdc.designpatterns.machine.V75ToV69Adapter;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("=== USA Region Coffee (Old) ===");
        CoffeeFactory usaFactory = new USACoffeeFactory();
        CoffeeMachineController usaController = new CoffeeMachineControllerImpl(
                usaFactory,
                new CoffeeMachineConnector()
        );
        String[] order1 = {"cappuccino", "espresso", "latte"};
        usaController.processOrder(order1);

        logger.info("\n=== Japan Region Coffee (Old) ===");
        CoffeeFactory japanFactory = new JapanCoffeeFactory();
        CoffeeMachineController japanController = new CoffeeMachineControllerImpl(
                japanFactory,
                new CoffeeMachineConnector()
        );
        String[] order2 = {"espresso", "cappuccino", "latte"};
        japanController.processOrder(order2);

        logger.info("\n=== USA Region Coffee with Toppings (Old) ===");
        String[] order3 = {"latte cream caramel", "cappuccino liquor"};
        usaController.processOrder(order3);

        logger.info("\n=== USA Region Coffee with Toppings (New) ===");
        NewCoffeeMachineConnector newConnector = NewCoffeeMachineConnector.getInstance();
        V75ToV69Adapter adapter = new V75ToV69Adapter(newConnector);
        CoffeeMachineController usaControllerWithNewConnector = new CoffeeMachineControllerImpl(
                usaFactory,
                adapter
        );
        String[] order4 = {"latte cream", "espresso", "cappuccino caramel liquor"};
        usaControllerWithNewConnector.processOrder(order4);
    }
}