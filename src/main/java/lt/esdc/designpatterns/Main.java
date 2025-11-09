package lt.esdc.designpatterns;

import lt.esdc.designpatterns.controller.CoffeeMachineController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger  logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        CoffeeMachineController controller = null;

        // Example order: 2 cappuccinos and 1 espresso and 1 latte
        String[] order = {"cappuccino", "espresso", "latte"};
        controller.processOrder(order);

    }
}