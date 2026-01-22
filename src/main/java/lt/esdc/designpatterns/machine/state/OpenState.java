package lt.esdc.designpatterns.machine.state;

import lt.esdc.designpatterns.machine.CoffeeMachineConnector;

public class OpenState implements ConnectorState {
    private int fails = 0;

    @Override
    public void handle(CoffeeMachineConnector ctx, String order) {
        try {
            ctx.realSend(order);
            fails = 0;
        } catch (Exception e) {
            fails++;
            if (fails >= 2) {
                ctx.setState(new ClosedState());
            }
            throw e;
        }
    }
}
