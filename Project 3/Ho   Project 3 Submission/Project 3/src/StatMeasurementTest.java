import java.text.ParseException;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author Anh-Tu Ngoc
 * @version 20 March 2018 
 * Testing the implementation of the StatsMeasurementClass 
 * Testing the implementation of the Measurement Class 
 */
public class StatMeasurementTest
{ 
    
    /** 
     * Testing both constructors and the assignments of parameters 
     * This method also tests the set and getter methods 
     */
    @Test
    public void statMeasurementConstructorTest()
    {
        GregorianCalendar gc = new GregorianCalendar(); 
        
        StatMeasurement stat = new StatMeasurement(300.0, gc, "onset", "1133", StatType.MIN); 
        //StatMeasurement stat4 = new StatMeasurement(-999.0, gc, "onset", "1133", StatType.MIN);
        //StatMeasurement stat2 = new StatMeasurement();    
         
        Assert.assertEquals(300.0, stat.getValue(), 0.0001);  
        Assert.assertEquals(gc, stat.getDateTimeOfMeasurment());
        Assert.assertEquals("1133", stat.getParamId()); 
        Assert.assertEquals(StatType.MIN, stat.getStatType()); 
        
        stat.setParamId("Susie");
        stat.setStatType(StatType.MAX);
        
        Assert.assertEquals("Susie", stat.getParamId());
        Assert.assertEquals(StatType.MAX, stat.getStatType());
        
        Assert.assertEquals("onset", stat.getStationId());
          
     
    }
    
    
    
    /**
     * Testing the isLessThan method 
     */
    @Test
    public void isLessThanTest()
    {
        
        GregorianCalendar gc = new GregorianCalendar(); 
        
        StatMeasurement stat = new StatMeasurement(300.0, gc, "onset", "1133", StatType.MIN); 
        StatMeasurement stat2 = new StatMeasurement(200.0, gc, "onset", "1133", StatType.MIN); 
        StatMeasurement stat3 = new StatMeasurement(-999.0, gc, "onset", "1133", StatType.MIN); 
        StatMeasurement stat4 = new StatMeasurement(-999.0, gc, "onset", "1133", StatType.MIN); 
        
        Assert.assertTrue(stat.isValid()); 
        Assert.assertFalse(stat3.isValid()); 
        Assert.assertTrue(stat2.isLessThan(stat));     
        Assert.assertFalse(stat.isLessThan(stat2));         
        Assert.assertTrue(stat3.isLessThan(stat4));  
        Assert.assertTrue(stat.isLessThan(stat3));
        Assert.assertFalse(stat3.isLessThan(stat));  
        Assert.assertTrue(stat2.isLessThan(stat4));
        
    }
    
    
    
    /**
     * Testing the isGreaterThan method 
     */
    @Test
    public void isGreaterThanTest()
    {
        
        GregorianCalendar gc = new GregorianCalendar(); 
        
        StatMeasurement stat = new StatMeasurement(300.0, gc, "onset", "1133", StatType.MIN); 
        StatMeasurement stat2 = new StatMeasurement(200.0, gc, "onset", "1133", StatType.MIN); 
        StatMeasurement stat3 = new StatMeasurement(-999.0, gc, "onset", "1133", StatType.MIN); 
        StatMeasurement stat4 = new StatMeasurement(-999.0, gc, "onset", "1133", StatType.MIN); 
        
        Assert.assertTrue(stat2.isValid());
        Assert.assertFalse(stat4.isValid()); 
        Assert.assertTrue(stat.isGreaterThan(stat2));
        Assert.assertFalse(stat2.isGreaterThan(stat));
        Assert.assertTrue(stat2.isGreaterThan(stat3));  
        Assert.assertFalse(stat3.isGreaterThan(stat)); 
        Assert.assertTrue(stat3.isGreaterThan(stat4));
         
    }
    
    /**
     * TEst
     */
    @Test
    public void toStringTest()
    {
        // GregorianCalendar gc = new GregorianCalendar(); 
        Measurement msrmt = new Measurement(34.5);
        Measurement msr = new Measurement(-960.0);
        // StatMeasurement stat = new StatMeasurement(300.0, gc, "onset", "1133", StatType.MIN); 
        
        Assert.assertEquals("34.5000", msrmt.toString()); 
        Assert.assertEquals("bad", msr.toString());  
    }
    
    
    /**
     * TEst 
     * @throws WrongTimeZoneException
     * @throws ParseException
     */
    @Test
    public void newerOlderThan() throws WrongTimeZoneException, ParseException 
    {
        GregorianCalendar gc = new GregorianCalendar(1998, 4, 15); 
        GregorianCalendar gc2 = new GregorianCalendar(1997, 4, 15); 
        
        StatMeasurement stat = new StatMeasurement(300.0, gc, "onset", "1133", StatType.MIN); 
        StatMeasurement stat2 = new StatMeasurement(300.0, gc2, "onset", "1133", StatType.MIN);  
        
        Assert.assertEquals(-1, stat.compareWithTimeString("2000-12-04T10:10:10 UTC"));
        Assert.assertEquals(1, stat.compareWithTimeString("1995-12-04T10:10:10 UTC"));
        
        Assert.assertEquals(true, stat.olderThan("2000-12-04T10:10:10 UTC"));    
        Assert.assertEquals(false, stat.olderThan("1995-12-04T10:10:10 UTC"));    
        
        Assert.assertEquals( true , stat2.newerThan("1995-12-04T10:10:10 UTC"));
        Assert.assertEquals( false , stat2.newerThan("2004-12-04T10:10:10 UTC"));
         
                                         
    }
    
    /**
     * Test
     */
    @Test
    public void toStringTest2() 
    {
        GregorianCalendar gc = new GregorianCalendar(1997, 4, 15);        
        StatMeasurement stat = new StatMeasurement(300.0, gc, "onset", "1133", StatType.MIN); 
        
        Assert.assertEquals("1133\t" + StatType.MIN.toString() + "\tonset\t300.0", stat.toString()); 
    }
}
