import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Assert; 

/**
 * Class testing the DaysStatistics class 
 * @author Anh-Tu Ngoc
 * @version July 4th 1776 
 */
public class DaysStatisticsTest 
{

    /**
     * Testing the find statistics class 
     * @throws WrongParameterIdException
     * @throws IOException
     * @throws WrongCopyrightException
     * @throws ParseException
     */
    @Test
    public void findStatisticsTest() throws WrongParameterIdException, IOException, 
                                     WrongCopyrightException, ParseException 
    {
        
        
        // Testing getMinimum method 
        
        String[] files = {"data/testfile.txt", "data/testfile2.txt", "data/testfile.txt"};         
        DaysStatistics ds = new DaysStatistics(files); 
        ds.findStatistics();   
        
        String expectedTair = "TAIR" + "\t" + "MIN" + "\t" + "STIL" + "\t" + "-12.0"; 
        Assert.assertEquals( expectedTair , ds.getMinimumDay("tair").toString());    
          
        String expectedTa9m = "TA9M" + "\t" + "MIN" + "\t" + "STIL" + "\t" + "-9.5"; 
        Assert.assertEquals( expectedTa9m , ds.getMinimumDay("ta9m").toString());     
        
        String expectedSrad = "SRAD" + "\t" + "MIN" + "\t" + "STIL" + "\t" + "-100.0";  
        Assert.assertEquals( expectedSrad , ds.getMinimumDay("srad").toString());     
        
        try
        {
            ds.getMinimumDay("aks").toString(); 
        }
        catch ( WrongParameterIdException e)
        {
            // Do Nothing, 'posed to happen 
        }         
         
    }
    
    
    
    
    /**
     * Testing the getMaximumDay method 
     * @throws WrongParameterIdException
     * @throws IOException
     * @throws WrongCopyrightException
     * @throws ParseException
     */
    @Test
    public void findStatisticsTest2() throws WrongParameterIdException, IOException, 
                                     WrongCopyrightException, ParseException 
    {
        // Testing getMaximum method
        

        String[] files = {"data/testfile.txt", "data/testfile2.txt", "data/testfile.txt"};         
        DaysStatistics ds = new DaysStatistics(files);  
        ds.findStatistics();   
         
        String expectedTairMax = "TAIR" + "\t" + "MAX" + "\t" + "STIL" + "\t" + "50.0"; 
        Assert.assertEquals( expectedTairMax , ds.getMaximumDay("tair").toString());        
         
        String expectedTa9mMax = "TA9M" + "\t" + "MAX" + "\t" + "STIL" + "\t" + "45.0";  
        Assert.assertEquals( expectedTa9mMax , ds.getMaximumDay("ta9m").toString());     
        
        String expectedSradMax = "SRAD" + "\t" + "MAX" + "\t" + "STIL" + "\t" + "500.0";  
        Assert.assertEquals( expectedSradMax , ds.getMaximumDay("srad").toString());  
    
        
        Assert.assertEquals(expectedTairMax + "\n" + "TAIR" + "\t" + "MIN" + "\t" + "STIL" + "\t" + "-12.0" + "\n", 
                ds.combineMinMaxStatistics("TAIR")); 
         
        try
        {
            ds.getMaximumDay("aks").toString(); 
        }
        catch ( WrongParameterIdException e)
        {
            // Do Nothing, 'posed to happen 
        }         
        
    }
    
    
    /**
     * Testing the toString method 
     * @throws ParseException 
     * @throws WrongCopyrightException   
     * @throws IOException 
     */
    @Test
    public void toStringTest() throws IOException, WrongCopyrightException, ParseException 
    {
        String[] files = {"data/testfile.txt"};         
        DaysStatistics ds = new DaysStatistics(files);   
        ds.findStatistics();  
        
        // Good Enough 
        Assert.assertEquals("ID\t STAT\t VALUE\t STID\t DATE\t T\t TIME\t TZ\r\n", ds.toString());
        
    }
    
    
     
}
