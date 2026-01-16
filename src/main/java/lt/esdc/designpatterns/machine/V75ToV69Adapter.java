package lt.esdc.designpatterns.machine;

public class V75ToV69Adapter implements CoffeeMachineV69 {
    private final CoffeeMachineV75 v75Machine;

    public V75ToV69Adapter(CoffeeMachineV75 v75Machine) {
        this.v75Machine = v75Machine;
    }

    @Override
    public void send(String order) {
        String token = v75Machine.getToken();
        String session = v75Machine.openSession(token);
        try {
            v75Machine.makeCoffee(token, session, order);
        } finally {
            v75Machine.closeSession(token, session);
        }
    }
}

