package lt.esdc.designpatterns.machine.state;

import lt.esdc.designpatterns.machine.CoffeeMachineConnector;

public interface ConnectorState {
    void handle(CoffeeMachineConnector ctx, String order);
}
