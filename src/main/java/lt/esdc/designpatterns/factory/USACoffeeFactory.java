package lt.esdc.designpatterns.factory;

import lt.esdc.designpatterns.domain.Coffee;
import lt.esdc.designpatterns.domain.CoffeeRecipe;
import lt.esdc.designpatterns.domain.coffee.Cappuccino;
import lt.esdc.designpatterns.domain.coffee.Espresso;
import lt.esdc.designpatterns.domain.coffee.Latte;

public class USACoffeeFactory implements CoffeeFactory {

    @Override
    public Coffee createEspresso() {
        return new Espresso(new CoffeeRecipe(90, 14, 0), 2.00);
    }

    @Override
    public Coffee createCappuccino() {
        return new Cappuccino(new CoffeeRecipe(250, 12, 180), 3.50);
    }

    @Override
    public Coffee createLatte() {
        return new Latte(new CoffeeRecipe(300, 12, 250), 4.00);
    }
}
