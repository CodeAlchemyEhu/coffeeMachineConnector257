package lt.esdc.designpatterns.observer;

import lt.esdc.designpatterns.domain.OrderHistory;

public class OrderHistoryListener implements OrderListener {
    private final OrderHistory history = new OrderHistory();

    @Override
    public void onOrderEvent(OrderEvent event) {
        history.add(event.getRecord());
    }

    public OrderHistory getHistory() {
        return history;
    }
}
