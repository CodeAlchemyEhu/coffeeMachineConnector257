package lt.esdc.designpatterns.chain;

import lt.esdc.designpatterns.strategy.DiscountStrategy;
import lt.esdc.designpatterns.strategy.LoyaltyDiscountStrategy;
import lt.esdc.designpatterns.strategy.NoDiscountStrategy;
import lt.esdc.designpatterns.strategy.StudentDiscountStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParseHandler implements OrderHandler {
    private OrderHandler next;

    @Override
    public void setNext(OrderHandler next) {
        this.next = next;
    }

    @Override
    public void handle(OrderContext ctx) {
        String[] parts = ctx.input.trim().toLowerCase().split("\\s+");
        if (parts.length == 0) throw new IllegalArgumentException("Empty");

        List<String> validDiscounts = Arrays.asList("student", "loyalty", "none");
        int index = 0;
        if (validDiscounts.contains(parts[0])) {
            ctx.discountType = parts[0];
            index++;
        } else {
            ctx.discountType = "none";
        }

        if (index >= parts.length) throw new IllegalArgumentException("No coffee");
        ctx.coffeeType = parts[index];
        index++;

        ctx.toppings = new ArrayList<>();
        List<String> validToppings = Arrays.asList("caramel", "cream", "liquor");
        for (int i = index; i < parts.length; i++) {
            if (validToppings.contains(parts[i])) {
                ctx.toppings.add(parts[i]);
            }
        }

        if (ctx.discountType.equals("student")) ctx.strategy = new StudentDiscountStrategy();
        else if (ctx.discountType.equals("loyalty")) ctx.strategy = new LoyaltyDiscountStrategy();
        else ctx.strategy = new NoDiscountStrategy();

        if (next != null) next.handle(ctx);
    }
}
