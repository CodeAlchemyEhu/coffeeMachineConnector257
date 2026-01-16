package lt.esdc.designpatterns.domain;

import java.util.ArrayList;
import java.util.List;

public class CoffeeWithToppings implements Coffee {
    private final Coffee baseCoffee;
    private final List<String> toppings;

    public CoffeeWithToppings(Coffee baseCoffee, List<String> toppings) {
        this.baseCoffee = baseCoffee;
        this.toppings = new ArrayList<>(toppings);
    }

    @Override
    public CoffeeRecipe getRecipe() {
        return baseCoffee.getRecipe();
    }

    @Override
    public String getName() {
        return baseCoffee.getName();
    }

    public String getCommandString() {
        String baseCommand = baseCoffee.getRecipe().toCommandString();
        if (toppings.isEmpty()) {
            return baseCommand;
        }
        StringBuilder result = new StringBuilder(baseCommand);
        for (String topping : toppings) {
            result.append(" ").append(topping);
        }
        return result.toString();
    }
}

