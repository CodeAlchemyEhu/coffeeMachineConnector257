package lt.esdc.designpatterns.domain.coffee;

import lt.esdc.designpatterns.domain.Coffee;
import lt.esdc.designpatterns.domain.CoffeeRecipe;

public class Cappuccino implements Coffee {
    private final CoffeeRecipe recipe;
    private final double price;

    public Cappuccino(CoffeeRecipe recipe, double price) {
        this.recipe = recipe;
        this.price = price;
    }

    @Override
    public CoffeeRecipe getRecipe() {
        return recipe;
    }

    @Override
    public String getName() {
        return "cappuccino";
    }

    @Override
    public double getPrice() {
        return price;
    }
}
