package lt.esdc.designpatterns.chain;

import lt.esdc.designpatterns.domain.CoffeeWithToppings;
import lt.esdc.designpatterns.machine.CoffeeMachineV69;

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
        machine.send(cmd);
    }
}
