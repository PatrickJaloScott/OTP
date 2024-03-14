package classtests;

import calculators.TemperatureCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TemperatureCalculatorTest {

    @Test
    void testFahrToCelBasic() {
        float degreeInFahr = 32;
        assertEquals(0, TemperatureCalculator.fahrToCel(degreeInFahr));
    }

    @Test
    void testRoundFToC() {
        float degree = 100;
        assertEquals(37.7777777778, TemperatureCalculator.fahrToCel(degree),0.3f);
    }
}