package lt.esdc.designpatterns.domain;

import lt.esdc.designpatterns.domain.coffee.Latte;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CoffeeWithToppingsTest {

    @Test
    void shouldAddToppingsToCommand() {
        CoffeeRecipe recipe = new CoffeeRecipe(300, 12, 250);
        Coffee baseCoffee = new Latte(recipe, 4.0);
        CoffeeWithToppings coffeeWithToppings = new CoffeeWithToppings(baseCoffee, java.util.Arrays.asList("cream", "caramel"));
        
        String command = coffeeWithToppings.getCommandString();
        
        assertEquals("300ml 12g 250ml cream caramel", command);
    }

    @Test
    void shouldReturnBaseCommandWhenNoToppings() {
        CoffeeRecipe recipe = new CoffeeRecipe(200, 15, 100);
        Coffee baseCoffee = new Latte(recipe, 4.0);
        CoffeeWithToppings coffeeWithToppings = new CoffeeWithToppings(baseCoffee, java.util.Collections.emptyList());
        
        String command = coffeeWithToppings.getCommandString();
        
        assertEquals("200ml 15g 100ml", command);
    }

    @Test
    void shouldReturnBaseName() {
        CoffeeRecipe recipe = new CoffeeRecipe(300, 12, 250);
        Coffee baseCoffee = new Latte(recipe, 4.0);
        CoffeeWithToppings coffeeWithToppings = new CoffeeWithToppings(baseCoffee, java.util.Arrays.asList("cream"));
        
        String name = coffeeWithToppings.getName();
        
        assertEquals("latte", name);
    }
}

