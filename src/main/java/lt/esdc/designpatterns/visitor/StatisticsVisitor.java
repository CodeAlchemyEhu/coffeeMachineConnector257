package lt.esdc.designpatterns.visitor;

import lt.esdc.designpatterns.domain.OrderRecord;

import java.util.HashMap;
import java.util.Map;

public class StatisticsVisitor implements OrderVisitor {
    private int total;
    private int successCount;
    private int failedCount;
    private double totalRevenue;
    private final Map<String, Integer> discountUsage = new HashMap<>();
    private final Map<String, Integer> coffeeUsage = new HashMap<>();
    private final Map<String, Integer> toppingUsage = new HashMap<>();

    @Override
    public void visit(OrderRecord record) {
        total++;
        if (record.isSuccess()) {
            successCount++;
            totalRevenue += record.getFinalPrice();
        } else {
            failedCount++;
        }

        if (record.getDiscountType() != null) {
            discountUsage.merge(record.getDiscountType(), 1, Integer::sum);
        }
        if (record.getCoffeeType() != null) {
            coffeeUsage.merge(record.getCoffeeType(), 1, Integer::sum);
        }
        for (String t : record.getToppings()) {
            toppingUsage.merge(t, 1, Integer::sum);
        }
    }

    public int getTotal() {
        return total;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public int getFailedCount() {
        return failedCount;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public double getAveragePrice() {
        if (successCount == 0) return 0;
        return totalRevenue / successCount;
    }

    public Map<String, Integer> getDiscountUsage() {
        return discountUsage;
    }

    public Map<String, Integer> getCoffeeUsage() {
        return coffeeUsage;
    }

    public Map<String, Integer> getToppingUsage() {
        return toppingUsage;
    }
}
