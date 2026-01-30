package lt.esdc.designpatterns.visitor;

import lt.esdc.designpatterns.domain.OrderRecord;

public interface OrderVisitor {
    void visit(OrderRecord record);
}
