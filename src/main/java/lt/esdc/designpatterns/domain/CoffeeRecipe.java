package lt.esdc.designpatterns.domain;

public class CoffeeRecipe {
    private final int waterMl;
    private final int coffeeG;
    private final int milkMl;

    public CoffeeRecipe(int waterMl, int coffeeG, int milkMl) {
        this.waterMl = waterMl;
        this.coffeeG = coffeeG;
        this.milkMl = milkMl;
    }

    public int getWaterMl() {
        return waterMl;
    }

    public int getCoffeeG() {
        return coffeeG;
    }

    public int getMilkMl() {
        return milkMl;
    }

    public String toCommandString() {
        return waterMl + "ml " + coffeeG + "g " + milkMl + "ml";
    }
}


