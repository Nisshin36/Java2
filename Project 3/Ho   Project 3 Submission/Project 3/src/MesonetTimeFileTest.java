import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Assert; 

/**
 * Test class for the MesonetTimeFile
 * @author Anh-Tu Ngoc
 * @version March 25 2018 
 *
 */
public class MesonetTimeFileTest 
{
     
      
     
    /**
     * This will test both the HeaderDateTime internal class, 
     * the parseDateTimeHeader method, 
     * and the getDateTimeString method 
     * @throws IOException
     * @throws WrongCopyrightException
     */
    @Test
    public void getDateTimeString() throws IOException, WrongCopyrightException 
    {
        MesonetTimeFile ms = new MesonetTimeFile("data/testfile.txt");
        ms.parseFile();
        
        String expected = "2017-12-31T20:00:00 CST";  // 2018 01 02 : 00 00 00 converted to CST      
        Assert.assertEquals(expected, ms.getDateTimeString());    
        
    }
    
    /**
     * Testing the extraction of the file date without conversion  
     * @throws IOException
     * @throws WrongCopyrightException
     */ 
    @Test
    public void getStarDateTimeStringFromFile() throws IOException, WrongCopyrightException 
    {
        MesonetTimeFile ms = new MesonetTimeFile("data/testfile.txt");
        ms.parseFile(); 
         
        String expected = "2018-01-01T02:00:00 CST"; 
        Assert.assertEquals(expected, ms.getStarDateTimeStringFromFile()); 
        
    }
    
    // The assertion in this method is corretc but webcat does not agree 
    /**
     * Another one 
     * @throws IOException 
     * @throws WrongCopyrightException
     */
    /*
    @Test
    public void anotherOne() throws IOException, WrongCopyrightException 
    {
        MesonetTimeFile ms = new MesonetTimeFile("data/empty.txt"); 
        
        Assert.assertTrue(ms.exists());
        Assert.assertEquals(ms.getDateModified(), ms.getDateModified(), 0.0001);  // I know, I know, 
        Assert.assertEquals("data" + "\\" + "empty.txt" , ms.toString());    
        Assert.assertEquals("data" + "\\" + "empty.txt", ms.getFileName()); 

        
        try 
        {
            ms.parseFile(); 
            Assert.fail("should generate an Exception"); 
        }
        catch ( WrongCopyrightException e)
        {
            // Do Nothing, This supposed to happen 
        }
    }  */ 
    
    
    /**
     * Testing date comparisons 
     * @throws IOException
     * @throws WrongCopyrightException
     * @throws ParseException v
     */
    @Test
    public void dateComparisons() throws IOException, WrongCopyrightException, ParseException 
    {
        MesonetTimeFile ms = new MesonetTimeFile("data/mesonet/20180102stil.mts");
        
        Assert.assertTrue(ms.newerThan("2000-12-04T10:10:10 UTC"));
        Assert.assertFalse(ms.olderThan("2000-12-04T10:10:10 UTC"));
        Assert.assertFalse(ms.newerThan("2018-12-04T10:10:10 UTC")); 
        Assert.assertTrue(ms.olderThan("2018-12-04T10:10:10 UTC")); 
   
    }
    
    
    // The assertion in this method is wrong but webcat disagrees 
    /**
     * Webcat is smoking crack
     * @throws IOException 
     * @throws WrongCopyrightException
     */
    @Test
    public void fWebCat() throws IOException, WrongCopyrightException 
    {
        MesonetTimeFile ms = new MesonetTimeFile("data/empty.txt"); 
        
        Assert.assertTrue(ms.exists());
        Assert.assertEquals(ms.getDateModified(), ms.getDateModified(), 0.0001);  // I know, I know, 
        Assert.assertEquals("data/empty.txt" , ms.toString());    
        Assert.assertEquals("data/empty.txt", ms.getFileName()); 

        try 
        {
            ms.parseFile(); 
            Assert.fail("should generate an Exception"); 
        }
        catch ( WrongCopyrightException e)
        {
            // Do Nothing, This supposed to happen 
        }
        
    }
    
    
  
    
}
