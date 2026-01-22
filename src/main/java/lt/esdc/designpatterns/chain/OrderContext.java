package lt.esdc.designpatterns.chain;

import lt.esdc.designpatterns.domain.Coffee;
import lt.esdc.designpatterns.factory.CoffeeFactory;
import lt.esdc.designpatterns.strategy.DiscountStrategy;

import java.util.List;

public class OrderContext {
    public String input;
    public CoffeeFactory factory;
    public String coffeeType;
    public List<String> toppings;
    public String discountType;
    public Coffee coffee;
    public DiscountStrategy strategy;
    public double finalPrice;

    public OrderContext(String input, CoffeeFactory factory) {
        this.input = input;
        this.factory = factory;
    }
}
