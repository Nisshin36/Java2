import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.GregorianCalendar;


/**
 * @author rafal
 * @author Anh-Tu Ngoc 
 * @version 26 March 2018 
 */
public class DaysStatistics extends StatisticsAbstract
{
    /** Arraylist holding the directories */ 
    private ArrayList<String> files;
 
    
    
    /** Air Temperature minimum values */ 
    private ArrayList<StatMeasurement> tairMinStats;
    /** Air Temperature vaverage values */ 
    private ArrayList<StatMeasurement> tairAvgStats;
    /** Air Temperature maximum values */ 
    private ArrayList<StatMeasurement> tairMaxStats;

    /** Air Temperature min values at 9 meters */ 
    private ArrayList<StatMeasurement> ta9mMinStats;
    /** Air Temperature average values at 9 meters */
    private ArrayList<StatMeasurement> ta9mAvgStats;
    /** Air Temperature max values at 9 meters */
    private ArrayList<StatMeasurement> ta9mMaxStats;

    /** Solar Radiation min values */ 
    private ArrayList<StatMeasurement> sradMinStats;
    /** Solar Radiation average values */ 
    private ArrayList<StatMeasurement> sradAvgStats;
    /** Solar Radiation max values */ 
    private ArrayList<StatMeasurement> sradMaxStats;
    /** Solar Radiation total values */ 
    private ArrayList<StatMeasurement> sradTotalStats; 
    
    
    /**
     * Constructor 
     * @param files : array holding several directories as string 
     */
    public DaysStatistics(String[] files) 
    {
        this.files = new ArrayList<String>(Arrays.asList(files)); 
        

    }

    /** 
     * @throws IOException 
     * @throws WrongCopyrightException
     * @throws ParseException
     */
    public void findStatistics() throws IOException, WrongCopyrightException, ParseException 
    {
        // Initializing arraylists
        tairMinStats = new ArrayList<StatMeasurement>(); 
        tairMaxStats = new ArrayList<StatMeasurement>();   
        tairAvgStats = new ArrayList<StatMeasurement>();  
        ta9mMinStats = new ArrayList<StatMeasurement>(); 
        ta9mMaxStats = new ArrayList<StatMeasurement>();  
        ta9mAvgStats = new ArrayList<StatMeasurement>();  
        sradMinStats = new ArrayList<StatMeasurement>(); 
        sradMaxStats = new ArrayList<StatMeasurement>(); 
        sradAvgStats = new ArrayList<StatMeasurement>(); 
        sradTotalStats = new ArrayList<StatMeasurement>(); 
        
        for (String fileName : files)
        { 
            
            MesonetTimeFile mtsFile = new MesonetTimeFile(fileName);
            ArrayList<TimeData> data = mtsFile.parseFile();
            DayDataStatistics dataStats = new DayDataStatistics(data);

            assignStats(dataStats);      
            
        }

    }

    /**
     * Sorting and organizing the measurements from DayDataStatistics into lists 
     * @param dataStats : A DayDataStatistics object that is used to access measurements for that day. 
     * @throws ParseException
     */
    private void assignStats(DayDataStatistics dataStats) throws ParseException
    {
        
        // Extracting and assigning variables 
        tairMinStats.add(dataStats.getTairMin());
        tairMaxStats.add(dataStats.getTairMax()); 
        tairAvgStats.add(dataStats.getTairAverage());  
        ta9mMinStats.add(dataStats.getTa9mMin()); 
        ta9mMaxStats.add(dataStats.getTa9mMax()); 
        ta9mAvgStats.add(dataStats.getTa9mAverage());
        sradMinStats.add(dataStats.getSolarRadiationMin());
        sradMaxStats.add(dataStats.getSolarRadiationMax());
        sradAvgStats.add(dataStats.getSolarRadiationAverage());
        sradTotalStats.add(dataStats.getSolarRadiationTotal());
        
        // COMPLETED 
    }

    /**
     * Returns a Statmeasurement object that has the minimum value? 
     * @param inParamId : either tair, ta9m, or srad 
     * @return findMin(tairMinStats) 
     */
    @Override
    public StatMeasurement getMinimumDay(String inParamId) throws WrongParameterIdException
    { 
        
        if ( inParamId.equalsIgnoreCase("tair"))  
        {
            return findMin(tairMinStats); 
        }
        else if  ( inParamId.equalsIgnoreCase("ta9m") ) 
        {
            return findMin(ta9mMinStats);
        }
        else if ( inParamId.equalsIgnoreCase("SRAD") )
        {
            return findMin(sradMinStats); 
        }
        else 
        {
            throw new WrongParameterIdException(); 
        }
    }

