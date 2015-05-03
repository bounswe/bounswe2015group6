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
}
