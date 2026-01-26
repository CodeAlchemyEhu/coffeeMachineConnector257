package lt.esdc.designpatterns.machine.state;

import lt.esdc.designpatterns.machine.CoffeeMachineConnector;

public class ClosedState implements ConnectorState {
    private int ignored = 0;

    @Override
    public void handle(CoffeeMachineConnector ctx, String order) {
        ignored++;
        System.out.println("Ignored");
        if (ignored >= 5) {
            ctx.setState(new SemiClosedState());
        }
    }
}
