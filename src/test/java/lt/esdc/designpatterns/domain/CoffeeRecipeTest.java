package lt.esdc.designpatterns.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CoffeeRecipeTest {

    @Test
    void shouldFormatCommandString() {
        CoffeeRecipe recipe = new CoffeeRecipe(200, 15, 100);
        
        String command = recipe.toCommandString();
        
        assertEquals("200ml 15g 100ml", command);
    }

    @Test
    void shouldReturnCorrectValues() {
        CoffeeRecipe recipe = new CoffeeRecipe(300, 12, 250);
        
        assertEquals(300, recipe.getWaterMl());
        assertEquals(12, recipe.getCoffeeG());
        assertEquals(250, recipe.getMilkMl());
    }
}

