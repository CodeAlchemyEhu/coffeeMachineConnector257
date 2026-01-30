package lt.esdc.designpatterns.chain;

import lt.esdc.designpatterns.domain.CoffeeWithToppings;
import lt.esdc.designpatterns.machine.CoffeeMachineV69;
import lt.esdc.designpatterns.observer.OrderEventPublisher;

public class SendHandler implements OrderHandler {
    private CoffeeMachineV69 machine;

    public SendHandler(CoffeeMachineV69 machine) {
        this.machine = machine;
    }

    @Override
    public void setNext(OrderHandler next) {
    }

    @Override
    public void handle(OrderContext ctx) {
        String cmd;
        if (ctx.coffee instanceof CoffeeWithToppings) {
            cmd = ((CoffeeWithToppings) ctx.coffee).getCommandString();
        } else {
            cmd = ctx.coffee.getRecipe().toCommandString();
        }
        try {
            machine.send(cmd);
            OrderEventPublisher.publish(ctx, true, null);
        } catch (Exception e) {
            OrderEventPublisher.publish(ctx, false, e);
            throw e;
        }
    }
}
