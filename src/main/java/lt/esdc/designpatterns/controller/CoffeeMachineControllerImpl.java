package lt.esdc.designpatterns.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lt.esdc.designpatterns.domain.Coffee;
import lt.esdc.designpatterns.domain.CoffeeWithToppings;
import lt.esdc.designpatterns.factory.CoffeeFactory;
import lt.esdc.designpatterns.machine.CoffeeMachineV69;

public class CoffeeMachineControllerImpl implements CoffeeMachineController {
    private static final Logger logger = LoggerFactory.getLogger(CoffeeMachineControllerImpl.class);
    
    private final CoffeeFactory coffeeFactory;
    private final CoffeeMachineV69 coffeeMachine;
    private final Map<String, Function<CoffeeFactory, Coffee>> coffeeCreators;
    private final List<String> validToppings;

    public CoffeeMachineControllerImpl(CoffeeFactory coffeeFactory, CoffeeMachineV69 coffeeMachine) {
        this.coffeeFactory = coffeeFactory;
        this.coffeeMachine = coffeeMachine;
        this.coffeeCreators = initializeCoffeeCreators();
        this.validToppings = Arrays.asList("caramel", "cream", "liquor");
    }

    private Map<String, Function<CoffeeFactory, Coffee>> initializeCoffeeCreators() {
        Map<String, Function<CoffeeFactory, Coffee>> creators = new HashMap<>();
        creators.put("espresso", CoffeeFactory::createEspresso);
        creators.put("cappuccino", CoffeeFactory::createCappuccino);
        creators.put("latte", CoffeeFactory::createLatte);
        return creators;
    }

    @Override
    public void processOrder(String[] order) {
        for (String drinkName : order) {
            processDrink(drinkName);
        }
    }

    private void processDrink(String drinkName) {
        String[] parts = drinkName.trim().toLowerCase().split("\\s+");
        if (parts.length == 0) {
            throw new IllegalArgumentException("Empty drink name");
        }

        String coffeeType = parts[0];
        Function<CoffeeFactory, Coffee> creator = coffeeCreators.get(coffeeType);

        if (creator == null) {
            throw new IllegalArgumentException("Unknown drink type: " + coffeeType);
        }

        Coffee coffee = creator.apply(coffeeFactory);

        List<String> toppings = new ArrayList<>();
        for (int i = 1; i < parts.length; i++) {
            String topping = parts[i];
            if (validToppings.contains(topping)) {
                toppings.add(topping);
            } else {
                throw new IllegalArgumentException("Unknown topping: " + topping);
            }
        }

        String command;
        if (!toppings.isEmpty()) {
            CoffeeWithToppings coffeeWithToppings = new CoffeeWithToppings(coffee, toppings);
            command = coffeeWithToppings.getCommandString();
            coffee = coffeeWithToppings;
        } else {
            command = coffee.getRecipe().toCommandString();
        }
        logger.info("Processing order: {} -> {}", coffee.getName(), command);
        coffeeMachine.send(command);
    }
}


