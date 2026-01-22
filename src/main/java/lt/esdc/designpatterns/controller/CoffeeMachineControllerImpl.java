package lt.esdc.designpatterns.controller;

import lt.esdc.designpatterns.chain.CreateHandler;
import lt.esdc.designpatterns.chain.DiscountHandler;
import lt.esdc.designpatterns.chain.OrderContext;
import lt.esdc.designpatterns.chain.OrderHandler;
import lt.esdc.designpatterns.chain.ParseHandler;
import lt.esdc.designpatterns.chain.SendHandler;
import lt.esdc.designpatterns.chain.ToppingHandler;
import lt.esdc.designpatterns.factory.CoffeeFactory;
import lt.esdc.designpatterns.machine.CoffeeMachineV69;

public class CoffeeMachineControllerImpl implements CoffeeMachineController {
    
    private final CoffeeFactory coffeeFactory;
    private final CoffeeMachineV69 coffeeMachine;

    public CoffeeMachineControllerImpl(CoffeeFactory coffeeFactory, CoffeeMachineV69 coffeeMachine) {
        this.coffeeFactory = coffeeFactory;
        this.coffeeMachine = coffeeMachine;
    }

    @Override
    public void processOrder(String[] order) {
        for (String drinkName : order) {
            OrderContext ctx = new OrderContext(drinkName, coffeeFactory);
            
            OrderHandler h1 = new ParseHandler();
            OrderHandler h2 = new CreateHandler();
            OrderHandler h3 = new ToppingHandler();
            OrderHandler h4 = new DiscountHandler();
            OrderHandler h5 = new SendHandler(coffeeMachine);

            h1.setNext(h2);
            h2.setNext(h3);
            h3.setNext(h4);
            h4.setNext(h5);

            h1.handle(ctx);
        }
    }
}
