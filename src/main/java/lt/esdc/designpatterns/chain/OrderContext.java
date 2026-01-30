package lt.esdc.designpatterns.chain;

import lt.esdc.designpatterns.domain.Coffee;
import lt.esdc.designpatterns.factory.CoffeeFactory;
import lt.esdc.designpatterns.observer.OrderListener;
import lt.esdc.designpatterns.strategy.DiscountStrategy;

import java.util.List;

public class OrderContext {
    public String input;
    public CoffeeFactory factory;
    public String region;
    public String coffeeType;
    public List<String> toppings;
    public String discountType;
    public Coffee coffee;
    public DiscountStrategy strategy;
    public double finalPrice;
    public List<OrderListener> listeners;
    public boolean notified;

    public OrderContext(String input, CoffeeFactory factory, List<OrderListener> listeners) {
        this.input = input;
        this.factory = factory;
        this.region = factory.getClass().getSimpleName().replace("CoffeeFactory", "");
        this.listeners = listeners;
    }
}
