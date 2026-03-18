import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void testAddMe() {
        assertEquals(5.0, Calculator.addMe(2.0, 3.0));
        assertEquals(-1.0, Calculator.addMe(2.0, -3.0));
        assertEquals(0.0, Calculator.addMe(0.0, 0.0));
    }

    @Test
    void testSubMe() {
        assertEquals(-1.0, Calculator.subMe(2.0, 3.0));
        assertEquals(5.0, Calculator.subMe(2.0, -3.0));
        assertEquals(0.0, Calculator.subMe(0.0, 0.0));
    }
}