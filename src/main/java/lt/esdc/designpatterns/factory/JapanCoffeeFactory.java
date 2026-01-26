package lt.esdc.designpatterns.factory;

import lt.esdc.designpatterns.domain.Coffee;
import lt.esdc.designpatterns.domain.CoffeeRecipe;
import lt.esdc.designpatterns.domain.coffee.Cappuccino;
import lt.esdc.designpatterns.domain.coffee.Espresso;
import lt.esdc.designpatterns.domain.coffee.Latte;

public class JapanCoffeeFactory implements CoffeeFactory {

    @Override
    public Coffee createEspresso() {
        return new Espresso(new CoffeeRecipe(45, 17, 0), 1.80);
    }

    @Override
    public Coffee createCappuccino() {
        return new Cappuccino(new CoffeeRecipe(180, 15, 80), 3.20);
    }

    @Override
    public Coffee createLatte() {
        return new Latte(new CoffeeRecipe(220, 14, 180), 3.80);
    }
}
