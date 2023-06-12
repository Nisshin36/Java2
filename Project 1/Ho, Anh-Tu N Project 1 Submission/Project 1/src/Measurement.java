/**
 * Takes the measurement variables, Determines whether it can be used for
 * calculations, And have values that can be return for calculations
 * 
 * @author Anh-Tu Ngoc
 * @version 9 February 2018
 */
public class Measurement
{
    /** Variable to be tested once assigned a value */ 
    private double value; 

    /** The statement that is assigned after the double value is tested */ 
    private boolean valid; 
    
    /** Constant variable that will be tested with double value variable */ 
    static final double ERROR_VALUE = -900;  

    /**
     * private construct 
     */
    public Measurement()
    {
        this.value = 0;
        this.valid = false; // I can't determine validity if there's no value to test
    }

    /**
     * Creates a Measurement object and determines whether the value in the
     * parameter is a valid measurement.
     * 
     * @param candidate
     * the double value of either the tair, ta9m, or solarRadiation,
     * being tested
     */
    public Measurement(double candidate)
    {
        if (candidate <= ERROR_VALUE) // If the value is less than or equal to -900, then it is not a valid
                                      // measurement
        {
            valid = false; // Report it to the boolean variable,
                           // The measurement object will not be assigned a value.
        }
        else // If it passes
        {
            valid = true; // Then it is a valid measurement.
            value = candidate; // And the value can be a legitimate measurement
        }
    }

    /**
     * Retrieve the value of the measurement if there is one.
     * 
     * @return the value
     */
    public double getValue()
    {
        return value;
    }

    /**
     * Retrieve the validation of the measurement
     * 
     * @return the validation
     */
    public boolean isValid()
    {
        return valid;
    }

    /**
     * Output the measurement.
     * 
     * @return The value of the measurement (If it's a correct value) If not, the
     *         the method will return a message stating that the value was bad.
     */
    public String toString()
    {
        if (valid) // If it was deemed a good value by the constructor
        {
            return "Value: " + value + ", Validity: " + valid; // Return the string 
        } 
        else // It the constructor deemed it a bad value,
        { // Such as the value is not valid,
            return "Not Available"; // Return error message. 
        } 
    }

}
