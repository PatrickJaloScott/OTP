package calculators;

/**
 * Calculator class for calculating temperatures
 */
public class TemperatureCalculator {
    /**
     * Converts temperature in degrees from Fahrenheit to Celsius
     * @param f degrees in Fahrenheit
     * @return degrees in Celsius, rounded
     */
    public static float fahrToCel(float f) {
        return Math.round((f-32)* 5/9);
    }
}
