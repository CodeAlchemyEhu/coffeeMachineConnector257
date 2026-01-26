package lt.esdc.designpatterns.chain;

public class CreateHandler implements OrderHandler {
    private OrderHandler next;

    @Override
    public void setNext(OrderHandler next) {
        this.next = next;
    }

    @Override
    public void handle(OrderContext ctx) {
        if (ctx.coffeeType.equals("espresso")) ctx.coffee = ctx.factory.createEspresso();
        else if (ctx.coffeeType.equals("cappuccino")) ctx.coffee = ctx.factory.createCappuccino();
        else if (ctx.coffeeType.equals("latte")) ctx.coffee = ctx.factory.createLatte();
        else throw new IllegalArgumentException("Unknown coffee");

        if (next != null) next.handle(ctx);
    }
}