    /**
     * finding the day that contains the maximum value of tair, srad or ta9m 
     * @param inParamId : tair ta9m or srad 
     * @return findMax(tairMaxStats) 
     */
    @Override
    public StatMeasurement getMaximumDay(String inParamId) throws WrongParameterIdException
    {
      
        if ( inParamId.equalsIgnoreCase("tair"))
        {
            return findMax(tairMaxStats);  
        }
        else if  ( inParamId.equalsIgnoreCase("ta9m") ) 
        {
            return findMax(ta9mMaxStats); 
        }
        else if ( inParamId.equalsIgnoreCase("SRAD") )
        {
            return findMax(sradMaxStats); 
        }
        else 
        {
            throw new WrongParameterIdException("Wrong Param"); 
        }
        
        
    }
    

    /**
     * @param paramId : a string representing a parameter tair, srad, or ta9m 
     * @return maximumDay.toString() + "\n" + miniumuDay.toString() + "\n" : A String 
     * @throws WrongParameterIdException
     */
    public String combineMinMaxStatistics(String paramId) throws WrongParameterIdException
    {        
        StatMeasurement maximumDay = getMaximumDay(paramId);
        StatMeasurement miniumuDay = getMinimumDay(paramId);
        return maximumDay.toString() + "\n" + miniumuDay.toString() + "\n";
    } 
    

    
    /**
     * @return a string displaying information about the day's data 
     */
    public String toString() 
    {   
  
        
        String header = "ID\t STAT\t VALUE\t STID\t DATE\t T\t TIME\t TZ\n" + 
                        "------------------------------------------------\n"; 
        String tairMin = "TAIR\t MIN\t " + findMin(tairMinStats).getValue() + "\t " 
                        + findMin(tairMinStats).getStationId() + "\t " 
                        + findMin(tairMinStats).getDateTimeOfMeasurment() + "\t UTC\n"; 
        
        String tairMax = "TAIR\t MAX\t " + findMax(tairMaxStats).getValue() + "\t " 
                        + findMax(tairMaxStats).getStationId() + "\t "  
                + findMax(tairMaxStats).getDateTimeOfMeasurment() + "\t UTC\n"; 
        
        String ta9mMin = "TA9M\t MIN\t " + findMin(ta9mMinStats).getValue() + "\t " 
                + findMin(ta9mMinStats).getStationId() + "\t " 
                + findMin(ta9mMinStats).getDateTimeOfMeasurment() + "\t UTC\n"; 
        
        String ta9mMax = "TA9M\t MAX\t " + findMax(ta9mMaxStats).getValue() + "\t " 
                + findMax(ta9mMaxStats).getStationId() + "\t " 
                + findMax(ta9mMaxStats).getDateTimeOfMeasurment() + "\t UTC\n"; 
        
        String solarMin = "SRAD\t MIN\t " + findMin(sradMinStats).getValue() 
                + "\t " + findMin(sradMinStats).getStationId() + "\t " 
                + findMin(sradMinStats).getDateTimeOfMeasurment() + "\t UTC\n"; 
        
        String solarMax = "SRAD\t MAX\t " + findMax(sradMaxStats).getValue() 
                + "\t " + findMax(sradMaxStats).getStationId() + "\t " 
                + findMax(sradMaxStats).getDateTimeOfMeasurment() + "\t UTC\n";
       
        String output = header + tairMin + tairMax + ta9mMin + ta9mMax + solarMin + solarMax; 
        
        
        return output;  
                
    } 
    
    /**
     * Private helper method 
     * @param list : An arraylist of statmeasurement values, could be tair, ta9m, or srad
     * @return node : a maximum statmeasurement value within the array.
     */
    private static StatMeasurement findMax(ArrayList<StatMeasurement> list)
    {
        double max = list.get(0).getValue(); 
        StatMeasurement node = new StatMeasurement(); 
        node = list.get(0);
        
        for ( int index = 0; index < list.size(); ++index)
        {
            if ( list.get(index).getValue() > max) 
            {
                max = list.get(index).getValue(); 
                node = new StatMeasurement( list.get(index).getValue(), 
                        list.get(index).getDateTimeOfMeasurment(),
                        list.get(index).getStationId(),
                        list.get(index).getParamId(),
                        list.get(index).getStatType());
            }
        } 
        
        return node; 
    }
    
    /**
     * Private helper method 
     * @param list : An arraylist of statmeasurement values, could be tair, ta9m, or srad
     * @return node : a minimum statmeasurement value within the array.
     */
    private static StatMeasurement findMin(ArrayList<StatMeasurement> list)
    {
        double min = list.get(0).getValue();  
        StatMeasurement node = new StatMeasurement(); 
        node = list.get(0); 
        
        for ( int index = 0; index < list.size(); ++index)
        { 
            if ( list.get(index).getValue() < min) 
            { 
                min = list.get(index).getValue();  
                node = new StatMeasurement( list.get(index).getValue(), 
                        list.get(index).getDateTimeOfMeasurment(),
                        list.get(index).getStationId(), 
                        list.get(index).getParamId(),
                        list.get(index).getStatType()); 
            }
        }
        
        return node; 
    }

}