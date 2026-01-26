package lt.esdc.designpatterns.machine.state;

import lt.esdc.designpatterns.machine.CoffeeMachineConnector;

public class SemiClosedState implements ConnectorState {
    @Override
    public void handle(CoffeeMachineConnector ctx, String order) {
        try {
            ctx.realSend(order);
            ctx.setState(new OpenState());
        } catch (Exception e) {
            ctx.setState(new ClosedState());
            throw e;
        }
    }
}
