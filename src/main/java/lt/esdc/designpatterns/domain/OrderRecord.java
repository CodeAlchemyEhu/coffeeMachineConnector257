package lt.esdc.designpatterns.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderRecord {
    private final String input;
    private final String region;
    private final String coffeeType;
    private final List<String> toppings;
    private final String discountType;
    private final double finalPrice;
    private final boolean success;
    private final String errorMessage;

    public OrderRecord(
            String input,
            String region,
            String coffeeType,
            List<String> toppings,
            String discountType,
            double finalPrice,
            boolean success,
            String errorMessage
    ) {
        this.input = input;
        this.region = region;
        this.coffeeType = coffeeType;
        this.toppings = toppings == null ? new ArrayList<>() : new ArrayList<>(toppings);
        this.discountType = discountType;
        this.finalPrice = finalPrice;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public String getInput() {
        return input;
    }

    public String getRegion() {
        return region;
    }

    public String getCoffeeType() {
        return coffeeType;
    }

    public List<String> getToppings() {
        return Collections.unmodifiableList(toppings);
    }

    public String getDiscountType() {
        return discountType;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
