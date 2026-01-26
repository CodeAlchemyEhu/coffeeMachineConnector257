package lt.esdc.designpatterns.chain;

public interface OrderHandler {
    void setNext(OrderHandler next);
    void handle(OrderContext ctx);
}
