package lab_3a;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;


public class TestCases {
    LocalTime smt = LocalTime.now();
    LocalTime emt = smt.plusHours(3);
    private final MovieTicketPriceCalculator calculator = new MovieTicketPriceCalculator(smt, emt, 10, 60);

    @Test
    public void testIllegalTime() {
        boolean thrown = false;
        try {
            new MovieTicketPriceCalculator(emt, smt, 10, 60);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testComputePriceEqualMatineeNoDiscount(){
        assertEquals((calculator.computePrice(smt, 12)), 2400);
    }

    @Test
    public void testComputePriceEqualMatineeDiscount(){
        assertEquals((calculator.computePrice(smt, 10)), 2100);
        assertEquals((calculator.computePrice(smt, 60)), 2000);
    }

    @Test
    public void testComputePriceAfterMatineeBeforeStandardNoDiscount(){
        assertEquals((calculator.computePrice(smt.plusHours(2), 12)), 2400);
    }

    @Test
    public void testComputePriceAfterMatineeBeforeStandardDiscount(){
        assertEquals((calculator.computePrice(smt.plusHours(2), 10)), 2100);
        assertEquals((calculator.computePrice(smt.plusHours(2), 60)), 2000);
    }

    @Test
    public void testComputePriceEqualStandardNoDiscount(){
        assertEquals((calculator.computePrice(emt, 12)), 2700);
    }
    @Test
    public void testComputePriceEqualStandardDiscount(){
        assertEquals((calculator.computePrice(smt.plusHours(2), 10)), 2100);
        assertEquals((calculator.computePrice(smt.plusHours(2), 60)), 2000);
    }

    @Test
    public void testComputePriceBeforeStandardNoDiscount(){
        assertEquals((calculator.computePrice(smt.plusMinutes(179), 12)), 2400);
    }
    @Test
    public void testComputePriceBeforeStandardDiscount(){
        assertEquals((calculator.computePrice(smt.plusMinutes(179), 10)), 2100);
        assertEquals((calculator.computePrice(smt.plusMinutes(179), 60)), 2000);
    }

    @Test
    public void testComputeDiscountInRange(){
        assertEquals((calculator.computeDiscount(12)), 0);
    }

    @Test
    public void testComputeDiscountEdgeCaseChild(){
        assertEquals((calculator.computeDiscount(10)), 300);
    }

    @Test
    public void testComputeDiscountChild(){
        assertEquals((calculator.computeDiscount(8)), 300);
    }

    @Test
    public void testComputeDiscountEdgeCaseSenior(){
        assertEquals((calculator.computeDiscount(60)), 400);
    }

    @Test
    public void testComputeDiscountSenior(){
        assertEquals((calculator.computeDiscount(65)), 400);
    }
}