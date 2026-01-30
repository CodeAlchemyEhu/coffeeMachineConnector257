package lt.esdc.designpatterns.visitor;

import lt.esdc.designpatterns.domain.OrderRecord;

import java.util.HashMap;
import java.util.Map;

public class ReportVisitor implements OrderVisitor {
    private int total;
    private int successCount;
    private int failedCount;
    private final Map<String, Integer> discountUsage = new HashMap<>();
    private final Map<String, Integer> toppingUsage = new HashMap<>();

    @Override
    public void visit(OrderRecord record) {
        total++;
        if (record.isSuccess()) {
            successCount++;
        } else {
            failedCount++;
        }
        if (record.getDiscountType() != null) {
            discountUsage.merge(record.getDiscountType(), 1, Integer::sum);
        }
        for (String t : record.getToppings()) {
            toppingUsage.merge(t, 1, Integer::sum);
        }
    }

    public String getReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("Orders processed: ").append(total).append("\n");
        sb.append("Successful: ").append(successCount).append("\n");
        sb.append("Failed: ").append(failedCount).append("\n\n");
        sb.append("Topping usage:\n");
        for (Map.Entry<String, Integer> e : toppingUsage.entrySet()) {
            sb.append(e.getKey()).append(": ").append(e.getValue()).append("\n");
        }
        sb.append("\nDiscount usage:\n");
        for (Map.Entry<String, Integer> e : discountUsage.entrySet()) {
            sb.append(e.getKey()).append(": ").append(e.getValue()).append("\n");
        }
        return sb.toString();
    }
}
