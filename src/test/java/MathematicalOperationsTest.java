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
}