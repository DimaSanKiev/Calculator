import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

public class CalculatorEngineTest {

    CalculatorEngine calcEngine = new CalculatorEngine();

    @Before
    public void runBeforeEveryTest() {
        calcEngine.currentTotal = 0;
    }

    @Test
    public void testGetTotalStringInt() throws Exception {
        calcEngine.currentTotal = 50;
        assertTrue(calcEngine.currentTotal % 1.0 == 0);
    }

    @Test
    public void testGetTotalStringDouble() throws Exception {
        calcEngine.equal("50.5");
        assertTrue(calcEngine.currentTotal % 1.0 != 0);
    }

    @Test
    public void testEqual() throws Exception {
        calcEngine.equal("20");
        assertEquals(calcEngine.currentTotal, 20.0);
    }

    @Test
    public void testAdd() throws Exception {
        calcEngine.add(20);
        assertEquals(calcEngine.currentTotal, 20.0);

    }

    @Test
    public void testSubtract() throws Exception {
        calcEngine.subtract(20);
        assertEquals(calcEngine.currentTotal, -20.0);
    }

    @Test
    public void testMultiplyByZero() throws Exception {
        calcEngine.multiply(10);
        assertEquals(calcEngine.currentTotal, 0.0);
    }

    @Test
    public void testDivideByZero() throws Exception {
        calcEngine.divide(10);
        assertEquals(calcEngine.currentTotal, 0.0);
    }

    @Test
    public void testMultiply() throws Exception {
        calcEngine.add(10);
        calcEngine.multiply(10);
        assertEquals(calcEngine.currentTotal, 100.0);
    }

    @Test
    public void testDivide() throws Exception {
        calcEngine.add(10);
        calcEngine.divide(10);
        assertEquals(calcEngine.currentTotal, 1.0);
    }
}