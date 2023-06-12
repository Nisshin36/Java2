import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

/**
 * This class will test the constructor and all the methods in the DayData class
 * Furthermore, this class will determine whether or not the minimums, maximums,
 * and averages are computed correctly in conjunction with the Measurement
 * class. REMARK: The instructions on the PDF sheet does not say anything about
 * testing the toString() method... so I ain't gon' do it.
 * 
 * 
 * 
 * @author Anh-Tu Ngoc
 * @version 14 February 2018
 */
public class DayDataTest
{
    /**
     * Testing the DayDaya constuctor and its variable assignments If this test
     * pass, then read() and defaultAssignment() must also pass, Since it is called
     * from the DayData consructor It also means that all data reading and parsing
     * is done correctly, since those operations are within the read() method.
     * 
     * @throws IOException
     */
    @Test
    public void dayDataTest() throws IOException
    {
        // Create a DayData object with a test file.
        DayData susie = new DayData(2018, 01, 01, "nrmn", "data/testfile.csv");

        // Testing the variable assignments
        Assert.assertEquals(2018, susie.getYear());
        Assert.assertEquals(01, susie.getMonth());
        Assert.assertEquals("nrmn", susie.getStationID());
    }
 
    /**
     * Testing the getter methods and all the instance variables
     * 
     * @throws IOException
     */
    @Test
    public void getterMethodTests() throws IOException
    {
        DayData susie = new DayData(2018, 01, 01, "nrmn", "data/testfile.csv"); 

        Assert.assertEquals(2018, susie.getYear());
        Assert.assertEquals(01, susie.getMonth()); 
        Assert.assertEquals("nrmn", susie.getStationID());  
        Assert.assertEquals(500, susie.getTairMax(), 0.0001); 
        Assert.assertEquals(-8.2, susie.getTairMin(), 0.0001);
        Assert.assertEquals(118.875, susie.getTairAverage(), 0.01); 
        Assert.assertEquals(2.7, susie.getTa9mMin(), 0.0001);
        Assert.assertEquals(9.5, susie.getTa9mMax(), 0.0001);
        Assert.assertEquals(5.45, susie.getTa9mAverage(), 0.0001);
        Assert.assertEquals(1450, susie.getSolarRadiationTotal(), 0.01);
        Assert.assertEquals(250, susie.getSolarRadiationMin(), 0.01);
        Assert.assertEquals(500, susie.getSolarRadiationMax(), 0.01);
        Assert.assertEquals(362.5, susie.getSolarRadiationAverage(), 0.01); 

    }
    
    /**
     * Test the daydata toString method
     */
    @Test
    public void toStringTest() throws IOException 
    {
        DayData example = new DayData(2018, 01, 01, "nrmn", "data/testfile.csv");
        
        String a = "Date: 2018-1-1, nrmn\n" 
                + "Air Temperature[1.5 m]: Min -8.2, Max 500.0, Average 118.875\n"
                + "Air Temperature[9.5 m]: Min 2.7, Max 9.5, Average 5.45\n" 
                + "Solar Radiation:        Min 250.0, Max 500.0, Average 362.5, Total 1450.0"; 
        Assert.assertTrue(example.toString().equals(a));      
        
    }
}
