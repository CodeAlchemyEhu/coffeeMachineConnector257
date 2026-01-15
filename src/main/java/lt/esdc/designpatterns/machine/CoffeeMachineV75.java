package lt.esdc.designpatterns.machine;

public interface CoffeeMachineV75 {
    String getToken();
    String openSession(String token);
    void makeCoffee(String token, String session, String coffee);
    void closeSession(String token, String session);
}
