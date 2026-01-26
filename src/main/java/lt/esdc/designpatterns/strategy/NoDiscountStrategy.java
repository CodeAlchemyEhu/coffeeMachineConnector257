package lt.esdc.designpatterns.strategy;

public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public double apply(double price) {
        return price;
    }
}
