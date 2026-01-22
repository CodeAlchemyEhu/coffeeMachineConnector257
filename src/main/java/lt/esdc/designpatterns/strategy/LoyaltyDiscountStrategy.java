package lt.esdc.designpatterns.strategy;

public class LoyaltyDiscountStrategy implements DiscountStrategy {
    @Override
    public double apply(double price) {
        return price * 0.9;
    }
}
