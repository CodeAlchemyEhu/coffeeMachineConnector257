package lt.esdc.designpatterns.observer;

import lt.esdc.designpatterns.domain.OrderRecord;

public class OrderEvent {
    private final OrderRecord record;

    public OrderEvent(OrderRecord record) {
        this.record = record;
    }

    public OrderRecord getRecord() {
        return record;
    }
}
