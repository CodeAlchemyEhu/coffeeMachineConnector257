package lt.esdc.designpatterns.machine;

import lt.esdc.designpatterns.machine.state.ConnectorState;
import lt.esdc.designpatterns.machine.state.OpenState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoffeeMachineConnector implements CoffeeMachineV69 {
    String pattern = "(?i)^\\d+ml \\d+g \\d+ml(?: [a-z]+)*$";
    private ConnectorState state = new OpenState();

    private static final Logger logger = LoggerFactory.getLogger(CoffeeMachineConnector.class);

    public void setState(ConnectorState state) {
        this.state = state;
    }

    @Override
    public void send(String order) {
        state.handle(this, order);
    }

    public void realSend(String order) {
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
