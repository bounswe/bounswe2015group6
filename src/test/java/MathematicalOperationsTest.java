import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MathematicalOperationsTest {
    
    static MathematicalOperations mathematicalOperations;
    
    @BeforeClass
    public static void init() {
        mathematicalOperations = new MathematicalOperations();
    }
    
    @Test
    public void testBinaryPlus() {
        assertEquals(4,mathematicalOperations.binaryPlus(4,0));
        assertEquals(0,mathematicalOperations.binaryPlus(-1,1));
        assertEquals(85,mathematicalOperations.binaryPlus(28,57));
    }
    
    @Test
    public void testBinaryMinus() {
    	assertEquals("10 minus 2 should be 8", 8, mathematicalOperations.binaryMinus(10,2));
    	assertEquals("1 minus 1 should be 0", 0, mathematicalOperations.binaryMinus(1,1));
    	assertEquals("1 minus 0 should be 1", 1, mathematicalOperations.binaryMinus(1,0));
    	assertEquals("1 minus 2 should be -1", -1, mathematicalOperations.binaryMinus(1,2));
    	assertEquals("-1 minus -2 should be 1", 1, mathematicalOperations.binaryMinus(-1,-2));
    }
    
    @Test
    public void testTimes() {
    	assertEquals("3 times 4 should be 12", 12, mathematicalOperations.times(3,4));
    	assertEquals("1 times 7 should be 7", 7, mathematicalOperations.times(1,7));
    	assertEquals("0 times 4 should be 0", 0, mathematicalOperations.times(0,4));
    	assertEquals("-1 times 2 should be -2", -2, mathematicalOperations.times(-1,2));
    	assertEquals("-3 times -2 should be 6", 6, mathematicalOperations.times(-3,-2));
    }
    
    @Test
    public void testDivide() {
        assertEquals("12 divided by 4 should be 3", 3, mathematicalOperations.divide(12,4));
        assertEquals("100 divided by 25 should be 4", 4, mathematicalOperations.divide(100,25));
        assertEquals("12 divided by 4 should be 3", 3, mathematicalOperations.divide(12,4));
        assertEquals("-24 divided by 8 should be -3", -3, mathematicalOperations.divide(-24,8));
    }
    
    @Test(expected = ArithmeticException.class)
    public void testDivisionWithException() {
        int i = mathematicalOperations.divide(12,0);
    }

    @Test
    public void testPower() {
        assertEquals("3 to the power of 4 should be 81", 81, mathematicalOperations.power(3, 4));
        assertEquals("12 to the power of 2 should be 144", 144, mathematicalOperations.power(12, 2));
        assertEquals("2 to the power of -1 should be 0.5", 0.5, mathematicalOperations.power(2, -1));
        assertEquals("0 to the power of 0 should be 1", 1, mathematicalOperations.power(0, 0));
        assertEquals("-7 to the power of 3 should be -343", -343, mathematicalOperations.power(-7, 3));
    }
    
    @Test
    public void testAbs(){
    	assertEquals("Absolute value of -1 should be 1", 1, mathematicalOperations.abs(-1));
    	assertEquals("Absolute value of -100 should be 100", 100, mathematicalOperations.abs(-100));
    	assertEquals("Absolute value of -58 should be 58", 58, mathematicalOperations.abs(-58));
    	assertEquals("Absolute value of 0 should be 0", 0, mathematicalOperations.abs(0));
    	assertEquals("Absolute value of 5 should be 5", 5, mathematicalOperations.abs(5));
    }
}
