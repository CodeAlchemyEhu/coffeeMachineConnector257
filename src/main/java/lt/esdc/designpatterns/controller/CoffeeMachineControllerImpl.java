package lt.esdc.designpatterns.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lt.esdc.designpatterns.domain.Coffee;
import lt.esdc.designpatterns.factory.CoffeeFactory;
import lt.esdc.designpatterns.machine.CoffeeMachineV69;

public class CoffeeMachineControllerImpl implements CoffeeMachineController {
    private static final Logger logger = LoggerFactory.getLogger(CoffeeMachineControllerImpl.class);
    
    private final CoffeeFactory coffeeFactory;
    private final CoffeeMachineV69 coffeeMachine;
    private final Map<String, Function<CoffeeFactory, Coffee>> coffeeCreators;

    public CoffeeMachineControllerImpl(CoffeeFactory coffeeFactory, CoffeeMachineV69 coffeeMachine) {
        this.coffeeFactory = coffeeFactory;
        this.coffeeMachine = coffeeMachine;
        this.coffeeCreators = initializeCoffeeCreators();
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
        String normalizedName = drinkName.trim().toLowerCase();
        Function<CoffeeFactory, Coffee> creator = coffeeCreators.get(normalizedName);

        if (creator == null) {
            throw new IllegalArgumentException("Unknown drink type: " + drinkName);
        }

        Coffee coffee = creator.apply(coffeeFactory);
        String command = coffee.getRecipe().toCommandString();
        logger.info("Processing order: {} -> {}", coffee.getName(), command);
        coffeeMachine.send(command);
    }
}


