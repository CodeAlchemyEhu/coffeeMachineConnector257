package lt.esdc.designpatterns.visitor;

import lt.esdc.designpatterns.domain.OrderHistory;
import lt.esdc.designpatterns.domain.OrderRecord;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderHistoryVisitorTest {

    @Test
    void shouldCalculateStatistics() {
        OrderHistory history = new OrderHistory();
        history.add(new OrderRecord("latte", "USA", "latte", Arrays.asList("cream"), "student", 3.0, true, null));
        history.add(new OrderRecord("espresso", "USA", "espresso", Collections.emptyList(), "none", 2.0, false, "fail"));

        StatisticsVisitor stats = new StatisticsVisitor();
        history.accept(stats);

        assertEquals(2, stats.getTotal());
        assertEquals(1, stats.getSuccessCount());
        assertEquals(1, stats.getFailedCount());
        assertEquals(3.0, stats.getTotalRevenue(), 0.0001);
        assertEquals(1, stats.getDiscountUsage().get("student").intValue());
        assertEquals(1, stats.getCoffeeUsage().get("latte").intValue());
        assertEquals(1, stats.getToppingUsage().get("cream").intValue());
    }

    @Test
    void shouldGenerateReport() {
        OrderHistory history = new OrderHistory();
        history.add(new OrderRecord("latte", "USA", "latte", Arrays.asList("cream"), "student", 3.0, true, null));
        history.add(new OrderRecord("espresso", "USA", "espresso", Collections.emptyList(), "none", 2.0, false, "fail"));

        ReportVisitor reportVisitor = new ReportVisitor();
        history.accept(reportVisitor);

        String report = reportVisitor.getReport();
        assertTrue(report.contains("Orders processed: 2"));
        assertTrue(report.contains("Successful: 1"));
        assertTrue(report.contains("Failed: 1"));
        assertTrue(report.contains("cream"));
        assertTrue(report.contains("student"));
    }
}
