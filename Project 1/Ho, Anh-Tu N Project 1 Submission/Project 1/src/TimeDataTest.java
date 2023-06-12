import org.junit.Assert;
import org.junit.Test;

/**
 * Testing the data parsing and whether the TimeData object receieves the
 * corretc values.
 * 
 * @author Anh-Tu Ngoc
 * @version 15 February 2018
 */
public class TimeDataTest
{

    /**
     * Testing the constructor to see whether it receives the correct parameters,
     * Also, test whether it the Measurement class can work with the TimeData class.
     */
    @Test
    public void timeDataConstructorAndGetterTest()
    {
        // Constructing some measurement variables for tair, ta9m, and solar radiation
        Measurement tair = new Measurement(9.0);
        Measurement ta9m = new Measurement(-900.0);
        Measurement solar = new Measurement(400);

        // Creating TimeData object to test its conturctor.
        TimeData dehTime = new TimeData("MSKV", 2022, 10, 15, 50, tair, ta9m, solar);

        // These will test the class's constructor and getter methods
        Assert.assertTrue(dehTime.getStationID().equals("MSKV"));
        Assert.assertEquals(2022, dehTime.getYear());
        Assert.assertEquals(10, dehTime.getMonth());
        Assert.assertEquals(15, dehTime.getDay());
        Assert.assertEquals(50, dehTime.getMinute());

        // This will test the class's getter methods and also the Measurement
        // constructor
        // Especially when a value should be flagged 
        Assert.assertEquals(9.0, dehTime.getTair().getValue(), 0.01);
        Assert.assertEquals(0, dehTime.getTa9m().getValue(), 0.01);
        Assert.assertEquals(400, dehTime.getSolarRadiation().getValue(), 0.01);
    }
    
    /**
     * Testing toStringMethod
     */
    @Test
    public void toStringTest()
    {
        // Constructing some measurement variables for tair, ta9m, and solar radiation
        Measurement tair = new Measurement(9.0);
        Measurement ta9m = new Measurement(-900.0);
        Measurement solar = new Measurement(400); 

        // Creating TimeData object 
        TimeData dehTime = new TimeData("MSKV", 2022, 10, 15, 50, tair, ta9m, solar);
        
        // Testing out its printing
        String output = String.format("Air temperature[1.5m] = %s, Air temperature[9m] = %s, Solar Radiation = %s",
                tair.getValue(), ta9m.getValue(), solar.getValue());
        Assert.assertEquals(output, dehTime.toString());  
    }
} 
