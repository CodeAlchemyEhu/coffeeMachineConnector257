package lt.esdc.designpatterns.strategy;

public class StudentDiscountStrategy implements DiscountStrategy {
    @Override
    public double apply(double price) {
        return price * 0.8;
    }
}
