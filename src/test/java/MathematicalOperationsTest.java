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
    	assertEquals("10 minus 2 should be 8", 8, mathematicalOperations.binaryMinus(10,8));
    	assertEquals("1 minus 1 should be 0", 0, mathematicalOperations.binaryMinus(1,1));
    	assertEquals("1 minus 0 should be 1", 1, mathematicalOperations.binaryMinus(1,0));
    	assertEquals("1 minus 2 should be -1", -1, mathematicalOperations.binaryMinus(1,2));
    	assertEquals("-1 minus -2 should be -1", -3, mathematicalOperations.binaryMinus(-1,-2));
    }
}