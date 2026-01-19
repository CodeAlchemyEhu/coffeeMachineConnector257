package lt.esdc.designpatterns.machine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoffeeMachineConnector implements CoffeeMachineV69 {
    String pattern = "(?i)^\\d+ml \\d+g \\d+ml(?: [a-z]+)*$";

    private static final Logger logger = LoggerFactory.getLogger(CoffeeMachineConnector.class);
    @Override
    public void send(String order) {

        if (order == null || order.isBlank()) {
            throw new IllegalArgumentException("Order string cannot be null or empty.");
        }

        if (!order.matches(pattern)) {
            throw new IllegalArgumentException(
                    "Invalid order format: " + order +
                            ". Expected format: <volume>ml <coffee>g <milk>ml (e.g. '200ml 15g 100ml')."
            );
        }

        logger.info("Preparing coffee: " + order);
        logger.info("..........");
        try {
            Thread.sleep(1000L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        logger.info("Ready");
    }
}
