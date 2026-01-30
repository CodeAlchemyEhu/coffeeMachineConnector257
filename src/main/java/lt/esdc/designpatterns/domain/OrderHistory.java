package lt.esdc.designpatterns.domain;

import lt.esdc.designpatterns.visitor.OrderVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderHistory {
    private final List<OrderRecord> orders = new ArrayList<>();

    public void add(OrderRecord record) {
        orders.add(record);
    }

    public List<OrderRecord> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    public void accept(OrderVisitor visitor) {
        for (OrderRecord record : orders) {
            visitor.visit(record);
        }
    }
}
