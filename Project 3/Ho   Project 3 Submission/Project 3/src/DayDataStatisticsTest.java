import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert; 

/**
 * Class to test the DayDataStatistics class and its methods 
 * @author Anh-Tu Ngoc
 * @version March 2018 
 *
 */
public class DayDataStatisticsTest
{
   
    /**
     * Testing the getSolarRadiation methods and the getStationID
     */
    @Test  
    public void getSolarRadiationTest()
    {

        TimeData sampleTimeData = new TimeData("LEXUS", 1998, 10, 10, 30, 
                new Measurement(100), new Measurement(200), new Measurement(500));
         
        TimeData sampleTimeData2 = new TimeData("LEXUS", 1995, 10, 10, 30, 
                new Measurement(100), new Measurement(200), new Measurement(600));
        
        TimeData sampleTimeData3 = new TimeData("LEXUS", 2000, 10, 10, 30, 
                new Measurement(100), new Measurement(200), new Measurement(700));
        
        ArrayList<TimeData> sampleArrayList = new ArrayList<TimeData>(); 
        sampleArrayList.add(sampleTimeData2); 
        sampleArrayList.add(sampleTimeData);
        sampleArrayList.add(sampleTimeData3); 
        
        DayDataStatistics sample = new DayDataStatistics(sampleArrayList);  
         
        Assert.assertEquals("SRAD" + "\t" + "AVG" + "\t" + "LEXUS" + "\t" + "600.0", 
                            sample.getSolarRadiationAverage().toString()); 
        
        String outputMax = "SRAD" + "\t" + "MAX" + "\t" + "LEXUS" + "\t" + "700.0"; 
        Assert.assertEquals(outputMax, sample.getSolarRadiationMax().toString());   
        
        String outputMin = "SRAD" + "\t" + "MIN" + "\t" + "LEXUS" + "\t" + "500.0"; 
        Assert.assertEquals(outputMin, sample.getSolarRadiationMin().toString());    
        
        String outputTOT = "SRAD" + "\t" + "TOT" + "\t" + "LEXUS" + "\t" + "1800.0";
        Assert.assertEquals(outputTOT, sample.getSolarRadiationTotal().toString()); 
         
        Assert.assertEquals("LEXUS", sample.getStationID());
        
    }
    
    /**
     * Testing the getMins, getMax, and getAverage for tair measurements 
     */
    @Test 
    public void getterTestForTair()
    {
        // TimeData(String stationID, int year, int month, int day, int minute, 
        // Measurement tair, Measurement ta9m, Measurement solarRadiation)
         
        TimeData sampleTimeData2 = new TimeData("LEXUS", 1995, 10, 10, 30, 
                new Measurement(500 ), new Measurement(300), new Measurement(600));
        TimeData sampleTimeData = new TimeData("LEXUS", 1998, 10, 10, 30, 
                new Measurement(100), new Measurement(200), new Measurement(500));
        
        TimeData sampleTimeData3 = new TimeData("LEXUS", 2000, 10, 10, 30, 
                new Measurement(100), new Measurement(200), new Measurement(700)); 
        
        
        ArrayList<TimeData> sampleArrayList = new ArrayList<TimeData>(); 
        sampleArrayList.add(sampleTimeData);  
        sampleArrayList.add(sampleTimeData2); 
        sampleArrayList.add(sampleTimeData3); 
        
        DayDataStatistics sample = new DayDataStatistics(sampleArrayList);   
        String outputMax = "TAIR" + "\t" + "MAX" + "\t" + "LEXUS" + "\t" + "500.0";  
        Assert.assertEquals(outputMax, sample.getTairMax().toString());  
        
        String outputMin = "TAIR" + "\t" + "MIN" + "\t" + "LEXUS" + "\t" + "100.0";  
        Assert.assertEquals(outputMin, sample.getTairMin().toString());   
        
        String outputAvg = "TAIR" + "\t" + "AVG" + "\t" + "LEXUS" + "\t" + "233.33333333333334";
        Assert.assertEquals(outputAvg, sample.getTairAverage().toString());   
        
         
     
    }
    
    
    /**
     * Testing the getMins, getMax, and getAverage for tair measurements 
     */
    @Test 
    public void getterTestForTair2()
    {
        // TimeData(String stationID, int year, int month, int day, int minute, 
        // Measurement tair, Measurement ta9m, Measurement solarRadiation)
         
        
        TimeData sampleTimeData = new TimeData("LEXUS", 1998, 10, 10, 30, 
                new Measurement(50), new Measurement(200), new Measurement(500));
        TimeData sampleTimeData2 = new TimeData("LEXUS", 1995, 10, 10, 30, 
                new Measurement(500 ), new Measurement(300), new Measurement(600));
        TimeData sampleTimeData4 = new TimeData("LEXUS", 1995, 10, 10, 30, 
                new Measurement(20 ), new Measurement(300), new Measurement(600));
        TimeData sampleTimeData3 = new TimeData("LEXUS", 2000, 10, 10, 30, 
                new Measurement(100), new Measurement(200), new Measurement(700)); 

         
         
        ArrayList<TimeData> sampleArrayList = new ArrayList<TimeData>(); 
        sampleArrayList.add(sampleTimeData);  
        sampleArrayList.add(sampleTimeData2); 
        sampleArrayList.add(sampleTimeData3);  
        sampleArrayList.add(sampleTimeData4); 
        
        DayDataStatistics sample = new DayDataStatistics(sampleArrayList);   
        String outputMin = "TAIR" + "\t" + "MIN" + "\t" + "LEXUS" + "\t" + "20.0";  
        Assert.assertEquals(outputMin, sample.getTairMin().toString());    
        
        
        
         
     
    }
    
