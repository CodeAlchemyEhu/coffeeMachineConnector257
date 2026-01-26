package lt.esdc.designpatterns.chain;

import lt.esdc.designpatterns.domain.CoffeeWithToppings;

public class ToppingHandler implements OrderHandler {
    private OrderHandler next;

    @Override
    public void setNext(OrderHandler next) {
        this.next = next;
    }

    @Override
    public void handle(OrderContext ctx) {
        if (!ctx.toppings.isEmpty()) {
            ctx.coffee = new CoffeeWithToppings(ctx.coffee, ctx.toppings);
        }
        if (next != null) next.handle(ctx);
    }
}
