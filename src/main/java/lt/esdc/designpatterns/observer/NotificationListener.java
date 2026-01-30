package lt.esdc.designpatterns.observer;

import lt.esdc.designpatterns.domain.OrderRecord;

public class NotificationListener implements OrderListener {
    @Override
    public void onOrderEvent(OrderEvent event) {
        OrderRecord record = event.getRecord();
        if (record.isSuccess()) {
            System.out.println("Order ready: " + record.getInput());
        } else {
            System.out.println("Order failed: " + record.getInput());
        }
    }
}