    /**
     * Testing the getMins, getMax, and getAverage for ta9m measurements 
     */
    @Test 
    public void getterTestForTa9m()
    {
        // TimeData(String stationID, int year, int month, int day, int minute, 
        // Measurement tair, Measurement ta9m, Measurement solarRadiation)

        TimeData sampleTimeData = new TimeData("LEXUS", 1998, 10, 10, 30, 
                new Measurement(100), new Measurement(200), new Measurement(500));
         
        TimeData sampleTimeData2 = new TimeData("LEXUS", 1995, 10, 10, 30, 
                new Measurement(500 ), new Measurement(300), new Measurement(600));
        
        TimeData sampleTimeData3 = new TimeData("LEXUS", 2000, 10, 10, 30, 
                new Measurement(100), new Measurement(100), new Measurement(700)); 
        
        ArrayList<TimeData> sampleArrayList = new ArrayList<TimeData>(); 
        sampleArrayList.add(sampleTimeData); 
        sampleArrayList.add(sampleTimeData2); 
        sampleArrayList.add(sampleTimeData3); 
        

        DayDataStatistics sample = new DayDataStatistics(sampleArrayList);  
        String outputMax = "TA9M" + "\t" + "MAX" + "\t" + "LEXUS" + "\t" + "300.0";
        Assert.assertEquals(outputMax, sample.getTa9mMax().toString() );
        
        String outputMin = "TA9M" + "\t" + "MIN" + "\t" + "LEXUS" + "\t" + "100.0";
        Assert.assertEquals(outputMin, sample.getTa9mMin().toString() );  
        
        String outputAvg = "TA9M" + "\t" + "AVG" + "\t" + "LEXUS" + "\t" + "200.0";
        Assert.assertEquals(outputAvg, sample.getTa9mAverage().toString() );    
         
        
    }
    
    /**
     * Testing the toString method
     */
    @Test
    public void toStringTest()
    {
        
        TimeData sampleTimeData = new TimeData("LEXUS", 1998, 10, 10, 30, 
                new Measurement(100), new Measurement(200), new Measurement(500));
         
        TimeData sampleTimeData2 = new TimeData("LEXUS", 1995, 10, 10, 30, 
                new Measurement(500 ), new Measurement(300), new Measurement(600));
        
        TimeData sampleTimeData3 = new TimeData("LEXUS", 2000, 10, 10, 30, 
                new Measurement(100), new Measurement(100), new Measurement(700)); 
        
        ArrayList<TimeData> sampleArrayList = new ArrayList<TimeData>(); 
        sampleArrayList.add(sampleTimeData); 
        sampleArrayList.add(sampleTimeData2); 
        sampleArrayList.add(sampleTimeData3); 
        DayDataStatistics sample = new DayDataStatistics(sampleArrayList);  
        
        String tairString = "100.0" + "\t" + "500.0" + "\t" + "233.33333333333334" + "\n"; 
        String ta9mString = "100.0" + "\t" + "300.0" + "\t" + "200.0" + "\n"; 
        String solarString = "500.0" + "\t" + "700.0" + "\t" + "600.0" + "\t" + "1800.0" + "\n"; 
        String header = "Min" + "\t" + "Max" + "\t" + "Average" + "\t" + "Total" + "\n";  
        
        String output = header + tairString + ta9mString + solarString; 
        
        Assert.assertEquals(output, sample.toString());
    }
    
    
    
}
