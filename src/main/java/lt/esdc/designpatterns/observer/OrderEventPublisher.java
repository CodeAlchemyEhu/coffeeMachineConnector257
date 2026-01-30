package lt.esdc.designpatterns.observer;

import lt.esdc.designpatterns.chain.OrderContext;
import lt.esdc.designpatterns.domain.OrderRecord;

public class OrderEventPublisher {
    public static void publish(OrderContext ctx, boolean success, Exception error) {
        if (ctx.listeners == null || ctx.listeners.isEmpty()) return;
        if (ctx.notified) return;
        String message = error == null ? null : error.getMessage();
        OrderRecord record = new OrderRecord(
                ctx.input,
                ctx.region,
                ctx.coffeeType,
                ctx.toppings,
                ctx.discountType,
                ctx.finalPrice,
                success,
                message
        );
        OrderEvent event = new OrderEvent(record);
        for (OrderListener listener : ctx.listeners) {
            listener.onOrderEvent(event);
        }
        ctx.notified = true;
    }
}
