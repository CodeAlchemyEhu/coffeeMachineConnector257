package lt.esdc.designpatterns.chain;

public class DiscountHandler implements OrderHandler {
    private OrderHandler next;

    @Override
    public void setNext(OrderHandler next) {
        this.next = next;
    }

    @Override
    public void handle(OrderContext ctx) {
        double base = ctx.coffee.getPrice();
        ctx.finalPrice = ctx.strategy.apply(base);
        System.out.println("Price: " + ctx.finalPrice);
        if (next != null) next.handle(ctx);
    }
}
