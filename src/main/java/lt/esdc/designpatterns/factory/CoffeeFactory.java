package lt.esdc.designpatterns.factory;

import lt.esdc.designpatterns.domain.Coffee;

public interface CoffeeFactory {
    Coffee createEspresso();
    Coffee createCappuccino();
    Coffee createLatte();
}


