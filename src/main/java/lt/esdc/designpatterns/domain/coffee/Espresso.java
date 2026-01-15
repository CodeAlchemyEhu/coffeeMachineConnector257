package lt.esdc.designpatterns.domain.coffee;

import lt.esdc.designpatterns.domain.Coffee;
import lt.esdc.designpatterns.domain.CoffeeRecipe;

public class Espresso implements Coffee {
    private final CoffeeRecipe recipe;

    public Espresso(CoffeeRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public CoffeeRecipe getRecipe() {
        return recipe;
    }

    @Override
    public String getName() {
        return "espresso";
    }
}


