import org.junit.Test;
import org.junit.Assert;

/**
 * Tests the measurement class
 * 
 * @author Anh-Tu Ngoc
 * @version 15 February 2018
 */
public class MeasurementTest
{
    /**
     * test measurment construct 
     */
    @Test
    public void measurementTest()
    {
        // Create some measurement objects
        Measurement georgeMiller = new Measurement(2.34);
        Measurement filthyFrank = new Measurement(0.34);
        Measurement pinkGuy = new Measurement(-950.0);
        Measurement joji = new Measurement(-5.10);

        // Testing the validity of these measurement values
        Assert.assertTrue(georgeMiller.isValid());
        Assert.assertTrue(filthyFrank.isValid());
        Assert.assertFalse(pinkGuy.isValid());
        Assert.assertTrue(joji.isValid());

        // Testing the double values assigned by the Measurement constructor
        Assert.assertEquals(2.34, georgeMiller.getValue(), 0.01);
        Assert.assertEquals(0.34, filthyFrank.getValue(), 0.01);
        Assert.assertEquals(0, pinkGuy.getValue(), 0.01);
        Assert.assertEquals(-5.10, joji.getValue(), 0.01); 
        Assert.assertFalse(0.34 == georgeMiller.getValue());
    }

    /**
     * Testing the default Measurement constructor
     */
    @Test
    public void measurementDefault()
    {
        // Calling a constructor with no arguments 
        Measurement df = new Measurement();
        Assert.assertEquals(0, df.getValue(), 0.01);
        Assert.assertFalse(df.isValid());
    }
    
    /**
     * Tets the toSTring method of Measurement class
     */
    @Test
    public void measurementtoString()
    {
        Measurement example = new Measurement(4.5);
        Assert.assertEquals("Value: 4.5, Validity: true", example.toString());
        Measurement example2 = new Measurement(-955.0);
        Assert.assertEquals("Not Available", example2.toString()); 
    }
    

}
