package lt.esdc.designpatterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lt.esdc.designpatterns.controller.CoffeeMachineController;
import lt.esdc.designpatterns.controller.CoffeeMachineControllerImpl;
import lt.esdc.designpatterns.factory.CoffeeFactory;
import lt.esdc.designpatterns.factory.JapanCoffeeFactory;
import lt.esdc.designpatterns.factory.USACoffeeFactory;
import lt.esdc.designpatterns.machine.CoffeeMachineConnector;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        // Example 1: USA region coffee factory
        logger.info("=== USA Region Coffee ===");
        CoffeeFactory usaFactory = new USACoffeeFactory();
        CoffeeMachineController usaController = new CoffeeMachineControllerImpl(
                usaFactory,
                new CoffeeMachineConnector()
        );
        String[] order1 = {"cappuccino", "espresso", "latte"};
        usaController.processOrder(order1);

        logger.info("\n=== Japan Region Coffee ===");
        // Example 2: Japan region coffee factory
        CoffeeFactory japanFactory = new JapanCoffeeFactory();
        CoffeeMachineController japanController = new CoffeeMachineControllerImpl(
                japanFactory,
                new CoffeeMachineConnector()
        );
        String[] order2 = {"espresso", "cappuccino", "latte"};
        japanController.processOrder(order2);
    }
}