package lt.esdc.designpatterns.machine;

public class CoffeeMachineV75Facade {
    private final CoffeeMachineV75 machine;
    private String currentToken;
    private String currentSession;

    public CoffeeMachineV75Facade(CoffeeMachineV75 machine) {
        this.machine = machine;
    }

    public void makeCoffee(String order) {
        if (currentSession == null) {
            currentToken = machine.getToken();
            currentSession = machine.openSession(currentToken);
        }
        machine.makeCoffee(currentToken, currentSession, order);
    }

    public void close() {
        if (currentSession != null && currentToken != null) {
            machine.closeSession(currentToken, currentSession);
            currentSession = null;
            currentToken = null;
        }
    }
}

