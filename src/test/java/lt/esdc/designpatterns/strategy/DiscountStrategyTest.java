package lt.esdc.designpatterns.strategy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountStrategyTest {

    @Test
    void noDiscountShouldReturnSamePrice() {
        DiscountStrategy strategy = new NoDiscountStrategy();
        assertEquals(10.0, strategy.apply(10.0), 0.001);
    }

    @Test
    void studentDiscountShouldApply20Percent() {
        DiscountStrategy strategy = new StudentDiscountStrategy();
        assertEquals(8.0, strategy.apply(10.0), 0.001);
    }

    @Test
    void loyaltyDiscountShouldApply10Percent() {
        DiscountStrategy strategy = new LoyaltyDiscountStrategy();
        assertEquals(9.0, strategy.apply(10.0), 0.001);
    }
}
